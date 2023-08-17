document.addEventListener("DOMContentLoaded", function () {
    const openModalBtns = document.querySelectorAll('.foodbtn');
    const modals = document.querySelectorAll('.ingr-modal');

    openModalBtns.forEach((openModalBtn, index) => {
        openModalBtn.addEventListener('click', () => {
            modals[index].style.display = 'block';
            loadRecipe(openModalBtn, modals[index]);
        });
    });

    // 클릭 이벤트 추가: 모달 바깥 부분을 클릭하면 모달이 닫힘
    modals.forEach((modal, index) => {
        window.addEventListener('click', (event) => {
            if (event.target === modal) {
                modal.style.display = 'none';
            }
        });
    });
});

// function loadRecipe(btn, modal) {
//     var foodId = btn.getAttribute("data-foodid");
//     var $recipeContent = modal.querySelector(".recipe-content");
//
//     // var ingrid = document.getElementById('ingrId-modal').value;
//     console.log("cyka")
//     console.log(foodId + "foodid");
//     // console.log(ingrid + "ingrid");
//     $.ajax({
//         url: "getRecipe/" + foodId,
//         type: "GET",
//         dataType: "html",
//         success: function (response) {
//             $recipeContent.innerHTML = response; // Add recipe information to the modal content
//         }
//     });
// }

// script/recipe-ajax.js
function loadRecipe(btn, modal) {
    var foodId = btn.getAttribute("data-foodid");
    var $recipeContent = modal.querySelector(".recipe-content");
    // var ingrid = document.getElementById('ingrId-modal').value;
    console.log("wTF")
    $.ajax({
        url: "getRecipe/" + foodId,
        type: "GET",
        dataType: "html",
        success: function (response) {
            $recipeContent.innerHTML = response; // Add recipe information to the modal content
        }
    });
}