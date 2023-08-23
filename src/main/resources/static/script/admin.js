$(document).ready(function() {//admin페이지 접속시 menu관리 자동로드
    function loadContent() {

        $("li:nth-child(1) a.menu-link").addClass("active");

        $.ajax({
            url: "adminMenu",
            success: function(response) {
                $(".section").html(response);
            },
            error: function(xhr, status, error) {
                var errorMessage = "Failed to load the page: " + error;
                $(".section").html(errorMessage);
            }
        });
    }

    $("a.menu-link").click(function(event) {
        event.preventDefault(); // 기본 링크 동작 방지

        var pageUrl = $(this).attr("href"); // 클릭한 링크의 URL
        var pageTitle = $(this).text(); // 클릭한 링크의 텍스트를 페이지 제목으로 사용
        // AJAX 요청
        $.ajax({
            url: pageUrl,
            success: function(response) {
                $(".section").html(response); // .section에 응답 페이지 삽입
            },
            error: function() {
                alert("페이지 로드에 실패했습니다.");
            }
        });
    });

    var initialPageUrl = window.location.pathname;
    loadContent(initialPageUrl);

    window.onpopstate = function(event) {
        if (event.state) {
            $(".section").html(event.state.content);
            document.title = event.state.pageTitle;
        } else {
            loadContent(window.location.pathname);
        }
    };
});

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

        $("a.menu-link").removeClass("active");

        $(this).addClass("active");

        var pageUrl = $(this).attr("href");
        var pageTitle = $(this).text();

        $.ajax({
            url: pageUrl,
            success: function(response) {
                $(".section").html(response);
            },
            error: function() {
                alert("페이지 로드에 실패했습니다.");
            }
        });
    });
});