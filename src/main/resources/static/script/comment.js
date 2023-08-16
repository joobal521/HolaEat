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
//         const userId = $('#logVal').val(); // Assuming logVal contains the user ID
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
//                     <div class="comments-item">
//                         <p>${comment.content}</p>
//                     </div>
//                 `);
//             });
//         });
//     }
//
//     // Assuming there's a button or trigger to add a comment
//     $('#add-comment-btn').click(addComment);
//
//     // Initial comments rendering
//     drawComments();
//
// });
