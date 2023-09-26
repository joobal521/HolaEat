<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-24
  Time: 오전 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>myComment</title>
    <link rel="stylesheet" type="text/css" href="/style/my.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<body>
<c:if test="${empty log}">
    <c:url var="login" value="/login"></c:url>
    <c:redirect url="${login}"></c:redirect>
</c:if>
<div class="my-review">
    <table class="board-table">
        <thead>
        <tr>
            <th>No</th>
            <th>작성자</th>
            <th>내용</th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:if test="${empty myCommentList }">
            <div style="font-size: 17px; font-weight: bold;">작성글이 없습니다.</div>
        </c:if>
        <c:forEach items="${myCommentList}" var="myComment">
            <tr>

                <td>${myComment.commentId}</td>
                <td>${myComment.userId}</td>
                <td>${myComment.content}</td>
                <td>
                    <button class="removeBtn2" data-id="${myComment.commentId}">삭제</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="more_btn">
        <button class="more-btn" id="moreView-btn">더보기</button>
    </div>
</div>
<script src="script/myReview.js"></script>
</body>
</html>
