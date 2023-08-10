<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="resources/style/form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <style>
        .selected {
            background-color: lightblue; /* 선택된 버튼 배경색 */
        }
    </style>
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <h2> ${userName}님을 위한 식단이 준비되어 있습니다! </h2>
    <h3>정확한 식단 제공을 위해, ${userName}님에 대해 더욱 자세히 알려주세요!</h3>
    <div class="form_wrap">
<%--        열량 계산 시작 --%>
        <form id="myform" action="/saveCalories" method="POST">
            <ul>
                <li>
                    <h2>성별</h2>
                    <label>
                        <input type="radio" id="male" name="gender" value="male" ${userGender eq 'male' ? 'checked' : ''}>
                        <span>남자</span>
                    </label>
                    <label>
                        <input type="radio" id="female" name="gender" value="female" ${userGender eq 'female' ? 'checked' : ''}>
                        <span>여자</span>
                    </label>
                </li>
                <li>
                    <h2>나이</h2>
                    <input type="text" id="age" name="age" value="${userAge}">
                    <span>세</span>
                </li>
                <li>
                    <h2>키</h2>
                    <input type="text" id="height" name="height" value="${userHeight}">
                    <span>cm</span>
                </li>
                <li>
                    <h2>몸무게</h2>
                    <input type="text" id="weight" name="weight" value="${userWeight}">
                    <span>kg</span>
                </li>
                <li>
                    <h2>알레르기</h2>
                    <select name="allergy" id="allergy">
                        <option value="">없음</option>
                        <option value="1" ${userAllergy eq '1' ? 'selected' : ''}>유제품</option>
                        <option value="2" ${userAllergy eq '2' ? 'selected' : ''}>갑각류</option>
                        <option value="3" ${userAllergy eq '3' ? 'selected' : ''}>과일류</option>
                        <option value="4" ${userAllergy eq '4' ? 'selected' : ''}>견과류</option>
                    </select>
                </li>
                <input type="button" id="save_btn" name="save_btn" value="저장">
                <input type="button" id="calculate" name="calculate" value="계산" onclick="calculateCalories()">

            </ul>
            <ul>
                <li>
                    <h2>필요 열량</h2>
                    <input type="text" id="recCalories" name="recCalories" value="${userRecCalories}">
                    <span>kcal</span>
                </li>
            </ul>
        </form>

    <div class="btn-container1">
        <a class="btn" href="/menu?national=all">All</a>
        <a class="btn" href="/menu?national=한식">한식</a>
        <a class="btn" href="/menu?national=양식">양식</a>
        <a class="btn" href="/menu?national=일식">일식</a>
        <a class="btn" href="/menu?national=중식">중식</a>
        <a class="btn" href="/menu?national=샐러드">샐러드</a>
    </div>

    <div class="store-container1">
        <c:forEach var="foodName" items="${foodNames}">
            <div class="store-item">${foodName}</div>
        </c:forEach>
    </div>

    <div class="btn-container2">
        <button>재료 목록</button>
    </div>

    <div class="store-container2"> <!-- 추가 -->
        <c:forEach var="ingrName" items="${ingrNames}">
            <div class="store-item"><button>${ingrName}</button></div>
        </c:forEach>
    </div>

    <div class="selected-ingredients">
        선택된 재료:
        <ul id="selected-list"></ul>
    </div>

    <script>
        const ingredientButtons = document.querySelectorAll('.store-item button');
        const selectedList = document.getElementById('selected-list');

        const selectedIngredients = [];

        ingredientButtons.forEach(button => {
            button.addEventListener('click', () => {
                const ingredient = button.textContent;

                if (!selectedIngredients.includes(ingredient)) {
                    selectedIngredients.push(ingredient);
                    button.classList.add('selected'); // 배경색 변경
                } else {
                    const index = selectedIngredients.indexOf(ingredient);
                    if (index !== -1) {
                        selectedIngredients.splice(index, 1);
                        button.classList.remove('selected'); // 배경색 원래대로
                    }
                }

                updateSelectedList();
            });
        });

        function updateSelectedList() {
            selectedList.innerHTML = '';
            selectedIngredients.forEach(ingredient => {
                const li = document.createElement('li');
                li.textContent = ingredient;
                selectedList.appendChild(li);
            });
        }
    </script>
<%--        열량 계산 끝 --%>

<%--        <div class="form_con">--%>
<%--        <form action="">--%>
<%--            <h2>취향에 맞게 식단을 짜보세요!</h2>--%>
<%--            <h3>선호하는 재료</h3>--%>
<%--            <label for="tofu">--%>
<%--                <input type="radio" id="tofu" name="prefer">--%>
<%--                두부--%>
<%--            </label>--%>
<%--            <label for="sesame">--%>
<%--                <input type="radio" id="sesame" name="prefer">--%>
<%--                깨--%>
<%--            </label>--%>
<%--            <label for="rice">--%>
<%--                <input type="radio" id="rice" name="prefer">--%>
<%--                쌀--%>
<%--            </label>--%>
<%--            <label for="potato">--%>
<%--                <input type="radio" id="potato" name="prefer">--%>
<%--                감자--%>
<%--            </label>--%>
<%--            <label for="pork">--%>
<%--                <input type="radio" id="pork" name="prefer">--%>
<%--                돼지고기--%>
<%--            </label>--%>
<%--            <label for="chicken">--%>
<%--                <input type="radio" id="chicken" name="prefer">--%>
<%--                닭고기--%>
<%--            </label>--%>
<%--            <label for="fish">--%>
<%--                <input type="radio" id="fish" name="prefer">--%>
<%--                생선--%>
<%--            </label>--%>
<%--            <label for="meat">--%>
<%--                <input type="radio" id="meat" name="prefer">--%>
<%--                소고기--%>
<%--            </label>--%>
<%--            <label for="peach">--%>
<%--                <input type="radio" id="peach" name="prefer">--%>
<%--                복숭아--%>
<%--            </label>--%>
<%--        </form>--%>
<%--        </div>--%>
    </div>
</section>
<script src="resources/js/cal.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
