function editIngr(element) {
    const id = element.parentNode.parentNode.id;
    const tr = $(`#${id}`);
    // const ingrName = tr.children('.ingrName');
    const ingrName = document.getElementById("input-ingrName-" + id);
    const allergySelect = document.getElementById("allergy-val-" + id);
    const monthSelect = document.getElementById("month-val-" + id);

    allergySelect.disabled = false;
    monthSelect.disabled = false;
    ingrName.readOnly = false;

    // 입력 가능한 상태에서 사용자 입력을 받도록 합니다.
    tr.find(".updateBtn, .cancelBtn, .imgBtn").show();
    tr.find(".editBtn").hide();
}

function updateIngr(element) {
    const id = element.parentNode.parentNode.id;
    const tr = $(`#${id}`);
    const ingrName = tr.find('.ingrName-input').val();
    const allergy = tr.find('.allergy-sel').val() === '예' ? true : false;
    const month = tr.find('.month-sel').val() === '예' ? true : false;
    var imageFile = $("#editImg-" + id)[0].files[0];

    console.log(allergy)
    console.log(month)

    var formData = new FormData();
    formData.append("ingrName", ingrName);
    formData.append("allergy", allergy);
    formData.append("month", month);
    if (imageFile != null) {
        formData.append("ingrImg", imageFile);
    }

    $.ajax({
        url: "adminIngr/" + id,
        method: "POST",
        contentType: false,
        processData: false,
        data: formData,
        success: function () {
            // $(".section").html(response);
            Admin.pageRelocate("adminIngr");
        },
        error: function () {
            // alert("데이터 업데이트에 실패했습니다.");
        }
    });
}

function cancelEditIngr(element) {
    const id = element.parentNode.parentNode.id;
    var row = $(element).closest("tr");
    const ingrNameInput = document.getElementById("input-ingrName-" + id);
    var dataIdValue = ingrNameInput.getAttribute("data-id");
    const allergySelect = document.getElementById("allergy-val-" + id);
    const monthSelect = document.getElementById("month-val-" + id);

    allergySelect.value = allergySelect.getAttribute("data-initial-value");
    monthSelect.value = monthSelect.getAttribute("data-initial-value");
    allergySelect.disabled = true;
    monthSelect.disabled = true;

    document.getElementById("input-ingrName-" + id).value = dataIdValue;
    ingrNameInput.readOnly = true;

    // 수정/취소 버튼 토글
    $(this).hide();
    row.find(".editBtn").show();
    row.find(".updateBtn, .imgBtn, .cancelBtn").hide();
}

function deleteIngr(element){
    var ingrId = element.getAttribute("data-id");

    console.log(ingrId)
    $.ajax({
        url: "adminIngr/delete/" + ingrId,
        method: "DELETE",
        success: function (response) {

            alert("삭제하기: " + ingrId);
            Admin.pageRelocate("adminIngr");
        },
        error: function () {
            // alert("삭제에 실패했습니다.");
        }
    });
}



var filterStates = {
    ".allergy-sel": "all",
    ".month-sel":"all"
};

function allergyFilter(){
    toggleFilter(".allergy-sel",this)
}


function monthFilter(){
    toggleFilter(".month-sel",this)
}

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
}

function applyFilters() {
    $(".admin-ingrList tr").each(function() {
        var shouldShow = true;

        for (var columnClass in filterStates) {
            if (filterStates.hasOwnProperty(columnClass)) {
                var cell = $(this).find(columnClass);
                var cellValue = cell.attr("data-initial-value");
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

function addIngr(){
    var formData = new FormData();
    formData.append("ingrName", $("#ingrName").val());
    formData.append("month", $("#month").prop("checked"));
    formData.append("allergy", $("#allergy").prop("checked"));
    formData.append("ingrImg", $("#addImg")[0].files[0]);

    $.ajax({
        url: "adminIngr/create",
        method: "POST",
        contentType: false,
        processData: false,
        data: formData,
        success: function (response) {
            alert("추가 완료");
            $("#addModal").css("display", "none");
            Admin.pageRelocate("adminIngr"); // adminIngr 페이지 로드
        },
        error: function () {
            // alert("추가 실패");
        }
    });
}



function addModal() {
    var modal = document.getElementById("addModal");
    modal.style.display = "block";
    var thead = document.querySelector("thead tr");

    // 모달 열기 시 thead를 static으로 변경
    thead.style.position = "static";
}

        // 모달 바깥을 클릭하면 모달 창을 닫습니다.
window.onclick = function(event) {
    var modal = document.getElementById("addModal");
    var thead = document.querySelector("thead tr");

    if (event.target == modal) {
        modal.style.display = "none";
        thead.style.position = "sticky";
        thead.style.top = "0";
    }
}

function closeModal() {
    var modal = document.getElementById("addModal");
    var thead = document.querySelector("thead tr");
    modal.style.display = "none";
    thead.style.position = "sticky";
    thead.style.top = "0";
}