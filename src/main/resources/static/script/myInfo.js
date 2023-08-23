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
        "method": "POST",
        "timeout": 0,
        "processData": false,
        "mimeType": "multipart/form-data",
        "contentType": false,
        "data": form,
    };

    $.ajax(settings)
        .done(function (response) {
            console.log(response);

            // 성공 시 프로필 이미지를 즉시 업데이트합니다.
            const newProfileImgUrl = URL.createObjectURL(profileImg);
            const profileImgElement = document.querySelector(".card img");
            profileImgElement.src = newProfileImgUrl; // 수정된 부분
            // 수정된 이미지 URL을 localStorage에 저장합니다.
            localStorage.setItem("profileImgUrl", newProfileImgUrl);
            Swal.fire({
                title: '프로필 수정 완료',
                text: '새로운 프로필을 이용해 보세요.',
                icon: 'success',
                showConfirmButton: true
            }).then((result) => {
                if (result.isConfirmed) {

                }
            });
            //alert("프로필 수정 성공.");
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error(jqXHR.responseText);
            Swal.fire({
                title: '프로필 수정 실패',
                text: '프로필 사진을 다시 바꿔주세요.',
                icon: 'error',
            });
            //alert("프로필 수정 실패.");
        });
}

// 페이지 로드 시 이미지 업데이트
document.addEventListener("DOMContentLoaded", function() {
    const profileImgUrl = localStorage.getItem("profileImgUrl");
    if (profileImgUrl) {
        const profileImgElement = document.querySelector(".card img");
        profileImgElement.src = profileImgUrl;
    }
});
