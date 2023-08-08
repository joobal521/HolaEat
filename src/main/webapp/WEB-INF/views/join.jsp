<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/style/form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<body>
<section>
    <div class="form_wrap">
        <form enctype="multipart/form-data">
            <div class="form_container">
                <ul>
                    <li>
                        <h2>아이디</h2>
                        <input type="text" id="userId" name="userId" placeholder="아이디">
                    </li>
                    <li>
                        <h2>비밀번호</h2>
                        <input type="password" id="userPassword" name="userPassword" placeholder="비밀번호">
                    </li>
                    <li>
                        <h2>이메일</h2>
                        <input type="email" id="userEmail" name="userEmail" placeholder="이메일">
                    </li>
                    <li>
                        <h2>이름</h2>
                        <input type="text" id="userName" name="userName" placeholder="이름">
                    </li>
                    <li>
                        <input type="hidden" id="userProfileImg" name="userProfileImg" value="resources/img/belle.jpg">
                    </li>
                    <li>
                        <button type="button" id="submit" name="submit" onclick="checkValue(form)">회원가입</button>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</section>
<script src="${path}/resources/js/join.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
