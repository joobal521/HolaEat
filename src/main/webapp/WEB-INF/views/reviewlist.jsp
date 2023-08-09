<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-08
  Time: 오후 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>reviewlist</title>
</head>
<body>
<c:forEach items="${boardlist}" var="board">

    <div>
        <div>작성자 : ${board.userId}</div>
        <div>제목 : ${board.title}</div>
        <div>${board.img}</div>
    </div>

</c:forEach>

<div>
    <a href="review_form">
        글쓰기
    </a>
</div>


</body>
</html>
