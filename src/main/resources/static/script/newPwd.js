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

let pwdChk = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*#?&])/; /* 영문 + 숫자 + 특수문자 */
let pwd_space = /[ ]/; /* 공백 */
$(function() {
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
        $('#chkNotice2').html('<br>비밀번호가 일치하지 않습니다.<br><br>').css('color', 'red'); /* 비밀번호 양식 오류일시 color: red */

    } else if ($('#newPassword').val() === $('#newPasswordCh').val()) {
        /* 모든 조건에 충족하고, 비밀번호와 비밀번호 확인란의 값이 일치할 때 */
        $('#chkNotice2').html('<br>비밀번호가 일치합니다. 사용 가능합니다.<br>').css('color', 'navy'); /* 일치시 color: darkblue */

    }

    if (pwd_space.test($('#newPasswordCh').val())) {
        $('#chkNotice2').html('<br>비밀번호는 공백을 포함할 수 없습니다.<br>').css('color', 'red');

    }
});
});

function checkValue() {
    const newPassword = $('#newPassword').val();
    const newPasswordCh = $('#newPasswordCh').val();


    let check = true;
    let pwdChk = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*#?&])/; /* 영문 + 숫자 + 특수문자 */
    let pwd_space = /[ ]/; /* 공백 */

    console.log(newPassword);

    if (newPassword === "") {
        $('#error-password').show();
        check = false;

    } else if (newPassword.length < 4 || newPassword.length > 10 || !pwdChk.test(newPassword) || pwd_space.test(newPassword)) {
        check = false;

    } else if (newPasswordCh === "") {
        check = false;

    } else if (newPassword !== newPasswordCh) {
        check = false;
    }

    if (check) {
        const data = {
            newPassword: newPassword,
            newPasswordCh: newPasswordCh,

        };

        $.ajax({
            method: "PUT",
            url: "api/v1/users/find-pwd",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(data){
            console.log(data);
            if (data.result === true) {
                Swal.fire({
                    title: '비밀번호 변경 완료',
                    text: '로그인 페이지로 돌아갑니다.',
                    icon: 'success',
                    showConfirmButton: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.href = "login"; // 메인 홈으로 이동
                    }
                });
                //alert("비밀번호 변경 완료")

            }else{
                Swal.fire({
                    title: '비밀번호 변경 실패',
                    text: '비밀번호 변경을 다시 시도 해주세요.',
                    icon: 'error',
                });
                //alert("기존 비밀번호가 일치하지 않습니다.")
            }
        }).fail(function (error){
            alert("비밀번호 변경 실패입니다: " + error.responseJSON.message);

        });

    }

}