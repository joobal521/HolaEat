document.addEventListener("DOMContentLoaded", function() {
    const openModalBtns = document.querySelectorAll('.foodbtn');
    const modals = document.querySelectorAll('.ingr-modal');

    openModalBtns.forEach((openModalBtn, index) => {
        openModalBtn.addEventListener('click', () => {
            modals[index].style.display = 'block';
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
$(document).ready(function() {

    $(".foodbtn").click(function() {
        var foodId = $(this).data("foodid");
        var $modal = $(this).closest(".ingr-modal");
        var $recipeContent = $modal.find("#recipe-content");

        $.ajax({
            url: "getRecipe/" + foodId,
            type: "GET",
            dataType: "html",

            success: function(response) {
                console.log("response:"+response)
                $recipeContent.append(response); // 레시피 정보를 해당 모달 내부에 추가
            }
        });
    });
});
