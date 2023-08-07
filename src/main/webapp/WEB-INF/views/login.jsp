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
    <link rel="stylesheet" href="../style/form.css">
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <div class="form_wrap">
        <form action="" method="POST">
            <span><h2>아이디</h2></span>
            <input type="text" id="id" name="id" placeholder="아이디">
            <span><h2>비밀번호</h2></span>
            <input type="password" id="password" name="password" placeholder="비밀번호">
            <input type="submit" id="submit" name="submit" value="로그인">
        </form>
    </div>
</section>
</body>
<c:import url="footer.jsp"/>
</html>
