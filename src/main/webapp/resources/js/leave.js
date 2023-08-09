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

    if (email === "") {
        check = false;

    } else if (password === "") {
        $('#error-password').show();
        $('#user_password').focus();//포커스 이동시켜서 다시 입력하라고
        check = false;
    }

    if (check === true) {
        const data = {
            userId: id,
            userPassword: password,

        };
        $.ajax({
            method: "DELETE",
            url: "Leave",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(data){
            console.log(data);
            if (data.result === true) {
                location.href = "/";
            } else {
                alert("회원탈퇴 실패.");
            }


        });
    }

}