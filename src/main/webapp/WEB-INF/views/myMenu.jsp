<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<c:import url="header.jsp"/>

<body>

<c:forEach items="${userMenus}" var="userMenu">
    <p>${userMenu.menuNo} - ${userMenu.userId}</p>
</c:forEach>

<script>
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

        // 추가 코드: myMenu 페이지 로딩 시 user_menu 정보 가져오기
        $("a.menu-link[href='/myMenu']").click(); // myMenu 페이지 로딩
    });

</script>

</body>
<c:import url="footer.jsp"/>

</html>
