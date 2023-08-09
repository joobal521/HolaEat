<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${path}/resources/style/ingredients.css">
    <title>Title</title>
    <style>
        .ingr-modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
        }

        .ingr-modal-content {
            background-color: #fff;
            width: 60%;
            margin: 100px auto;
            padding: 20px;
        }
    </style>
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
                            <input type="button" class="foodbtn" value="${monthFood.foodName}">
                            <div class="ingr-modal">
                                <div class="ingr-modal-content">
                                    <h2>${monthFood.foodName}</h2>
                                    <span>${monthFood.foodGroup}</span>
                                        <!--레시피?? -->
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

    document.addEventListener("DOMContentLoaded", function() {
        const openModalBtns = document.querySelectorAll('.foodbtn');
        const modals = document.querySelectorAll('.ingr-modal');

        openModalBtns.forEach((openModalBtn, index) => {
            openModalBtn.addEventListener('click', () => {
                modals[index].style.display = 'block';
            });
        });

        // 클릭 이벤트 추가: 모달 바깥 부분을 클릭하면 모달이 닫힘
        modals.forEach((modal, index) => {
            window.addEventListener('click', (event) => {
                if (event.target === modal) {
                    modal.style.display = 'none';
                }
            });
        });
    });

</script>
</body>
<c:import url="footer.jsp"/>
</html>
