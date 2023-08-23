<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setDateHeader("Expires", 0); %>

<html>
<head>
    <title>ADMIN</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script>
        $(document).ready(function() {//admin페이지 접속시 menu관리 자동로드
            function loadContent() {

                $("li:nth-child(1) a.menu-link").addClass("active");

                $.ajax({
                    url: "adminMenu",
                    success: function(response) {
                        $(".section").html(response);
                    },
                    error: function(xhr, status, error) {
                        var errorMessage = "Failed to load the page: " + error;
                        $(".section").html(errorMessage);
                    }
                });
            }

            $("a.menu-link").click(function(event) {
                event.preventDefault(); // 기본 링크 동작 방지

                var pageUrl = $(this).attr("href"); // 클릭한 링크의 URL
                var pageTitle = $(this).text(); // 클릭한 링크의 텍스트를 페이지 제목으로 사용
                // AJAX 요청
                $.ajax({
                    url: pageUrl,
                    success: function(response) {
                        $(".section").html(response); // .section에 응답 페이지 삽입
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
    </script>


    <link rel="stylesheet" type="text/css" href="style/admin.css">
</head>
<c:import url="header.jsp"/>
<body>
<c:choose>
    <c:when test="${empty authority}">
        <c:redirect url="/" /> <!-- authority가 비어있을 경우 인덱스로 리다이렉션 -->
    </c:when>
</c:choose>
<section class="wrap">

    <section class="aside">
        <ul>
            <li>
                <a href="adminMenu" class="menu-link">메뉴 관리</a>
            </li>
            <li>
                <a href="adminIngr" class="menu-link">식자재 관리</a>
            </li>
            <li>
                <a href="adminReview" class="menu-link">후기 게시판 관리</a>
            </li>
            <li>
                <a href="adminHealth" class="menu-link">건강 정보</a>
            </li>
            <%--            <li>--%>
            <%--                <a href="adminUser" class="menu-link">유저 관리</a>--%>
            <%--            </li>--%>
        </ul>
    </section>
    <section class="section">

    </section>
</section>
<script src="script/admin.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>