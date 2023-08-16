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
    <c:forEach items="${blob}" var="imgblob">
        <img src="data:image/png;base64,${blob}" id="img" name="img" alt="Review Image">
    </c:forEach>
    <c:forEach items="${ingredientsList}" var="ingredient" varStatus="status">
        <div class="ingr-img">
            <a class="ingrOfMonth" href="">


            </a>
            <h3>${ingredient.ingrName}</h3>
        </div>
        <div class="ingr-foods">
            <c:forEach items="${monthFoodIngrList}" var="monthFoodIngr">
                <c:forEach items="${monthFoods}" var="monthFood"
                           varStatus="foodStatus"
                           begin="0" end="${monthFoodIngr.ingrId==ingredient.ingrId ? 1 : 0}">  <!-- 루프 중복 줄이기 -->
                    <c:if test="${monthFoodIngr.ingrId==ingredient.ingrId &&
                      monthFoodIngr.foodId==monthFood.foodId}">
                        <input type="button" class="foodbtn" value="${monthFood.foodName}"
                               data-foodid="${monthFood.foodId}">
                        <input type="hidden" id="ingrId-modal" value="${ingredient.ingrId}">
                        <div class="ingr-modal">
                            <div class="ingr-modal-content">
                                <h2>${monthFood.foodName}</h2>
                                <span>${monthFood.foodGroup}</span>
                                <div class="recipe-content" id="recipe-content${foodStatus.index}">
                                    <!--ajax-->
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </div>
    </c:forEach>
</section>
<script>

</script>
</body>
<c:import url="footer.jsp"/>
</html>