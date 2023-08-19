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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<style>
    #find-section {
        text-align: center;
        margin-top: 50px;
    }

    .text_box {
        margin-bottom: 20px;
    }

    .find_text {
        font-size: 24px;
    }


    .pwd_box {
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

    .sava-btn {
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
<section id="find-section">
    <div class="text_box">
        <h2 class="find_text">새로운 비밀번호를 입력해주세요</h2>
    </div>
    <form id=find>
        <div class="pwd_box">
            <ul class="form-box">
            <li class="input-container">
            <h2 class="form-title">새 비밀번호</h2>
            <input type="password" class="input-box" id="new_password"
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
                    id="user_password_ch" placeholder="비밀번호 확인">
                    <span class="err" id="chkNotice2" size="1"></span>
                </li>

            <li class="btn_box">
                <input type="button" class="sava-btn" id="find-btn" value="저장"
                       onclick="checkValue()">
            </li>
            </ul>
        </div>
    </form>
</section>
</body>
<c:import url="footer.jsp"/>
</html>
