<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="style/ingredients.css">

    <style>
        .food-wrap {
            display: flex;
        }

        .food-img-container {
            flex: 1;
            position: relative;
            width: 400px;
            max-width: 100%; /* 이미지가 컨테이너보다 작을 때도 화면 너비에 맞춤 */
            overflow: hidden;
            border-radius: 50%;
        }

        .food-img-container::before {
            content: "";
            display: block;
            padding-top: 100%; /* 1:1 비율을 유지하려면 100%로 설정 */
        }

        .food-img-container img {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .food-info-container {
            flex: 1;
            background: #b9b9b9;
            height: 90%;
            margin: auto;
            border-radius: 5px;
        }

        .food-info {
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .food-details {
            margin: 0 auto;
        }
        .food-nutrition{
            display: flex;
            text-align: center;
            margin: 0 auto;
        }
        .recipe-list {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            flex: 1;
        }



        .food-nutrition span:hover {
            border-color: #000; /* 변경하고자 하는 색상 */
        }

        /*.carb {*/

        /*    background-color: #FF5733;*/
        /*}*/
        /*.fat {*/

        /*    background-color: #FFC300;*/
        /*}*/
        /*.protein {*/

        /*    background-color: #36A2EB;*/
        /*}*/
        /*.sugars {*/

        /*    background-color: #4CAF50;*/
        /*}*/
        /*.natrium {*/
        /*    background-color: #9C27B0;*/
        /*}*/
        .carb{
            background-color: #CFFFB3; /* 연한 초록색 */

        }
        .fat {
            background-color: #FFD1DF; /* 연한 분홍색 */
        }

        .protein {
            background-color: #DACAFF; /* 연한 보라색 */
        }

        .sugars {
            background-color: #FFFAAF; /* 연한 노란색 */
        }

        .natrium {
            background-color: #C9A2D7; /* 연한 파란색 */
        }

        .food-nutrition span {
            display: inline-block;
        }
        .bar-chart {
            border-radius: 20px;
            display: flex;
            max-width: 400px;
            height: 30px;
            overflow: hidden;
            text-align: center;
            position: relative; /* 추가된 부분 */
            margin:10px auto;
        }

        .bar-chart .bar {
            position: relative; /* 추가된 부분 */
        }

        .bar-chart .percentage-box {
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            padding: 5px;
            background-color: rgba(0, 0, 0, 0.8);
            color: white;
            border-radius: 5px;
            opacity: 0;
            transition: opacity 0.3s, visibility 0.3s;
            z-index: 10;
            height: 90%;
            justify-content: center;
        }

        .bar-chart .bar:hover .percentage-box {
            visibility: visible;
            opacity: 1;
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
                <h1>${food.foodName}(${food.foodWeight}g)</h1>
                <span>${food.foodGroup}</span>
                <span>${food.foodNational}</span>
            </div>
            <div class="food-nutrition">
                <span>총 열량 : <br/>${food.kcal} Kcal</span>
                <span class="carb">탄수화물 : ${food.carb}g</span>
                <span class="fat">지방 : ${food.fat}g</span>
                <span class="protein">단백질 : ${food.protein}g</span>
                <span class="sugars">당 : ${food.sugars}g</span>
                <span class="natrium">나트륨 : ${food.natrium}mg</span>
                <c:set var="total" value="${food.carb + food.fat + food.protein + food.sugars + (food.natrium/100)}"/>

            </div>
            <div class="bar-chart">
                <div class="bar carb" style="width: ${(food.carb / total) * 100}%;">
                    <div class="percentage-box">${Math.round((food.carb / total) * 100 * 10) / 10}%</div>
                </div>
                <div class="bar fat" style="width: ${(food.fat / total) * 100}%;">
                    <div class="percentage-box">${Math.round((food.fat / total) * 100 * 10) / 10}%</div>
                </div>
                <div class="bar protein" style="width: ${(food.protein / total) * 100}%;">
                    <div class="percentage-box">${Math.round((food.protein / total) * 100 * 10) / 10}%</div>
                </div>
                <div class="bar sugars" style="width: ${(food.sugars / total) * 100}%;">
                    <div class="percentage-box">${Math.round((food.sugars / total) * 100 * 10) / 10}%</div>
                </div>
                <div class="bar natrium" style="width: ${(food.natrium / (total * 100)) * 100}%;">
                    <div class="percentage-box">${Math.round((food.natrium / (total * 100)) * 100 * 10) / 10}%</div>
                </div>
            </div>

            <script>

            </script>
        </div>
        <div class="recipe-list">
            <h2>레시피</h2>
            <ul>
                <c:forEach items="${recipe}" var="foodrecipe">
                    <li id="step1">1.${foodrecipe.step_01}</li>
                    <li id="step2">2.${foodrecipe.step_02}</li>
                    <li id="step3">3.${foodrecipe.step_03}</li>
                    <li id="step4">4.${foodrecipe.step_04}</li>
                    <li id="step5">5.${foodrecipe.step_05}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>