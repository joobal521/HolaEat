//포로필 이미지 수정
function updateImg(htmlForm,profileNo) {
    const profileImg = htmlForm.userProfileImg.files[0];
    const id = htmlForm.userId.value;

    console.log(id);
    console.log(profileImg);
    console.log(profileNo);

    const form = new FormData();
    form.append("profileImg", profileImg);
    form.append("id", id); // userId 대신 id로 변경

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
            // 성공 시 프로필 이미지를 즉시 업데이트합니다.
            const newProfileImgUrl = URL.createObjectURL(profileImg);
            const profileImgElement = document.querySelector(".card img");
            profileImgElement.src = newProfileImgUrl; // 수정된 부분
            // 수정된 이미지 URL을 localStorage에 저장합니다.
            localStorage.setItem("profileImgUrl", newProfileImgUrl);
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error(jqXHR.responseText);
            alert("프로필 수정 실패.");
        });
}

// 다른 페이지에서 이미지를 업데이트합니다.
document.addEventListener("DOMContentLoaded", function() {
    const profileImgUrl = localStorage.getItem("profileImgUrl");
    if (profileImgUrl) {
        const profileImgElement = document.querySelector(".card img");
        profileImgElement.src = profileImgUrl;
    }
});
