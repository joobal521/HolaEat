<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-18
  Time: 오후 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>비밀번호 찾기</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<style>
    .form_wrap {

    margin-top: 50px;
    margin-bottom: 122px;
    }

    .text_box {
        text-align: center;
    margin-bottom: 20px;
    }

    .find_text {

    font-size: 24px;
    }

    .comment {
    font-size: 14px;
    color: #777;
    }

    .email_box {
    background-color: #fff;
    border: 1px solid #ccc;
    padding: 20px;
    margin: 0 auto;
    max-width: 500px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }

    .input-container {
    margin-bottom: 15px;
    }

    .form-title {
    font-size: 16px;
    margin-bottom: 5px;
    }

    .input-box {
    width: 60%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    }

    /*버튼*/
    .email-btn, .code-btn{
        padding: 10px 20px;
        border-radius: 4px;
        border: 1px solid  #265037;
        background-color: white;
        color: #265037;
        cursor: pointer;
        margin-left: 10px;
    }
    .email-btn:hover {
        color: #1e6b7b;
        background: aliceblue;
    }
    .code-btn:hover {
        color: #1e6b7b;
        background: aliceblue;
    }

    .show-btn{
        padding: 10px 40px;
        background-color: #265037;
        color:#DCE4CF;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 30px;
    }

    .show-btn:hover{
        background-color: aliceblue;
        color: #1e6b7b;
    }


    .btn_box {
    text-align: center;
    margin-top: 20px;
    }

    /* 경고 메시지 및 오류 스타일 */
    .error {
    color: #ff0000;
    font-size: 12px;
    display: none;
    }

    .err {
    font-size: 12px;
    margin-left: 5px;
    }
</style>
</head>
<c:import url="header.jsp"/>
<body>
<div class="form_wrap">
    <div class="text_box">
        <h2 class="find_text">비밀번호 찾기</h2>
        <p class="comment">비밀 번호 재설정을 위해 이메일을 입력해주세요.</p>
    </div>
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
                <input type="button" class="show-btn" id="next-btn" value="다음"
                       onclick="findPwd()">
            </div>
        </div>
    </form>
</div>
<script src="script/find2.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
