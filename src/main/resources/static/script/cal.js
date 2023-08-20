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
            type   : "PUT",
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
    var menuType = document.getElementById("menu_type").value; // 식단 종류 값 가져오기

    // 열량 계산 로직을 추가

    var baseCalories = 0;
    if (gender === "male") {
        baseCalories = (10 * weight + 6.25 * height - 5 * age + 5) * 1.55;
    } else if (gender === "female") {
        baseCalories = (10 * weight + 6.25 * height - 5 * age - 161) * 1.55;
    }

    // 알레르기에 따른 보정 값 추가

    if (allergy !== "0") {
        baseCalories *= 0.9; // 알레르기가 있을 경우 열량을 90%로 조정
    }

    // 체중조절식이 선택된 경우 500kcal 빼기
    if (menuType === "2") {
        baseCalories -= 500;
    }

    // 결과를 필드에 채움
    document.getElementById("recCalories").value = baseCalories.toFixed(2); // 소수점 2자리까지 표시
}

function generateMenuInfo(menu, index) {
    var menuInfoHtml = "";

    for (var i = 1; i <= 5; i++) {
        var foodName = menu['food' + i];
        var foodWeight = menu['food' + i + 'Weight'];
        var foodKcal = menu['food' + i + 'Kcal'];
        var foodCarb = menu['food' + i + 'Carb'];
        var foodProtein = menu['food' + i + 'Protein'];
        var foodFat = menu['food' + i + 'Fat'];

        menuInfoHtml += `${foodName} (${foodWeight}g)  ` +
            `<button class='toggle-btn'>Nutrition</button>` +
            `<div class='nutritional-info hidden'>` +
            `칼로리: ${foodKcal}Kcal <br>` +
            `탄수화물: ${foodCarb}g <br>` +
            `단백질: ${foodProtein}g <br>` +
            `지방: ${foodFat}g <br>` +
            `</div>`;
    }

    return menuInfoHtml;
}

function fetchAndDisplayMenu(selectedNational) {
    var userRecCalories = parseInt($('#recCalories').val());

    $.get("/menus/generate", {national: selectedNational}, function (data) {
        var generatedMenus = data;
        var generatedMenusDiv = document.getElementById("generatedMenus");

        var resultHtml = "<h2>회원님만을 위한 식단입니다</h2><ul>";

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


                var totalCalories = menu.food1Kcal + menu.food2Kcal + menu.food3Kcal + menu.food4Kcal + menu.food5Kcal;
                var totalCarbs = menu.food1Carb + menu.food2Carb + menu.food3Carb + menu.food4Carb + menu.food5Carb;
                var totalProteins = menu.food1Protein + menu.food2Protein + menu.food3Protein + menu.food4Protein + menu.food5Protein;
                var totalFats = menu.food1Fat + menu.food2Fat + menu.food3Fat + menu.food4Fat + menu.food5Fat;

                var menuInfoHtml = generateMenuInfo(menu, index);

                resultHtml += "<li>식단정보<br>" + menuInfoHtml + "<br>" +
                    "주재료1: " + menu.main + "<br>" +
                    "주재료2: " + menu.main2 + "<br>" +
                    "총 무게: " + menuTotalWeight + "g" + "<br>" +
                    "총 칼로리: " + totalCalories + "Kcal" + "<br>" +
                    "총 탄수화물: " + totalCarbs + "g" + "<br>" +
                    "총 단백질: " + totalProteins + "g" + "<br>" +
                    "총 지방: " + totalFats + "g" + "<br>" +
                    "</li></hr></br></br></br></hr>";
            }
        });

        resultHtml += "</ul>";
        generatedMenusDiv.innerHTML = resultHtml;

        var toggleButtons = document.querySelectorAll('.toggle-btn');
        toggleButtons.forEach(function (button) {
            button.addEventListener('click', function () {
                var nutritionalInfo = this.nextElementSibling;
                nutritionalInfo.classList.toggle('hidden'); // Toggle the 'hidden' class
            });
        });
    });
}




function fetchAndDisplayAllMenus(selectedValue) {
    var userRecCalories = parseInt($('#recCalories').val());

    // 메뉴 카테고리와 ID 매핑
    var menuIdMapping = {
        "한식": 1,
        "중식": 2,
        "일식": 3,
        "양식": 4,
        "샐러드": 5
    };

    var menuId = menuIdMapping[selectedValue];
    var selectedPrefer = $('#prefer').val();
    var selectedDislike = $('#dislike').val();

    $.get("/menus/generate", function (data) {
        var generatedMenus = data;
        var generatedMenusDiv = document.getElementById("generatedMenus");
        var resultHtml = "<h2>회원님만을 위한 식단입니다</h2><ul>";

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

                    // 코드 정리를 위해 변수를 사용하여 HTML 구조 생성
                    var menuInfoHtml = "";

                    for (var i = 1; i <= 5; i++) {
                        menuInfoHtml += `${menu['food' + i]} (${menu['food' + i + 'Weight']}g)  ` +
                            `<button class='toggle-btn'>Nutrition</button>` +
                            `<div class='nutritional-info hidden'>` +
                            `칼로리: ${menu['food' + i + 'Kcal']}Kcal <br>` +
                            `탄수화물: ${menu['food' + i + 'Carb']}g <br>` +
                            `단백질: ${menu['food' + i + 'Protein']}g <br>` +
                            `지방: ${menu['food' + i + 'Fat']}g <br>` +
                            `</div>`;
                    }

                    var menuTotalWeight = totalWeight;
                    var totalCalories = menu.food1Kcal + menu.food2Kcal + menu.food3Kcal + menu.food4Kcal + menu.food5Kcal;
                    var totalCarbs = menu.food1Carb + menu.food2Carb + menu.food3Carb + menu.food4Carb + menu.food5Carb;
                    var totalProteins = menu.food1Protein + menu.food2Protein + menu.food3Protein + menu.food4Protein + menu.food5Protein;
                    var totalFats = menu.food1Fat + menu.food2Fat + menu.food3Fat + menu.food4Fat + menu.food5Fat;

                    resultHtml += `<li>식단정보<br>${menuInfoHtml}<br>` +
                        `주재료1: ${menu.main}<br>` +
                        `주재료2: ${menu.main2}<br>` +
                        `총 무게: ${menuTotalWeight}g<br>` +
                        `총 칼로리: ${totalCalories}Kcal<br>` +
                        `총 탄수화물: ${totalCarbs}g<br>` +
                        `총 단백질: ${totalProteins}g<br>` +
                        `총 지방: ${totalFats}g<br>` +
                        `</li>`;
                }
            }
        });

        resultHtml += "</ul>";
        generatedMenusDiv.innerHTML = resultHtml;

        var toggleButtons = document.querySelectorAll('.toggle-btn');
        toggleButtons.forEach(function (button) {
            button.addEventListener('click', function () {
                var nutritionalInfo = this.nextElementSibling;
                nutritionalInfo.classList.toggle('hidden'); // Toggle the 'hidden' class
            });
        });
    });
}

