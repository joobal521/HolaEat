$(document).ready(function () {

    // "선호하는 재료"와 "선호하지 않는 재료" 값이 같은지 확인하고, 같으면 선택을 막음
    $('#prefer, #dislike').change(function () {
        var preferValue = $('#prefer').val();
        var dislikeValue = $('#dislike').val();

        // 두 값이 모두 "<option value="">없음</option>"이 아닐 때에만 비교
        if (preferValue !== "" && dislikeValue !== "" && preferValue === dislikeValue) {
            Swal.fire({
                title: '선호하는 재료와 선호하지 않는 재료는 같을 수 없습니다.', text: '다시 선택 해주세요.', icon: 'error'
            });
            $(this).val(""); // 선택을 초기화
        } else {
            fetchAndDisplayMenu(selectedNational);
        }
    });
    $('#idx_save_btn').click(function () {

        var recCaloriesValue = $('#recCaloriesValue').text(); // 추출된 값


        // 칼로리 계산 값이 없을 경우 알림 띄우기
        if (isNaN(recCaloriesValue)) {
            Swal.fire({
                title: '칼로리 계산 값을 입력해주세요.', icon: 'error'
            });
            return; // 칼로리 값이 없으면 함수 종료
        }

        // 폼 데이터 수집
        var formData = {
            gender     : $('input[name="gender"]:checked').val(),
            age        : $('#age').val(),
            height     : $('#height').val(),
            weight     : $('#weight').val(),
            allergy    : $('#allergy').val(), // recCalories: $('#recCalories').val()
            recCalories: recCaloriesValue


        };

        // Ajax 통신
        saveFormData(formData);
    });

    // 저장 버튼 클릭 이벤트 핸들러
    $('#save_btn').click(function () {
        var genderChecked = $('input[name="gender"]:checked').length > 0;

        // 폼 데이터 수집
        var formData = {
            gender     : $('input[name="gender"]:checked').val(),
            age        : $('#age').val(),
            height     : $('#height').val(),
            weight     : $('#weight').val(),
            allergy    : $('#allergy').val(),
            recCalories: $('#recCalories').val(),
        };

        // 칼로리 계산 값이 없거나 라디오 버튼이 체크되지 않았을 경우 알림 띄우기
        if (!genderChecked || formData.age === '' || formData.height === '' || formData.weight === '' || formData.allergy === '' || formData.recCalories === '') {
            Swal.fire({
                title: '칼로리 정보가 입력되지 않았습니다.', text: '칼로리를 먼저 계산해 주세요.', icon: 'error'
            });
            return; // 하나라도 비어있으면 함수 종료
        }

        // Ajax 통신
        saveFormData(formData);
    });

    // "선호하는 재료"와 "선호하지 않는 재료" 필터링
    $('#prefer, #dislike').change(function () {
        fetchAndDisplayMenu(selectedNational);
    });

    // 메뉴 카테고리 변경 이벤트 핸들러
    $('#menu_type').change(function () {
        fetchAndDisplayAllMenus($(this).val());
    });
});

// 저장 함수
function saveFormData(formData) {
    $.ajax({
        type    : "POST", url: "/saveDetails", data: formData, success: function (data) {
            console.log("저장 성공:", data);
            updateFields(data);
            Swal.fire({
                title: '저장 완료', text: '저장에 성공했습니다!', icon: 'success'
            });
            $(this).val(""); // 선택을 초기화

        }, error: function (error) {
            console.error("저장 에러:", error);
            console.log("gender:", formData.gender);
            console.log("age:", formData.age);
            console.log("height:", formData.height);
            console.log("weight:", formData.weight);
            console.log("allergy:", formData.allergy);
            console.log("recCalories:", formData.recCalories);
        }
    });
}


