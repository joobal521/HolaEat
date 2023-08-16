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


function fetchAndDisplayMenu(selectedNational) {
    $.get("/menus/generate", { national: selectedNational }, function(data) {
        var generatedMenus = data; // 서버 응답 데이터
        var generatedMenusDiv = document.getElementById("generatedMenus");

        // 결과를 생성하여 웹 페이지에 표시
        var resultHtml = "<h2>산출된 식단</h2><ul>";
        generatedMenus.forEach(function(menu, index) {
            if (index !== 0) {
                resultHtml += "<hr>"; // 첫 번째 메뉴 이후에만 수평선 추가
            }

            var totalCalories = menu.food1Weight + menu.food2Weight + menu.food3Weight + menu.food4Weight + menu.food5Weight;

            resultHtml += "<li>" +
                "음식1: " + menu.food1 + " (" + menu.food1Weight + "Kcal)</br>" +
                "음식2: " + menu.food2 + " (" + menu.food2Weight + "Kcal)</br>" +
                "음식3: " + menu.food3 + " (" + menu.food3Weight + "Kcal)</br>" +
                "음식4: " + menu.food4 + " (" + menu.food4Weight + "Kcal)</br>" +
                "음식5: " + menu.food5 + " (" + menu.food5Weight + "Kcal)</br>" +
                "주재료1: " + menu.main1 + "</br>" +
                "주재료2: " + menu.main2 + "</br>" +
                "총 칼로리: " + totalCalories + "Kcal" +
                "</li></br>";
        });
        resultHtml += "</ul>";

        generatedMenusDiv.innerHTML = resultHtml;
    });
}


function fetchAndDisplayAllMenus(selectedValue) {
    var menuIdMapping = {
        "한식": 1,
        "중식": 2,
        "일식": 3,
        "양식": 4,
        "샐러드": 5
    };

    var menuId = menuIdMapping[selectedValue];

    $.get("/menus/generate", function(data) {
        var generatedMenus = data;
        var generatedMenusDiv = document.getElementById("generatedMenus");
        var resultHtml = "<h2>모든 식단</h2><ul>";

        generatedMenus.forEach(function(menu, index) {
            if (index !== 0) {
                resultHtml += "<hr>";
            }

            if (menu.menuId === menuId) {
                var totalCalories = menu.food1Weight + menu.food2Weight + menu.food3Weight + menu.food4Weight + menu.food5Weight;

                resultHtml += "<li>" +
                    "음식1: " + menu.food1 + " (" + menu.food1Weight + "Kcal)</br>" +
                    "음식2: " + menu.food2 + " (" + menu.food2Weight + "Kcal)</br>" +
                    "음식3: " + menu.food3 + " (" + menu.food3Weight + "Kcal)</br>" +
                    "음식4: " + menu.food4 + " (" + menu.food4Weight + "Kcal)</br>" +
                    "음식5: " + menu.food5 + " (" + menu.food5Weight + "Kcal)</br>" +
                    "주재료1: " + menu.main1 + "</br>" +
                    "주재료2: " + menu.main2 + "</br>" +
                    "총 칼로리: " + totalCalories + "Kcal" +
                    "</li>" + "</br>";
            }
        });

        resultHtml += "</ul>";
        generatedMenusDiv.innerHTML = resultHtml;
    });
}