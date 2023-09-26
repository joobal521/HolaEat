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
                <a href="adminHealth/1" class="menu-link">건강 정보</a>
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
<script src="script/adminIngr.js"></script>
<script src="script/adminReview.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>