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
                history.pushState(null, pageTitle, pageUrl);
            },
            error: function() {
                alert("페이지 로드에 실패했습니다.");
            }
        });
    });

    var initialPageUrl = window.location.pathname;
    loadContent(initialPageUrl);

    window.onpopstate = function(event) {
        if (event.state) {
            $(".section").html(event.state.content);
            document.title = event.state.pageTitle;
        } else {
            loadContent(window.location.pathname);
        }
    };
});

// 브라우저 뒤로가기/앞으로 가기 시 이벤트 처리
// window.onpopstate = function(event) {
//     if (event.state) {
//         $(".section").html(event.state.content);
//         document.title = event.state.pageTitle;
//     }
// };

$(document).ready(function() {
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



