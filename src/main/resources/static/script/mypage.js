// mypage.js

function updateImg(htmlForm) {
    const profileImg = htmlForm.userProfileImg.files[0];
    const id=htmlForm.userId.value;

    console.log(id);

    const formData = new FormData();
    formData.append("userId", $("#userId").val());
    formData.append("profileImg", profileImg);

    $.ajax({
        method: "PUT",
        url: "api/v1/my/profile",
        data: formData,
        contentType: false,
        processData: false,
        enctype: "multipart/form-data",
        dataType: "json",
        success: function(data) {
            console.log(data);
            if (data.result === true) {
                alert("프로필 업로드 완료");
                // 이미지 데이터 업데이트
                $("#profileImage").attr("src", "data:image/png;base64," + data.base64ImageData);
            } else {
                alert("프로필 업로드 실패");
            }
        },
    }).fail(function(error) {
        alert("프로필 이미지 수정 실패입니다: " + error.responseJSON.message);
    });
}
