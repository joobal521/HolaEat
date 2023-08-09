<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/style/form.css">
</head>
<c:import url="header.jsp"/>
<body>
<section id = "review-section">
    <h2>리뷰 게시판 상세</h2>

        <h2>게시글</h2>
        <div id="review-contents">
            <input type="text" id="title" name="title"  >
            <input type="text" id="userId" value="${sessionScope.userId}" readonly>
            <textarea id="content" name="content" readonly ></textarea>
            <div id="image-container">
                <img id="img" name="img" >
            </div>
            <input type="datetime" id="created_at" readonly>
            <input type="datetime" id="modified_at" readonly>
        </div>

</section>
</body>
<c:import url="footer.jsp"/>
</html>
