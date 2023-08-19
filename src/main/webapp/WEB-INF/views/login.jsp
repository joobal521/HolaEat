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
   <style>
    .form_wrap {
    text-align: center;
    margin-top: 50px;
    }

    #form-login {
    background-color: #fff;
    border: 1px solid #ccc;
    padding: 20px;
    margin: 0 auto;
    max-width: 400px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }

    .form-title {
    font-size: 16px;
    margin-bottom: 10px;
    }

    .input-box{
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-bottom: 15px;
    }

    .submit-btn {
    background-color: #007bff;
    color: #fff;
    border: none;
    padding: 8px 15px;
    border-radius: 5px;
    cursor: pointer;
    }

    .find-btn {
    margin-top: 10px;
    }

    .f-text {
    color: #007bff;
    text-decoration: none;
    margin: 0 10px;
    }

    .f-text:hover {
    text-decoration: underline;
    }

   </style>
</head>
<c:import url="header.jsp"/>
<body>
    <div class="form_wrap">
        <form id="form-login" action="login" method="POST">
            <span><h2 class="form-title">아이디</h2></span>
            <input type="text" class="input-box" id="id" name="userId" placeholder="아이디">
            <span><h2 class="form-title">비밀번호</h2></span>
            <input type="password" class="input-box" id="password" name="userPassword" placeholder="비밀번호">
            <input type="submit" class="submit-btn" id="submit" name="submit" value="로그인" onclick="validateForm()">
        </form>
        <div class="find">
            <p class="find-btn">
                <a href="find-user" class="f-text">아이디 찾기 ></a>
                <a href="find-pwd" class="f-text">비밀번호 찾기 ></a>
            </p>
        </div>
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
</body>
<c:import url="footer.jsp"/>
</html>
