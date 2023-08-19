function addComment() {
    const reviewNo = $('#reviewNo').val();
    const msgBoxValue = $('#msg-box').val();
    const userId = $('#logVal').val();
    console.log("userId logval변환확인" + userId)

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
            alert('로그인 후 이용 가능합니다.');
        } else if (msgBoxValue.trim() === "") {
            alert('댓글을 입력해주세요.');
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
        commentContainer.empty();

        comments.forEach(function (comment) {
            drawComments(comment.userId, comment.content, comment.commentId);
        });
    });
}

// 댓글 출력
function drawComments(userId, content, commentId) {
    const log = $('#logVal').val();
    const isUserAuthor = userId === log;

    const displayState = `
        <form class="comment-item" id="comment-item-${commentId}">
            <input type="text" value="ID 확인용: ${userId}" readonly />
            <br>
            <textarea readonly>${content}</textarea>
            <br>
            <div class="comment-btn">
                ${isUserAuthor ? `<button class="commentEditBtn" onclick="showEditPage(${commentId}, '${content}')">수정</button>` : ''}
                ${isUserAuthor ? `<button class="commentDeleteBtn" onclick="deleteComment(${commentId})">삭제</button>` : ''}
            </div>
        </form>
    `;

    const commentContainer = $('#comment-container');
    commentContainer.prepend(displayState);
}

function showEditPage(commentId, content) {
    const commentItem = $(`#comment-item-${commentId}`);
    const contentTextarea = commentItem.find('textarea');
    const editButton = commentItem.find('.commentEditBtn');
    const deleteButton = commentItem.find('.commentDeleteBtn');


    const editPage = `
        <div class="edit-page" id="edit-page-${commentId}">
            <textarea>${content}</textarea>
            <br>
            <button class="saveBtn" onclick="updateComments(${commentId})">저장</button>
            <button class="cancelBtn" onclick="cancelEditPage(${commentId}, '${content}')">취소</button>
            <button class="deleteBtn" onclick="deleteComment(${commentId})">삭제</button>
        </div>
    `;

    contentTextarea.hide();
    editButton.hide();
    deleteButton.hide();
    commentItem.append(editPage);
}

// 수정 페이지 취소
function cancelEditPage(commentId, content) {
    const commentItem = $(`#comment-item-${commentId}`);
    const contentTextarea = commentItem.find('textarea');
    const editButton = commentItem.find('.commentEditBtn');
    const deleteButton = commentItem.find('.commentDeleteBtn');
    const editPage = $(`#edit-page-${commentId}`);

    contentTextarea.show();
    editButton.show();
    deleteButton.show();
    editPage.remove();
}

// 댓글 수정
function updateComments(commentId) {
    const commentItem = $(`#comment-item-${commentId}`);
    const contentTextarea = commentItem.find('textarea');
    const newContent = contentTextarea.val();

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
                loadComments(reviewNo);
            } else {
                alert(response.message);
            }
        },
        error: function (error) {
            console.error(error);
        }
    });
}


$(document).ready(function () {
    const reviewNo = $('#reviewNo').val();
    loadComments(reviewNo);
});


// 댓글 삭제
function deleteComment(commentId) {
    if (confirm('정말로 삭제하시겠습니까?')) {
        $.ajax({
            type: 'DELETE',
            url: `/comment/${commentId}/delete`,
            success: function (response) {
                if (response.message === 'success') {
                    const reviewNo = $('#reviewNo').val();
                    loadComments(reviewNo);
                } else {
                    alert(response.message);
                }
            },
            error: function (error) {
                console.error(error);
            }
        });
    }
}



/*댓글 등록 취소 */
function delComment() {
    $('#msg-box').val('');
}







