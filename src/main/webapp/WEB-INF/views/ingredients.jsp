<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${path}/resources/style/ingredients.css">
    <title>Title</title>
</head>
<c:import url="header.jsp"/>
<body>
<section class="ingr-section">
    <h2> 이달의 식재료 </h2>
    <c:forEach items="${ingredientsList}" var="ingredient">
        <div class="ingr-img">
            <a class="ingrOfMonth" href=""></a>
            <h3>${ingredient.ingrName}</h3>
        </div>
        <div class="ingr-foods">

            <c:forEach items="${monthFoodIngrList}" var="monthFoodIngr">
                <c:if test="${monthFoodIngr.ingrId==ingredient.ingrId}">
                    <c:forEach items="${monthFoods}" var="monthFood">
                        <c:if test="${monthFoodIngr.foodId==monthFood.foodId}">
                            <a class="ingr-food" href="">${monthFood.foodName}</a>
                        </c:if>
                    </c:forEach>
                </c:if>


            </c:forEach>


        </div>
    </c:forEach>


</section>
</body>
<c:import url="footer.jsp"/>
</html>
