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
        .preferred.selected{
            background-color: lightblue;
        }

        .unpreferred.selected{
            background-color: lightcoral;
            container: white;
        }
        .dislike_title, .prefer_title {
            display: flex;
            flex-direction: row;
            justify-content: space-evenly;
        }
        .store-container1, .store-container2{
            display: flex;
            flex-direction: row;
            justify-content: space-around;
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
                        <input type="radio" id="male" name="gender"
                               value="male" ${userGender eq 'male' ? 'checked' : ''}>
                        <span>남자</span>
                    </label>
                    <label>
                        <input type="radio" id="female" name="gender"
                               value="female" ${userGender eq 'female' ? 'checked' : ''}>
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
        <%--    열량 계산기 끝 --%>

        <div class="json_wrap">

<%--선호--%>
            <div class="prefer_title">
                <h2>선호하는 재료(최대 3개)</h2>
                <button class="default1" onclick="resetPreferredIngredients()">초기화</button>
            </div>
            <div class="ingredients">

                <div class="btn-container1">
                    <button>재료 목록</button>
                </div>
                <div class="store-container1">
                    <c:forEach var="ingrName" items="${ingrNames}">
                        <div class="store-item">
                            <button class="preferred">${ingrName}</button>
                        </div>
                    </c:forEach>
                </div>

                <div class="selected-ingredients1">
                    선택된 재료:
                    <ul id="preferred-selected-list"></ul>
                </div>
                <hr>
<%--비선호--%>
                <div class="dislike_title">
                    <h2>선호하지 않는 재료(최대 3개)</h2>
                    <button class="default2" onclick="resetUnpreferredIngredients()">초기화</button>

                </div>
                <div class="btn-container2">
                    <button>재료 목록</button>
                </div>

                <div class="store-container2">
                    <c:forEach var="ingrName" items="${ingrNames}">
                        <div class="store-item">
                            <button class="unpreferred">${ingrName}</button>
                        </div>
                    </c:forEach>
                </div>

                <div class="selected-ingredients2">
                    선택된 재료:
                    <ul id="unpreferred-selected-list"></ul>
                </div>
            </div>
<%--            재료 선택 끝--%>
            <hr>
            <div class="personal_menu">
                <h2>${userName}님만을 위한 맞춤식단이 여기 있습니다!</h2>
            </div>

        </div>

    </div>
</section>
<script src="resources/js/cal.js"></script>
<script src="resources/js/ingredients.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
