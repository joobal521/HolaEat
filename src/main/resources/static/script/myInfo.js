//포로필 이미지 수정
function updateImg(htmlForm,profileNo) {
    const profileImg = htmlForm.userProfileImg.files[0];
    const id = htmlForm.userId.value;


    const form = new FormData();
    form.append("profileImg", profileImg);
    form.append("id", id); // userId 대신 id로 변경

    var settings = {
        "url": "profile/" + profileNo,
        "method": "POST",
        "timeout": 0,
        "processData": false,
        "mimeType": "multipart/form-data",
        "contentType": false,
        "data": form,
    };

    $.ajax(settings)
        .done(function (response) {

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

//이미지 썸네일
function writeThumbnail() {
    const fileInput = document.getElementById('file');
    const imgElement = document.getElementById('img');
    const imagePreview = document.getElementById('image-preview');

    const file = fileInput.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            imgElement.src = e.target.result;
            imagePreview.style.display = 'block'; // 이미지 썸네일을 보여줌
        };
        reader.readAsDataURL(file);
    } else {
        imgElement.src = '';
        imagePreview.style.display = 'none'; // 이미지 썸네일을 숨김
    }
}
