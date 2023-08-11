<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${path}/resources/js/recipe-ajax.js"></script>
    <link rel="stylesheet" type="text/css" href="${path}/resources/style/ingredients.css">
    <title>Title</title>
    <%--    --%>
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
                            <input type="button" class="foodbtn" value="${monthFood.foodName}"
                                   data-foodid="${monthFood.foodId}">
                            <div class="ingr-modal">
                                <div class="ingr-modal-content">
                                    <h2>${monthFood.foodName}</h2>
                                    <span>${monthFood.foodGroup}</span>
<%--                                    <div class="recipe-content" id="recipe-content">--%>
                                            <input type="text" name="recipe-content" class="recipe-content">
<%--                                    </div>--%>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </div>
    </c:forEach>
</section>
<script>


</script>
</body>
<c:import url="footer.jsp"/>
</html>
