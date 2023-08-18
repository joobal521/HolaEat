<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-17
  Time: 오전 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>healthPage</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<h1>건강 정보 상세 페이지</h1>
<c:if test="${health!=null}">
    <div id="health-contents">
    <form id="health-form" enctype="multipart/form-data">
    <div class="health_info_no">
    <label for="healthNo">No.</label>
    <input type="text" id="healthNo" name="healthNo" value="${health.healthNo}" readonly>
    </div>
    <div class="health_info_title">
    <label for="title">제목</label>
    <input type="text" id="title" name="title" value="${health.title}" readonly>
    </div>
    <div class="health_info_admin">
    <p>작성지: 관리자</p>
    </div>

    <div class="health_info_content">
    <label for="content">내용</label>
    <input type="text" id="content" name="content" value="${health.content}" readonly >
    </div>
    <div id="image-container">
    <img src="data:image/png;base64,${blob}" id="img" name="img"  alt="Review Image">
    </div>

</body>
<c:import url="footer.jsp"/>
</html>
