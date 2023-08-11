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
    <link rel="stylesheet" type="text/css" href="${path}/resources/style/joinForm.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <div class="form_wrap">
        <form enctype="multipart/form-data">
            <div class="form_container">
                <ul>
                    <li>
                        <h2>아이디</h2>
                        <input type="text" id="userId" name="userId" placeholder="아이디">
                        <input
                                type="button" id="idDupl" name="idDupl"
                                value="중복 확인" onclick="chkId()">
                    </li>

                    <p class="error" id="error-id">아이디는 필수정보입니다.</p>

                    <li>
                        <h2>비밀번호</h2>
                        <input type="password" id="userPassword" name="userPassword" placeholder="4-10자의 영문, 특수문자, 숫자 조합">
                    </li>

                    <li>
                    <p class="err">*특수문자는 '! @ # $ % ^ & +='만 사용 가능합니다.</p>
                        <p class="error" id="error-password">비밀번호는 필수정보입니다.</p>
                    <span class="err" id="chkNotice1" size="1"></span>
                   </li>

                <li>
                    <h2>비밀번호 확인</h2>
                    <input type="password" id="userPasswordCh" name="userPasswordCh" placeholder="비밀번호 확인">
                    <span class="err" id="chkNotice2" size="1"></span>
                </li>

                    <li>
                        <h2>이메일</h2>
                        <input type="email" id="userEmail" name="userEmail"  placeholder="example@holaEat.com">
                        <input type="button" class="square"
                                                      id="email_ch" onclick="emailAuthentication()" value="인증메일 보내기">
                        <p class="error" id="error-email">이메일은 필수정보입니다.</p>
                        <span class="err" id="chkEmail" size="1"></span>
                    </li>
                    <li>
                        <input type="text" name="code" class="square" id="code"
                               maxlength="10">
                    <input type="button" id="code_ch" onclick="authCodeCheck()"
                           value="인증">
                    </li>


                    <li>
                        <h2>이름</h2>
                        <input type="text" id="userName" name="userName" placeholder="이름">
                        <p class="error" id="error-name">이름은 필수정보입니다.</p>
                    </li>

                    <div class="label-wrapper">
                        <label for="agree_all" class="title">이용약관 동의<span
                                class="required">(필수)</span></label>
                        <div class="agr">
                            <input type="checkbox" id="agree_all" name="agree_all"><span
                                class="agr_text">전체동의</span>
                        </div>

                        <div class="agr">
                            <input type="checkbox" id="user_check1"
                                                      name="user_check"><span class="agr_text"><a
                                    href="term" class="term_title">이용약관</a> 동의<strong>(필수)</strong> </span>
                        </div>

                        <div class="agr">
                            <input type="checkbox" id="user_check2"
                                                      name="user_check"><span class="agr_text"><a
                                    href="term2" class="term_title">개인정보 처리방침</a> 동의<strong>(필수)</strong>
					</span>
                        </div>

                        <li class="agr">
                          <input type="checkbox" id="user_check3"
                                                      name="user_check"><span class="agr_text">이메일 수신<strong>(선택)</strong></span>

                        </li>
                    </div>

                    <li>
                        <input type="hidden" id="userProfileImg" name="userProfileImg" value="">
                    </li>
                    <li>
                        <button type="button" id="submit" name="submit" onclick="checkValue(form)">회원가입</button>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</section>
<script src="${path}/resources/js/join.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
