document.addEventListener("DOMContentLoaded", function () {
    const btn = document.querySelectorAll('.addIngr');
    const modals = document.querySelectorAll('.addIngr-modal');

    btn.forEach((btn, index) => {
        btn.addEventListener('click', () => {
            modals[index].style.display = 'block';
            loadRecipe(btn, modals[index]);
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
    $("a.menu-link").click(function(event) {
        event.preventDefault();

        // Remove 'active' class from all links
        $("a.menu-link").removeClass("active");

        // Add 'active' class to the clicked link
        $(this).addClass("active");

        var pageUrl = $(this).attr("href");
        var pageTitle = $(this).text();

        $.ajax({
            url: pageUrl,
            success: function(response) {
                $(".section").html(response);
                history.pushState(null, pageTitle, pageUrl);
            },
            error: function() {
                alert("페이지 로드에 실패했습니다.");
            }
        });
    });
});
