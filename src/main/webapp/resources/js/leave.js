$('#user_password').on('change', e => {
    const password = $('#user_password').val();

    if (password !== "") {
        $('#error-password').hide();
    }
});


function checkValue(htmlForm) {
    const id = htmlForm.userId.value;
    const password = htmlForm.userPassword.value;

    let check = true;
    console.log(id);

    if (id === "") {//readlonly라서 없어도 됨
        check = false;

    } else if (password === "") {
        $('#error-password').show();
        $('#userPassword').focus();//포커스 이동시켜서 다시 입력하라고
        check = false;
    }

    if (check === true) {
        console.log("들어온다");
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
                location.href = "/";
                sessionStorage.removeItem("log");
            } else {
                alert("회원탈퇴 실패.");
            }
        }).fail(function (error){
            alert("회원탈퇴 실패입니다: " + error.responseJSON.message);


        });
    }

}