function calculateCalories() {
    // 필요한 변수들을 가져옴
    var gender = '';
    var genderElement = document.querySelector('input[name="gender"]:checked');
    if (genderElement !== null) {
        gender = genderElement.value;
    }

    var age = parseFloat(document.getElementById("age").value);
    var height = parseFloat(document.getElementById("height").value);
    var weight = parseFloat(document.getElementById("weight").value);
    var allergy = document.getElementById("allergy").value;
    var menuType = document.getElementById("menu_type").value; // 식단 종류 값 가져오기

    // 칼로리 계산 값이 없을 경우 알림 띄우기
    if (gender === '' || isNaN(age) || isNaN(height) || isNaN(weight) || allergy === '' || menuType === '') {
        Swal.fire({
            title: '필요한 값을 모두 입력해주세요.', icon: 'error'
        });
        return; // 하나라도 비어있으면 함수 종료
    }

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
    // var foodKcal = menu['food' + i + 'Kcal'];
    // var foodCarb = menu['food' + i + 'Carb'];
    // var foodProtein = menu['food' + i + 'Protein'];
    // var foodFat = menu['food' + i + 'Fat'];
    var menuInfoHtml = "";

    for (var i = 1; i <= 5; i++) {
        var foodName = menu['food' + i];
        var foodWeight = menu['food' + i + 'Weight'];
        var foodImage = `/food/image/${foodName}`; // 이미지 URL

        menuInfoHtml += `<div class='food-info'>` +
            `<img src='${foodImage}' alt='Food Image' class='food-image' draggable="false" onclick='showNutritionalInfo(this)'/>`
            +"<br>" + `${foodName}` + "<br>" +`(${foodWeight}g) ` +
            `</div>` + `</div>`;
    }
    // `<div class='nutritional-info hidden'>` +
    // `<button class='toggle-btn'>Close</button>` +
    // `칼로리: ${foodKcal}Kcal <br>` +
    // `탄수화물: ${foodCarb}g <br>` +
    // `단백질: ${foodProtein}g <br>` +
    // `지방: ${foodFat}g <br>` +

    return menuInfoHtml;
}


