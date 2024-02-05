//내가 쓴글 삭제
$(document).ready(function() {

    $(".removeBtn").click(function () {
        var reviewNo = $(this).data("id");// FormData 객체 생성
        Swal.fire({
            title: '정말 삭제하시겠습니까?',
            text: '게시글 삭제 후 복구 불가합니다.',
            icon: 'warning',
            showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
            confirmButtonColor: '#265037', // confrim 버튼 색깔 지정
            confirmButtonText: '확인', // confirm 버튼 텍스트 지정
            cancelButtonText: '취소', // cancel 버튼 텍스트 지정
        }).then((result) => {
            if (result.isConfirmed) {
                var form = new FormData();

                var settings = {
                    "url": "/" + reviewNo + "/delete",
                    "method": "DELETE",
                    "timeout": 0,
                    "processData": false,
                    "mimeType": "multipart/form-data",
                    "contentType": false,
                    "data": form
                };

                $.ajax(settings).done(function (response) {
                    location.href = "myPage";
                });

            }
        });

    });
})
//수정
function redirectToHealthUpdate(reviewNo) {
    window.location.href = "../reviewUpdate?reviewNo=" + reviewNo;
}


//댓글삭제
$(document).ready(function() {

    $(".removeBtn2").click(function () {
        var commentId = $(this).data("id");// FormData 객체 생성
        Swal.fire({
            title: '정말 삭제하시겠습니까?',
            text: '댓글 삭제 후 복구 불가합니다.',
            icon: 'warning',
            showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
            confirmButtonColor: '#265037', // confrim 버튼 색깔 지정
            confirmButtonText: '확인', // confirm 버튼 텍스트 지정
            cancelButtonText: '취소', // cancel 버튼 텍스트 지정
        }).then((result) => {

            if (result.isConfirmed) {
                $.ajax({
                    type: 'DELETE',
                    url: "/comment/"+commentId+"/delete",
                    success: function (response) {
                        if (response.message !== "") {
                            console.log("삭제 후 이동");
                            location.href = "myPage";
                            console.log("마이페이지로");

                        }
                        // else {
                        //     alert(response.message);
                        // }
                    },
                    error: function (error) {
                        console.error(error);
                    }
                });
            }
        });

    });
})

//더보기 버튼
$(function() {
    $("tr").hide();
    $("tr").slice(0, 4).show(); // 초기갯수
    $("#moreView-btn").click(function(e) { // 더보기 버튼 클릭
        e.preventDefault();
        $("tr:hidden").slice(0, 4).show(); // 클릭시 리스트 갯수 지정
        if ($("tr:hidden").length == 0) { // 컨텐츠 남아있는지 확인
            $("#moreView-btn").hide(); //더이상의 리스트가 없다면 버튼 사라짐
        }
    });
});



// 브라우저 뒤로가기/앞으로 가기 시 이벤트 처리
// window.onpopstate = function(event) {
//     if (event.state) {
//         $(".section").html(event.state.content);
//         document.title = event.state.pageTitle;
//     }
// };