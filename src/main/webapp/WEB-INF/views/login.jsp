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
    <link rel="stylesheet" type="text/css" href="style/form.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <div class="form_wrap">
        <form action="login" method="POST">
            <span><h2>아이디</h2></span>
            <input type="text" id="id" name="userId" placeholder="아이디">
            <span><h2>비밀번호</h2></span>
            <input type="password" id="password" name="userPassword" placeholder="비밀번호">
            <input type="submit" id="submit" name="submit" value="로그인" onclick="validateForm()">
        </form>
    </div>
    <script>
        function validateForm() {
            var userId = document.getElementById("id").value;
            var password = document.getElementById("password").value;

            if (userId.trim() === "") {
                alert("아이디를 입력하세요.");
                return false; // 폼 제출 중단
            }

            if (password.trim() === "") {
                alert("비밀번호를 입력하세요.");
                return false; // 폼 제출 중단
            }

            // 유효성 검증에 통과했을 경우
            return true; // 폼 제출 진행
        }

        var loginFailed = <%= request.getAttribute("loginFailed") %>;
        if (loginFailed) {
            swal("로그인 실패!", "아이디 또는 비밀번호가 올바르지 않습니다!", "error");
            //alert("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    </script>
</section>
</body>
<c:import url="footer.jsp"/>
</html>
