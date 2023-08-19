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
    <title>join</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="style/joinForm.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .form_wrap {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form_container {
            padding: 20px;
        }

        .form-title {
            font-size: 20px;
            margin-bottom: 10px;
        }

        .input-container {
            margin-bottom: 20px;
        }

        .input-box {
            width: 70%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        .btn-dupl,
        .email-btn,
        .code-btn,
        .join-btn {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .email-btn{
            margin-top: 10px;
        }
.btn_join{
    display: flex;
    justify-content: center;
}

        .join-btn{
            margin-top: 30px;
        }



        .agr_text {
            font-size: 14px;
            vertical-align: middle;
        }

        .agr {
            margin-bottom: 10px;
        }

        .required {
            color: red;
        }

        .error {
            color: red;
            font-size: 14px;
            display: none;
        }


        .label-wrapper {
            margin-top: 20px;
            padding: 10px;
        }

    </style>
</head>
<c:import url="header.jsp"/>
<body>
    <div class="form_wrap">
        <form enctype="multipart/form-data">
            <div class="form_container">
                <ul class="form-box">
                    <li class="input-container">
                        <h2 class="form-title">아이디</h2>
                        <input type="text" class="input-box" id="userId" name="userId" placeholder="아이디">
                        <input
                                type="button" class="btn-dupl" id="idDupl" name="idDupl"
                                value="중복 확인" onclick="chkId()">
                        <p class="error" id="error-id">아이디는 필수 정보입니다.</p>
                    </li>


                    <li class="input-container">
                        <h2 class="form-title">비밀번호</h2>
                        <input type="password" class="input-box" id="userPassword" name="userPassword" placeholder="4-10자의 영문, 특수문자, 숫자 조합">
                    </li>

                    <li class="input-container">
                    <p class="err">*특수문자는 '! @ # $ % ^ & +='만 사용 가능합니다.</p>
                        <p class="error" id="error-password">비밀번호는 필수 정보입니다.</p>
                    <span class="err" id="chkNotice1" size="1"></span>
                   </li>

                <li class="input-container">
                    <h2 class="form-title">비밀번호 확인</h2>
                    <input type="password" class="input-box" id="userPasswordCh" name="userPasswordCh" placeholder="비밀번호 확인">
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

                    <div class="label-wrapper">
                        <li class="agr">
                        <label for="agree_all" class="title">이용 약관 동의<span
                                class="required">(필수)</span></label>
                        </li>
                        <div class="agr">
                            <input type="checkbox" id="agree_all" name="agree_all"><span
                                class="agr_text">전체 동의</span>
                        </div>

                        <div class="agr">
                            <input type="checkbox" id="user_check1"
                                                      name="user_check"><span class="agr_text"><a
                                    href="term" class="term_title">이용 약관</a> 동의<strong>(필수)</strong> </span>
                        </div>

                        <div class="agr">
                            <input type="checkbox" id="user_check2"
                                                      name="user_check"><span class="agr_text"><a
                                    href="term2" class="term_title">개인 정보 처리 방침</a> 동의<strong>(필수)</strong>
					</span>
                        </div>

                        <li class="agr">
                          <input type="checkbox" id="user_check3"
                                                      name="user_check"><span class="agr_text">이메일 수신<strong>(선택)</strong></span>

                        </li>
                    </div>

                    <li class="btn_join">
                        <button type="button" class="join-btn" id="submit" name="submit" onclick="checkValue(form)">회원가입</button>
                    </li>
                </ul>
            </div>
        </form>
    </div>
<script src="script/join.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
