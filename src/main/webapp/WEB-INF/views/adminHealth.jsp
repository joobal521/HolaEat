<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-18
  Time: 오후 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin-health</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<body>
<h1 class="admin-title">건강 정보 글 관리</h1>
<button type="button" id="write-btn"> <a href="healthForm">글쓰기</a></button>
        <div class="admin-menu">

            <table>
                <thead>
                <tr>
                    <th>건강 정보 No</th>
                    <th>건강 정보 제목</th>
                    <th>내용</th>
                    <th>파일</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
                </thead>
                <tbody class="admin-healthList">
                <c:forEach items="${healthList}" var="health">
                    <tr>
                        <td>${health.healthNo}</td>
                        <td class="healthTitle">${health.title}</td>
                        <td class="healthContent">${health.content}</td>
                        <td><c:if test="${not empty imageBase64}">
                            <img src="data:image/jpeg;base64,${imageBase64}" id="img" name="img" alt="health Image">
                        </c:if></td>

                        <td>
                            <button type="button" class="updateBtn" data-id="${health.healthNo}"  onclick="redirectToHealthUpdate(${health.healthNo})" >수정</button>
                        </td>
                        <td>
                            <button type="button" class="removeBtn" data-id="${health.healthNo}" >삭제</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
<script src="script/adminHealth.js"></script>
</body>

</html>
