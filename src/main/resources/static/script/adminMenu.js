$(document).ready(function () {

    $(".nutrition-cell").addClass("hide-nutrition");

    $("#toggleNutrition").click(function () {
        $(".nutrition-cell").toggleClass("hide-nutrition");

        var buttonText = $(".nutrition-cell").hasClass("hide-nutrition") ? "영양소 보기" : "영양소 숨기기";
        $(this).text(buttonText);
    });
});

function editFood(element) {
    const id = element.parentNode.parentNode.id;
    const tr = $(`#${id}`);

    const foodNameInput = document.getElementById(`input-foodName-${id}`);
    const foodGroupInput = document.getElementById(`input-foodGroup-${id}`);
    const foodNationalInput = document.getElementById(`input-foodNational-${id}`);
    const allergySelect = document.getElementById(`allergyInfo-val-${id}`);
    const weightControlSelect = document.getElementById(`weightControl-val-${id}`);
    const veganSelect = document.getElementById(`vegan-val-${id}`);
    const balancedSelect = document.getElementById(`balanced-val-${id}`);
    const sideDishSelect = document.getElementById(`sideDish-val-${id}`);
    const kcalInput = document.getElementById(`input-kcal-${id}`);
    const foodWeightInput = document.getElementById(`input-foodWeight-${id}`);
    const carbInput = document.getElementById(`input-carb-${id}`);
    const proteinInput = document.getElementById(`input-protein-${id}`);
    const fatInput = document.getElementById(`input-fat-${id}`);
    const sugarsInput = document.getElementById(`input-sugars-${id}`);
    const natriumInput = document.getElementById(`input-natrium-${id}`);

    foodNameInput.readOnly = false;
    foodGroupInput.readOnly = false;
    foodNationalInput.readOnly = false;
    allergySelect.disabled = false;
    weightControlSelect.disabled = false;
    veganSelect.disabled = false;
    balancedSelect.disabled = false;
    sideDishSelect.disabled = false;
    kcalInput.readOnly = false;
    foodWeightInput.readOnly = false;
    carbInput.readOnly = false;
    proteinInput.readOnly = false;
    fatInput.readOnly = false;
    sugarsInput.readOnly = false;
    natriumInput.readOnly = false;

    // 수정/취소 버튼 토글
    tr.find(".updateBtn, .cancelBtn, .imgBtn").show();
    tr.find(".editBtn").hide();
}


function updateFood(element) {
    const id = element.parentNode.parentNode.id;
    const tr = $(`#${id}`);

    // 음식 데이터 가져오기
    const foodName = document.getElementById(`input-foodName-${id}`).value;
    const foodGroup = document.getElementById(`input-foodGroup-${id}`).value;
    const foodNational = document.getElementById(`input-foodNational-${id}`).value;
    const allergy = document.getElementById(`allergyInfo-val-${id}`).value === '예' ? true : false;
    const weightControl = document.getElementById(`weightControl-val-${id}`).value === '예' ? true : false;
    const vegan = document.getElementById(`vegan-val-${id}`).value === '예' ? true : false;
    const balanced = document.getElementById(`balanced-val-${id}`).value === '예' ? true : false;
    const sideDish = document.getElementById(`sideDish-val-${id}`).value === '예' ? true : false;
    const kcal = document.getElementById(`input-kcal-${id}`).value;
    const foodWeight = document.getElementById(`input-foodWeight-${id}`).value;
    const carb = document.getElementById(`input-carb-${id}`).value;
    const protein = document.getElementById(`input-protein-${id}`).value;
    const fat = document.getElementById(`input-fat-${id}`).value;
    const sugars = document.getElementById(`input-sugars-${id}`).value;
    const natrium = document.getElementById(`input-natrium-${id}`).value;

    // 파일 업로드 관련 처리 (이미지 파일 선택)
    const imageFile = document.getElementById(`editImg-${id}`).files[0];

    // FormData를 사용하여 데이터 전송 준비
    const formData = new FormData();
    formData.append("foodName", foodName);
    formData.append("foodGroup", foodGroup);
    formData.append("foodNational", foodNational);
    formData.append("allergyInfo", allergy);
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

    if (imageFile) {
        formData.append("foodImg", imageFile);
    }

    // AJAX를 사용하여 서버로 데이터 전송
    $.ajax({
        url: `adminMenu/${id}`,
        method: "POST",
        contentType: false,
        processData: false,
        data: formData,
        success: function () {
            // 성공적으로 업데이트된 경우 원하는 동작 수행 (예: 화면 새로고침)
            Admin.pageRelocate("adminMenu");
        },
        error: function () {
            // 실패 시 처리 (예: 오류 메시지 표시)
            alert("데이터 업데이트에 실패했습니다.");
        }
    });
}


