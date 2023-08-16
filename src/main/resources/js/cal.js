$(document).ready(function () {
    $('#save_btn').click(function () {
        var gender = $('input[name="gender"]:checked').val();
        var age = $('#age').val();
        var height = $('#height').val();
        var weight = $('#weight').val();
        var allergy = $('#allergy').val();
        var recCalories = $('#recCalories').val();

        var formData = {
            gender: gender,
            age: age,
            height: height,
            weight: weight,
            allergy: allergy,
            recCalories: recCalories
        };

        $.ajax({
            type: "POST",
            url: "/saveCalories", // 해당 URL에 맞게 수정
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

                alert("저장에 성공하였습니다.")

            },
            error: function (error) {
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

