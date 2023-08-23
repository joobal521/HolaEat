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
                <th><button id="filterToggleAllergy">알러지</button></th>
                <th><button id="filterToggleWeightControl">체중조절식</button></th>
                <th><button id="filterToggleVegan">비건</button></th>
                <th><button id="filterToggleBalanced">균형식</button></th>
                <th><button id="filterToggleSideDish">반찬</button></th>
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
                <tr>
                    <td>${food.foodId}</td>
                    <td class="foodName">${food.foodName}</td>
                    <td class="foodGroup">${food.foodGroup}</td>
                    <td class="foodNational">${food.foodNational}</td>
                    <td class="allergyInfo">${food.allergyInfo ? '예' : '아니오'}</td>
                    <td class="weightControl">${food.weightControl ? '예' : '아니오'}</td>
                    <td class="vegan">${food.vegan ? '예' : '아니오'}</td>
                    <td class="balanced">${food.balanced ? '예' : '아니오'}</td>
                    <td class="sideDish">${food.sideDish ? '예' : '아니오'}</td>
                    <td class="kcal nutrition-cell">${food.kcal}</td>
                    <td class="foodWeight nutrition-cell">${food.foodWeight}</td>
                    <td class="carb nutrition-cell">${food.carb}</td>
                    <td class="protein nutrition-cell">${food.protein}</td>
                    <td class="fat nutrition-cell">${food.fat}</td>
                    <td class="sugars nutrition-cell">${food.sugars}</td>
                    <td class="natrium nutrition-cell">${food.natrium}</td>

                    <td>
                        <button class="editBtn" data-id="${food.foodId}">수정하기</button>
                        <button class="updateBtn" data-id="${food.foodId}" style="display: none;">수정완료</button>
                        <button class="cancelBtn" data-id="${food.foodId}" style="display: none;">수정취소</button>
                        <input type="file" id="editImg-${food.foodId}" name="foodImg" class="imgBtn"
                               style="display: none;" accept="image/png, image/jpg, image/jpeg, image/gif">
                        <button class="imgUpdate" data-id="${food.foodImg}" style="display: none;">사진업로드</button>
                    </td>
                    <td>
                        <button class="removeBtn" data-id="${food.foodId}">삭제하기</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="18" style="text-align: center;">
                    <button id="loadMoreBtn" data-currentPage="${currentPage + 1}">더 보기</button>
                </td>
            </tr>
            </tfoot>
        </table>
        <div class="admin-menu-add">
            <input type="button" id="addBtn" value="추가하기">
            <div id="addModal" class="modal" style="display: none">
                <div class="modal-content">
                    <span class="close">&times;</span>
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
                        <input type="submit" value="추가완료">
                    </form>
                </div>
            </div>
        </div>

    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="script/adminMenu.js"></script>
</body>
