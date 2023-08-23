
    function scrollToTop() {
    $("html, body").animate({
        scrollTop: 0
    }, "slow");
}

    // function likeUp(buttonElement) {
    //     const reviewNo = $(buttonElement).data("id");
    //     console.log(reviewNo);
    //     console.log("1");
    // }
    //
    // $(".likeUp-btn").click(function () {
    //     var reviewNo = $(this).data("id");
    //
    //     console.log(reviewNo);
    //
    //     // 좋아요 등록
    //     $.ajax({
    //         url: "/reviewlike/" + reviewNo,
    //         method: "PUT",
    //     })
    //
    // })


    //완성코드(8/23)
    // $(".likeUp-btn").click(function () {
    //     var reviewNo = $(this).data("id");
    //     var heartIcon = $(this).find('i.fa-regular');
    //
    //     console.log(reviewNo + "js확인");
    //
    //     // 좋아요 등록 및 취소 처리
    //     $.ajax({
    //         url: "/reviewlike/" + reviewNo,
    //         method: "POST",
    //         success: function (data) {
    //             if (data.success) {
    //                 // 아이콘 클래스 변경
    //                 heartIcon.toggleClass('fa-solid').toggleClass('fa-regular');
    //             }
    //         }
    //     });


    // $(".likeUp-btn").click(function () {
    //     var reviewNo = $(this).data("id");
    //     var heartIcon = $(this).find('i.fa-heart');
    //
    //
    //     console.log(reviewNo + "js확인");
    //
    //     // 좋아요 등록 및 취소 처리
    //     $.ajax({
    //         url: "/reviewlike/" + reviewNo,
    //         method: "POST",
    //         success: function (data) {
    //             if (data.success) {
    //                 // 아이콘 클래스 변경
    //                 if (heartIcon.hasClass('fas')) {
    //                     heartIcon.removeClass('fas').addClass('far');
    //                 } else {
    //                     heartIcon.removeClass('far').addClass('fas');
    //                 }
    //             }
    //         }
    //     });
    // });


    $(".likeUp-btn").click(function () {
        var reviewNo = $(this).data("id");
        var heartText = $(this).find('.heart');

        console.log(reviewNo + " js확인");

        // 좋아요 등록 및 취소 처리
        $.ajax({
            url: "/reviewlike/" + reviewNo,
            method: "POST",
            success: function (data) {
                if (data.success) {
                    // 텍스트 변경
                    if (heartText.text() === "로그인 하트") {
                        heartText.text("로그인 하트 취소");
                    } else {
                        heartText.text("로그인 하트");
                    }
                }
            }
        });
    });

    // $(".likeUp-btn").click(function () {
    //     var reviewNo = $(this).data("id");
    //     var heartText = $(this).find('.heart');
    //
    //     // 좋아요 등록 및 취소 처리
    //     $.ajax({
    //         url: "/reviewlike/" + reviewNo,
    //         method: "POST",
    //         success: function (data) {
    //             if (data.includes("liked")) {
    //                 heartText.text("풀하트"); // Liked, so change to filled heart
    //             } else if (data.includes("unliked")) {
    //                 heartText.text("빈하트"); // Unliked, so change to empty heart
    //             }
    //         }
    //     });
    // });




    // $.ajax({
        //     url: "/reviewlike/" + reviewNo,
        //     method: "PUT",
        //     success: function (data) {
        //         if (data.success) {
        //             // 아이콘 클래스 변경
        //             $heartIcon.toggleClass('fa-solid').toggleClass('fa-regular');
        //         }
        //     }
        // });








    //로그아웃 상태에서 좋아요 버튼 눌렀을때
    $(".likeUp-logout").click(function (){
    // alert("로그인 후 이용가능합니다.");
        Swal.fire({
            title: '로그인 후 이용가능합니다.',
            text: '로그인 페이지로 이동됩니다.',
            icon: 'warning',
            confirmButtonColor: '#265037', // confrim 버튼 색깔 지정
            confirmButtonText: '확인', // confirm 버튼 텍스트 지정

        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "login";}
        });
})

