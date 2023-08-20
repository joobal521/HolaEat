<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        .recipe-content {
            display: flex;
            align-items: center;
            margin: 20px;
        }

        .recipe-img-container {
            width: 250px;
            height: 250px;
            background-color: #f3f3f3;
            border-radius: 50%;
            overflow: hidden;
            margin-right: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .recipe-details {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .food-info {
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .recipe-list {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            flex: 1;
        }


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
        <div class="recipe-details">
            <div class="food-info">
                <h1>${food.foodName}</h1>
                <span>${food.foodGroup}</span>
                <span>${food.foodNational}</span>
                <div>
                    <span>총 열량 : ${food.kcal}</span>
                    <span>탄수화물 : ${food.carb}</span>
                    <span>지방 : ${food.fat}</span>
                    <span>단백질 : ${food.protein}</span>
                    <span>당 : ${food.sugars}</span>
                    <span>나트륨 : ${food.natrium}</span>
                </div>
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
            <div class="nutrition-chart-container">
                <canvas id="nutritionChart"></canvas>
            </div>
        </div>
    </div>


    <script>
        // Chart.js 라이브러리 스크립트를 포함한 <script> 태그를 추가해야 합니다.


        $(document).ready(function () {
            // 차트를 그릴 캔버스 엘리먼트
            var nutritionChartCanvas = document.getElementById("nutritionChart");

            // 차트 데이터
            var data = {
                labels: ["탄수화물", "단백질", "지방", "당류", "나트륨"],
                datasets: [
                    {
                        label: "영양소",
                        data: [
                            parseInt('${food.carb}'),
                            parseInt('${food.protein}'),
                            parseInt('${food.fat}'),
                            parseInt('${food.sugars}'),
                            parseInt('${food.natrium}')
                        ],
                        backgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56",
                            "#4BC0C0",
                            "#9966FF"
                        ],
                        borderColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56",
                            "#4BC0C0",
                            "#9966FF"
                        ],
                        borderWidth: 1
                    }
                ]
            };




            // 차트 생성
            var nutritionChart = new Chart(nutritionChartCanvas, {
                type: "doughnut", // Change the chart type to "doughnut"
                data: data,
                options: options
            });

        });

    </script>
</body>