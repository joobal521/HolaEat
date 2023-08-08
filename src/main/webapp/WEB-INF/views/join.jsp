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
    <title>Title</title>
    <link rel="stylesheet" href="style/form.css">
</head>
<c:import url="header.jsp"/>
<body>
<body>
<section>
    <div class="form_wrap">
        <form action="">
            <div class="form_container">
                <ul>
                    <li>
                        <h2>아이디</h2>
                        <input type="text" id="id" name="id" placeholder="아이디">
                    </li>
                    <li>
                        <h2>비밀번호</h2>
                        <input type="password" id="password" name="password" placeholder="비밀번호">
                    </li>
                    <li>
                        <h2>이메일</h2>
                        <input type="email" id="email" name="email" placeholder="이메일">
                    </li>
                    <li>
                        <h2>이름</h2>
                        <input type="text" id="name" name="name" placeholder="이름">
                    </li>
                    <li>
                        <input type="submit">
                    </li>
                </ul>
            </div>
        </form>
    </div>
</section>
</body>
<c:import url="footer.jsp"/>
</html>
