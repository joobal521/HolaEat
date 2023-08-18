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
            gender     : gender,
            age        : age,
            height     : height,
            weight     : weight,
            allergy    : allergy,
            recCalories: recCalories,
            prefer     : selectedPrefer, // 추가된 부분: prefer 값을 formData에 추가
            dislike    : selectedDislike // 추가된 부분: dislike 값을 formData에 추
        };

        $.ajax({
            type   : "POST",
            url    : "/saveDetails",
            data   : formData,
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
            error  : function (error) {
                console.error("저장 에러:", error);
                // 에러 처리
            }
        });
    });
});

function calculateCalories() {
    // 필요한 변수들을 가져옴
    var gender = document.querySelector('input[name="gender"]:checked').value;
    var age = parseFloat(document.getElementById("age").value);
    var height = parseFloat(document.getElementById("height").value);
    var weight = parseFloat(document.getElementById("weight").value);
    var allergy = document.getElementById("allergy").value;

    // 열량 계산 로직을 추가

    var baseCalories = 0;
    if (gender === "male") {
        baseCalories = (10 * weight + 6.25 * height - 5 * age + 5)*1.2;
    } else if (gender === "female") {
        baseCalories = (10 * weight + 6.25 * height - 5 * age - 161)*1.2;
    }

    // 알레르기에 따른 보정 값 추가

    if (allergy !== "") {
        baseCalories *= 0.9; // 알레르기가 있을 경우 열량을 90%로 조정
    }

    // 결과를 필드에 채움
    document.getElementById("recCalories").value = baseCalories.toFixed(2); // 소수점 2자리까지 표시

}

function fetchAndDisplayMenu(selectedNational) {
    var userRecCalories = parseInt($('#recCalories').val());

    $.get("/menus/generate", {national: selectedNational}, function (data) {
        var generatedMenus = data;
        var generatedMenusDiv = document.getElementById("generatedMenus");

        var resultHtml = "<h2>산출된 식단</h2><ul>";
        var totalCalories = 0;

        var selectedPrefer = $('#prefer').val();
        var selectedDislike = $('#dislike').val();

        generatedMenus.forEach(function (menu, index) {
            if (index !== 0) {
                resultHtml += "<hr>";
            }

            var menuTotalWeight = menu.food1Weight + menu.food2Weight + menu.food3Weight + menu.food4Weight + menu.food5Weight;


            // "선호하는 재료"와 "선호하지 않는 재료"를 모두 검사하여 필터링
            if ((selectedPrefer === "" || menu.main === selectedPrefer || menu.main2 === selectedPrefer) &&
                (selectedDislike === "" || !menu.main.includes(selectedDislike) && !menu.main2.includes(selectedDislike)) &&
                menuTotalWeight <= userRecCalories) {

                let totalCalories = menu.food1Kcal + menu.food2Kcal + menu.food3Kcal + menu.food4Kcal + menu.food5Kcal;
                let totalCarbs = menu.food1Carb + menu.food2Carb + menu.food3Carb + menu.food4Carb + menu.food5Carb;
                let totalProteins = menu.food1Protein + menu.food2Protein + menu.food3Protein + menu.food4Protein + menu.food5Protein;
                let totalFats = menu.food1Fat + menu.food2Fat + menu.food3Fat + menu.food4Fat + menu.food5Fat;

                resultHtml += "<li>" +
                    "음식1: " + menu.food1 + " (" + menu.food1Weight + "g)  </br>" +
                    "칼로리: " + menu.food1Kcal + "Kcal </br>" +
                    "탄수화물: " + menu.food1Carb + "g </br>" +
                    "단백질: " + menu.food1Protein + "g </br>" +
                    "지방: " + menu.food1Fat + "g </br>" +


                    "음식2: " + menu.food2 + " (" + menu.food2Weight + "g) </br>" +
                    "칼로리: " + menu.food2Kcal + "Kcal </br>" +
                    "탄수화물: " + menu.food2Carb + "g </br>" +
                    "단백질: " + menu.food2Protein + "g </br>" +
                    "지방: " + menu.food2Fat + "g </br>" +


                    "음식3: " + menu.food3 + " (" + menu.food3Weight + "g)</br>" +
                    "칼로리: " + menu.food3Kcal + "Kcal </br>" +
                    "탄수화물: " + menu.food3Carb + "g </br>" +
                    "단백질: " + menu.food3Protein + "g </br>" +
                    "지방: " + menu.food3Fat + "g </br>" +


                    "음식4: " + menu.food4 + " (" + menu.food4Weight + "g) </br>" +
                    "칼로리: " + menu.food4Kcal + "Kcal </br>" +
                    "탄수화물: " + menu.food4Carb + "g </br>" +
                    "단백질: " + menu.food4Protein + "g </br>" +
                    "지방: " + menu.food4Fat + "g </br>" +


                    "음식5: " + menu.food5 + " (" + menu.food5Weight + "g) </br>" +
                    "칼로리: " + menu.food5Kcal + "Kcal </br>" +
                    "탄수화물: " + menu.food5Carb + "g </br>" +
                    "단백질: " + menu.food5Protein + "g </br>" +
                    "지방: " + menu.food5Fat + "g </br>" +

                    "주재료1: " + menu.main + "</br>" +
                    "주재료2: " + menu.main2 + "</br>" +
                    "총 무게: " + menuTotalWeight + "g" + "</br>" +
                    "총 칼로리: " + totalCalories + "Kcal" +
                    "총 탄수화물: " + totalCarbs + "g" +
                    "총 단백질: " + totalProteins + "g" +
                    "총 지방: " + totalFats + "g" +

                    "</hr></li></br>";
                // new Chart(document.getElementById("bar-chart"), {
                //     type   : 'bar',
                //     data   : {
                //         labels  : ["탄수화물", "단백질", "지방"],
                //         datasets: [
                //             {
                //                 label          : "Population (millions)",
                //                 backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
                //                 data           : [totalCarbs, totalProteins, totalFats]
                //             }
                //         ]
                //     },
                //     options: {
                //         legend: {display: false},
                //         title : {
                //             display: true,
                //             text   : 'Predicted world population (millions) in 2050'
                //         }
                //     }
                // });
                //

            }
        });

        resultHtml += "</ul>";
        generatedMenusDiv.innerHTML = resultHtml;
    });
}


