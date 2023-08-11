<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ADMIN</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<c:choose>
    <c:when test="${empty authority}">
        <c:redirect url="/" /> <!-- authority가 비어있을 경우 인덱스로 리다이렉션 -->
    </c:when>
</c:choose>

<section class="aside">
<ul>
    <li>
        <a href="">메뉴 관리</a>
        <a href="">식자재 관리</a>
        <a href="">후기 게시판 관리</a>
        <a href="">건강 정보</a>
        <a href="">유저 관리</a>
    </li>
</ul>
</section>
<section class="section">

</section>
<script src="${path}/resources/js/admin.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
