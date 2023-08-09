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
    baseCalories = 10 * weight + 6.25 * height - 5 * age + 5;
} else if (gender === "female") {
    baseCalories = 10 * weight + 6.25 * height - 5 * age - 161;
}

    // 알레르기에 따른 보정 값 추가

    if (allergy !== "") {
    baseCalories *= 0.9; // 알레르기가 있을 경우 열량을 90%로 조정
}

    // 결과를 필드에 채움
    document.getElementById("recCalories").value = baseCalories.toFixed(2); // 소수점 2자리까지 표시


}

    // cal.js
    function validateForm() {
        var ageInput = document.getElementById("age").value;
        if (isNaN(ageInput) || ageInput === "") {
            alert("나이를 유효한 숫자로 입력해주세요.");
            return false; // 폼 제출 중단
        }

    }

// Ajax로 폼 값을 서버에 비동기로 전송하는 함수
function saveFormValues() {
    var formData = {
        gender: $("input[name='gender']:checked").val(),
        age: $("#age").val(),
        height: $("#height").val(),
        weight: $("#weight").val(),
        allergy: $("#allergy").val(),
        recCalories: $("#recCalories").val()
    };

    $.ajax({
        type: "POST",
        url: "/saveCalories", // 폼 데이터를 처리하는 컨트롤러 URL
        data: formData,
        success: function(data) {
            $("#userAge").text(data.age);
            $("#userWeight").text(data.weight);
            $("#userHeight").text(data.height);
            $("#userAllergy").text(data.allergy);
            $("#userRecCalories").text(data.recCalories);
        }
    });
}


// 폼 필드 값이 변경될 때마다 saveFormValues 함수 호출
$("input, select").on("change", function() {
    saveFormValues();
});

updateSessionValues();

function updateSessionValues() {
    $.ajax({
        type: "GET",
        url: "/getSessionValues", // 세션 값을 가져오는 컨트롤러 URL
        success: function(data) {
            $("#age").val(data.userAge);
            $("#height").val(data.userHeight);
            $("#weight").val(data.userWeight);
            $("#allergy").val(data.userAllergy);
            $("#recCalories").val(data.userRecCalories);
        }
    });
}