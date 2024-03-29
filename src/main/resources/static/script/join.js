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
let isEmailChecked=false;//이메일 중복
let isToKenChecked = false; //이메일 인증

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
            $('#chkNotice2').html('<br>비밀번호가 일치하지 않습니다.<br><br>').css('color', 'red'); /* 비밀번호 양식 오류일시 color: red */

        } else if ($('#userPassword').val() === $('#userPasswordCh').val()) {
            /* 모든 조건에 충족하고, 비밀번호와 비밀번호 확인란의 값이 일치할 때 */
            $('#chkNotice2').html('<br>비밀번호가 일치합니다. 사용 가능합니다.<br>').css('color', 'navy'); /* 일치시 color: darkblue */

        }

        if (pwd_space.test($('#userPasswordCh').val())) {
            $('#chkNotice2').html('<br>비밀번호는 공백을 포함할 수 없습니다.<br>').css('color', 'red');

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

    if(id===""){
        Swal.fire({
            title: '사용 불가능한 아이디',
            text: '아이디를 다시 입력해 주세요.',
            icon: 'error'
        });
        //alert("사용 불가능한 아이디입니다.")
    }else {

        const data = {
            userId: id,
        };
        $.ajax({
            method: 'POST',
            url: 'users/check-userId',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",

        }).done(function (data) {
            if (data.result === true) {
                isIdChecked = true;
                Swal.fire({
                    title: '사용 가능한 아이디',
                    text: '아이디를 사용해 주세요.',
                    icon: 'success'
                });
                //alert("사용 가능한 아이디입니다.")
                //$('#chkMsgEmail').html('사용 가능한 아이디입니다.').css('color', 'navy');
            } else {
                Swal.fire({
                    title: '이미 사용 중인 아이디',
                    text: '다른 아이디를 입력해주세요.',
                    icon: 'warning'
                });
                //alert("이미 사용 중인 아이디입니다.")
                // $('#chkMsgEmail').html('이미 사용중인 아이디입니다.').css('color', 'red');
            }

        }).fail(function (error) {
            alert("아이디 중복 검사 실패입니다: " + error.responseJSON.message);
        });

    }

}

//이메일 중복검사
function chkEmail() {
    var email = $('#userEmail').val();

    if(email===""){
        Swal.fire({
            title: '사용 불가능한 이메일',
            text: '이메일을 다시 입력해 주세요.',
            icon: 'error'
        });
        //alert("사용 불가능한 이메일입니다.")
    }else {


        const data = {
            userEmail: email,
        };
        $.ajax({
            method: 'POST',
            url: 'users/check-userEmail',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",

        }).done(function (data) {
            if (data.result === true) {
                isEmailChecked = true;
                Swal.fire({
                    title: '사용 가능한 이메일',
                    text: '이메일을 사용해 주세요.',
                    icon: 'success'
                });
                //alert("사용 가능한 이메일입니다.")
                //$('#chkMsgEmail').html('사용 가능한 아이디입니다.').css('color', 'navy');
            } else {
                Swal.fire({
                    title: '이미 사용 중인 이메일',
                    text: '다른 이메일을 입력해주세요.',
                    icon: 'warning'
                });
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


         const data = {
             userEmail: email,
         };

         $.ajax({
             type: "POST",
             url: "users/verification-email",
             data: JSON.stringify(data),
             contentType: "application/json; charset=utf-8",
             dataType: "json",

         }).done(function (data) {
             console.log(data);
             if (data !== null) {
                 $("#code-ch").prop('disabled', false);
                 code = data;
                 console.log(code);
                 Swal.fire({
                     title: '인증 번호 전송 완료',
                     text: '인증 번호를 확인해 주세요.',
                     icon: 'success',
                 });
                 //console.log("확인 코드: " + response.verification_code);
                 //console.log("확인 코드 유효 시간: " + response.verification_duration + "분");
             } else {
                 Swal.fire({
                     title: '인증 번호 전송 실패',
                     text: '이메일 전송을 다시 시도해 주세요.',
                     icon: 'error',
                 });

             }
         }).fail(function (error) {
             alert("이메일 인증 보내기 실패입니다: " + error.responseJSON.message);
         });
     }else {
         Swal.fire({
             title: '인증 불가능',
             text: '이메일 중복을 확인해 주세요.',
             icon: 'warning',
         });

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
            Swal.fire({
                title: '인증 성공',
                text: '인증 번호가 일치합니다.',
                icon: 'success',
            });
            //alert("인증 되었습니다.");
            $("#input-code").prop('disabled', true);
            $("#code-ch").prop('disabled', true);
            isToKenChecked = true;
        } else {
            Swal.fire({
                title: '인증 실패',
                text: '인증 코드가 맞지 않습니다.',
                icon: 'error',
            });

        }

        // if (data.result === "The token code has expired.") {
        //     alert('다시 인증번호를 입력받아주세요');
        // }
    }else {
        $("#input-code").prop('disabled', true);
        $("#code-ch").prop('disabled', true);
        Swal.fire({
            title: '인증 불가능',
            text: '이메일 중복을 확인해 주세요.',
            icon: 'warning',
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
        Swal.fire({
            title: '회원 가입 실패',
            text: '약관 동의를 체크해 주세요.',
            icon: 'warning'
        });

        check = false;
    }


    if (check && isIdChecked && isEmailChecked &&isToKenChecked ) {
        const data = {
            userId: id,
            userPassword: password,
            userEmail: email,
            userName: name,
        };

        $.ajax({
            method: "POST",
            url: "users/join",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(data){
                if (data.result === true) {
                    Swal.fire({
                        title: '회원 가입 완료',
                        text: 'holaEat의 가족이 되신 걸 축하합니다.',
                        icon: 'success',
                        showConfirmButton: true
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.href = "login";
                        }
                    });
                }else{
                    Swal.fire({
                        title: '회원 가입 실패',
                        text: '회원 가입을 다시 시도 해주세요.',
                        icon: 'error',
                        showConfirmButton: true
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.href="join";
                        }
                    });

                }
            }).fail(function (error){
                alert("회원가입 실패입니다: " + error.responseJSON.message);

        });



    }else if (!isIdChecked) {
        Swal.fire({
            title: '회원 가입 실패',
            text: '아이디 중복을 확인해 주세요.',
            icon: 'warning'
        });

    }else if(!isEmailChecked){
        Swal.fire({
            title: '회원 가입 실패',
            text: '이메일 중복을 확인해 주세요.',
            icon: 'warning'
        });

    }else if(!isToKenChecked){
        Swal.fire({
            title: '회원 가입 실패',
            text: '이메일 인증을 먼저 해주세요.',
            icon: 'warning'
        });

    }


}
