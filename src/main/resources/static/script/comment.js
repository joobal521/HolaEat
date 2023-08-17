
function addComment() {
    const reviewNo = $('#reviewNo').val();
    const msgBoxValue = $('#msg-box').val();
    const userId = $('#logVal').val();
    console.log("userId logval변환확인"+ userId)

    const requestData = {
        reviewNo: reviewNo,
        content: msgBoxValue,
        userId: userId
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
            loadComments(reviewNo); // Update comments without refreshing the page
            $('#msg-box').val('');
        }

    });
}

// 페이지 로드 시 댓글 데이터를 가져와 화면에 표시하는 함수
function loadComments(reviewNo) {
    $.get(`/comment/${reviewNo}`, function (comments) {
        const commentContainer = $('#comment-container');
        commentContainer.empty();

        comments.forEach(function (comment) {
            drawComments(comment.userId, comment.content); // 댓글 데이터로 화면에 표시
        });
    });
}

function drawComments(userId, content) {
    const commentContainer = $('#comment-container');
    console.log("userId 출력확인2 : " + userId);
    const newComment = `
        <div class="comment-item">

            <p>ID 확인용 : ${userId}</p>
            <br>
            <p>${content}</p>
            <br>
        </div>
    `;
    commentContainer.prepend(newComment);
}

$(document).ready(function () {
    const reviewNo = $('#reviewNo').val();
    loadComments(reviewNo);
});