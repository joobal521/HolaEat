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
        $('#userPassword').parent().css('border-color', 'lightgrey');
        $('#userPassword').parent().css('border-top', 'none');
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
let isIdChecked = false; //아이디 중복
let isToKenChecked = false;

$(function() {
    /*이메일 유효성*/
    $('#userEmail').keyup(function() {
        $('#chkEmail').html('');
        if (!regExp.test($('#userEmail').val())) {
            $('#chkEmail').html('올바른 이메일 형식이 아닙니다.<br>').css('color', 'red');
        }
    });

    /* 비밀번호 유효성 검사 */
    $('#userPassword').keyup(function() { /* keyup: 사용자가 키보드를 누르고 떼는 순간 이벤트 발생 */
        $('#chkNotice1').html(''); /* .html() -> 선택한 요소 안의 내용을 호출하거나 바꾸어준다. */
        $('#chkNotice2').html('');

        /* 비밀번호 길이 검사 */
        /* 비밀번호의 길이가 4글자 미만이거나, 10글자 초과일 때 */
        /* 숫자와 특수문자 포함 */
        if ($('#userPassword').val().length < 4 || $('#userPassword').val().length > 16 || !pwdChk.test($('#userPassword').val())) {
            $('#chkNotice1').html('비밀번호는 영문, 숫자와 특수문자 조합 4-16자 이내로 입력해주세요.<br>').css('color', 'red');

        }
        if (pwd_space.test($('#userPassword').val())) {
            $('#chkNotice1').html('비밀번호는 공백을 포함할 수 없습니다.<br>').css('color', 'red');

        }

    });

    /* 비밀번호, 비밀번호 확인 일치 검사 */
    $('#userPasswordCh').keyup(function() {
        if ($('#userPassword').val() !== $('#userPasswordCh').val()) {
            /* 비밀번호와 비밀번호 확인란의 값이 일치하지 않을 때 */
            $('#chkNotice2').html('비밀번호가 일치하지 않습니다.<br><br>').css('color', 'red'); /* 비밀번호 양식 오류일시 color: red */

        } else if ($('#userPassword').val() === $('#userPasswordCh').val()) {
            /* 모든 조건에 충족하고, 비밀번호와 비밀번호 확인란의 값이 일치할 때 */
            $('#chkNotice2').html('비밀번호가 일치합니다. 사용 가능합니다.<br>').css('color', 'navy'); /* 일치시 color: darkblue */

        }

        if (pwd_space.test($('#userPasswordCh').val())) {
            $('#chkNotice2').html('비밀번호는 공백을 포함할 수 없습니다.<br>').css('color', 'red');

        }
    });
});

/*전체 동의 선택/해제*/
const agreeChkAll =
    document.querySelector('input[name=agree_all]');
agreeChkAll.addEventListener('change', (e) => {
    let agreeChk =
        document.querySelectorAll('input[name=user_check]');
    for (let i = 0; i < agreeChk.length; i++) {
        agreeChk[i].checked = e.target.checked;
    }
});

/*아이디 중복검사*/
function chkId() {
    var id = $('#userId').val();


    const data = {
        userId: id,
    };
        $.ajax({
            method: 'POST',
            url: 'api/v1/users/userIdDupl',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",

        }).done(function(data){
                if (data.result === true) {
                    isIdChecked = true;
                    alert("사용 가능한 아이디입니다.")
                    //$('#chkMsgEmail').html('사용 가능한 아이디입니다.').css('color', 'navy');
                } else {
                    alert("이미 사용중인 아이디입니다.")
                   // $('#chkMsgEmail').html('이미 사용중인 아이디입니다.').css('color', 'red');
                }

        }).fail(function (error){
            alert("아이디 중복 검사 실패입니다: " + error.responseJSON.message);
        });



}



/* 이메일 인증번호 전송 */
function emailAuthentication() {

        var email = $('#userEmail').val();
        $("#email_ch").prop('disabled', true);

        const data = {
            userEmail: email,
        };

        $.ajax({
            type: "POST",
            url: "api/v1/users/emails/verification-requests",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            }).done(function(data){
                if (data.result === true) {
                    $("#code_ch").prop('disabled', false);
                    alert("인증번호를 확인을 해주세요.");
                    console.log("이메일 확인 코드가 발송되었습니다.");
                    //console.log("확인 코드: " + response.verification_code);
                    //console.log("확인 코드 유효 시간: " + response.verification_duration + "분");
                } else {
                    console.log("이메일 확인 코드 발송에 실패하였습니다.");
                }
        }).fail(function (error){
            alert("이메일인증 보내기 실패입니다: " + error.responseJSON.message);
        });

}


/* 인증번호 확인*/
function authCodeCheck() {
    if (isIdChecked) {
        var inputCode = $('#code').val();
        $.ajax({
            type: "POST",
            url: "/CheckEmailAuthToken",
            data: { input_code: inputCode },
              }).done(function(data){
                console.log(data);
                if (data.result === "VERIFICATION_SENT") {
                    alert("인증되었습니다.");
                    $("#code").prop('disabled', true);
                    $("#code_ch").prop('disabled', true);
                    isToKenChecked = true;
                }
                if (data.result === "The token code is invalid.") {
                    alert("인증코드가 맞지않습니다.");
                }
                if (data.result === "The token code has expired.") {
                    alert('다시 인증번호를 입력받아주세요');
                }
        }).fail(function (error){
            alert("이메일인증 보내기 실패입니다: " + error.responseJSON.message);

        });
    }
}



function checkValue(htmlForm) {
    const id = htmlForm.userId.value;
    const password = htmlForm.userPassword.value;
    const passwordCh = htmlForm.userPasswordCh.value;
    const email = htmlForm.userEmail.value;
    const name = htmlForm.userName.value;


    let check = true;
    var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    let pwdChk = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*#?&])/; /* 영문 + 숫자 + 특수문자 */
    let pwd_space = /[ ]/; /* 공백 */

    console.log(id);
    console.log(password);


    if (id === "") {
        $('#error-id').show();
        $('#userId').parent().css('border-color', 'red');
        check = false;

    }

    if (password === "") {
        $('#error-password').show();
        check = false;

    }
    if (password.length < 4 || password.length > 16 || !pwdChk.test(password) || pwd_space.test(password)) {
        check = false;
    }

     if (password !== passwordCh) {
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

    if (!$('#user_check1').prop('checked') || !$('#user_check2').prop('checked')) { //체크박스 미체크시
        alert("약관 동의를 체크해주세요.");
        check = false;
    }


    if (check && isIdChecked) {

        const data = {
            userId: id,
            userPassword: password,
            userEmail: email,
            userName: name,
        };

        $.ajax({
            method: "POST",
            url: "api/v1/users/join",
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
                alert("회원가입 실패입니다: " + error.responseJSON.message);

        });



    } else if (!isIdChecked) {
        alert("아이디 중복 확인을 해주세요.");
    }

}
