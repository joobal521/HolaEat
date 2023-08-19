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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<section>
<h2>아이디 찾기</h2>

    <form id=find>
        <div class="email_box">
            <li class="input-container">
                <h2 class="form-title">이메일</h2>
                <input type="email" class="input-box" id="userEmail" name="userEmail"  placeholder="example@holaEat.com">
                <input type="button" class="email-btn"
                       id="email_ch" onclick="emailAuthentication()" value="인증 메일 보내기">
                <p class="error" id="error-email">이메일은 필수정보입니다.</p>
                <span class="err" id="chkEmail" size="1"></span>
            </li>
            <div class="email-num-box">
                <li class="input-container">
                    <input class="input-box"  name="input-code" id="input-code" placeholder="인증번호 6자리를 입력해주세요!" maxlength="6">
                    <input type="button" class="code-btn" id="code-ch" onclick="authCodeCheck()"
                           value="인증">
                </li>
            </div>
            <div class="btn_box">
                <input type="button" id="find-btn" value="아이디 조회 하기"
                       onclick="checkValue(form)">
            </div>
        </div>
    </form>

</section>
<script src="script/findId.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
