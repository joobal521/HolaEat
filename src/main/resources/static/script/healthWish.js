
//로그인 하지 않은 상태에서 즐겨찾기 버튼을 눌렀을때
$(".like-btn-logOut").click(function(e) {
    e.preventDefault();
    var healthNo = $(this).data("id"); // data-id 값을 가져옴
    Swal.fire({
        title: '회원만 가능합니다',
        text: '로그인 페이지로 돌아갑니다.',
        icon: 'warning',
        showConfirmButton: true
    }).then((result) => {
        if (result.isConfirmed) {
            location.href = "/login"; // 로그인으로 이동
        }
    });
});

function toggleStar(){

}