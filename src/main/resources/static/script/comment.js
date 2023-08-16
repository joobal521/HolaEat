
function addComment() {
    const reviewNo = $('#reviewNo').val();
    const msgBoxValue = $('#msg-box').val();
    const userId = $('#logVal').val();

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

        if (response.reviewCommentlist === true) {
            drawComments();
            $('#msg-box').val('');
        } else {
            if (userId === "") {
                alert('로그인 후 이용 가능합니다.');
            } else {
                alert('댓글을 입력해주세요.');
            }
        }
    });
}

// function drawComments() {
//     const reviewNo = $('#reviewNo').val();
//
//     $.ajax({
//         method: "GET",
//         url: `/comment?reviewNo=${reviewNo}`,
//     }).done(response => {
//         const list = response; // Assuming response is an array of comments
//         console.log(list);
//
//         $('#comment-container').empty();
//
//         list.forEach(comment => {
//             $('#comment-container').append(`
//                 <div class="comment-item">
//                     <p>${comment.userId}</p>
//                     <br>
//                     <p>${comment.content}</p>
//                     <p>${comment.createdAt}</p>
//                 </div>
//             `);
//         });
//     });
// }

$(document).ready(function () {
    $('#msg-box').click(function () {
        $('#msg-box').empty();
    });

    $('#commentBtn').click(function () {
        addComment();
    });

    drawComments();
});






// function  addComment(htmlForm){
//
//     const content = htmlForm.content.value;
//     console.log("content : " + content);
//
//     const userId = htmlForm.userId.val();
//     console.log("userId : " + userId);
//
//     let check = true;
//
//
//     if(check){
//
//         var form = new FormData();
//         form.append("content",content);
//
//
//
//
//
//
//
//
//
//
//     }
//
//
// }
//
//
//

// $(document).ready(function () {
//
//     $('#msg-box').click(function () {
//         $('#msg-box').empty();
//     });
//
//     function addComment() {
//         const reviewNo = $('#reviewNo').val();
//         const msgBoxValue = $('#msg-box').val();
//         const userId = $('#logVal').val();
//
//         $.ajax({
//             method: "POST",
//             url: `/comment?reviewNo=${reviewNo}&msg=${msgBoxValue}`,
//         }).done(response => {
//             console.log(response);
//
//             if (response.msg === 'success') {
//                 drawComments();
//                 $('#msg-box').val('');
//             } else {
//                 if (userId === "") {
//                     alert('로그인 후 이용 가능합니다.');
//                 } else {
//                     alert('댓글을 입력해주세요.');
//                 }
//             }
//         });
//     }
//
//     function drawComments() {
//         const reviewNo = $('#reviewNo').val();
//
//         $.ajax({
//             method: "GET",
//             url: `/comment?reviewNo=${reviewNo}`,
//         }).done(response => {
//             const list = response; // Assuming response is an array of comments
//             console.log(list);
//
//             $('#comment-container').empty();
//
//             list.forEach(comment => {
//                 $('#comment-container').append(`
//                     <div class="comment-item">
//                         <p>${comment.content}</p>
//                     </div>
//                 `);
//             });
//         });
//     }
//
//     // Assuming there's a button or trigger to add a comment
//     // $('#add-comment-btn').click(addComment);
//
//     // Initial comments rendering
//     drawComments();
//
// });
