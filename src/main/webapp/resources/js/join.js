$('#userId').on('change', e => {
    if ($('#userId').val() !== "") {
        $('#error-id').hide();
        $('#userId').parent().css('border-color', 'lightgrey');
        $('#userId').parent().css('border-top', 'none');
    }
});
$('#userPassword').on('change', e => {
    if ($('#userPassword').val() !== "") {
        $('#error-password').hide();
        $('#user_password').parent().css('border-color', 'lightgrey');
        $('#user_paswword').parent().css('border-top', 'none');
    }
});


$('#user_name').on('change', e => {
    if ($('#user_name').val() !== "") {
        $('#error-name').hide();
        $('#user_name').parent().css('border-color', 'lightgrey');
        $('#user_name').parent().css('border-top', 'none');
    }
});
function checkValue(htmlForm) {
    const id = htmlForm.userId.value;
    const password = htmlForm.userPassword.value;
   // const password_ch = htmlForm.user_password_ch.value;
    const email = htmlForm.userEmail.value;
    const name = htmlForm.userName.value;

    let check = true;
    var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    let pwdChk = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*#?&])/; /* 영문 + 숫자 + 특수문자 */
    let pwd_space = /[ ]/; /* 공백 */

    console.log(id);
    console.log(password);


    if (check===true) {
        const data = {
            userId: id,
            userPassword: password,
            userEmail: email,
            userName: name
        };
        $.ajax({
            method: "POST",
            url: "/join",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(data){
                console.log(data);
                if (data.result === true) {
                    alert("회원가입 완료")
                    location.href = "login";
                }else{
                    alert("회원가입 실패")
                    location.href="join"
                }
            }).fail(function (error){
                alert("회원가입 실패: " + error.responseJSON.message);

        });



    }
}
