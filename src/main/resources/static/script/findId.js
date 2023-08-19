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
            $('#chkEmail').html('올바른 이메일 형식이 아닙니다.<br>').css('color', 'red');
        }
    });

});


let isToKenChecked = false;
let code;

/* 이메일 인증 번호 전송 */
function emailAuthentication() {

        var email = $('#userEmail').val();
        $("#code-ch").prop('disabled', true);
        $("#find-btn").prop('disabled', true);


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



}


/* 인증 번호 확인*/
function authCodeCheck() {

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


}

const button = document.querySelector('#find-btn');
button.addEventListener('click', () => {
    if (isToKenChecked) { // isToKenChecked 가 true 이면
        //아이디 조회 가능

        window.location.href = "newPassword"; // 이동할 페이지 url
    } else {
        alert('이메일 인증을 먼저 해주세요!') // 경고 메시지 출력
    }
});
