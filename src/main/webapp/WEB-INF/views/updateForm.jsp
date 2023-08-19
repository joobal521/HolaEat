<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-09
  Time: 오후 3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>update</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="style/joinForm.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<style>
    .form_wrap{
        padding:5% 10%;
    }

    /* 폼 스타일링 */
    .form_container {
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 20px;
    text-align: center;
    }

    /* 입력 상자 스타일링 */
    .input-container {
    margin-bottom: 20px;
    }

    .input-box {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    }

    /* 버튼 스타일링 */
    #submit {
    background-color: #3498db;
    color: #fff;
    border: none;
    padding: 8px 16px;
    cursor: pointer;
    border-radius: 5px;
    }

    #submit:hover {
    background-color: #2980b9;
    }

    /* 에러 메시지 스타일링 */
    .error {
    color: #e74c3c;
    font-size: 14px;
    display: none;
    margin-top: 5px;
    }

    /* 인증 코드 입력란 및 버튼 스타일링 */
    .input-code {
    width: 70%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    }

    .code-btn {
    background-color: #3498db;
    color: #fff;
    border: none;
    padding: 6px 12px;
    cursor: pointer;
    border-radius: 5px;
    }

    .code-btn:hover {
    background-color: #2980b9;
    }

    /* 중복 확인 버튼 스타일링 */
    .btn-dupl {
    background-color: #3498db;
    color: #fff;
    border: none;
    padding: 6px 12px;
    cursor: pointer;
    border-radius: 5px;
    margin-left: 5px;
    }

    .btn-dupl:hover {
    background-color: #2980b9;
    }

    /* 이메일 버튼 스타일링 */
    .email-btn {
    background-color: #3498db;
    color: #fff;
    border: none;
    padding: 6px 12px;
    cursor: pointer;
    border-radius: 5px;
    margin-left: 5px;
    }

    .email-btn:hover {
    background-color: #2980b9;
    }

    /* 오류 메시지 스타일링 */
    .err {
    color: #e74c3c;
    font-size: 14px;
    }

</style>

</head>
<body>
<section>
    <div class="form_wrap">
        <form enctype="multipart/form-data">
            <div class="form_container">
                <ul>
                    <li class="input-container">
                        <h2 class="form-title">아이디</h2>
                        <input type="text" class="input-box" id="userId" name="userId" value="${sessionScope.log}" readonly>
                    </li>

                    <li class="input-container">
                     <h2 class="form-title">비밀번호</h2>
                        <input type="password" class="input-box" id="userPassword" name="userPassword" placeholder="비밀번호">
                    </li>

                    <li class="input-container">
                        <h2 class="form-title">새 비밀번호</h2>
                        <input type="password" class="input-box" id="newPassword" name="newPassword" placeholder="4-10자의 영문, 특수문자, 숫자 조합">
                    </li>

                    <li class="input-container">
                        <p class="err">*특수문자는 '! @ # $ % ^ & +='만 사용 가능합니다.</p>
                        <p class="error" id="error-password">비밀번호는 필수 정보입니다.</p>
                        <span class="err" id="chkNotice1" size="1"></span>
                    </li>

                    <li class="input-container">
                        <h2 class="form-title">새 비밀번호 확인</h2>
                        <input type="password" class="input-box" id="newPasswordCh" name="newPasswordCh" placeholder="비밀번호 확인">
                        <span class="err" id="chkNotice2" size="1"></span>
                    </li>

                    <li class="input-container">
                        <h2 class="form-title">이메일</h2>
                        <input type="email" class="input-box" id="userEmail" name="userEmail"  placeholder="example@holaEat.com">
                        <input
                                type="button" class="btn-dupl" id="emailDupl" name="emailDupl"
                                value="중복 확인" onclick="chkEmail()">
                        <input type="button" class="email-btn"
                               id="email_ch" onclick="emailAuthentication()" value="인증 메일 보내기">
                        <p class="error" id="error-email">이메일은 필수 정보입니다.</p>
                        <span class="err" id="chkEmail" size="1"></span>
                    </li>

                    <li class="input-container">
                        <input class="input-box"  name="input-code" id="input-code" placeholder="인증번호 6자리를 입력해주세요!" maxlength="6">
                        <input type="button" class="code-btn" id="code-ch" onclick="authCodeCheck()"
                               value="인증">
                    </li>

                    <li class="input-container">
                        <h2 class="form-title">이름</h2>
                        <input type="text" class="input-box" id="userName" name="userName" placeholder="이름">
                        <p class="error" id="error-name">이름은 필수 정보입니다.</p>
                    </li>

                    <li>
                        <button type="button" id="submit" name="submit" onclick="checkValue(form)">회원 정보 수정</button>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</section>
<script src="script/update.js"></script>


</body>
</html>