function fetchAndDisplayAllMenus(selectedValue) {
    var userRecCalories = parseInt($('#recCalories').val());

    var menuIdMapping = {
        "한식" : 1,
        "중식" : 2,
        "일식" : 3,
        "양식" : 4,
        "샐러드": 5
    };

    var menuId = menuIdMapping[selectedValue];

    var selectedPrefer = $('#prefer').val(); // 선택된 "선호하는 재료"의 value 가져오기
    var selectedDislike = $('#dislike').val(); // 선택된 "선호하지 않는 재료"의 value 가져오기

    $.get("/menus/generate", function (data) {
        var generatedMenus = data;
        var generatedMenusDiv = document.getElementById("generatedMenus");
        var resultHtml = "<h2>모든 식단</h2><ul>";

        generatedMenus.forEach(function (menu, index) {
            if (index !== 0) {
                resultHtml += "<hr>";
            }

            if (menu.menuId === menuId) {
                var totalWeight = menu.food1Weight + menu.food2Weight + menu.food3Weight + menu.food4Weight + menu.food5Weight;

                // "선호하는 재료"와 "선호하지 않는 재료"를 모두 검사하여 필터링
                if ((selectedPrefer === "" || menu.main === selectedPrefer || menu.main2 === selectedPrefer) &&
                    (selectedDislike === "" || !menu.main.includes(selectedDislike) && !menu.main2.includes(selectedDislike)) &&
                    totalWeight <= userRecCalories) {
                    let totalCalories = menu.food1Kcal + menu.food2Kcal + menu.food3Kcal + menu.food4Kcal + menu.food5Kcal;
                    let totalCarbs = menu.food1Carb + menu.food2Carb + menu.food3Carb + menu.food4Carb + menu.food5Carb;
                    let totalProteins = menu.food1Protein + menu.food2Protein + menu.food3Protein + menu.food4Protein + menu.food5Protein;
                    let totalFats = menu.food1Fat + menu.food2Fat + menu.food3Fat + menu.food4Fat + menu.food5Fat;

                    resultHtml += "<li>" +
                        "음식1: " + menu.food1 + " (" + menu.food1Weight + "g)  </br>" +
                        "칼로리: " + menu.food1Kcal + "Kcal </br>" +
                        "탄수화물: " + menu.food1Carb + "g </br>" +
                        "단백질: " + menu.food1Protein + "g </br>" +
                        "지방: " + menu.food1Fat + "g </br>" +


                        "음식2: " + menu.food2 + " (" + menu.food2Weight + "g) </br>" +
                        "칼로리: " + menu.food2Kcal + "Kcal </br>" +
                        "탄수화물: " + menu.food2Carb + "g </br>" +
                        "단백질: " + menu.food2Protein + "g </br>" +
                        "지방: " + menu.food2Fat + "g </br>" +


                        "음식3: " + menu.food3 + " (" + menu.food3Weight + "g)</br>" +
                        "칼로리: " + menu.food3Kcal + "Kcal </br>" +
                        "탄수화물: " + menu.food3Carb + "g </br>" +
                        "단백질: " + menu.food3Protein + "g </br>" +
                        "지방: " + menu.food3Fat + "g </br>" +


                        "음식4: " + menu.food4 + " (" + menu.food4Weight + "g) </br>" +
                        "칼로리: " + menu.food4Kcal + "Kcal </br>" +
                        "탄수화물: " + menu.food4Carb + "g </br>" +
                        "단백질: " + menu.food4Protein + "g </br>" +
                        "지방: " + menu.food4Fat + "g </br>" +


                        "음식5: " + menu.food5 + " (" + menu.food5Weight + "g) </br>" +
                        "칼로리: " + menu.food5Kcal + "Kcal </br>" +
                        "탄수화물: " + menu.food5Carb + "g </br>" +
                        "단백질: " + menu.food5Protein + "g </br>" +
                        "지방: " + menu.food5Fat + "g </br>" +

                        "주재료1: " + menu.main + "</br>" +
                        "주재료2: " + menu.main2 + "</br>" +
                        "총 무게: " + totalWeight + "g" + "</br>" +
                        "총 칼로리: " + totalCalories + "Kcal" +
                        "총 탄수화물: " + totalCarbs + "g" +
                        "총 단백질: " + totalProteins + "g" +
                        "총 지방: " + totalFats + "g" +

                        "</hr></li></br>";

                    resultHtml += "<li>" +

                        "</li>" + "</br>";



                }
            }

        });

        resultHtml += "</ul>";
        generatedMenusDiv.innerHTML = resultHtml;
    });
}


// 차트

