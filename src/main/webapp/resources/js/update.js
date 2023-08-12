$('#newPassword').on('change', e => {
    if ($('#newPassword').val() !== "") {
        $('#error-password').hide();
        $('#newPassword').parent().css('border-color', 'lightgrey');
        $('#newPassword').parent().css('border-top', 'none');
    }
});
$('#newPasswordCh').on('change', e => {
    if ($('#newPasswordCh').val() !== "") {
        $('#error-passwordCh').hide();
        $('#newPasswordCh').parent().css('border-color', 'lightgrey');
        $('#newPasswordCh').parent().css('border-top', 'none');
    }
});
$('#userEmail').on('change', e => {
    if ($('#userEmail').val() !== "") {
        $('#error-email').hide();
        $('#userEmail').parent().css('border-color', 'lightgrey');
        $('#userEmail').parent().css('border-top', 'none');
    }
});

$('#userName').on('change', e => {
    if ($('#userName').val() !== "") {
        $('#error-name').hide();
        $('#userName').parent().css('border-color', 'lightgrey');
        $('#userName').parent().css('border-top', 'none');
    }
});

var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
let pwdChk = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*#?&])/; /* 영문 + 숫자 + 특수문자 */
let pwd_space = /[ ]/; /* 공백 */
let isIdChecked = false;
let isToKenChecked = false;

$(function() {
    /*이메일 유효성*/
    $('#userEmail').keyup(function () {
        $('#chkEmail').html('');
        if (!regExp.test($('#userEmail').val())) {
            $('#chkEmail').html('올바른 이메일 형식이 아닙니다.<br>').css('color', 'red');
        }
    });

    /* 비밀번호 유효성 검사 */
    $('#newPassword').keyup(function () { /* keyup: 사용자가 키보드를 누르고 떼는 순간 이벤트 발생 */
        $('#chkNotice1').html(''); /* .html() -> 선택한 요소 안의 내용을 호출하거나 바꾸어준다. */
        $('#chkNotice2').html('');

        /* 비밀번호 길이 검사 */
        /* 비밀번호의 길이가 4글자 미만이거나, 10글자 초과일 때 */
        /* 숫자와 특수문자 포함 */
        if ($('#newPassword').val().length < 4 || $('#newPassword').val().length > 10 || !pwdChk.test($('#newPassword').val())) {
            $('#chkNotice1').html('비밀번호는 영문, 숫자와 특수문자 조합 4-10자 이내로 입력해주세요.<br>').css('color', 'red');

        }
        if (pwd_space.test($('#newPassword').val())) {
            $('#chkNotice1').html('비밀번호는 공백을 포함할 수 없습니다.<br>').css('color', 'red');

        }

    });

    /* 비밀번호, 비밀번호 확인 일치 검사 */
    $('#newPasswordCh').keyup(function () {
        if ($('#newPassword').val() !== $('#newPasswordCh').val()) {
            /* 비밀번호와 비밀번호 확인란의 값이 일치하지 않을 때 */
            $('#chkNotice2').html('비밀번호가 일치하지 않습니다.<br><br>').css('color', 'red'); /* 비밀번호 양식 오류일시 color: red */

        } else if ($('#newPassword').val() === $('#newPasswordCh').val()) {
            /* 모든 조건에 충족하고, 비밀번호와 비밀번호 확인란의 값이 일치할 때 */
            $('#chkNotice2').html('비밀번호가 일치합니다. 사용 가능합니다.<br>').css('color', 'navy'); /* 일치시 color: darkblue */

        }

        if (pwd_space.test($('#newPasswordCh').val())) {
            $('#chkNotice2').html('비밀번호는 공백을 포함할 수 없습니다.<br>').css('color', 'red');

        }
    });
});

function checkValue() {
    const id= $('#userId').val();
    const password = $('#userPassword').val();
    const newPassword = $('#newPassword').val();
    const newPasswordCh = $('#newPasswordCh').val();
    const email = $('#userEmail').val();
    const name = $('#userName').val();


    let check = true;
    var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    let pwdChk = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*#?&])/; /* 영문 + 숫자 + 특수문자 */
    let pwd_space = /[ ]/; /* 공백 */


    console.log(newPassword);



    if (newPassword === "") {
        $('#error-password').show();
        check = false;

    }
    if (newPassword.length < 4 || newPassword.length > 10 || !pwdChk.test(newPassword) || pwd_space.test(newPassword)) {
        check = false;
    }

    if (newPassword !== newPasswordCh) {
        check = false;

    }

    if (email === "") {
        $('#error-email').show();
        check = false;
    }

    if (!regExp.test(email)) {
        check = false;

    }

    if (name === "") {
        $('#error-name').show();
        check = false;

    }



    if (check) {

        const data = {
            userId:id,
            userPassword: password,
            newPassword: newPassword,
            newPasswordCh: newPasswordCh,
            userEmail: email,
            userName: name,

        };

        $.ajax({
            method: "PUT",
            url: "api/v1/users/update",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(data){
            console.log(data);
            if (data.result === true) {
                alert("회원수정 완료")
                location.href = "/";
            }else{
                alert("기존 비밀번호가 일치하지 않습니다.")
                location.href="update"
            }
        }).fail(function (error){
            alert("회원수정 실패입니다: " + error.responseJSON.message);

        });



    }

}



