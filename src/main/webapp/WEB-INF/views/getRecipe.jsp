<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
    <div>
    <c:if test="${not empty blob }">
        <div id="image-container">
            <img src="data:image/png;base64,${blob}" id="ingrImg" name="ingrIm"  alt="ingr Img">
            <img src="data:image/jpeg;base64,${blob}" id="img" name="img" alt="Ingredient Image">
        </div>
    </c:if>
        <div>
            <div>
                <h1>${food.foodName}</h1>
                <span>${food.foodGroup}</span>
                <span>${food.foodNational}</span>
            </div>

            <div>
                <h3>총 열량 : ${food.kcal}</h3>
                <h3>탄수화물 : ${food.carb}</h3>
                <h3>지방 : ${food.fat}</h3>
                <h3>단백질 : ${food.protein}</h3>
                <h3>당 : ${food.sugars}</h3>
                <h3>나트륨 : ${food.natrium}</h3>
            </div>

        </div>
        <h3>레시피</h3>
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



<script>
    // Chart.js 라이브러리 스크립트를 포함한 <script> 태그를 추가해야 합니다.


    $(document).ready(function() {
        // 차트를 그릴 캔버스 엘리먼트
        var nutritionChartCanvas = document.getElementById("nutritionChart");

        // 차트 데이터
        var data = {
            labels: [ "탄수화물", "단백질", "지방", "당류", "나트륨"],
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
                        "rgba(255, 99, 132, 0.2)",
                        "rgba(54, 162, 235, 0.2)",
                        "rgba(255, 206, 86, 0.2)",
                        "rgba(75, 192, 192, 0.2)",
                        "rgba(153, 102, 255, 0.2)",
                    ],
                    borderColor: [
                        "rgba(255, 99, 132, 1)",
                        "rgba(54, 162, 235, 1)",
                        "rgba(255, 206, 86, 1)",
                        "rgba(75, 192, 192, 1)",
                        "rgba(153, 102, 255, 1)",
                    ],
                    borderWidth: 1
                }
            ]
        };


        // 차트 옵션
        var options = {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        };

        // 차트 생성
        var nutritionChart = new Chart(nutritionChartCanvas, {
            type: "bar", // 다른 유형도 사용 가능: "line", "radar" 등
            data: data,
            options: options
        });
    });

</script>
</body>