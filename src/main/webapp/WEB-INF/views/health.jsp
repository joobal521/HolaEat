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
    <link rel="stylesheet" type="text/css" href="/style/health.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<div class="health-section">
    <h2>건강 정보 상세</h2>
    <c:if test="${health!=null}">
        <div id="health-contents">
            <form id="health-detail" enctype="multipart/form-data">
                <div class="health_detail_no">
                    <span>No.</span>
                    <input type="text" id="healthNo" name="healthNo" value="${health.healthNo}" readonly>
                </div>
                <div class="health_detail_title">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="title" value="${health.title}" readonly>
                </div>
                <div class="health_detail_userId">
                    <label for="admin">작성자</label>
                    <input type="text" id="admin" name="admin" value="관리자" readonly>
                </div>

                <div class="health_detail_content">
                    <label for="content">내용</label>
                    <textarea id="content" name="content" readonly>${health.content}</textarea>
                        <%--                        <input type="text" id="content" name="content" value="${review.content}">--%>
                </div>
                <div id="image-container">
        <c:if test="${blob !=null}">
                    <img src="data:image/png;base64,${blob}" id="img" name="img"  alt="Health Image" style=" max-width: 50%;  height: auto;">
        </c:if>
                </div>
<%--                <c:if test="${health.userId == log}">--%>
<%--                    <button type="button" id="update" name="update" onclick="redirectToReviewUpdate(reviewNo)">수정--%>
<%--                    </button>--%>
<%--                    <button type="button" id="delete" name="delete"--%>
<%--                            onclick="CheckValueDelete(document.getElementById('review_detail'), ${review.reviewNo})">삭제--%>
<%--                    </button>--%>
<%--                </c:if>--%>
                <button type="button" id="backToList" name="backToList" onclick="goBackToList()">목록</button>
            </form>
        </div>
    </c:if>

</div>
</body>
<script src="/script/adminHealth.js"></script>
<c:import url="footer.jsp"/>
</html>
