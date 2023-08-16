function checkValue(htmlForm) {
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;
    const profileImg = htmlForm.userProfileImg.files[0];

    if (title.trim() === "") {
        console.log("Title is required.");
        return; // 제목이 비어있을 경우 처리 중단
    }

    let check = true;
    let title_space = /[ ]/; /* 공백 */
    console.log(title);

    if (check) {
        const data = {
            userId: id,
            userPassword: password,

        };
        $.ajax({
            method: "DELETE",
            url: "api/v1/users/leave",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(data){
            console.log(data);
            if (data.result === true) {
                location.href = "../../../webapp";
                sessionStorage.removeItem("log");
            } else {
                alert("회원탈퇴 실패. 비밀번호가 올바르지 않습니다.");
            }
        }).fail(function (error){
            alert("회원탈퇴 실패입니다: " + error.responseJSON.message);


        });
    }

}