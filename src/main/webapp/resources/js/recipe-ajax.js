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

function loadRecipe(btn, modal) {
    var foodId = btn.getAttribute("data-foodid");
    var $recipeContent = modal.querySelector(".recipe-content");

    $.ajax({
        url: "getRecipe/" + foodId,
        type: "GET",
        dataType: "html",
        success: function (response) {
            // console.log("response:" + response);
            $recipeContent.innerHTML = response; // 레시피 정보를 해당 모달 내부에 추가
        }
    });
}