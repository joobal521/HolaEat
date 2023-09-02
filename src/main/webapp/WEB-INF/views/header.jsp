<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/style/grid.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<link rel="icon" type="image/png" sizes="32x32" href="https://ibb.co/hDfBQQm">

<html>
    <head>
        <link rel="shortcut icon" href="https://ibb.co/TcccKLh">

        <meta property="og:title" content="holaEat">
        <meta property="og:description" content="사용자들에게 맛있고 건강한 맞춤식단를 제공하는 웹 서비스입니다.">
        <meta property="og:image" content="https://raw.githubusercontent.com/joobal521/HolaEat/main/src/main/resources/static/img/logo.png">

        <title>holaEat</title>
    </head>
    <body>
    <header>

        <div class="header_wrap">
            <div class="top_wrap">
                <div class="logo">
                    <a href="/">
<%--                        <img class="logo_img" src="img/logo.png">--%>
                        <img class="logo_img" src="https://i.ibb.co/b111HJd/logo.png" style="max-height: 100px">
                    </a>
                    <%--                로고 옆 로그인 상태 표시--%>
                    <c:choose>
                        <c:when test="${not empty log }">
                            <span><a href="myPage">${userResponseDto.userName}</a>님 안녕하세요!</span>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </div>

                <%--            우측 상단 헤더--%>
                <div class="user_nav">
                    <c:choose>
                        <c:when test="${not empty log }">

                            <form action="../logout" method="POST">
                                <input type="submit" id="logout" name="logout" value="로그아웃">
                            </form>
                            <a id="my" href="../myPage">내 정보</a>
                        </c:when>
                        <c:otherwise>
                            <a id="login" href="../login">로그인</a>
                            <a id="join" href="../join">회원가입</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="nav">
                <ul class="menu align-center expanded text-center SMN_effect-5" style="padding-left: 0 !important;">

                    <li>
                        <a href="../">
                            <span>홈페이지</span>
                            <span>Home</span>
                        </a>
                    </li>
                    <li>
                        <a href="../ingredients">
                            <span>이달의 식재료</span>
                            <span>Mothnly Ingredients</span>
                        </a>
                    </li>


                    <c:choose>
                        <c:when test="${not empty log }">
                            <li>
                                <a href="../menu">
                                    <span>맞춤식단</span>
                                    <span>Personal Menu</span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="../login">
                                    <span>맞춤식단</span>
                                    <span>Personal Menu</span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <li>
                        <a href="../reviewlist/1">
                            <span>이용후기</span>
                            <span>Review</span>
                        </a>
                    </li>

                    <li>
                        <a href="../health-list/1">
                            <span>건강정보</span>
                            <span>Health Info</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </header>
    </body>
</html>


