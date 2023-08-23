
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
