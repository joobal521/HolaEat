
    function scrollToTop() {
    $("html, body").animate({
        scrollTop: 0
    }, "slow");
}


    // $(".likeUp-btn").click(function () {
    //     var reviewNo = $(this).data("id");
    //     var heartText = $("#result").value===1 ? "하트" : "빈하트";
    //     $(this).text("하트");
    //
    //     console.log(reviewNo + " js확인");
    //
    //     // 좋아요 등록 및 취소 처리
    //     $.ajax({
    //         url: "/reviewlike/" + reviewNo,
    //         method: "POST",
    //         success: function (data) {
    //             if (data.success) {
    //                 // 텍스트 변경
    //                 if (heartText.text() === "하트") {
    //                     heartText.text("빈하트");
    //                 }
    //                 if(heartText.text() === "빈하트"){
    //                     heartText.text("하트");
    //                 }
    //             }
    //         }
    //     });
    // });

    $(".likeUp-btn").click(function () {
        var button = $(this);
        var reviewNo = button.data("id");
        var heartText = button.find(".heart");

        console.log(reviewNo + " js확인");

        $.ajax({
            url: "/reviewlike/" + reviewNo,
            method: "POST",
            success: function (data) {
                if (data.success) {
                    // 텍스트 변경
                    console.log(reviewNo + "ajax확인")
                    if (heartText.find("i").hasClass("fa-regular")) {
                        heartText.html('<i class="fa-solid fa-heart"></i>');
                    } else {
                        heartText.html('<i class="fa-regular fa-heart"></i>');
                    }
                }
            }
        });
    });



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

