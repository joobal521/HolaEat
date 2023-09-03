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
    console.log(password);

    if (check === true) {
        const data = {
            userId: id,
            userPassword: password,

        };
        $.ajax({
            method: "DELETE",
            url: "users/leave",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(data){
            console.log(data);
            if (data.result === true) {
                Swal.fire({
                    title: '회원 탈퇴 완료',
                    text: '메인 홈으로 돌아갑니다.',
                    icon: 'success',
                    showConfirmButton: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.href = "/";
                        sessionStorage.removeItem("log");
                    }
                });

            } else {
                Swal.fire({
                    title: '회원 탈퇴 실패',
                    text: '비밀번호가 올바르지 않습니다.',
                    icon: 'error',
                });

                //alert("회원탈퇴 실패. 비밀번호가 올바르지 않습니다.");
            }
        }).fail(function (error){
            alert("회원탈퇴 실패입니다: " + error.responseJSON.message);


        });
    }

}