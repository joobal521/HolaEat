<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-18
  Time: 오후 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>new password</title>
    <link rel="stylesheet" type="text/css" href="/style/newPwd.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>
<c:import url="header.jsp"/>
<body>
<div id="find-section">
    <div class="text_box">
        <h2 class="find_text">새로운 비밀번호를 입력해주세요.</h2>
    </div>
    <form id=find method="put" action="">
        <div class="pwd_box">
            <ul class="form-box">
            <li class="input-container">
            <h2 class="form-title">새 비밀번호</h2>
            <input type="password" class="input-box" id="newPassword"
                    name="new_password" placeholder="4-10자의 영문, 특수문자, 숫자 조합">
            </li>
            <li class="input-container">
                <p class="err">*특수문자는 '! @ # $ % ^ & +='만 사용 가능합니다.</p>
                <p class="error" id="error-password">새 비밀번호를 입력해주세요.</p>
                <span class="err" id="chkNotice1" size="1"></span>
            </li>

                <li class="input-container">
            <h2 class="form-title">새 비밀번호 확인</h2>
            <input type="password" class="input-box" name="user_password_ch"
                    id="newPasswordCh" placeholder="비밀번호 확인">
                    <span class="err" id="chkNotice2" size="1"></span>
                </li>

            <li class="btn_box">
                <input type="button" class="save-btn" id="find-btn" value="저장" onclick="checkValue(form)">
            </li>
            </ul>
        </div>
    </form>
</div>
<script src="script/newPwd.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
