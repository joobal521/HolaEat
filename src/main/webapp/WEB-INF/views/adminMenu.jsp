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

                        <label for="foodImg">사진:</label>
                        <input type="file" id="foodImg" name="foodImg"
                               accept="image/png, image/jpg, image/jpeg, image/gif">
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

            // 현재 데이터를 input 요소로 변경
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
            var imageFile = $("#editImg-" + ingrId)[0].files[0]; // Get the selected image file

            // Create FormData object and append the data
            var formData = new FormData();
            formData.append("foodName", foodName);
            formData.append("allergyInfo", allergyInfo);
            formData.append("weightControl", weightControl);
            formData.append("vegan", vegan);
            formData.append("balanced", balanced);
            formData.append("sideDish", sideDish);
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





    //     $(".cancelBtn").click(function() {
    //         var row = $(this).closest("tr");
    //         var foodName = row.find(".editFoodName").val();
    //         var allergyInfo = row.find(".editAllergyInfo").prop("checked");
    //         var month = row.find(".editMonth").prop("checked");
    //
    //         row.find(".ingrName").text(ingrName);
    //         row.find(".allergy").text(allergy ? '예' : '아니오');
    //         row.find(".month").text(month ? '예' : '아니오');
    //
    //         // 수정/취소 버튼 토글
    //         $(this).hide();
    //         row.find(".editBtn").show();
    //         row.find(".updateBtn, .imgBtn").hide(); // Hide update and image upload buttons
    //     });
    //
    //
    //     $(".removeBtn").click(function() {
    //         var ingrId = $(this).data("id");
    //
    //         $.ajax({
    //             url: "adminIngr/delete/" + ingrId,
    //             method: "DELETE",
    //             success: function(response) {
    //                 // Handle success if needed
    //                 alert("삭제하기: " + ingrId);
    //             },
    //             error: function() {
    //                 alert("삭제에 실패했습니다.");
    //             }
    //         });
    //     });
    //
    //
    //
    //     $("#addBtn").click(function() {
    //         $("#addModal").css("display", "block");
    //     });
    //
    //     $(".close").click(function() {
    //         $("#addModal").css("display", "none");
    //     });
    //
    //     $(window).click(function(event) {
    //         if (event.target == document.getElementById("addModal")) {
    //             $("#addModal").css("display", "none");
    //         }
    //     });
    //
    //     // Handle the form submission
    //     $("#addForm").submit(function(event) {
    //         event.preventDefault();
    //
    //         var formData = new FormData();
    //         formData.append("ingrName", $("#ingrName").val());
    //         formData.append("month", $("#month").prop("checked"));
    //         formData.append("allergy", $("#allergy").prop("checked"));
    //         formData.append("ingrImg", $("#addImg")[0].files[0]);
    //
    //
    //         // Send the data to the backend using AJAX
    //         $.ajax({
    //             url: "adminIngr/create",
    //             method: "POST",
    //             contentType: false, // Set to false when using FormData
    //             processData: false, // Set to false when using FormData
    //             data: formData,
    //             success: function(response) {
    //                 alert("추가 완료");
    //                 $("#addModal").css("display", "none");
    //                 // TODO: Update the ingredient list if needed
    //             },
    //             error: function() {
    //                 alert("추가 실패");
    //             }
    //
    //         });
    //
    //     });
    });
</script>

</body>
