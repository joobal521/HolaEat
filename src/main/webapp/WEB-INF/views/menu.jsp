<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="com.spring.holaeat.domain.ingredients.Ingredients" %>
<%@ page import="com.spring.holaeat.domain.ingredients.IngredientsRepository" %><%--
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
        .selected-prefer button,.selected-dislike button{
            margin-left: 8px; /* 버튼과 재료 사이의 간격 조정 */
            background-color: transparent;
            border: none;
            color: red; /* 'X' 버튼의 색상 설정 */
            cursor: pointer;
        }

        .category button.selected {
            background-color: darkcyan;
        }

        .dislike_title, .prefer_title {
            display: flex;
            flex-direction: row;
            justify-content: space-evenly;
        }

        .store-container1, .store-container2 {
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
            <%--        카테고리    --%>
            <div class="category_title">
                <h2>어떤 메뉴를 드시고 싶으신가요?</h2>
            </div>
            <div class="category">
                <select name="national" id="national">
                    <option value="">선택하세요</option>
                    <option class="korean" value="">한식</option>
                    <option class="chinese" value="">중식</option>
                    <option class="japanese" value="">일식</option>
                    <option class="western" value="">양식</option>
                    <option class="salad" value="">샐러드</option>
                </select>
            </div>
            <div class="prefer">
                <h2>선호하는 재료</h2>
                <select name="prefer" id="prefer">
                    <option value="">선택하세요</option>
                    <option value="1">우유</option>
                    <option value="2">메밀</option>
                    <option value="3">땅콩</option>
                    <option value="4">대두</option>
                    <option value="5">밀</option>
                    <option value="6">고등어</option>
                    <option value="7">게</option>
                    <option value="8">새우</option>
                    <option value="9">복숭아</option>
                    <option value="10">토마토</option>
                    <option value="11">두부</option>
                    <option value="12">깨</option>
                    <option value="13">쌀</option>
                    <option value="14">두유</option>
                    <option value="15">감자</option>
                    <option value="16">계란</option>
                    <option value="17">쇠고기</option>
                    <option value="18">생선</option>
                    <option value="19">닭고기</option>
                    <option value="20">돼지고기</option>
                    <option value="21">수박</option>
                    <option value="22">참외</option>
                    <option value="23">케찹</option>
                    <option value="24">소금</option>
                    <option value="25">포도</option>
                    <option value="26">연근</option>
                </select>
            </div>

            <div class="selected-prefer">
                <h2>선택된 재료</h2>
                <ul id="selectedIngredientsList"></ul>
            </div>

            <button id="savePreferButton">저장</button>


            <div class="dislike">
                <h2>선호하지 않는 재료</h2>
                <select name="dislike" id="dislike">
                    <option value="">선택하세요</option>
                    <option value="1">우유</option>
                    <option value="2">메밀</option>
                    <option value="3">땅콩</option>
                    <option value="4">대두</option>
                    <option value="5">밀</option>
                    <option value="6">고등어</option>
                    <option value="7">게</option>
                    <option value="8">새우</option>
                    <option value="9">복숭아</option>
                    <option value="10">토마토</option>
                    <option value="11">두부</option>
                    <option value="12">깨</option>
                    <option value="13">쌀</option>
                    <option value="14">두유</option>
                    <option value="15">감자</option>
                    <option value="16">계란</option>
                    <option value="17">쇠고기</option>
                    <option value="18">생선</option>
                    <option value="19">닭고기</option>
                    <option value="20">돼지고기</option>
                    <option value="21">수박</option>
                    <option value="22">참외</option>
                    <option value="23">케찹</option>
                    <option value="24">소금</option>
                    <option value="25">포도</option>
                    <option value="26">연근</option>
                </select>
            </div>

            <button id="saveDislikeButton">저장</button>

            <div class="selected-dislike">
                <h2>선택된 재료</h2>
                <ul id="selectedUnIngredientsList"></ul>
            </div>

            <hr>
            <div class="personal_menu">
                <h2>${userName}님만을 위한 맞춤식단이 여기 있습니다!</h2>
            </div>

        </div>


</section>
<script src="resources/js/cal.js"></script>
<script src="resources/js/ingredients.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>
