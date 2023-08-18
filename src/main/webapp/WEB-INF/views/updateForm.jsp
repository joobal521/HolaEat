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
</head>
<body>
<section>
    <div class="form_wrap">
        <form enctype="multipart/form-data">
            <div class="form_container">
                <ul>
                    <li>
                        <h2>아이디</h2>
                        <input type="text" id="userId" name="userId" value="${sessionScope.log}" readonly>
                    </li>

                    <li>
                     <h2>비밀번호</h2>
                        <input type="password" id="userPassword" name="userPassword" placeholder="비밀번호">
                    </li>

                    <li>
                        <h2>새 비밀번호</h2>
                        <input type="password" id="newPassword" name="newPassword" placeholder="4-10자의 영문, 특수문자, 숫자 조합">
                    </li>

                    <li>
                        <p class="err">*특수문자는 '! @ # $ % ^ & +='만 사용 가능합니다.</p>
                        <p class="error" id="error-password">비밀번호는 필수정보입니다.</p>
                        <span class="err" id="chkNotice1" size="1"></span>
                    </li>

                    <li>
                        <h2>새 비밀번호 확인</h2>
                        <input type="password" id="newPasswordCh" name="newPasswordCh" placeholder="비밀번호 확인">
                        <span class="err" id="chkNotice2" size="1"></span>
                    </li>

                    <li>
                        <h2>이메일</h2>
                        <input type="email" id="userEmail" name="userEmail"  placeholder="example@holaEat.com">
                        <p class="error" id="error-email">이메일은 필수정보입니다.</p>
                        <span class="err" id="chkEmail" size="1"></span>
                    </li>

                    <li>
                        <h2>이름</h2>
                        <input type="text" id="userName" name="userName" placeholder="이름">
                        <p class="error" id="error-name">이름은 필수정보입니다.</p>
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
