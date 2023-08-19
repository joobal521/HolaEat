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
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .section-container {
            text-align: center;
            margin-top: 50px;
            margin-bottom: 50px;
        }

        .find-title {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .id_box {
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 20px;
            margin: 0 auto;
            max-width: 400px;
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
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .email-btn, .code-btn, .show-btn {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn_box {
            text-align: center;
            margin-top: 20px;
        }


        /*아이디 조회*/
        .show-id{
            font-size: 12px;
        }


        /*경고 메시지*/
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
<div class="section-container">
    <h2 class="find-title">아이디 찾기</h2>
    <form id=find>

        <div class="id_box">
            <li class="input-container">
                <h2 class="form-title">이름</h2>
                <input type="text" class="input-box" id="userName" name="userName" placeholder="이름">
                <p class="error" id="error-name">이름은 필수 정보입니다.</p>
            </li>
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
                <input type="button" id="find-btn" class="show-btn" value="아이디 조회 하기">
            </div>
            <div class="show_box">
                <p class="show-id" id="result"></p>
            </div>
        </div>
    </form>

</div>
<script src="script/find.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
