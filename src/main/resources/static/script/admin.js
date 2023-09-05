var Admin = Admin || {}; // Admin 네임스페이스 생성

$(document).ready(function () {//admin페이지 접속시 menu관리 자동로

    loadContent = function (url) {
        if (url === "adminMenu") {
            $("li:nth-child(1) a.menu-link").addClass("active");
        }
        $.ajax({
            url: url,
            success: function (response) {
                $(".section").html(response);
            },
            error: function (xhr, status, error) {
                var errorMessage = "Failed to load the page: " + error;
                $(".section").html(errorMessage);
            }
        });
    };

    Admin.pageRelocate = function (url) {
        loadContent(url);
    }

    $("a.menu-link").click(function (event) {
        event.preventDefault(); // 기본 링크 동작 방지
        $("a.menu-link").removeClass("active");

        $(this).addClass("active");
        var pageUrl = $(this).attr("href"); // 클릭한 링크의 URL
        // AJAX 요청
        $.ajax({
            url: pageUrl,
            success: function (response) {
                $(".section").empty();
                $(".section").html(response); // .section에 응답 페이지 삽입
            },
            // error: function() {
            //     alert("페이지 로드에 실패했습니다.");
            // }
        });
    });

    var initialPageUrl = "adminMenu"

    loadContent(initialPageUrl);


});
