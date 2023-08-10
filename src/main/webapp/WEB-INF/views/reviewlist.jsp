<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-08
  Time: 오후 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>reviewlist</title>
    <link rel="stylesheet" href="../style/form.css">
</head>
<c:import url="header.jsp"/>
<body>
<div class="wrapper">
<div>게시글 목록</div>
<c:forEach items="${reviewlist}" var="review">

    <a href="<c:url value='/review/${review.reviewNo}'/>">
    <div style="border: 1px solid black; margin: 1px;">
        <div>NO. ${review.reviewNo}</div>
        <div>작성자 : ${review.userId}</div>
        <div>제목 : ${review.title}</div>
        <img src="data:image/png, image/jpg, image/jpeg, image.gif;base64,${blob}" id="img" alt="Review Image">
    </div>
    </a>
</c:forEach>

<div>
    <a href="reviewform">
        글쓰기
    </a>
</div>

</div>
</body>
<script src="${path}/resources/js/review.js"></script>
<c:import url="footer.jsp"/>
</html>
