<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>pills</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="${path}/resources/js/admin.js"></script>
    <style>
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div>
    <form id="form" method="post" action="gainpower">
        <input type="text" id="adminid" name="adminid">
        <input type="password" id="adminpwd" name="adminpwd">
        <input type="submit" id="submit" value="PROCEED">
    </form>
    <div id="error-message" class="error-message"></div>
</div>

</body>
</html>
