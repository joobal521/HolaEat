function addComment() {
    const reviewNo = $('#reviewNo').val();
    const msgBoxValue = $('#msg-box').val();
    const userId = $('#logVal').val();
    console.log("userId logval변환확인" + userId)

    if (msgBoxValue.trim() === "") {
        // 댓글이 비어 있는 경우 경고를 표시하고 요청을 보내지 않음
        Swal.fire({
            title: '댓글을 입력해주세요.',
            icon: 'success',
            confirmButtonColor: '#265037',
        });
        return; // 댓글이 비어 있으므로 함수 종료
    }

    const requestData = {
        reviewNo: reviewNo, content: msgBoxValue, userId: userId
    };

    $.ajax({
        method: "POST",
        url: "/comment/" + reviewNo,
        data: JSON.stringify(requestData),
        contentType: "application/json",
        dataType: "json",
    }).done(response => {
        console.log(response);

        if (userId === "") {
            // alert('로그인 후 이용 가능합니다.');
            Swal.fire({
                title: '로그인 후 이용 가능합니다.',
                text: '로그인 페이지로 이동하시겠습니까?',
                icon: 'warning',
                showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                confirmButtonColor: '#265037', // confrim 버튼 색깔 지정
                confirmButtonText: '확인', // confirm 버튼 텍스트 지정
                cancelButtonText: '취소', // cancel 버튼 텍스트 지정
            }).then((result) => {

                if (result.isConfirmed) {
                    location.href = "login";
                }
            });

        } else {
            console.log("userId 출력확인1 : " + userId);
            loadComments(reviewNo);
            $('#msg-box').val('');
        }

    });
}

// // 페이지 로드 시 댓글 데이터를 가져와 화면에 표시
function loadComments(reviewNo) {
    $.get(`/comment/${reviewNo}`, function (comments) {
        const commentContainer = $('#comment-container');
        //새로운 댓글을 표시하기 전에 이전에 표시된 모든 댓글을 삭제
        commentContainer.empty();

        comments.forEach(function (comment) {
            drawComments(comment.userId, comment.content, comment.commentId, comment.createdAt);
        });
    });
}

// 댓글 출력
function drawComments(userId, content, commentId, createdAt) {
    const log = $('#logVal').val();
    const isUserAuthor = userId === log;
    createdAt = createdAt.replace("T", " ");

    const displayState = `
        <form class="comment-item" id="comment-item-${commentId}">
            <input type="text" value="ID : ${userId}" readonly />
            <br>
            <textarea data-id="${content}" readonly>${content}</textarea>
            <input class="comment-time" type="text" value="${createdAt}" readonly />
            <br>
            <div class="comment-btn">
                ${isUserAuthor ? `<button type="button" class="commentEditBtn" onclick="showEditPage(${commentId}, '${content}')">수정</button>` : ''}
                ${isUserAuthor ? `<button type="button"  style="display: none;" class="commentSaveBtn" onclick="saveComment(${commentId})">저장</button>` : ''}
                ${isUserAuthor ? `<button type="button"  style="display: none;" class="commentCancelBtn" onclick="cancelEdit(${commentId})" type="button">취소</button>` : ''}
                ${isUserAuthor ? `<button type="button" class="commentDeleteBtn" onclick="deleteComment(${commentId})">삭제</button>` : ''}
            </div>
        </form>
    `;

    const commentContainer = $('#comment-container');
    commentContainer.prepend(displayState);
}

// 수정 버튼을 눌렀을 때 호출되는 함수
function showEditPage(commentId) {
    const commentItem = $(`#comment-item-${commentId}`);
    const textarea = commentItem.find('textarea');
    const editButton = commentItem.find('.commentEditBtn');

    // 이전 댓글 내용을 저장
    textarea.data('original-content', textarea.val());

    // textarea의 readonly 속성 제거하여 수정 가능하도록 설정
    textarea.prop('readonly', false);

    // 버튼을 수정 버튼에서 저장 버튼과 취소 버튼으로 변경
    commentItem.find(".commentCancelBtn, .commentSaveBtn").show();
    commentItem.find(".commentEditBtn").hide();

}

// 저장 버튼을 눌렀을 때 호출되는 함수(댓글 수정)
function saveComment(commentId) {
    const commentItem = $(`#comment-item-${commentId}`);
    const textarea = commentItem.find('textarea');
    const newContent = textarea.val(); // 수정된 내용 가져오기

    // 서버로 수정 내용을 보내는 AJAX 요청 수행
    const formData = new FormData();
    formData.append('content', newContent);

    $.ajax({
        type: 'PUT',
        url: `/comment/${commentId}/update`,
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
            if (response.message === 'success') {
                const reviewNo = $('#reviewNo').val();
                const newContent = reviewCommentRequestDto.getContent(); // newContent 값을 가져옴
                updateContent(newContent); // updateContent 함수에 전달
                loadComments(reviewNo);
            }
            // else {
            //     alert(response.message);
            // }
        },
        error: function (error) {
            console.error(error);
        }
    });


    // 수정 상태를 다시 읽기 전용으로 변경
    textarea.prop('readonly', true);

    // 수정 버튼으로 변경
    commentItem.find(".commentCancelBtn, .commentSaveBtn").hide();
    commentItem.find(".commentEditBtn").show();
}

// 취소 버튼을 눌렀을 때 호출되는 함수
function cancelEdit(commentId) {
    const commentItem = $(`#comment-item-${commentId}`);
    const textarea = commentItem.find('textarea');

    // 이전 댓글 내용을 가져와서 textarea에 설정
    textarea.val(textarea.data('original-content'));

    // 취소 후 다시 읽기 전용으로 변경
    textarea.prop('readonly', true);

    commentItem.find(".commentCancelBtn, .commentSaveBtn").hide();
    commentItem.find(".commentEditBtn").show();
}


$(document).ready(function () {
    const reviewNo = $('#reviewNo').val();
    loadComments(reviewNo);
});


// 댓글 삭제
function deleteComment(commentId) {
    Swal.fire({
        title: '정말 삭제하시겠습니까?',
        text: '댓글 삭제 후 복구 불가합니다.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#265037',
        confirmButtonText: '확인',
        cancelButtonText: '취소',
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: 'DELETE',
                url: `/comment/${commentId}/delete`,
                success: function (response) {
                    if (response.message === 'success') {
                        const reviewNo = $('#reviewNo').val();
                        console.log("삭제 확인");
                        loadComments(reviewNo); // 삭제 후에 loadComments 함수를 호출
                    } else {
                        alert('삭제 실패'); // 실패한 경우에 대한 처리
                    }
                },
                error: function (error) {
                    console.error(error);
                    alert('삭제 요청 중 오류가 발생했습니다.'); // 오류 처리
                }
            });
        }
    });
}


/*댓글 등록 취소 */
function delComment() {
    $('#msg-box').val('');
}

