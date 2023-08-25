<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="com.spring.holaeat.domain.ingredients.Ingredients" %>
<%@ page import="com.spring.holaeat.domain.ingredients.IngredientsRepository" %>
<%@ page import="com.spring.holaeat.domain.menu.Menu" %>
<%@ page import="org.json.JSONObject" %>


<%@ page import="com.spring.holaeat.domain.menu.Menu" %>
<%@ page import="com.spring.holaeat.domain.menu.MenuRepository" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="style/form.css">
    <link rel="stylesheet" type="text/css" href="style/menu.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <div class="body_header">
        <h2> ${userResponseDto.userName}님을 위한 식단이 준비되어 있습니다! </h2>
        <h3 class="alert_h3">정확한 식단 제공을 위해, ${userResponseDto.userName}님에 대해 더욱 자세히 알려주세요!</h3>
    </div>
    <div class="form_wrap">
        <%--        열량 계산 시작 --%>
        <form id="myform" action="/saveCalories" method="POST">
            <ul>
                <li>
                    <h2>성별</h2>
                    <label>
                        <input type="radio" id="male" name="gender"
                               value="male" ${userResponseDto.userGender eq 'male' ? 'checked' : ''}>
                        <span>남자</span>
                    </label>
                    <label>
                        <input type="radio" id="female" name="gender"
                               value="female" ${userResponseDto.userGender eq 'female' ? 'checked' : ''}>
                        <span>여자</span>
                    </label>
                </li>
                <li>
                    <label for="age">
                        <h2>나이(세)</h2>
                    </label>
                    <input type="text" id="age" name="age" value="${userResponseDto.userAge}">
                </li>
                <li>
                    <label for="height">
                        <h2>키(cm)</h2>
                    </label>
                    <input type="text" id="height" name="height" value="${userResponseDto.userHeight}">
                </li>
                <li>
                    <label for="weight">
                        <h2>몸무게(kg)</h2>
                    </label>
                    <input type="text" id="weight" name="weight" value="${userResponseDto.userWeight}">
                </li>
                <li>
                    <label for="allergy">
                        <h2>알레르기</h2>
                    </label>
                    <select name="allergy" id="allergy">
                        <option value="5">없음</option>
                        <option value="1" ${userResponseDto.userAllergy eq '1' ? 'selected' : ''}>유제품</option>
                        <option value="2" ${userResponseDto.userAllergy eq '2' ? 'selected' : ''}>갑각류</option>
                        <option value="3" ${userResponseDto.userAllergy eq '3' ? 'selected' : ''}>과일류</option>
                        <option value="4" ${userResponseDto.userAllergy eq '4' ? 'selected' : ''}>견과류</option>
                    </select>
                </li>
                <li>
                    <h2>식단종류</h2>
                    <select name="menu_type" id="menu_type">
                        <option value="1">일반균형식</option>
                        <option value="2">체중조절식</option>
                        <option value="3">비건</option>
                    </select>
                </li>
                <input type="button" class="button" id="calculate" name="calculate" value="계산하기"
                       onclick="calculateCalories()">
            </ul>
            <ul>
                <li>
                    <h2>하루 필요 열량</h2>
                    <input type="text" class="btn6" id="recCalories" name="recCalories"
                           value="${userResponseDto.userRecCalories}" readonly>
                    <span>kcal</span>
                </li>
            </ul>
            <div id="btn_wrap">
                <input type="button" class="button" id="save_btn" name="save_btn" value="내 칼로리 정보 저장">
            </div>
            <div class="json_wrap">

                <%--        카테고리    --%>

                <div class="prefer">
                    <h2>이 재료는 넣어주세요!</h2>
                    <select name="prefer" id="prefer">
                        <option value="">없음</option>
                        <option value="우유">우유</option>
                        <option value="메밀">메밀</option>
                        <option value="땅콩">땅콩</option>
                        <option value="밀">밀</option>
                        <option value="새우">새우</option>
                        <option value="두부">두부</option>
                        <option value="감자">감자</option>
                        <option value="계란">계란</option>
                        <option value="쇠고기">쇠고기</option>
                        <option value="생선">생선</option>
                        <option value="닭고기">닭고기</option>
                        <option value="돼지고기">돼지고기</option>
                    </select>
                </div>

                <div class="dislike">
                    <h2>이 재료는 빼주세요!</h2>
                    <select name="dislike" id="dislike">
                        <option value="">없음</option>
                        <option value="우유">우유</option>
                        <option value="메밀">메밀</option>
                        <option value="땅콩">땅콩</option>
                        <option value="밀">밀</option>
                        <option value="새우">새우</option>
                        <option value="두부">두부</option>
                        <option value="감자">감자</option>
                        <option value="계란">계란</option>
                        <option value="쇠고기">쇠고기</option>
                        <option value="생선">생선</option>
                        <option value="닭고기">닭고기</option>
                        <option value="돼지고기">돼지고기</option>
                    </select>
                </div>
                <div id="btn_wrap">
                    <input type="button" class="button" id="menu_btn" name="menu_btn" value="모든 메뉴보기"
                           onclick="fetchAndDisplayMenu()">
                </div>
                <li>
                    <h3 class="mini_h3">열량 계산 기준</h3>
                    <span class="mini">*알레르기가 있으시면 권장 칼로리가 10% 감소합니다.</span><br>
                    <span class="mini">*체중조절식은 일반균형식 대비 500kcal 감소합니다.</span><br>
                    <span class="mini">*식단은 최대 3개까지 구성하실 수 있으며,<br>세 식단의 칼로리는 하루 필요 열량을 넘을 수 없습니다.</span>
                </li>
            </div>
        </form>

        <div class="cat" style="display: flex; justify-content: space-evenly">
            <span>어떤 메뉴를 원하세요?</span>
            <div class="category_title">
                <div class="category">
                    <select name="national" id="national" onchange="fetchAndDisplayAllMenus(this.value)">
                        <option value="">선택하세요</option>
                        <option class="korean" value="한식">한식</option>
                        <option class="chinese" value="중식">중식</option>
                        <option class="japanese" value="일식">일식</option>
                        <option class="western" value="양식">양식</option>
                        <option class="salad" value="샐러드">샐러드(비건식)</option>
                    </select>
                    <button id="reset_btn">모두 드래그 해제</button>
                    <%--                    <button id="menu_save_btn" onclick="saveSelectedMenus()">식단 저장</button>--%>
                </div>
            </div>
        </div>
        <div class="personal_menu" style="display: flex">
            <div class="menus_wrap">
                <div id="generatedMenus" class="menu_list" style="width: 50%; height: 500px;">
                    <!-- 이 부분에 동적으로 생성되는 식단 목록이 들어갈 예정입니다. -->
                </div>
                <hr class="main_hr">
                <div id="selectedMenus" class="menu_list" style="width: 50%;height: 500px; overflow: scroll">
                    <!-- 드래그해서 이동한 식단 목록이 여기에 들어갈 예정입니다. -->
                    <%--                <div class="image-with-content">--%>
                    <%--                    <img class="food_img" src="img/table.jpg" alt="식단 이미지">--%>
                    <%--                    <div class="content">--%>
                    <%--                    </div>--%>
                    <%--                </div>--%>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="script/cal.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>