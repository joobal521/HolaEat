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