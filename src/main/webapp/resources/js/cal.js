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


function fetchAndDisplayMenu(selectedValue) {
    if (selectedValue === "") {
        document.getElementById("generatedMenus").innerHTML = "";
        return;
    }

    $.ajax({
        url: "/menus/generate",
        type: "GET",
        dataType: "json",
        success: function (data) {
            let menuContainer = document.getElementById("generatedMenus");
            menuContainer.innerHTML = "";

            for (let i = 0; i < data.length; i++) {
                let menu = data[i];
                if (menu.national === selectedValue) {
                    let menuDiv = document.createElement("div");
                    menuDiv.className = "menu-item";
                    menuDiv.innerHTML = `
                        <h3>${menu.menuId}</h3>
                        <p>음식 1: ${menu.food1Name}</p>
                        <p>음식 2: ${menu.food2Name}</p>
                        <p>음식 3: ${menu.food3Name}</p>
                        <p>음식 4: ${menu.food4Name}</p>
                        <p>음식 5: ${menu.food5Name}</p>
                    `;
                    menuContainer.appendChild(menuDiv);
                }
            }
        },
        error: function (xhr, status, error) {
            console.error("Error fetching menus:", error);
        }
    });
}
