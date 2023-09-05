<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>

    .hide-nutrition {
        display: none;
    }

</style>
<body>
<h1 class="admin-title">메뉴관리</h1>
<button id="toggleNutrition" class="toggle-button">영양소 보기</button>
<div class="admin-menu">
    <table>
        <thead>
        <tr>
            <th>음식 ID</th>
            <th>음식 이름</th>
            <th>식품군</th>
            <th>음식종류(나라)</th>
            <th>
                <button id="filterToggleAllergy" onclick="allergyInfoFilter()">알러지</button>
            </th>
            <th>
                <button id="filterToggleWeightControl" onclick="weightControlFilter()">체중조절식</button>
            </th>
            <th>
                <button id="filterToggleVegan" onclick="veganFilter()">비건</button>
            </th>
            <th>
                <button id="filterToggleBalanced" onclick="balancedFilter()">균형식</button>
            </th>
            <th>
                <button id="filterToggleSideDish" onclick="sideDishFilter()">반찬</button>
            </th>
            <th class="nutrition-cell">총 열량(kcal)</th>
            <th class="nutrition-cell">중량</th>
            <th class="nutrition-cell">탄수화물(g)</th>
            <th class="nutrition-cell">단백질(g)</th>
            <th class="nutrition-cell">지방(g)</th>
            <th class="nutrition-cell">당류(g)</th>
            <th class="nutrition-cell">나트륨(mg)</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        </thead>
        <tbody class="admin-foodList">
        <c:forEach items="${foodList}" var="food">
            <tr id="${food.foodId}">
                <td>${food.foodId}</td>
                <td class="foodName">
                    <input type="text" class="foodName-input" id="input-foodName-${food.foodId}"
                           value="${food.foodName}" readonly data-initial-value="${food.foodName}">
                </td>
                <td class="foodGroup">
                    <input type="text" class="foodGroup-input" id="input-foodGroup-${food.foodId}"
                           value="${food.foodGroup}" readonly data-initial-value="${food.foodGroup}">
                </td>
                <td class="foodNational">
                    <input type="text" class="foodNational-input" id="input-foodNational-${food.foodId}"
                           value="${food.foodNational}" readonly data-initial-value="${food.foodNational}">
                </td>
                <td class="allergyInfo">
                    <select class="allergyInfo-sel" id="allergyInfo-val-${food.foodId}"  data-initial-value="${!food.allergyInfo ? '아니오' : '예'}" disabled>
                        <option value="예" ${food.allergyInfo ? 'selected' : ''}>예</option>
                        <option value="아니오" ${!food.allergyInfo ? 'selected' : ''}>아니오</option>
                    </select>
                </td>
                <td class="weightControl">
                    <select class="weightControl-sel" id="weightControl-val-${food.foodId}" data-initial-value="${!food.weightControl ? '아니오' : '예'}" disabled>
                        <option value="예" ${food.weightControl ? 'selected' : ''}>예</option>
                        <option value="아니오" ${!food.weightControl ? 'selected' : ''} >아니오</option>
                    </select>
                </td>
                <td class="vegan">
                    <select class="vegan-sel" id="vegan-val-${food.foodId}"  data-initial-value="${!food.vegan ? '아니오' : '예'}" disabled>
                        <option value="예" ${food.vegan ? 'selected' : ''}>예</option>
                        <option value="아니오" ${!food.vegan ? 'selected' : ''} >아니오</option>
                    </select>
                </td>
                <td class="balanced">
                    <select class="balanced-sel" id="balanced-val-${food.foodId}" data-initial-value="${food.balanced ? '예' : '아니오'}" disabled>
                        <option value="예" ${food.balanced ? 'selected' : ''} >예</option>
                        <option value="아니오" ${!food.balanced ? 'selected' : ''}>아니오</option>
                    </select>
                </td>
                <td class="sideDish">
                    <select class="sideDish-sel" id="sideDish-val-${food.foodId}" data-initial-value="${food.sideDish ? '예' : '아니오'}" disabled>
                        <option value="예" ${food.sideDish ? 'selected' : ''} >예</option>
                        <option value="아니오" ${!food.sideDish ? 'selected' : ''}>아니오</option>
                    </select>
                </td>
                <td class="kcal nutrition-cell">
                    <input type="number" class="kcal-input" id="input-kcal-${food.foodId}" value="${food.kcal}" readonly data-initial-value="${food.kcal}">
                </td>
                <td class="foodWeight nutrition-cell">
                    <input type="number" class="foodWeight-input" id="input-foodWeight-${food.foodId}" value="${food.foodWeight}" readonly data-initial-value="${food.foodWeight}">
                </td>
                <td class="carb nutrition-cell">
                    <input type="number" class="carb-input" id="input-carb-${food.foodId}" value="${food.carb}" readonly data-initial-value="${food.carb}">
                </td>
                <td class="protein nutrition-cell">
                    <input type="number" class="protein-input" id="input-protein-${food.foodId}" value="${food.protein}" readonly data-initial-value="${food.protein}">
                </td>
                <td class="fat nutrition-cell">
                    <input type="number" class="fat-input" id="input-fat-${food.foodId}" value="${food.fat}" readonly data-initial-value="${food.fat}">
                </td>
                <td class="sugars nutrition-cell">
                    <input type="number" class="sugars-input" id="input-sugars-${food.foodId}" value="${food.sugars}" readonly data-initial-value="${food.sugars}">
                </td>
                <td class="natrium nutrition-cell">
                    <input type="number" class="natrium-input" id="input-natrium-${food.foodId}" value="${food.natrium}" readonly data-initial-value="${food.natrium}">
                </td>
                <td>
                    <button class="editBtn" data-id="${food.foodId}" onclick="editFood(this)">수정하기</button>
                    <button class="updateBtn" data-id="${food.foodId}" style="display: none;" onclick="updateFood(this)">수정완료</button>
                    <button class="cancelBtn" data-id="${food.foodId}" style="display: none;" onclick="cancelEditFood(this)">수정취소</button>
                    <input type="file" id="editImg-${food.foodId}" name="foodImg" class="imgBtn"
                           style="display: none;" accept="image/png, image/jpg, image/jpeg, image/gif">
                    <button class="imgUpdate" data-id="${food.foodId}" style="display: none;">사진업로드</button>
                </td>
                <td>
                    <button class="removeBtn" data-id="${food.foodId}" onclick="deleteFood(this)">삭제하기</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="admin-menu-add">
        <input type="button" id="addBtn" value="추가하기" onclick="addModal()">
        <div id="addModal" class="modal" style="display: none">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <form id="addForm">
                    <label for="foodName">음식 이름:</label>
                    <input type="text" id="foodName" name="foodName" required><br><br>

                    <label for="foodGroup">식품군:</label>
                    <input type="text" id="foodGroup" name="foodGroup" required><br><br>

                    <label for="foodNational">음식종류(나라):</label>
                    <input type="text" id="foodNational" name="foodNational" required><br><br>

                    <label for="allergy">알러지:</label>
                    <input type="checkbox" id="allergy" name="allergy"><br><br>

                    <label for="weightControl">체중조절식:</label>
                    <input type="checkbox" id="weightControl" name="weightControl"><br><br>

                    <label for="vegan">비건:</label>
                    <input type="checkbox" id="vegan" name="vegan"><br><br>

                    <label for="balanced">균형식:</label>
                    <input type="checkbox" id="balanced" name="balanced"><br><br>

                    <label for="sideDish">반찬:</label>
                    <input type="checkbox" id="sideDish" name="sideDish"><br><br>

                    <label for="kcal">총 열량(kcal):</label>
                    <input type="number" id="kcal" name="kcal" required><br><br>

                    <label for="foodWeight">중량(g):</label>
                    <input type="number" id="foodWeight" name="foodWeight" required><br><br>

                    <label for="carb">탄수화물(g):</label>
                    <input type="number" id="carb" name="carb" required><br><br>

                    <label for="protein">단백질(g):</label>
                    <input type="number" id="protein" name="protein" required><br><br>

                    <label for="fat">지방(g):</label>
                    <input type="number" id="fat" name="fat" required><br><br>

                    <label for="sugars">당류(g):</label>
                    <input type="number" id="sugars" name="sugars" required><br><br>

                    <label for="natrium">나트륨(mg):</label>
                    <input type="number" id="natrium" name="natrium" required><br><br>

                    <label for="foodImg">사진:</label>
                    <input type="file" id="foodImg" name="foodImg" accept="image/png, image/jpg, image/jpeg, image/gif">
                    <input type="button" value="추가완료" onclick="addFood()">
                </form>
            </div>
        </div>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="script/adminMenu.js"></script>
</body>
