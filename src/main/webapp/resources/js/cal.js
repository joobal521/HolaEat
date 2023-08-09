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
//
// // '저장' 버튼 클릭 시에 호출되는 함수
// function saveFormValues() {
//     var formData = {
//         gender: $("input[name='gender']:checked").val(),
//         age: $("#age").val(),
//         height: $("#height").val(),
//         weight: $("#weight").val(),
//         allergy: $("#allergy").val(),
//         recCalories: $("#recCalories").val()
//     };
//
//     $.ajax({
//         type: "POST",
//         url: "/saveCalories", // 폼 데이터를 처리하는 컨트롤러 URL
//         data: formData,
//         success: function(data) {
//             // 필요한 업데이트 수행
//             $("#age").val(data.age);
//             $("#height").val(data.height);
//             $("#weight").val(data.weight);
//             $("#allergy").val(data.allergy);
//             $("#recCalories").val(data.recCalories);
//         }
//     });
// }
//
// // '저장' 버튼 클릭 이벤트 리스너
// $("#save_btn").on("click", function() {
//     saveFormValues(); // 비동기 요청 및 화면 업데이트 수행
// });
//
// // 계산 함수 (예시)
// function calculateCalories() {
//     // 계산 로직
// }
