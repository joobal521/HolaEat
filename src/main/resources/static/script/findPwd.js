$('#userEmail').on('change', e => {
    if ($('#userEmail').val() !== "") {
        $('#error-email').hide();
        $('#userEmail').parent().css('border-color', 'lightgrey');
        $('#userEmail').parent().css('border-top', 'none');
    }
});

//이메일 유효성 체크
var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
$(function() {
    /*이메일 유효성*/
    $('#userEmail').keyup(function () {
        $('#chkEmail').html('');
        if (!regExp.test($('#userEmail').val())) {
            $('#chkEmail').html('<br>올바른 이메일 형식이 아닙니다.<br>').css('color', 'red');
        }
    });

});


let isToKenChecked = false;
let code;

/* 이메일 인증 번호 전송 */
function emailAuthentication() {

    var email = $('#userEmail').val();
    $("#code-ch").prop('disabled', true);
    //비밀번호 찾기-다음 버튼
    $("#next-btn").prop('disabled', true);
    console.log(email);

    if(email==="" || !regExp.test(email)) {
        Swal.fire({
            title: '이메일 입력 필수',
            text: '이메일을 정확히 입력해 주세요.',
            icon: 'warning',
        });
    }else {

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
                //alert("인증 번호를 확인해 주세요.");
                console.log("이메일 확인 코드가 발송되었습니다.");
                //console.log("확인 코드: " + response.verification_code);
                //console.log("확인 코드 유효 시간: " + response.verification_duration + "분");
            } else {
                Swal.fire({
                    title: '인증 번호 전송 실패',
                    text: '이메일 전송을 다시 시도해 주세요.',
                    icon: 'error',
                });
                //console.log("이메일 확인 코드 발송에 실패하였습니다.");
            }
        }).fail(function (error) {
            alert("이메일 인증 보내기 실패입니다: " + error.responseJSON.message);
        });
    }

}


/* 인증 번호 확인*/
function authCodeCheck() {
    var email = $('#userEmail').val();

    if(email==="" || !regExp.test(email)){
        Swal.fire({
            title: '이메일 입력 필수',
            text: '이메일을 정확히 입력해 주세요.',
            icon: 'warning',
        });
        //alert("사용 불가능한 이메일입니다.")
    }else {

        var inputCode = $('#input-code').val();
        var codeAsNumber = parseInt(code, 10); // 10진수로 파싱
        var inputCodeAsNumber = parseInt(inputCode, 10);

        console.log(inputCodeAsNumber);
        console.log(codeAsNumber);
        if (inputCodeAsNumber === codeAsNumber) {
            console.log("인증 번호 일치");
            Swal.fire({
                title: '인증 성공',
                text: '인증 번호가 일치합니다.',
                icon: 'success',
            });
            //alert("인증 되었습니다.");
            $("#input-code").prop('disabled', true);
            $("#code-ch").prop('disabled', true);
            //비밀번호 찾기-다음 버튼
            $("#next-btn").prop('disabled', false);
            isToKenChecked = true;

        } else {
            Swal.fire({
                title: '인증 실패',
                text: '인증 코드가 맞지 않습니다.',
                icon: 'error',
            });
            //alert("인증 코드가 맞지 않습니다.")
        }
    }

}

function findPwd(){
    if(isToKenChecked) {
        location.href = "new-pwd";
     }else {
        Swal.fire({
            title: '비밀번호 찾기 실패',
            text: '이메일 인증을 먼저 해주세요.',
            icon: 'error',
        });
        //alert('이메일 인증을 먼저 해주세요!') // 경고 메시지 출력
    }
}


