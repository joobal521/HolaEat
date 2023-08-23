<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" type="text/css" href="/style/grid.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%--기준 URL로 설정--%>
<base href="http://localhost:8081/">
<body>
<header>
    <div class="header_wrap">
        <div class="top_wrap">
            <div class="logo">
                <a href="/">
                    <img src="img/logo.png">
                </a>
                <%--                로고 옆 로그인 상태 표시--%>
                <c:choose>
                    <c:when test="${not empty log }">
                        <span><a href="mypage">${userResponseDto.userName}</a>님 안녕하세요!</span>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </div>

            <%--            우측 상단 헤더--%>
            <div class="user_nav">
                <c:choose>
                    <c:when test="${not empty log }">

                        <form action="logout" method="POST">
                            <input type="submit" id="logout" name="logout" value="로그아웃">
                        </form>
                        <a id="my" href="mypage">내 정보</a>
                            </c:when>
                    <c:otherwise>
                        <a id="login" href="login">로그인</a>
                        <a id="join" href="join">회원가입</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="nav">
            <ul class="menu align-center expanded text-center SMN_effect-5">

                <li>
                    <a href="/">
                        <span>홈페이지</span>
                        <span>Home</span>
                    </a>
                </li>
                <li>
                    <a href="ingredients">
                        <span>이달의</br>식재료</span>
                        <span>Mothnly Ingredients</span>
                    </a>
                </li>


            <c:choose>
                <c:when test="${not empty log }">
                    <li>
                        <a href="menu">
                            <span>맞춤식단</span>
                            <span>Personal Menu</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="login">
                            <span>맞춤식단</span>
                            <span>Personal Menu</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
                <li>
                    <a href="reviewlist/1">
                        <span>이용후기</span>
                            <span>Review</span>
                    </a>
                </li>

                <li>
                    <a href="health-list/1">
                        <span>건강정보</span>
                        <span>Health Info</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</header>
</body>
