
function scrollToTop() {
    $("html, body").animate({
        scrollTop: 0
    }, "slow");
}

function toggleHeart(reviewNo) {
    var heartIcon = document.getElementById("heart-icon-" + reviewNo);
    var totalLikesElement = document.getElementById("total-likes-" + reviewNo); // Select the total likes element

    var newHeartStatus = (heartIcon.classList.contains("fa-regular")) ? 1 : 0;

    $.ajax({
        url: "/reviewlike/" + reviewNo,
        method: "POST",
        data: { newHeartStatus: newHeartStatus },
        success: function (data) {
            if (heartIcon.classList.contains("fa-regular")) {
                heartIcon.classList.remove("fa-regular");
                heartIcon.classList.add("fa-solid");
                // 총 좋아요 개수 반영
                totalLikesElement.textContent = parseInt(totalLikesElement.textContent) + 1; // Increment the likes count
            } else {
                heartIcon.classList.remove("fa-solid");
                heartIcon.classList.add("fa-regular");
                // 총 좋아요 개수 반영
                totalLikesElement.textContent = parseInt(totalLikesElement.textContent) - 1; // Decrement the likes count
            }
        }
    });
}



//로그아웃 상태에서 좋아요 버튼 눌렀을때
$(".likeUp-logout").click(function (){
    Swal.fire({
        title: '로그인 후 이용가능합니다.',
        text: '로그인 페이지로 이동됩니다.',
        icon: 'warning',
        showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
        confirmButtonColor: '#265037', // confrim 버튼 색깔 지정
        confirmButtonText: '확인', // confirm 버튼 텍스트 지정
        cancelButtonText: '취소', // cancel 버튼 텍스트 지정

    }).then((result) => {
        if (result.isConfirmed) {
            location.href = "/login";}
    });
})

