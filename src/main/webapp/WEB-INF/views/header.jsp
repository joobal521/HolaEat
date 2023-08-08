<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${path}/resources/style/grid.css">
<body>
<header>
    <div class="header_wrap">
        <div class="top_wrap">
            <div class="logo">
                <a href="/">
                    <img src="resources/img/logo.png">
                </a>
                <%--                로고 옆 로그인 상태 표시--%>
                <c:choose>
                    <c:when test="${not empty log }">
                        <span>${userName}님 안녕하세요!</span>
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
                            </c:when>
                    <c:otherwise>
                        <a href="login">로그인</a>
                    </c:otherwise>
                </c:choose>
                        <a href="join">회원가입</a>
            </div>
        </div>
        <div class="nav">
            <a href="ingredients">이달의 식재료</a>
            <a href="menu">맞춤식단</a>
            <a href="review">이용후기</a>
        </div>
    </div>
</header>
</body>
