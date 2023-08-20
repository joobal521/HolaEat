$(document).ready(function() {
    $(".editBtn").click(function() {
        var row = $(this).closest("tr");
        var ingrNameCell = row.find(".ingrName");
        var allergyCell = row.find(".allergy");
        var monthCell = row.find(".month");

        // 현재 데이터를 input 요소로 변경
        ingrNameCell.html("<input type='text' class='editIngrName' value='" + ingrNameCell.text() + "'>");
        allergyCell.html("<input type='checkbox' class='editAllergy' " + (allergyCell.text() === "예" ? "checked" : "") + ">");
        monthCell.html("<input type='checkbox' class='editMonth' " + (monthCell.text() === "예" ? "checked" : "") + ">");

        // 수정/취소 버튼 토글
        $(this).hide();
        row.find(".updateBtn, .cancelBtn,.imgBtn").show();
    });


    $(".updateBtn").click(function() {
        var row = $(this).closest("tr");
        var ingrId = $(this).data("id");
        var ingrName = row.find(".editIngrName").val();
        var allergy = row.find(".editAllergy").prop("checked");
        var month = row.find(".editMonth").prop("checked");
        var imageFile = $("#editImg-" + ingrId)[0].files[0]; // Get the selected image file

        // Create FormData object and append the data
        var formData = new FormData();
        formData.append("ingrName", ingrName);
        formData.append("allergy", allergy);
        formData.append("month", month);
        if (imageFile != null) {
            formData.append("ingrImg", imageFile);
        }

        // Send the data to the backend using AJAX
        $.ajax({
            url: "adminIngr/" + ingrId,
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
        var ingrName = row.find(".editIngrName").val();
        var allergy = row.find(".editAllergy").prop("checked");
        var month = row.find(".editMonth").prop("checked");

        row.find(".ingrName").text(ingrName);
        row.find(".allergy").text(allergy ? '예' : '아니오');
        row.find(".month").text(month ? '예' : '아니오');

        // 수정/취소 버튼 토글
        $(this).hide();
        row.find(".editBtn").show();
        row.find(".updateBtn, .imgBtn").hide(); // Hide update and image upload buttons
    });


    $(".removeBtn").click(function() {
        var ingrId = $(this).data("id");

        $.ajax({
            url: "adminIngr/delete/" + ingrId,
            method: "DELETE",
            success: function(response) {
                // Handle success if needed
                alert("삭제하기: " + ingrId);
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

    // Handle the form submission
    $("#addForm").submit(function(event) {
        event.preventDefault();

        var formData = new FormData();
        formData.append("ingrName", $("#ingrName").val());
        formData.append("month", $("#month").prop("checked"));
        formData.append("allergy", $("#allergy").prop("checked"));
        formData.append("ingrImg", $("#addImg")[0].files[0]);


        // Send the data to the backend using AJAX
        $.ajax({
            url: "adminIngr/create",
            method: "POST",
            contentType: false, // Set to false when using FormData
            processData: false, // Set to false when using FormData
            data: formData,
            success: function(response) {
                alert("추가 완료");
                $("#addModal").css("display", "none");
                // TODO: Update the ingredient list if needed
                window.location.href="admin"
            },
            error: function() {
                // alert("추가 실패");
            }

        });

    });
});

$(document).ready(function() {
    $("#filterToggleAllergy").click(function() {
        toggleFilter(".allergy", "예", $(this));
    });

    $("#filterToggleMonth").click(function() {
        toggleFilter(".month", "예", $(this));
    });


    function toggleFilter(columnClass, targetValue, buttonElement) {
        var isActive = buttonElement.hasClass("active");

        $(".admin-ingrList tr").each(function() {
            var cell = $(this).find(columnClass);
            var cellValue = cell.text();

            if (isActive && cellValue !== targetValue) {
                $(this).hide();
            } else {
                $(this).show();
            }
        });

        buttonElement.toggleClass("active");
    }

});