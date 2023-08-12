// document.addEventListener("DOMContentLoaded", function () {
//
//     const errorMsg = document.getElementById('error-message');
//     const form = document.getElementById('form');
//
//     form.addEventListener('submit', function (event) {
//         event.preventDefault(); // 기본 제출 동작 막기
//
//         const id = document.getElementById('adminid').value;
//         const pwd = document.getElementById('adminpwd').value;
//
//         if (id.trim() === '' || pwd.trim() === '') {
//             errorMsg.textContent = 'ID/PASSWORD CANNOT BE EMPTY';
//             errorMsg.style.display = 'block';
//         } else {
//             errorMsg.style.display = 'none';
//             console.log("gogo")
//             // 아이디와 비밀번호를 서버로 전송하고 확인하기
//             $.ajax({
//                 type: "POST",
//                 url: "gainpower", // 서버의 URL
//                 data: {
//                     adminid: id,
//                     adminpwd: pwd
//                 },
//
//                 success: function (response) {
//                     console.log("response:"+response)
//                     if (response !=null) {
//                         // 유효한 경우 폼 제출
//                         form.submit();
//                     } else {
//                         console.log("esle:"+response)
//                         // 유효하지 않은 경우 오류 메시지 표시 또는 다른 동작 수행
//                         errorMsg.textContent = 'Invalid ID/PASSWORD';
//                         errorMsg.style.display = 'block';
//                     }
//                 },
//                 error: function () {
//                     // 에러 처리
//                     errorMsg.textContent = 'Error occurred';
//                     errorMsg.style.display = 'block';
//
//                 }
//             });
//         }
//     });
//
// });


