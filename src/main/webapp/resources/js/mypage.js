function updateImg(htmlForm){
    const profile= htmlForm.userProfileImg.files[0];



    $.ajax({
        method: "PUT",
        url: "api/v1/my/profile",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).done(function(data){
        console.log(data);
        if (data.result === true) {
            alert("프로필 업로드 완료")
            location.href = "mypage";
        }else{
            alert("프로필 업로드 실패")
            location.href="join"
        }
    }).fail(function (error){
        alert("프로필 이미지 수정 실패입니다: " + error.responseJSON.message);

    });


}
