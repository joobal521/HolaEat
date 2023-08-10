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
        <c:if test="${review!=null}">

            <div id="review-contents">
                <div>
                    <input type="text" id="title" name="title" value="${review.get(0).title}">
                </div>
                <div>
                    <input type="text" id="userId" value="${review.get(0).userId}" readonly>
                </div>
                <div>
                    <textarea id="content" name="content" value="${review.get(0).content}" readonly ></textarea>
                </div>
                <div id="image-container">
                    <img id="img" name="img" >
                </div>
                <input type="datetime" id="created_at" readonly>
                <input type="datetime" id="modified_at" readonly>
            </div>
        </c:if>


</section>
</body>
<c:import url="footer.jsp"/>
</html>
