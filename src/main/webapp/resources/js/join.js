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



    if (id === "") {
        $('#error-email').show();
        $('#userEmail').parent().css('border-color', 'red');
        check = false;

    }

    if (!regExp.test(email)) {
        check = false;

    }
    if (password === "") {
        $('#error-password').show();
        check = false;

    }
    if (password.length < 4 || password.length > 10 || !pwdChk.test(password) || pwd_space.test(password)) {
        check = false;
    }

    // if (password !== password_ch) {
    //     check = false;
    //
    // }

    if (email=== "") {
        $('#error-nickname').show();
        check = false;
    }

    if (name === "") {
        $('#error-name').show();
        check = false;

    }



    // if (!$('#user_check1').prop('checked') || !$('#user_check2').prop('checked')) { //체크박스 미체크시
    //     alert("약관 동의를 체크해주세요.");
    //     check = false;
    // }



    if (check) {
        htmlForm.submit();
        $.ajax({
            method: "POST",
            url: "/join",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(response) {
                console.log(response);
                if (response.result === true) {
                    location.href = "login";
                } else {
                    alert('회원정보가 존재합니다.');
                }
            }
        });


    }
}
