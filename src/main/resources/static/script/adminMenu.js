$(document).ready(function() {
    $("#loadMoreBtn").click(function() {
        var currentPage = $(this).data("currentPage");
        loadMoreData(currentPage);
    });


    $(".admin-menu").on("click", ".editBtn", function() {
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
        var foodWeightCell = row.find(".foodWeight");
        var carbCell = row.find(".carb");
        var proteinCell = row.find(".protein");
        var fatCell = row.find(".fat");
        var sugarsCell = row.find(".sugars");
        var natriumCell = row.find(".natrium");

        kcalCell.html("<input type='number' class='editKcal' value='" + kcalCell.text() + "'>");
        foodWeightCell.html("<input type='number' class='editFoodWeight' value='" + foodWeightCell.text() + "'>");
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
        var foodWeight = row.find(".editFoodWeight").val();
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
        formData.append("foodWeight", foodWeight);
        formData.append("carb", carb);
        formData.append("protein", protein);
        formData.append("fat", fat);
        formData.append("sugars", sugars);
        formData.append("natrium", natrium);

        console.log(imageFile)
        if (imageFile != null) {
            formData.append("foodImg", imageFile);
        }

        // Send the data to the backend using AJAX
        $.ajax({
            url: "adminMenu/" + foodId,
            method: "PUT",
            contentType: false,
            processData: false,
            data: formData,
            success: function() {
                // $(".section").html(response);
                window.location.href="admin"
            },
            error: function() {
                // alert("데이터 업데이트에 실패했습니다.");
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
        var originalFoodWeight = row.find(".editFoodWeight").val();
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
        row.find(".foodWeight").text(originalFoodWeight);
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
                window.location.href="admin"
            },
            error: function() {
                // alert("삭제에 실패했습니다.");
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
        formData.append("foodWeight", $("#foodWeight").val());
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
                window.location.href="admin"
            },
            error: function() {
                // alert("추가 실패");
            }
        });
    });

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
});

$(document).ready(function() {

    $(".nutrition-cell").addClass("hide-nutrition");

    $("#toggleNutrition").click(function() {
        $(".nutrition-cell").toggleClass("hide-nutrition");

        var buttonText = $(".nutrition-cell").hasClass("hide-nutrition") ? "영양소 보기" : "영양소 숨기기";
        $(this).text(buttonText);
    });
});