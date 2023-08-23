<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>개인정보처리방침</title>
  <link rel="stylesheet" type="text/css" href="style/term.css">
</head>
<c:import url="header.jsp"/>
<body>

<div th:if="${food}">
  <img th:src="@{'data:image/jpeg;base64,' + ${#strings.encodeBase64(food.foodImg)}}" alt="Food Image">
</div>

</body>
<c:import url="footer.jsp"/>
</html>
