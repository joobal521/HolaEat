<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
</head>
<body>

<div>
    <h1>메뉴관리</h1>
    <div class="admin-menu">
        <table>
            <thead>
            <tr>
                <th>음식 ID</th>
                <th>음식 이름</th>
                <th>식품군</th>
                <th>음식종류(나라)</th>>
                <th>알러지</th>
                <th>체중조절식</th>
                <th>비건</th>
                <th>균형식</th>
                <th>반찬</th>
                <th>탄수화물(g)</th>
                <th>단백질(g)</th>
                <th>지방(g)</th>
                <th>당류(g)</th>
                <th>나트륨(mg)</th>
                <th>수정</th>
                <th>삭제</th>
            </tr>
            </thead>
            <tbody class="admin-foodList">
            <c:forEach items="${foodList}" var="food">
                <tr>
                    <td>${food.foodId}</td>
                    <td class="foodName">${food.foodName}</td>
                    <td class="allergyInfo">${food.allergyInfo ? '예' : '아니오'}</td>
                    <td class="weightControl">${food.weightControl ? '예' : '아니오'}</td>
                    <td class="vegan">${food.vegan ? '예' : '아니오'}</td>
                    <td class="balanced">${food.balanced ? '예' : '아니오'}</td>
                    <td class="sideDish">${food.sideDish ? '예' : '아니오'}</td>
                    <td class="carb">${food.carb}</td>
                    <td class="protein">${food.protein}</td>
                    <td class="fat">${food.fat}</td>
                    <td class="sugars">${food.sugars}</td>
                    <td class="natrium">${food.natrium}</td>
                    <td>
                        <button class="editBtn" data-id="${food.foodId}">수정하기</button>
                        <button class="updateBtn" data-id="${food.foodId}" style="display: none;">수정완료</button>
                        <button class="cancelBtn" data-id="${food.foodId}" style="display: none;">수정취소</button>
                        <input type="file" id="editImg-${food.foodId}" name="ingrImg" class="imgBtn"
                               style="display: none;" accept="image/png, image/jpg, image/jpeg, image/gif">
                        <button class="imgUpdate" data-id="${food.foodImg}" style="display: none;">사진업로드</button>
                    </td>
                    <td>
                        <button class="removeBtn" data-id="${food.foodId}">삭제하기</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <input type="button" id="addBtn" value="추가하기">
            <div id="addModal" class="modal" style="display: none">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <form id="addForm">
                        <label for="foodName">음식 이름:</label>
                        <input type="text" id="foodName" name="foodName" required><br><br>

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
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

    $(document).ready(function() {
        $(".editBtn").click(function() {
            var row = $(this).closest("tr");
            var foodNameCell = row.find(".foodName");
            var allergyInfoCell = row.find(".allergyInfo");
            var weightControlCell = row.find(".weightControl");
            var veganCell = row.find(".vegan");
            var balancedCell = row.find(".balanced");
            var sideDishCell = row.find(".sideDish");
            var carbCell = row.find(".carb");
            var proteinCell = row.find(".protein");
            var fatCell = row.find(".fat");
            var sugarsCell = row.find(".sugars");
            var natriumCell = row.find(".natrium");

            carbCell.html("<input type='number' class='editCarb' value='" + carbCell.text() + "'>");
            proteinCell.html("<input type='number' class='editProtein' value='" + proteinCell.text() + "'>");
            fatCell.html("<input type='number' class='editFat' value='" + fatCell.text() + "'>");
            sugarsCell.html("<input type='number' class='editSugars' value='" + sugarsCell.text() + "'>");
            natriumCell.html("<input type='number' class='editNatrium' value='" + natriumCell.text() + "'>");
            foodNameCell.html("<input type='text' class='editFoodName' value='" + foodNameCell.text() + "'>");
            allergyInfoCell.html("<input type='checkbox' class='editAllergyInfo' " + (allergyInfoCell.text() === "예" ? "checked" : "") + ">");
            weightControlCell.html("<input type='checkbox' class='editWeightControl' " + (weightControlCell.text() === "예" ? "checked" : "") + ">");
            veganCell.html("<input type='checkbox' class='editVegan' " + (veganCell.text() === "예" ? "checked" : "") + ">");
            balancedCell.html("<input type='checkbox' class='editBalanced' " + (balancedCell.text() === "예" ? "checked" : "") + ">");
            sideDishCell.html("<input type='checkbox' class='editSideDish' " + (sideDishCell.text() === "예" ? "checked" : "") + ">");

            // 수정/취소 버튼 토글
            $(this).hide();
            row.find(".updateBtn, .cancelBtn,.imgBtn").show();
        });


        $(".updateBtn").click(function() {
            var row = $(this).closest("tr");
            var foodId = $(this).data("id");
            var foodName = row.find(".editFoodName").val();
            var allergyInfo = row.find(".editAllergyInfo").prop("checked");
            var weightControl = row.find(".editWeightControl").prop("checked");
            var vegan = row.find(".editVegan").prop("checked");
            var balanced = row.find(".editBalanced").prop("checked");
            var sideDish = row.find(".editSideDish").prop("checked");
            var carb = row.find(".editCarb").val();
            var protein = row.find(".editProtein").val();
            var fat = row.find(".editFat").val();
            var sugars = row.find(".editSugars").val();
            var natrium = row.find(".editNatrium").val();
            var imageFile = $("#editImg-" + foodId)[0].files[0];

            var formData = new FormData();
            formData.append("foodName", foodName);
            formData.append("allergyInfo", allergyInfo);
            formData.append("weightControl", weightControl);
            formData.append("vegan", vegan);
            formData.append("balanced", balanced);
            formData.append("sideDish", sideDish);
            formData.append("carb", carb);
            formData.append("protein", protein);
            formData.append("fat", fat);
            formData.append("sugars", sugars);
            formData.append("natrium", natrium);
            if (imageFile != null) {
                formData.append("ingrImg", imageFile);
            }

            // Send the data to the backend using AJAX
            $.ajax({
                url: "adminMenu/" + foodId,
                method: "PUT",
                contentType: false,
                processData: false,
                data: formData,
                success: function(response) {
                    $(".section").html(response);
                },
                error: function() {
                    alert("데이터 업데이트에 실패했습니다.");
                }
            });
        });

        $(".cancelBtn").click(function() {
            var row = $(this).closest("tr");
            var foodId = $(this).data("id");
            var foodName = row.find(".editFoodName").val();
            var allergyInfo = row.find(".editAllergyInfo").prop("checked");
            var weightControl = row.find(".editWeightControl").prop("checked");
            var vegan = row.find(".editVegan").prop("checked");
            var balanced = row.find(".editBalanced").prop("checked");
            var sideDish = row.find(".editSideDish").prop("checked");

            row.find(".foodName").text(foodName);
            row.find(".allergyInfo").text(allergyInfo ? '예' : '아니오');
            row.find(".weightControl").text(weightControl ? '예' : '아니오');
            row.find(".vegan").text(vegan ? '예' : '아니오');
            row.find(".balanced").text(balanced ? '예' : '아니오');
            row.find(".sideDish").text(sideDish ? '예' : '아니오');

            // 수정/취소 버튼 토글
            $(this).hide();
            row.find(".editBtn").show();
            row.find(".updateBtn, .imgBtn").hide(); // Hide update and image upload buttons
        });


        $(".removeBtn").click(function() {
            var foodId = $(this).data("id");

            $.ajax({
                url: "adminMenu/delete/" + foodId,
                method: "DELETE",
                success: function(response) {
                    // Handle success if needed
                    alert("삭제하기: " + foodId);
                },
                error: function() {
                    alert("삭제에 실패했습니다.");
                }
            });
        });

        $("#addBtn").click(function() {
            $("#addModal").css("display", "block");
        });

        $(".close").click(function() {
            $("#addModal").css("display", "none");
        });

        $(window).click(function(event) {
            if (event.target == document.getElementById("addModal")) {
                $("#addModal").css("display", "none");
            }
        });

        // Handle the form submission
        $("#addForm").submit(function(event) {
            event.preventDefault();

            var formData = new FormData();
            formData.append("foodName", $("#foodName").val());
            formData.append("allergyInfo", $("#allergy").prop("checked"));
            formData.append("weightControl", $("#weightControl").prop("checked"));
            formData.append("vegan", $("#vegan").prop("checked"));
            formData.append("balanced", $("#balanced").prop("checked"));
            formData.append("sideDish", $("#sideDish").prop("checked"));
            formData.append("carb", $("#carb").val());
            formData.append("protein", $("#protein").val());
            formData.append("fat", $("#fat").val());
            formData.append("sugars", $("#sugars").val());
            formData.append("natrium", $("#natrium").val());
            formData.append("foodImg", $("#foodImg")[0].files[0]);

            $.ajax({
                url: "adminMenu/create",
                method: "POST",
                contentType: false, // Set to false when using FormData
                processData: false, // Set to false when using FormData
                data: formData,
                success: function(response) {
                    alert("추가 완료");
                    $("#addModal").css("display", "none");
                    // TODO: Update the menu list if needed
                },
                error: function() {
                    alert("추가 실패");
                }
            });
        });
        });
</script>
</body>