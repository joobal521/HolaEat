<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-09
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>leave</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/style/form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<section id="delete-section">
    <div class="delete">
        <h2 class="delete_text"> "${sessionScope.userName}"님 정말떠나시나요?</h2>
        <form id="delete">
            <div>
                <input type="text" class="square" id="userId"
                       name="userId" value="${sessionScope.log}" readonly>
            </div>
            <div>
                <input type="password" class="square" id="userPassword"
                       name="userPassword" placeholder="비밀번호">
            </div>
            <ul>
                <li class="error" id="error-password">비밀번호를 입력해주세요.
                <li>
            </ul>
            <button type="button" id="delete-btn"
                    onclick="checkValue(form)">회원탈퇴</button>
        </form>

        <div class="back_box">
            <a  href="myPage"><span class="back">마이페이지로 이동</span></a>
        </div>
    </div>
</section>
<script src="${path}/resources/js/leave.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
