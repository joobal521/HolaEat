<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-09
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>leave</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="style/myPage.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        #leave-section {
            background-color: #f5f5f5;
            padding: 20px;
        }

        .form_wrap {
            max-width: 600px;
            margin: 0 auto;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .leave_text {
            font-size: 24px;
            text-align: center;
            margin-bottom: 20px;
        }

        .leave-form {
            padding: 20px;
        }

        .input-leave {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            margin-bottom: 10px;
        }

        .error {
            color: red;
            font-size: 14px;
            display: none;
        }

        .leave-btn {
            padding: 10px 20px;
            background-color: #e74c3c;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        /* Add your own custom styles here */
    </style>
</head>
<body>
<section id="leave-section">
    <div class="form_wrap">
        <h2 class="leave_text"> "${sessionScope.userName}"님 정말 떠나시나요?</h2>
        <form class="leave-form" id="delete">
            <div class="form_container">
                <ul>
            <li class="input-userId">
                <input type="text" class="input-leave" id="userId"
                       name="userId" value="${sessionScope.log}" readonly>
            </li>
            <li class="input-password">
                <input type="password" class="input-leave" id="userPassword"
                       name="userPassword" placeholder="비밀번호">
            </li>
                <li class="error" id="error-password">비밀번호를 입력해주세요.
                </li>


                    <button type="button" class="leave-btn" id="delete-btn"
                            onclick="checkValue(form)">회원탈퇴</button>

                </ul>

            </div>
        </form>
    </div>
</section>
<script src="script/leave.js"></script>
</body>
</html>
