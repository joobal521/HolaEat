$(document).ready(function () {
    $('#save_btn').click(function () {
        var gender = $('input[name="gender"]:checked').val();
        var age = $('#age').val();
        var height = $('#height').val();
        var weight = $('#weight').val();
        var allergy = $('#allergy').val();
        var recCalories = $('#recCalories').val();
        var selectedPrefer = $('#prefer').val();
        var selectedDislike = $('#dislike').val();

        var formData = {
            gender: gender,
            age: age,
            height: height,
            weight: weight,
            allergy: allergy,
            recCalories: recCalories,
            prefer: selectedPrefer, // 추가된 부분: prefer 값을 formData에 추가
            dislike: selectedDislike // 추가된 부분: dislike 값을 formData에 추
        };

        $.ajax({
            type: "POST",
            url: "/saveDetails",
            data: formData,
            success: function (data) {
                // 서버 응답 처리
                console.log("저장 성공:", data);
                // 필요한 업데이트 작업 수행
                $('#age').val(data.age);
                $('#height').val(data.height);
                $('#weight').val(data.weight);
                $('#allergy').val(data.allergy);
                $('#recCalories').val(data.recCalories);

                alert("저장에 성공하였습니다.");
            },
            error: function (error) {
                console.error("저장 에러:", error);
                // 에러 처리
            }
        });
    });
});


function fetchAndDisplayMenu() {
    $.get("/menus/generate", function(data) {
        var generatedMenus = data; // 서버에서 받은 JSON 데이터
        var generatedMenusDiv = document.getElementById("generatedMenus");

        // 결과를 생성하여 웹 페이지에 표시
        var resultHtml = "<h2>산출된 식단</h2><ul>";
        generatedMenus.forEach(function(menu) {
            resultHtml += "<li>메뉴 ID: " + menu.menuId +
                ", 알레르기 정보: " + menu.allergy +
                ", 음식1: " + menu.food1 +
                ", 음식2: " + menu.food2 +
                ", 음식3: " + menu.food3 +
                ", 음식4: " + menu.food4 +
                ", 음식5: " + menu.food5 +
                ", 메인: " + menu.main +
                ", 메인2: " + menu.main2 +
                "</li>";
        });
        resultHtml += "</ul>";

        generatedMenusDiv.innerHTML = resultHtml;
    });
}
