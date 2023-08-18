
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
            drawComments(comment.userId, comment.content, comment.commentId); // 댓글 데이터로 화면에 표시
        });
    });
}

function drawComments( userId, content, commentId) {
    const commentContainer = $('#comment-container');
    console.log("userId 출력확인2 : " + userId);



    console.log("commentId : " + commentId);
    const newComment = `
        <form class="comment-item">

            <input type="text" value="ID 확인용 : ${userId}"  readonly/>
            <br>
            <input type="text" value="${content}" readonly/>
            <br>

 <button id="commentUpdateBtn" onclick="updateComments(${commentId})" class="commentUpdateBtn">수정</button>
    <button id="commentDeleteBtn" onclick="deleteComments(${commentId})" class="commentDeleteBtn">삭제</button>
 
                   <br>
        </form>
    `;
    commentContainer.prepend(newComment);
}

// 수정 버튼 클릭 시 호출되는 함수
function updateComments(commentId) {
    const commentItem = $(`#comment-item-${commentId}`);
    const contentInput = commentItem.find('.content-input');

    contentInput.prop('readonly', false); // readonly 해제
    contentInput.focus(); // 입력 필드에 포커스
}

// 수정된 내용을 저장하고 화면을 업데이트하는 함수
function saveUpdatedComments(commentId) {
    const commentItem = $(`#comment-item-${commentId}`);
    const contentInput = commentItem.find('.content-input');
    const newContent = contentInput.val();

    //수정된 내용 업데이트

        $.ajax({
        type: "PUT",
        url: `/comment/${commentId}/update`,
        dataType: "json",
        success: function(response) {

            if (response.message === "success") {
                const reviewNo = $('#reviewNo').val(); // Get reviewNo value
                loadComments(reviewNo);
            } else {
                alert(response.message);
            }
        },
        error: function(error) {
            console.error(error);
        }
    });

    // 화면 업데이트
    contentInput.prop('readonly', true); // 다시 readonly로 설정
    contentInput.val(newContent); // 수정된 내용으로 값 설정
}



$(document).ready(function () {
    const reviewNo = $('#reviewNo').val();
    loadComments(reviewNo);
});



//댓글 삭제
function deleteComments(commentId) {
    $.ajax({
        type: "DELETE",
        url: `/comment/${commentId}/delete`,
        dataType: "json",
        success: function(response) {
            // Handle the response from the server
            if (response.message === "success") {
                const reviewNo = $('#reviewNo').val(); // Get reviewNo value
                loadComments(reviewNo);
            } else {
                alert(response.message);
            }
        },
        error: function(error) {
            // Handle errors
            console.error(error);
        }
    });


}


//댓글수정
// function updateComments(htmlForm,commentId){
//     const content = htmlForm.content.value;
//
//     if (content.trim() === "") {
//         alert("수정한 내용이 없습니다.");
//         return;
//     }
//
//     var form = new FormData();
//     form.append("content", content);
//
//
//     $.ajax({
//         type: "PUT",
//         url: `/comment/${commentId}/update`,
//         dataType: "json",
//         success: function(response) {
//
//             if (response.message === "success") {
//                 const reviewNo = $('#reviewNo').val(); // Get reviewNo value
//                 loadComments(reviewNo);
//             } else {
//                 alert(response.message);
//             }
//         },
//         error: function(error) {
//             console.error(error);
//         }
//     });
// }


/*댓글 등록 취소 */
function delComment() {
    $('#msg-box').val('');}

