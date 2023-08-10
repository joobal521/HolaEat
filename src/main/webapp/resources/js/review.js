$('#title').on('change', e => {
    if ($('#title').val() !== "") {
        $('#error-title').hide();
        $('#title').parent().css('border-color', 'lightgrey');
        $('#title').parent().css('border-top', 'none');
    }
});





function checkValueWrite(htmlForm) {
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;
    const imgFile = htmlForm.img.files[0];

    if (title.trim() === "") {
        console.log("Title is required.");
        return; // 제목이 비어있을 경우 처리 중단
    }

    let check = true;
    let title_space = /[ ]/; /* 공백 */


    if (check) {

        console.log(title);
        console.log(content);
        console.log(imgFile);

        var form = new FormData();
        form.append("title", title);
        form.append("content", content);

        const imgElement = document.getElementById('img');
        if (imgFile) {
            imgElement.src = URL.createObjectURL(imgFile);
        } else {
            imgElement.src = ''; // 이미지 없을 때 빈 상태로 설정
        }

        var settings = {
            "url": "/write",
            "method": "POST",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form
        };

        $.ajax(settings).done(function (response) {

            console.log(response);
            location.href = "reviewlist";
        });



    }
}





// function CheckValueUpdate(htmlForm){
//     const title = htmlForm.title.value;
//     const content = htmlForm.content.value;
//     const imgFile = htmlForm.img.files[0];
//
//
//     let check = true;
//
//     if(check){
//
//         console.log(title);
//         console.log(content);
//         console.log(imgFile);
//
//
//         var form = new FormData();
//         form.append("title", title);
//         form.append("content", content);
//         form.append("img", imgFile);
//
//         var settings = {
//             "url": "/update",
//             "method": "PUT",
//             "timeout": 0,
//             "processData": false,
//             "mimeType": "multipart/form-data",
//             "contentType": false,
//             "data": form
//         };
//     }
//
//
//
//     $.ajax(settings).done(function (response) {
//         console.log(response);
//         location.href = "reviewUpdate";
//     });
//
//
//
// }

function CheckValueDelete(htmlForm, reviewNo){

    const title = htmlForm.title.value;
    const content = htmlForm.content.value;


    let check = true;


    let imgFile = null;
    if (htmlForm.img && htmlForm.img.files && htmlForm.img.files[0]) {
        imgFile = htmlForm.img.files[0];
    }

    if(check){

        console.log("title "  + title);
        console.log("content : " + content);
        console.log("imgFile : " + imgFile);


        var form = new FormData();
        form.append("title", title);
        form.append("content", content);

        if (imgFile) {
            form.append("img", imgFile);
        }

        var settings = {
            "url": "/" + reviewNo + "/delete",
            "method": "DELETE",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
            location.href = "reviewlist";
        });


    }


}



function goBack() {
    // 현재 스크롤 위치를 최상단으로 올려줌
    window.scrollTo(0, 0);
    // 뷰포트 크기를 고정
    document.documentElement.style.overflow = 'hidden';
    // 뒤로가기 실행
    history.back();
    // 뷰포트 크기 고정을 해제
    document.documentElement.style.overflow = 'auto';
}


