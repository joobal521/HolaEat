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

// 페이지 로드 시 댓글 데이터를 가져와 화면에 표시
function loadComments(reviewNo) {
    $.get(`/comment/${reviewNo}`, function (comments) {
        const commentContainer = $('#comment-container');
        commentContainer.empty();

        comments.forEach(function (comment) {
            drawComments(comment.userId, comment.content, comment.commentId);
        });
    });
}

//댓글 출력

function drawComments(userId, content, commentId) {
    const commentContainer = $('#comment-container');
    console.log("userId 출력확인2 : " + userId);

    const log = $('#logVal').val();

    console.log("commentId : " + commentId);
    const newComment = `
        <form class="comment-item" id="comment-item-${commentId}">
            <input type="text" id="comment-id" value="ID 확인용 : ${userId}" readonly/>
            <br>
            <textarea id="comment-content-${commentId}" readonly>${content}</textarea>
            <br>
            <div class="comment-btn">
                <c:choose>
                    <c:when test="${userId == log}">
                        <button id="commentUpdateBtn-${commentId}" onclick="updateComments(${commentId})" class="commentUpdateBtn">수정</button>
                        <button id="commentDeleteBtn-${commentId}" onclick="deleteComments(${commentId})" class="commentDeleteBtn">삭제</button>
                    </c:when>
                    <c:otherwise>
                        <div></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    `;
    commentContainer.prepend(newComment);
}

// 수정
function updateComments(commentId) {
    const commentItem = $(`#comment-item-${commentId}`);
    const contentTextarea = commentItem.find('textarea');
    const newContent = contentTextarea.val();


    if (contentTextarea.prop('readonly')) {
        contentTextarea.prop('readonly', false);
        const updateButton = commentItem.find('.commentUpdateBtn');
        updateButton.text('저장');
    } else {
        const formData = new FormData();
        formData.append('content', newContent);


        $.ajax({
            type: "PUT",
            url: `/comment/${commentId}/update`,
            data: formData, // Use FormData
            processData: false,
            contentType: false,
            success: function (response) {
                if (response.message === "success") {
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

        contentTextarea.prop('readonly', true);
        const updateButton = commentItem.find('.commentUpdateBtn');
        updateButton.text('수정');
    }
}

$(document).ready(function () {
    const reviewNo = $('#reviewNo').val();
    loadComments(reviewNo);
});


//댓글 삭제
function deleteComments(commentId) {
    $.ajax({
        type: "DELETE", url: `/comment/${commentId}/delete`, dataType: "json", success: function (response) {
            if (response.message === "success") {
                const reviewNo = $('#reviewNo').val();
                loadComments(reviewNo);
            } else {
                alert(response.message);
            }
        }, error: function (error) {
            // Handle errors
            console.error(error);
        }
    });


}


/*댓글 등록 취소 */
function delComment() {
    $('#msg-box').val('');
}

