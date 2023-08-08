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
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <h2> 식재료 </h2>
    <table border="1">
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach items="${ingredientsList}" var="ingredient">
            <tr>
                <td>${ingredient.ingrName}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
<c:import url="footer.jsp"/>
</html>
