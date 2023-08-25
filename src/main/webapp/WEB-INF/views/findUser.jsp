<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-19
  Time: 오후 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>findId</title>
    <link rel="stylesheet" type="text/css" href="/style/findUser.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <style>

    </style>

</head>
<c:import url="header.jsp"/>
<body>

<div class="find-user">
<h2 class="find-title">아이디 찾기</h2>
<div class="form_wrap">

    <form id=find>
        <div class="id_box">
            <div class="input-name">
                <h2 class="form-title">이름</h2>
                <input type="text" class="input-box" id="userName" name="userName" placeholder="이름">
                <p class="error" id="error-name">이름은 필수 정보입니다.</p>
            </div>
            <div class="input-email">
                <h2 class="form-title">이메일</h2>
                <input type="email" class="input-box" id="userEmail" name="userEmail"  placeholder="example@holaEat.com">
                <input type="button" class="email-btn"
                       id="email_ch" onclick="emailAuthentication()" value="인증 메일 보내기">
                <p class="error" id="error-email">이메일은 필수정보입니다.</p>
                <span class="err" id="chkEmail" size="1"></span>

            </div>

            <div class="input-code">
                    <input class="input-box"  name="input-code" id="input-code" placeholder="인증번호 6자리를 입력해주세요!" maxlength="6">
            <input type="button" class="code-btn" id="code-ch" onclick="authCodeCheck()"
                           value="인증">
            </div>
            <div class="btn_box">
                <input type="button" id="find-btn" class="show-btn" value="아이디 조회 하기">
            </div>
            <div class="show_box">
                <p class="show-id" id="result"></p>
            </div>
            <p class="go-login">
                <a href="login" class="g-text">로그인 하기 ></a>

            </p>

        </div>
    </form>

</div>
</div>
<script src="script/find.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
