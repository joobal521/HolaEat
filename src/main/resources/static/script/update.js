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
let isEmailChecked=false;//이메일 중복
let isToKenChecked = false;//이메일 인증

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
            $('#chkNotice2').html('<br>비밀번호가 일치하지 않습니다.<br><br>').css('color', 'red'); /* 비밀번호 양식 오류일시 color: red */

        } else if ($('#newPassword').val() === $('#newPasswordCh').val()) {
            /* 모든 조건에 충족하고, 비밀번호와 비밀번호 확인란의 값이 일치할 때 */
            $('#chkNotice2').html('<br>비밀번호가 일치합니다. 사용 가능합니다.<br>').css('color', 'navy'); /* 일치시 color: darkblue */

        }

        if (pwd_space.test($('#newPasswordCh').val())) {
            $('#chkNotice2').html('비밀번호는 공백을 포함할 수 없습니다.<br>').css('color', 'red');

        }
    });
});

//이메일 중복검사
function chkEmail() {
    var email = $('#userEmail').val();

    if (email === "") {
        swal('사용 불가능한 이메일', '이메일을 다시 입력해 주세요.', 'error')
        //alert("사용 불가능한 이메일입니다.")
    } else {


        const data = {
            userEmail: email,
        };
        $.ajax({
            method: 'POST',
            url: 'api/v1/users/userEmail-check',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",

        }).done(function (data) {
            if (data.result === true) {
                isEmailChecked = true;
                swal('사용 가능한 이메일', '입력하신 이메일을 사용해 주세요.', 'success')
                //alert("사용 가능한 이메일입니다.")
                //$('#chkMsgEmail').html('사용 가능한 아이디입니다.').css('color', 'navy');
            } else {
                swal('이미 사용 중인 이메일', '다른 이메일을 입력해주세요.', 'warning')
                //alert("이미 사용 중인 이메일입니다.")
                // $('#chkMsgEmail').html('이미 사용중인 아이디입니다.').css('color', 'red');
            }

        }).fail(function (error) {
            alert("이메일 중복 검사 실패입니다: " + error.responseJSON.message);
        });

    }
}

    let code;

    /* 이메일 인증 번호 전송 */
    function emailAuthentication() {

        if(isEmailChecked) { //이메일 중복 확인 먼저
            var email = $('#userEmail').val();
            $("#code-ch").prop('disabled', true);

            console.log(email);

            const data = {
                userEmail: email,
            };

            $.ajax({
                type: "POST",
                url: "api/v1/users/verification-email",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",

            }).done(function (data) {
                console.log(data);
                if (data !== null) {
                    $("#code-ch").prop('disabled', false);
                    code = data;
                    console.log(code);
                    swal('인증 번호 전송 완료','인증 번호를 확인해 주세요.','success')
                    //alert("인증 번호를 확인해 주세요.");
                    console.log("이메일 확인 코드가 발송되었습니다.");
                    //console.log("확인 코드: " + response.verification_code);
                    //console.log("확인 코드 유효 시간: " + response.verification_duration + "분");
                } else {
                    swal('인증 번호 전송 실패','이메일 전송을 다시 시도해 주세요.','error')
                    //console.log("이메일 확인 코드 발송에 실패하였습니다.");
                }
            }).fail(function (error) {
                alert("이메일 인증 보내기 실패입니다: " + error.responseJSON.message);
            });
        }else {
            swal('인증 불가능 ','이메일 중복을 확인해 주세요.','warning')
            //alert("이메일 중복을 확인해 주세요.")
        }

    }


    /* 인증 번호 확인*/
    function authCodeCheck() {

        if(isEmailChecked) { //이메일 중복 확인 먼저
            var inputCode = $('#input-code').val();
            var codeAsNumber = parseInt(code, 10); // 10진수로 파싱
            var inputCodeAsNumber = parseInt(inputCode, 10);

            console.log(inputCodeAsNumber);
            console.log(codeAsNumber);
            if (inputCodeAsNumber === codeAsNumber) {
                console.log("인증 번호 일치");
                swal('인증 성공','인증 되었습니다.','success')
                //alert("인증 되었습니다.");
                $("#input-code").prop('disabled', true);
                $("#code-ch").prop('disabled', true);
                isToKenChecked = true;
            } else {
                swal('인증 실패','인증 코드가 맞지 않습니다.','error')
                // alert("인증 코드가 맞지 않습니다.")
            }

            // if (data.result === "The token code has expired.") {
            //     alert('다시 인증번호를 입력받아주세요');
            // }
        }else {
            $("#input-code").prop('disabled', true);
            $("#code-ch").prop('disabled', true);
            swal('인증 불가능 ','이메일 중복을 확인해 주세요.','warning')
            //alert("이메일 중복을 확인해 주세요.")
        }

    }


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



    if (check &&isEmailChecked && isToKenChecked) {

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
                swal('회원 수정 완료 ','메인 홈으로 돌아갑니다~','success')
                //alert("회원수정 완료")
                location.href = "/";
            }else{
                swal('회원 수정 실패 ','기존 비밀번호가 일치하지 않습니다.','error')
                //alert("기존 비밀번호가 일치하지 않습니다.")
            }
        }).fail(function (error){
            alert("회원수정 실패입니다: " + error.responseJSON.message);

        });



    }else if(!isEmailChecked){
        swal('회원 수정 불가능 ','이메일 중복을 확인해 주세요.','warning')

    }else if(!isToKenChecked){
        swal('회원 수정 불가능 ','이메일 인증을 먼저 해주세요.','warning')
    }

}