function fetchAndDisplayMenu(selectedNational) {
    var userRecCalories = parseInt($('#recCalories').val());
    var selectedAllergy = parseInt($('#allergy').val());


    // 칼로리 계산 값이 없을 경우 알림 띄우기
    if (isNaN(userRecCalories)) {
        Swal.fire({
            title: '칼로리 정보가 입력되지 않았습니다.', text: '칼로리를 먼저 계산해 주세요.', icon: 'error'
        });
        return; // 칼로리 값이 없으면 함수 종료
    }

    $.get("/menus/generate", {national: selectedNational}, function (data) {
        var generatedMenus = data;
        var generatedMenusDiv = document.getElementById("generatedMenus");

        var resultHtml = "<h3>원하시는 식단을 오른쪽으로<br>드래그 해보세요!</h3><ul>";

        var selectedPrefer = $('#prefer').val();
        var selectedDislike = $('#dislike').val();


        generatedMenus.forEach(function (menu, index) {
            // if (index !== 0) {
            //     resultHtml += "<hr>";
            // }


            var menuTotalWeight = menu.food1Weight + menu.food2Weight + menu.food3Weight + menu.food4Weight + menu.food5Weight;

            // Check if the menu's allergy value matches the selected allergy option
            if (menu.allergy !== selectedAllergy && (selectedPrefer === "" || menu.main === selectedPrefer || menu.main2 === selectedPrefer) && (selectedDislike === "" || !menu.main.includes(selectedDislike) && !menu.main2.includes(selectedDislike)) && menuTotalWeight <= userRecCalories) {

                var totalCalories = menu.food1Kcal + menu.food2Kcal + menu.food3Kcal + menu.food4Kcal + menu.food5Kcal;
                var totalCarbs = menu.food1Carb + menu.food2Carb + menu.food3Carb + menu.food4Carb + menu.food5Carb;
                var totalProteins = menu.food1Protein + menu.food2Protein + menu.food3Protein + menu.food4Protein + menu.food5Protein;
                var totalFats = menu.food1Fat + menu.food2Fat + menu.food3Fat + menu.food4Fat + menu.food5Fat;

                var menuInfoHtml = generateMenuInfo(menu); // 식단 순번 추가

                resultHtml += "<li draggable='true'>" + menuInfoHtml + "<br>"
                    + `<div class='cals_wrap'>총 칼로리: <span class='cals'>${totalCalories}</span>Kcal<br>`
                    + "총 무게: " + menuTotalWeight + "g<br>"
                    + "총 탄수화물: " + totalCarbs + "g<br>"
                    + "총 단백질: " + totalProteins + "g<br>"
                    + `총 지방: ${totalFats}g</div><br>`
                    + "</li>";
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
    var selectedAllergy = parseInt($('#allergy').val());

    if (isNaN(userRecCalories)) {
        Swal.fire({
            title: '칼로리 정보가 입력되지 않았습니다.',
            text: '칼로리를 먼저 계산해 주세요.',
            icon: 'error'
        });
        return;
    }

    var menuIdMapping = {
        "한식": 1, "중식": 2, "일식": 3, "양식": 4, "샐러드(비건식)": 5
    };

    var menuId = menuIdMapping[selectedValue];
    var selectedPrefer = $('#prefer').val();
    var selectedDislike = $('#dislike').val();

    $.get("/menus/generate", function (data) {
        var generatedMenus = data;
        var generatedMenusDiv = document.getElementById("generatedMenus");
        var resultHtml = "<h2>원하시는 식단을 오른쪽으로 드래그 해보세요!</h2><ul>";

        generatedMenus.forEach(function (menu, index) {

            if (menu.menuId === menuId) {
                var menuTotalWeight = menu.food1Weight + menu.food2Weight + menu.food3Weight + menu.food4Weight + menu.food5Weight;

                if (menu.allergy !== selectedAllergy && (selectedPrefer === "" || menu.main === selectedPrefer || menu.main2 === selectedPrefer) && (selectedDislike === "" || !menu.main.includes(selectedDislike) && !menu.main2.includes(selectedDislike)) && menuTotalWeight <= userRecCalories) {

                    var totalCalories = menu.food1Kcal + menu.food2Kcal + menu.food3Kcal + menu.food4Kcal + menu.food5Kcal;
                    var totalCarbs = menu.food1Carb + menu.food2Carb + menu.food3Carb + menu.food4Carb + menu.food5Carb;
                    var totalProteins = menu.food1Protein + menu.food2Protein + menu.food3Protein + menu.food4Protein + menu.food5Protein;
                    var totalFats = menu.food1Fat + menu.food2Fat + menu.food3Fat + menu.food4Fat + menu.food5Fat;

                    var menuInfoHtml = generateMenuInfo(menu);

                    resultHtml += "<li draggable='true'>" + menuInfoHtml + "<br>"
                        + `<span class='cals_wrap'>총 칼로리: <span class='cals'>${totalCalories}</span>Kcal<br>`
                        + "총 무게: " + menuTotalWeight + "g<br>"
                        + "총 탄수화물: " + totalCarbs + "g<br>"
                        + "총 단백질: " + totalProteins + "g<br>"
                        + `총 지방: ${totalFats}g</span><br>`
                        + `<canvas id="nut_chart"></canvas>`
                        + "</li><br>";
                }
            }
        });

        resultHtml += "</ul>";
        generatedMenusDiv.innerHTML = resultHtml;

        var toggleButtons = document.querySelectorAll('.toggle-btn');
        toggleButtons.forEach(function (button) {
            button.addEventListener('click', function () {
                var nutritionalInfo = this.nextElementSibling;
                nutritionalInfo.classList.toggle('hidden');
            });
        });
    });
}



// 업데이트된 데이터로 페이지의 필드 업데이트
function updateFields(data) {
    $('#age').val(data.age);
    $('#height').val(data.height);
    $('#weight').val(data.weight);
    $('#allergy').val(data.allergy);
    $('#recCalories').val(data.recCalories);
    $('#prefer').val(data.prefer);
    $('#dislike').val(data.dislike);

    // 업데이트된 데이터를 JSP의 필드에도 반영
    $('#userAge').text(data.age);
    $('#userHeight').text(data.height);
    $('#userWeight').text(data.weight);
    $('#userAllergy').text(data.allergy);
    $('#userRecCalories').text(data.recCalories);
    $('#userPrefer').text(data.prefer);
    $('#userDislike').text(data.dislike);
}

// 드래그 앤 드롭을 위한 이벤트 처리
document.addEventListener('DOMContentLoaded', function () {
    const menuLists = document.querySelectorAll('.menu_list');
    let draggedItem = null;
    const userRecCalories = parseInt($('#recCalories').val());

    menuLists.forEach(function (menuList) {
        menuList.addEventListener('dragstart', function (e) {
            draggedItem = e.target;
            setTimeout(function () {
                e.target.style.display = 'none';
            }, 0);
        });

        menuList.addEventListener('dragend', function (e) {
            setTimeout(function () {
                draggedItem.style.display = 'flex'; // 변경된 부분
                draggedItem = null;
            }, 0);
        });

        menuList.addEventListener('dragover', function (e) {
            e.preventDefault();
        });

        menuList.addEventListener('dragenter', function (e) {
            e.preventDefault();
            this.style.backgroundColor = 'rgba(0, 0, 0, 0.2)';
        });

        menuList.addEventListener('dragleave', function () {
            this.style.backgroundColor = 'transparent';
        });

        menuList.addEventListener('drop', function (e) {
            this.style.backgroundColor = 'transparent';

            const selectedMenus = document.querySelectorAll('#selectedMenus .cals');
            let totalCalories = 0;

            selectedMenus.forEach(function (item) {
                const caloriesText = item.textContent;
                const calories = parseInt(caloriesText.replace('Kcal', ''));
                totalCalories += calories;
            });

            console.log("Total Calories:", totalCalories);
            console.log("Total userRecCalories:", userRecCalories);

            if (totalCalories > userRecCalories || selectedMenus.length > 2) {
                if (totalCalories > userRecCalories) {
                    Swal.fire({
                        title: '선택한 식단의 칼로리 합이 <br>권장 칼로리를 초과합니다.', text: '다시 확인해주세요.', icon: 'warning'

                    });
                    return; // 드래그 앤 드롭 불가 조건이 충족되면 함수 종료
                }
                if (selectedMenus.length > 2) {
                    Swal.fire({
                        title: '식단 항목이 3개를 <br>초과했습니다.', text: '최대 3개까지 선택할 수 있습니다.', icon: 'warning'
                    });
                    return; // 드래그 앤 드롭 불가 조건이 충족되면 함수 종료
                }
                return; // 드래그 앤 드롭 불가 조건이 충족되면 함수 종료
            }

            this.appendChild(draggedItem);
            calculateTotalCalories();
        });
    });

    function calculateTotalCalories() {
        const selectedMenus = document.querySelectorAll('#selectedMenus .cals');
        let totalCalories = 0;

        selectedMenus.forEach(function (item) {
            const caloriesText = item.textContent;
            const calories = parseInt(caloriesText.replace('Kcal', ''));
            totalCalories += calories;
        });

        console.log("Total Calories:", totalCalories); // Output
        console.log("Total userRecCalories:", userRecCalories); // Output

        if (selectedMenus.length > 3 || totalCalories > userRecCalories) {
            if (selectedMenus.length > 3) {
                Swal.fire({
                    title: '식단 항목이 3개를 초과했습니다.',
                    text: '최대 3개까지 선택할 수 있습니다.',
                    icon: 'warning',
                });
                return;
            }

            if (totalCalories > userRecCalories) {
                Swal.fire({
                    title: '선택한 식단의 칼로리 합이 권장 칼로리를 초과합니다.',
                    text: '다시 확인해주세요.',
                    icon: 'warning',
                    onClose: function () {
                        // 권장 칼로리를 초과할 경우 #selectedMenus 내용 초기화
                        $('#selectedMenus').empty();
                        resetSelectedMenus(); // #selectedMenus 초기화

                    }
                });
            }

            return; // 선택한 식단이 3개 초과 또는 권장 칼로리를 초과할 경우 함수 종료
        }
    }

    function resetSelectedMenus() {
        // #selectedMenus 내용 초기화
        $('#selectedMenus').empty();
    }

    // 초기화 버튼 클릭 이벤트 핸들러
    $('#reset_btn').click(function () {
        const generatedMenusDiv = document.getElementById("generatedMenus");
        const selectedMenusDiv = document.getElementById("selectedMenus");

        // #selectedMenus로 옮겨진 식단을 다시 #generatedMenus로 복원
        while (selectedMenusDiv.firstChild) {
            generatedMenusDiv.appendChild(selectedMenusDiv.firstChild);
        }

        // 칼로리 계산 함수 호출
        calculateTotalCalories();
    });
});



// 초기화
document.addEventListener('DOMContentLoaded', function () {
    const foodImages = document.querySelectorAll('.food-image');
    foodImages.forEach(function (image) {
        image.addEventListener('click', function () {
            showNutritionalInfo(this);
        });
    });

    // 식단 정보에 대한 차트 생성
    const nutritionalCharts = document.querySelectorAll('.nutrition-chart');
    nutritionalCharts.forEach(function (chartElement) {
        const chartCtx = chartElement.getContext('2d');
        const nutritionChart = new Chart(chartCtx, {
            type      : 'doughnut', data: {
                labels: ['탄수화물', '단백질', '지방'], datasets: [{
                    data           : [40, 20, 10], // 예시 데이터, 실제 데이터로 대체 필요
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                }],
            }, options: {
                responsive: true, maintainAspectRatio: false, title: {
                    display: true, text: '영양 성분 비율',
                },
            },
        });
    });
});

// 이미지를 클릭하여 영양 성분 정보를 보여주는 함수
// function showNutritionalInfo(imageElement) {
//     const menuContainer = imageElement.closest('.menu-container');
//     if (menuContainer) {
//         const nutritionalInfoOverlay = menuContainer.querySelector('.nutritional-info-overlay');
//         if (nutritionalInfoOverlay) {
//             nutritionalInfoOverlay.style.display = 'flex';
//
//             // 영양 성분 데이터를 가져오는 로직 (아래 코드는 예시이며, 실제 데이터에 맞게다 수정 필요)
//             const totalCarbs = 40;
//             const totalProteins = 20;
//             const totalFats = 10;
//
//             // 도넛 차트 데이터 및 옵션 설정
//             const ctx = nutritionalInfoOverlay.querySelector('#nutritionChart').getContext('2d');
//             const nutritionChart = new Chart(ctx, {
//                 type      : 'doughnut', data: {
//                     labels: ['탄수화물', '단백질', '지방'], datasets: [{
//                         data           : [totalCarbs, totalProteins, totalFats],
//                         backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
//                     }],
//                 }, options: {
//                     responsive: true, maintainAspectRatio: false, title: {
//                         display: true, text: '영양 성분 비율',
//                     },
//                 },
//             });
//         }
//     }
// }