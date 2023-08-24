$(document).ready(function() {
    // 현재 활성화된 메뉴 버튼의 jQuery 객체를 저장하는 변수
    let activeMenu = null;

    // 메뉴 버튼 클릭 이벤트 핸들러
    $("a.menu-link").click(function(event) {
        event.preventDefault(); // 기본 링크 동작 방지

        // 이미 활성화된 메뉴를 클릭한 경우, 아무런 동작하지 않음
        if (activeMenu === this) {
            return;
        }

        // 현재 활성화된 메뉴 버튼 비활성화
        if (activeMenu) {
            $(activeMenu).removeClass("active");
        }

        // 클릭한 메뉴 버튼 활성화
        $(this).addClass("active");
        activeMenu = this;

        var pageUrl = $(this).attr("href"); // 클릭한 링크의 URL
        var pageTitle = $(this).text(); // 클릭한 링크의 텍스트를 페이지 제목으로 사용
        // AJAX 요청
        $.ajax({
            url: pageUrl,
            success: function(response) {
                $(".section").html(response); // .section에 응답 페이지 삽입
                // 브라우저 주소 표시줄 업데이트
                // history.pushState(null, pageTitle, pageUrl);
            },
            error: function() {
                alert("페이지 로드에 실패했습니다.");
            }
        });
    });

    //var initialPageUrl = window.location.pathname;
    window.onpopstate = function(event) {
        if (event.state) {
            $(".section").html(event.state.content);
            document.title = event.state.pageTitle;
        } else {
            //loadContent(window.location.pathname);
        }
    };

    $("a.menu-link").click(function(event) {
        event.preventDefault();

        $("a.menu-link").removeClass("active");

        $(this).addClass("active");

        var pageUrl = $(this).attr("href");
        var pageTitle = $(this).text();

        $.ajax({
            url: pageUrl,
            success: function(response) {
                $(".section").html(response);
                history.pushState(null, pageTitle, pageUrl);
            },
            error: function() {
                alert("페이지 로드에 실패했습니다.");
            }
        });
    });
});

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
                    console.log(response);
                    location.href = "mypage";
                });

            }
        });

    });
})
//수정
function redirectToHealthUpdate(reviewNo) {
    window.location.href = "../reviewUpdate?reviewNo=" + reviewNo;
}

// 브라우저 뒤로가기/앞으로 가기 시 이벤트 처리
// window.onpopstate = function(event) {
//     if (event.state) {
//         $(".section").html(event.state.content);
//         document.title = event.state.pageTitle;
//     }
// };





