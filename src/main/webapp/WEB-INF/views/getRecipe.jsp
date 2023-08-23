<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="style/ingredients.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/Chart.min.js"></script>
    <style>
        .food-wrap {
            display: flex;
        }
        .food-img-container {
            flex: 1; /* Take up 50% width */
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
        }
        .food-img-container img {
            max-width: 100%;
            max-height: 100%;
            object-fit: cover;
        }
        .food-info-container {
            flex: 1; /* Take up 50% width */
            background: #b9b9b9;
        }
        .food-img-container img {
            object-fit: cover;
            scale:50%;
        }
        .food-info-container {
            width: 400px;
            height: 300px;
            background: #b9b9b9;
        }
    </style>
</head>
<body>
<div class="food-wrap">
    <div class="food-img-container">
        <img src="data:image/jpeg;base64,${foodImg}" id="img" name="img" alt="Food Image">
    </div>
    <div class="food-info-container">
        <div class="food-info">
            <div class="food-details">
                <h1>${food.foodName}</h1>
                <span>${food.foodGroup}</span>
                <span>${food.foodNational}</span>
            </div>
            <div class="food-nutrition">
                <span>총 열량 : ${food.kcal}</span>
                <span>탄수화물 : ${food.carb}</span>
                <span>지방 : ${food.fat}</span>
                <span>단백질 : ${food.protein}</span>
                <span>당 : ${food.sugars}</span>
                <span>나트륨 : ${food.natrium}</span>
            </div>
        </div>
        <div class="recipe-list">
            <h2>레시피</h2>
            <ul>
                <c:forEach items="${recipe}" var="foodrecipe">
                    <li id="step1">${foodrecipe.step_01}</li>
                    <li id="step2">${foodrecipe.step_02}</li>
                    <li id="step3">${foodrecipe.step_03}</li>
                    <li id="step4">${foodrecipe.step_04}</li>
                    <li id="step5">${foodrecipe.step_05}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>