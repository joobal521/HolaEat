<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="script/recipe-ajax.js"></script>
    <link rel="stylesheet" type="text/css" href="style/ingredients.css">
    <title>Title</title>
    <%--    --%>
</head>
<c:import url="header.jsp"/>
<body>
<h2> 이달의 식재료 </h2>
<section class="ingr-section">
    <c:forEach items="${ingrOfMonth}" var="ingrMonth">
        <div class="ingr-container">
            <div class="ingr-name">
                <h1>${ingrMonth.ingrName}</h1>
            </div>
            <div class="ingr-img">
                <c:set var="ingrImg" value="${blob[ingrMonth.ingrId]}"></c:set>
                <c:if test="${not empty ingrImg}">
                    <img src="data:image/jpeg;base64,${ingrImg}" id="img" name="img" alt="Ingredient Image">
                </c:if>
            </div>

            <div class="ingrFood">
                <c:forEach items="${foodIngrList}" var="foodList" varStatus="roll">
                    <c:forEach items="${monthFoodList}" var="monthList">
                        <c:if test="${foodList.foodId==monthList.foodId&&foodList.ingrId==ingrMonth.ingrId}">
                            <input type="button" class="foodbtn" value="${monthList.foodName}"
                                   data-foodid="${monthList.foodId}">
                            <input type="hidden" id="ingrId-modal" value="${ingrMonth.ingrId}">
                            <div class="ingr-modal">
                                <div class="ingr-modal-content">
                                    <div class="recipe-content" id="recipe-content${roll.index}">
                                        <!--ajax-->
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</section>
<%--<section>--%>
<%--    <c:forEach items="${ingrOfMonth}" var="ingrMonth">--%>
<%--        <div class="ingr-container">--%>
<%--            <div>--%>
<%--                <h1>${ingrMonth.ingrName}</h1>--%>
<%--            </div>--%>
<%--            <c:set var="ingrImg" value="${blob[ingrMonth.ingrId]}"></c:set>--%>
<%--            <c:if test="${not empty ingrImg}">--%>
<%--                <img src="data:image/jpeg;base64,${ingrImg}" id="img" name="img" alt="Ingredient Image">--%>
<%--            </c:if>--%>
<%--            <div>--%>
<%--                <c:set var="uniqueFoodNames" value="" scope="page"></c:set>--%>
<%--                <c:forEach items="${foodIngrList}" var="foodList">--%>
<%--                    <c:if test="${foodList.ingrId == ingrMonth.ingrId && not uniqueFoodNames.contains(foodList.foodName)}">--%>
<%--                        <input type="button" class="foodbtn" value="${foodList.foodName}" data-foodid="${foodList.foodId}">--%>
<%--                        <input type="hidden" id="ingrId-modal" value="${ingrMonth.ingrId}">--%>
<%--                        <div class="ingr-modal">--%>
<%--                            <div class="ingr-modal-content">--%>
<%--                                <div class="recipe-content">--%>
<%--                                    <!--ajax-->--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <c:set var="uniqueFoodNames" value="${uniqueFoodNames},${foodList.foodName}" scope="page"></c:set>--%>
<%--                    </c:if>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--</section>--%>
</body>
<c:import url="footer.jsp"/>
</html>