function cancelEditFood(element) {
    const id = element.getAttribute("data-id");
    const row = document.getElementById(id);

    const foodNameInput = document.getElementById(`input-foodName-${id}`);
    const foodGroupInput = document.getElementById(`input-foodGroup-${id}`);
    const foodNationalInput = document.getElementById(`input-foodNational-${id}`);
    const allergySelect = document.getElementById(`allergyInfo-val-${id}`);
    const weightControlSelect = document.getElementById(`weightControl-val-${id}`);
    const veganSelect = document.getElementById(`vegan-val-${id}`);
    const balancedSelect = document.getElementById(`balanced-val-${id}`);
    const sideDishSelect = document.getElementById(`sideDish-val-${id}`);
    const kcalInput = document.getElementById(`input-kcal-${id}`);
    const foodWeightInput = document.getElementById(`input-foodWeight-${id}`);
    const carbInput = document.getElementById(`input-carb-${id}`);
    const proteinInput = document.getElementById(`input-protein-${id}`);
    const fatInput = document.getElementById(`input-fat-${id}`);
    const sugarsInput = document.getElementById(`input-sugars-${id}`);
    const natriumInput = document.getElementById(`input-natrium-${id}`);

    // Retrieve the initial values
    const initialFoodName = foodNameInput.getAttribute("data-initial-value");
    const initialFoodGroup = foodGroupInput.getAttribute("data-initial-value");
    const initialFoodNational = foodNationalInput.getAttribute("data-initial-value");
    const initialAllergy = allergySelect.getAttribute("data-initial-value");
    const initialWeightControl = weightControlSelect.getAttribute("data-initial-value");
    const initialVegan = veganSelect.getAttribute("data-initial-value");
    const initialBalanced = balancedSelect.getAttribute("data-initial-value");
    const initialSideDish = sideDishSelect.getAttribute("data-initial-value");
    const initialKcal = kcalInput.getAttribute("data-initial-value");
    const initialFoodWeight = foodWeightInput.getAttribute("data-initial-value");
    const initialCarb = carbInput.getAttribute("data-initial-value");
    const initialProtein = proteinInput.getAttribute("data-initial-value");
    const initialFat = fatInput.getAttribute("data-initial-value");
    const initialSugars = sugarsInput.getAttribute("data-initial-value");
    const initialNatrium = natriumInput.getAttribute("data-initial-value");

    // Set the input values to their initial values
    foodNameInput.value = initialFoodName;
    foodGroupInput.value = initialFoodGroup;
    foodNationalInput.value = initialFoodNational;
    allergySelect.value = initialAllergy;
    weightControlSelect.value = initialWeightControl;
    veganSelect.value = initialVegan;
    balancedSelect.value = initialBalanced;
    sideDishSelect.value = initialSideDish;
    kcalInput.value = initialKcal;
    foodWeightInput.value = initialFoodWeight;
    carbInput.value = initialCarb;
    proteinInput.value = initialProtein;
    fatInput.value = initialFat;
    sugarsInput.value = initialSugars;
    natriumInput.value = initialNatrium;

    // Disable input fields and selects
    foodNameInput.readOnly = true;
    foodGroupInput.readOnly = true;
    foodNationalInput.readOnly = true;
    allergySelect.disabled = true;
    weightControlSelect.disabled = true;
    veganSelect.disabled = true;
    balancedSelect.disabled = true;
    sideDishSelect.disabled = true;
    kcalInput.readOnly = true;
    foodWeightInput.readOnly = true;
    carbInput.readOnly = true;
    proteinInput.readOnly = true;
    fatInput.readOnly = true;
    sugarsInput.readOnly = true;
    natriumInput.readOnly = true;

    // Toggle buttons
    row.querySelector(".editBtn").style.display = "block";
    row.querySelector(".updateBtn").style.display = "none";
    row.querySelector(".cancelBtn").style.display = "none";
    row.querySelector(".imgBtn").style.display = "none";
}



function deleteFood(element) {
    const id = element.parentNode.parentNode.id;

    $.ajax({
        url: "adminMenu/delete/" + id,
        method: "DELETE",
        success: function (response) {
            // Handle success if needed
            alert("삭제하기: " + id);
            Admin.pageRelocate("adminMenu");
        },
        error: function () {
            // alert("삭제에 실패했습니다.");
        }
    });
}

function addFood() {

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
        success: function (response) {
            alert("추가 완료");
            $("#addModal").css("display", "none");
            Admin.pageRelocate("adminMenu");
        },
        error: function () {
            // alert("추가 실패");
        }
    });
}

var foodFilterStates = {
    ".allergyInfo-sel": "all",
    ".weightControl-sel": "all",
    ".vegan-sel": "all",
    ".balanced-sel": "all",
    ".sideDish-sel": "all"
};

function allergyInfoFilter(){
    toggleFoodFilter(".allergyInfo-sel",this)
}
function weightControlFilter(){
    toggleFoodFilter(".weightControl-sel",this)
}

function veganFilter(){
    toggleFoodFilter(".vegan-sel",this)
}

function sideDishFilter(){
    toggleFoodFilter(".sideDish-sel",this)
}
function balancedFilter(){
    toggleFoodFilter(".balanced-sel",this)
}

function toggleFoodFilter(columnClass, buttonElement) {
    var currentState = foodFilterStates[columnClass];

    if (currentState === "all") {
        foodFilterStates[columnClass] = "yes";
    } else if (currentState === "yes") {
        foodFilterStates[columnClass] = "no";
    } else if (currentState === "no") {
        foodFilterStates[columnClass] = "all";
    }

    applyFoodFilters();
}

function applyFoodFilters() {
    $(".admin-foodList tr").each(function() {
        var shouldShow = true;

        for (var columnClass in foodFilterStates) {
            if (foodFilterStates.hasOwnProperty(columnClass)) {
                var cell = $(this).find(columnClass);
                var cellValue = cell.attr("data-initial-value");
                var currentState = foodFilterStates[columnClass];

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