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
    <link rel="stylesheet" type="text/css" href="style/login.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>
<c:import url="header.jsp"/>
<body>
<div class="login_box">
<h1 class="title">Login</h1>
    <div class="form_wrap">
        <form id="form-login" action="login" method="POST">
            <h2 class="form-title">아이디</h2>
            <input type="text" class="input-box" id="id" name="userId" placeholder="아이디">
            <h2 class="form-title">비밀번호</h2>
            <input type="password" class="input-box" id="password" name="userPassword" placeholder="비밀번호">
            <div class="btn_login">
            <input type="submit" class="submit-btn" id="submit" name="submit" value="로그인" onclick="validateForm()">
            </div>
        </form>
        <div class="find">
            <p class="find-btn">
                <a href="find-user" class="f-text">아이디 찾기 ></a>
                <a href="find-pwd" class="f-text">비밀번호 찾기 ></a>
            </p>
        </div>
    </div>
</div>
    <script>
        function validateForm() {
            var userId = document.getElementById("id").value;
            var password = document.getElementById("password").value;

            console.log(userId);
            console.log(password);

            // 유효성 검증에 통과했을 경우
            return true; // 폼 제출 진행
        }

        var loginFailed = <%= request.getAttribute("loginFailed") %>;
        if (loginFailed) {
            Swal.fire({
                title: '로그인 실패',
                text: '아이디 또는 비밀번호가 올바르지 않습니다.',
                icon: 'error',
            });
            //alert("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    </script>
</body>
<c:import url="footer.jsp"/>
</html>
