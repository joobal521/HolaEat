$(document).ready(function() {//admin페이지 접속시 menu관리 자동로드
    function loadContent() {

        $("li:nth-child(1) a.menu-link").addClass("active");

        $.ajax({
            url: "myInfo",
            success: function(response) {
                $(".section").html(response);
            },
            error: function() {
                alert("Failed to load the page.");
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
                // 브라우저 주소 표시줄 업데이트
                history.pushState(null, pageTitle, pageUrl);
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

// 브라우저 뒤로가기/앞으로 가기 시 이벤트 처리
// window.onpopstate = function(event) {
//     if (event.state) {
//         $(".section").html(event.state.content);
//         document.title = event.state.pageTitle;
//     }
// };

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
                history.pushState(null, pageTitle, pageUrl);
            },
            error: function() {
                alert("페이지 로드에 실패했습니다.");
            }
        });
    });
});


//프로필 이미지 수정
function updateImg(htmlForm, profileNo) {
    const profileImg = htmlForm.userProfileImg.files[0];
    const userId = htmlForm.userId.value;

    const form = new FormData();
    form.append("profileImg", profileImg);
    form.append("userId", userId); // userId 추가

    var settings = {
        "url": "api/v1/my/profile/" + profileNo,
        "method": "PUT",
        "timeout": 0,
        "processData": false,
        "mimeType": "multipart/form-data",
        "contentType": false,
        "data": form,
    };

    $.ajax(settings)
        .done(function (response) {
            console.log(response);
            alert("프로필 수정 성공.");
            const newProfileImgUrl = URL.createObjectURL(profileImg);

            const profileImgElement = document.querySelector(".card img");
            profileImgElement.src = newProfileImgUrl;
            localStorage.setItem("profileImgUrl", newProfileImgUrl);
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error(jqXHR.responseText);
            alert("프로필 수정 실패.");
        });
}
