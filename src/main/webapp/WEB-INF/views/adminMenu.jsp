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
                <th><button id="filterToggleAllergy">알러지</button></th>
                <th><button id="filterToggleWeightControl">체중조절식</button></th>
                <th><button id="filterToggleVegan">비건</button></th>
                <th><button id="filterToggleBalanced">균형식</button></th>
                <th><button id="filterToggleSideDish">반찬</button></th>
                <th>총 열량(kcal)</th>
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
                    <td class="foodGroup">${food.foodGroup}</td>
                    <td class="foodNational">${food.foodNational}</td>
                    <td class="allergyInfo">${food.allergyInfo ? '예' : '아니오'}</td>
                    <td class="weightControl">${food.weightControl ? '예' : '아니오'}</td>
                    <td class="vegan">${food.vegan ? '예' : '아니오'}</td>
                    <td class="balanced">${food.balanced ? '예' : '아니오'}</td>
                    <td class="sideDish">${food.sideDish ? '예' : '아니오'}</td>
                    <td class="kcal">${food.kcal}</td>
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
            var foodGroupCell = row.find(".foodGroup");
            var foodNationalCell = row.find(".foodNational");
            var allergyInfoCell = row.find(".allergyInfo");
            var weightControlCell = row.find(".weightControl");
            var veganCell = row.find(".vegan");
            var balancedCell = row.find(".balanced");
            var sideDishCell = row.find(".sideDish");
            var kcalCell = row.find(".kcal");
            var carbCell = row.find(".carb");
            var proteinCell = row.find(".protein");
            var fatCell = row.find(".fat");
            var sugarsCell = row.find(".sugars");
            var natriumCell = row.find(".natrium");

            kcalCell.html("<input type='number' class='editKcal' value='" + kcalCell.text() + "'>");
            carbCell.html("<input type='number' class='editCarb' value='" + carbCell.text() + "'>");
            proteinCell.html("<input type='number' class='editProtein' value='" + proteinCell.text() + "'>");
            fatCell.html("<input type='number' class='editFat' value='" + fatCell.text() + "'>");
            sugarsCell.html("<input type='number' class='editSugars' value='" + sugarsCell.text() + "'>");
            natriumCell.html("<input type='number' class='editNatrium' value='" + natriumCell.text() + "'>");
            foodNameCell.html("<input type='text' class='editFoodName' value='" + foodNameCell.text() + "'>");
            foodGroupCell.html("<input type='text' class='editFoodGroup' value='" + foodGroupCell.text() + "'>");
            foodNationalCell.html("<input type='text' class='editFoodNational' value='" + foodNationalCell.text() + "'>");
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
            var foodGroup = row.find(".editFoodGroup").val(); // Retrieve value from input
            var foodNational = row.find(".editFoodNational").val();
            var allergyInfo = row.find(".editAllergyInfo").prop("checked");
            var weightControl = row.find(".editWeightControl").prop("checked");
            var vegan = row.find(".editVegan").prop("checked");
            var balanced = row.find(".editBalanced").prop("checked");
            var sideDish = row.find(".editSideDish").prop("checked");
            var kcal = row.find(".editKcal").val();
            var carb = row.find(".editCarb").val();
            var protein = row.find(".editProtein").val();
            var fat = row.find(".editFat").val();
            var sugars = row.find(".editSugars").val();
            var natrium = row.find(".editNatrium").val();
            var imageFile = $("#editImg-" + foodId)[0].files[0];

            var formData = new FormData();
            formData.append("foodName", foodName);
            formData.append("foodGroup", foodGroup); // Append the value
            formData.append("foodNational", foodNational); // Append the value
            formData.append("allergyInfo", allergyInfo);
            formData.append("weightControl", weightControl);
            formData.append("vegan", vegan);
            formData.append("balanced", balanced);
            formData.append("sideDish", sideDish);
            formData.append("kcal", kcal);
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

            // Restore original values from data attributes
            var originalFoodName = row.find(".editFoodName").val();
            var originalFoodGroup = row.find(".editFoodGroup").val();
            var originalFoodNational = row.find(".editFoodNational").val();
            var originalAllergyInfo = row.find(".editAllergyInfo").prop("checked");
            var originalWeightControl = row.find(".editWeightControl").prop("checked");
            var originalVegan = row.find(".editVegan").prop("checked");
            var originalBalanced = row.find(".editBalanced").prop("checked");
            var originalSideDish = row.find(".editSideDish").prop("checked");
            var originalKcal = row.find(".editKcal").val();
            var originalCarb = row.find(".editCarb").val();
            var originalProtein = row.find(".editProtein").val();
            var originalFat = row.find(".editFat").val();
            var originalSugars = row.find(".editSugars").val();
            var originalNatrium = row.find(".editNatrium").val();

            // Set input values to original values
            row.find(".foodName").text(originalFoodName);
            row.find(".foodGroup").text(originalFoodGroup);
            row.find(".foodNational").text(originalFoodNational);
            row.find(".allergyInfo").text(originalAllergyInfo ? '예' : '아니오');
            row.find(".weightControl").text(originalWeightControl? '예' : '아니오');
            row.find(".vegan").text( originalVegan? '예' : '아니오');
            row.find(".balanced").text(originalBalanced? '예' : '아니오');
            row.find(".sideDish").text( originalSideDish? '예' : '아니오');
            row.find(".kcal").text(originalKcal);
            row.find(".carb").text(originalCarb);
            row.find(".protein").text(originalProtein);
            row.find(".fat").text(originalFat);
            row.find(".sugars").text(originalSugars);
            row.find(".natrium").text(originalNatrium);

            // // Hide input fields
            // row.find(".editFoodName, .editFoodGroup, .editFoodNational, .editCarb, .editProtein, .editFat, .editSugars, .editNatrium").hide();
            //
            // // Show original text cells
            // row.find(".foodName, .foodGroup, .foodNational, .carb, .protein, .fat, .sugars, .natrium").show();

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

        $("#addForm").submit(function(event) {
            event.preventDefault();

            var formData = new FormData();
            formData.append("foodName", $("#foodName").val());
            formData.append("foodNational", $("#foodNational").val());
            formData.append("foodGroup", $("#foodGroup").val());
            formData.append("allergyInfo", $("#allergy").prop("checked"));
            formData.append("weightControl", $("#weightControl").prop("checked"));
            formData.append("vegan", $("#vegan").prop("checked"));
            formData.append("balanced", $("#balanced").prop("checked"));
            formData.append("sideDish", $("#sideDish").prop("checked"));
            formData.append("kcal", $("#kcal").val());
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

    // $(document).ready(function() {
    //     $("#filterToggleAllergy").click(function() {
    //         toggleFilter(".allergyInfo", "예", $(this));
    //     });
    //
    //     $("#filterToggleWeightControl").click(function() {
    //         toggleFilter(".weightControl", "예", $(this));
    //     });
    //     $("#filterToggleVegan").click(function() {
    //         toggleFilter(".vegan", "예", $(this));
    //     });
    //     $("#filterToggleBalanced").click(function() {
    //         toggleFilter(".balanced", "예", $(this));
    //     });
    //     $("#filterToggleSideDish").click(function() {
    //         toggleFilter(".sideDish", "예", $(this));
    //     });
    //
    //
    //     function toggleFilter(columnClass, targetValue, buttonElement) {
    //         var isActive = buttonElement.hasClass("active");
    //
    //         $(".admin-foodList tr").each(function() {
    //             var cell = $(this).find(columnClass);
    //             var cellValue = cell.text();
    //
    //             if (isActive && cellValue !== targetValue) {
    //                 $(this).hide();
    //             } else {
    //                 $(this).show();
    //             }
    //         });
    //
    //         buttonElement.toggleClass("active");
    //     }
    //
    // });
    $(document).ready(function() {
        var filterStates = {
            ".allergyInfo": "all",
            ".weightControl": "all",
            ".vegan": "all",
            ".balanced": "all",
            ".sideDish": "all"
        };

        $("#filterToggleAllergy").click(function() {
            toggleFilter(".allergyInfo", $(this));
        });

        $("#filterToggleWeightControl").click(function() {
            toggleFilter(".weightControl", $(this));
        });

        $("#filterToggleVegan").click(function() {
            toggleFilter(".vegan", $(this));
        });

        $("#filterToggleBalanced").click(function() {
            toggleFilter(".balanced", $(this));
        });

        $("#filterToggleSideDish").click(function() {
            toggleFilter(".sideDish", $(this));
        });

        function toggleFilter(columnClass, buttonElement) {
            var currentState = filterStates[columnClass];

            if (currentState === "all") {
                filterStates[columnClass] = "yes";
            } else if (currentState === "yes") {
                filterStates[columnClass] = "no";
            } else if (currentState === "no") {
                filterStates[columnClass] = "all";
            }

            applyFilters();
            updateButtonState(columnClass, buttonElement);
        }

        function applyFilters() {
            $(".admin-foodList tr").each(function() {
                var shouldShow = true;

                for (var columnClass in filterStates) {
                    if (filterStates.hasOwnProperty(columnClass)) {
                        var cell = $(this).find(columnClass);
                        var cellValue = cell.text();
                        var currentState = filterStates[columnClass];

                        if (currentState === "yes" && cellValue !== "예") {
                            shouldShow = false;
                            break;
                        } else if (currentState === "no" && cellValue !== "아니오") {
                            shouldShow = false;
                            break;
                        }
                    }
                }

                if (shouldShow) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
        }

        // function updateButtonState(columnClass, buttonElement) {
        //     var currentState = filterStates[columnClass];
        //     var buttonText = "";
        //
        //     if (currentState === "all") {
        //         buttonText = "예/아니오 모두 보기";
        //     } else if (currentState === "yes") {
        //         buttonText = "예 만 보기";
        //     } else if (currentState === "no") {
        //         buttonText = "아니오 만 보기";
        //     }
        //
        //     buttonElement.text(buttonText);
        // }

        $(document).ready(function() {
            var sortOrder = {
                "kcal": "none",
                "carb": "none",
                "protein": "none",
                "fat": "none",
                "sugars": "none",
                "natrium": "none"
            };

            $("#sortKcalAsc").click(function() {
                sortTable("kcal", "asc");
            });

            $("#sortKcalDesc").click(function() {
                sortTable("kcal", "desc");
            });

            $("#sortCarbAsc").click(function() {
                sortTable("carb", "asc");
            });

            $("#sortCarbDesc").click(function() {
                sortTable("carb", "desc");
            });

            // Add similar click handlers for other nutrients...

            function sortTable(nutrient, order) {
                var rows = $(".admin-foodList tr").get();

                rows.sort(function(row1, row2) {
                    var value1 = parseFloat($(row1).find("." + nutrient).text());
                    var value2 = parseFloat($(row2).find("." + nutrient).text());

                    if (order === "asc") {
                        return value1 - value2;
                    } else {
                        return value2 - value1;
                    }
                });

                $(".admin-foodList").empty();
                $.each(rows, function(index, row) {
                    $(".admin-foodList").append(row);
                });

                // Update sortOrder
                for (var key in sortOrder) {
                    if (sortOrder.hasOwnProperty(key)) {
                        sortOrder[key] = "none";
                    }
                }
                sortOrder[nutrient] = order;
            }
        });

    });



</script>
</body>