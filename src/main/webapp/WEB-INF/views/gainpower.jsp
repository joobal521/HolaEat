<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>pills</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="script/admin.js"></script>
    <link rel="stylesheet" type="text/css" href="style/admin.css">
    <style>
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<section class="gainpower">

    <div class="gain-power-container">
        <h1>AUTHORITY ACCESS</h1>
        <form class="gainpower-form" id="form" method="post" action="gainpower">
            <div>
                <input type="text" id="adminid" name="adminid">
                <input type="password" id="adminpwd" name="adminpwd">
            </div>
            <div>
                <input type="submit" id="submit" value="PROCEED">
            </div>
        </form>
        <div id="error-message" class="error-message"></div>
    </div>
</section>
</body>
</html>
