<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="style/ingredients.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/Chart.min.js"></script>


    <style>


    </style>
</head>
<body>
<div>
    <c:if test="${not empty foodImg }">
    <div id="image-container">
        <img src="data:image/png;base64,${blob}" id="ingrImg" name="ingrIm" alt="ingr Img">
    </div>
    </c:if>
    <div class="recipe-content">
        <div class="recipe-img-container">
            <img src="data:image/png;base64,${foodImg}" id="img" name="img" alt="Food Image">
        </div>
        <div class="food-wrap">
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
                <div class="nutrition-chart-container">
                    <canvas id="nutritionChart"></canvas>
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


    <script>
        // Get the canvas element
        var ctx = document.getElementById('nutritionChart').getContext('2d');

        // Data for the chart
        var data = {
            labels: ['Carbohydrates', 'Fats', 'Proteins'],
            datasets: [{
                data: [${food.carb}, ${food.fat}, ${food.protein}],
                backgroundColor: [
                    'rgb(255,62,102)',  // Carbohydrates color
                    'rgb(0,152,255)', // Fats color
                    'rgb(255,219,0)'  // Proteins color
                ],
                borderWidth: 1
            }]
        };

        // Chart configuration
        var config = {
            type: 'polarArea',
            data: data,
            options: {
                responsive: true
            }
        };

        // Create the chart
        var myChart = new Chart(ctx, config);
    </script>

</body>