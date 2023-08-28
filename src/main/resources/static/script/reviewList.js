
function scrollToTop() {
    $("html, body").animate({
        scrollTop: 0
    }, "slow");
}
function toggleHeart(reviewNo) {
    var heartIcon = document.getElementById("heart-icon-" + reviewNo);
    var resultInput = document.getElementById("result-" + reviewNo);

    var newHeartStatus = (heartIcon.classList.contains("fa-regular")) ? 1 : 0;

    $.ajax({
        url: "/reviewlike/" + reviewNo,
        method: "POST",
        data: { newHeartStatus: newHeartStatus }, // 좋아요 상태를 서버로 전달
        success: function (data) {

            // 텍스트 변경
            if (heartIcon.classList.contains("fa-regular")) {
                heartIcon.classList.remove("fa-regular");
                heartIcon.classList.add("fa-solid");

                console.log("resultInput:", resultInput);
                console.log("totalLikesElement:"+ totalLikesElement);
                resultInput.value = "1";
            } else {
                heartIcon.classList.remove("fa-solid");
                heartIcon.classList.add("fa-regular");
                resultInput.value = "0";
            }

            // 좋아요 개수 업데이트
            // var likeCountElement = document.getElementById("like-count-" + reviewNo);
            // likeCountElement.innerText = data.updatedLikeCount;

            //  리뷰 번호에 대한 좋아요 수만 반환하는
            //  GET /reviewlike/{reviewNo}/likes
            // $.ajax({
            //     url: "/reviewlike/" + reviewNo + /totallikes/,
            //     method: "GET",
            //     dataType: "json",
            //     success: function (data) {
            //         var totalLikesElement = document.getElementById("total-likes-" + reviewNo);
            //         totalLikesElement.innerText = "Total Likes: " + data.totalLikes;
            //     }
            // });
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

