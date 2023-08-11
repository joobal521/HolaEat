// $(document).ready(function() {
//     const blob = $('#blob').val();
//     console.log("blob : ", blob);
//     $('#img').attr('src', URL.createObjectURL(blob));
// })

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



        // var form = new FormData();
        // form.append("title", title);
        // form.append("content", content);
        //
        // const imgElement = document.getElementById('img');
        // if (imgFile) {
        //     imgElement.src = URL.createObjectURL(imgFile);
        // } else {
        //     imgElement.src = ''; // 이미지 없을 때 빈 상태로 설정
        // }
        //
        // var settings = {
        //     "url": "/write",
        //     "method": "POST",
        //     "timeout": 0,
        //     "processData": false,
        //     "mimeType": "multipart/form-data",
        //     "contentType": false,
        //     "data": form
        // };
        //
        // $.ajax(settings).done(function (response) {
        //
        //     console.log(response);
        //     // location.href = "reviewlist";
        // });
        var form = new FormData();
        form.append("title", title);
        form.append("content", content);

        const imgElement = document.getElementById('img');
        const imgFile = imgElement.files[0]; // 이미지 파일 가져오기

        console.log(title);
        console.log(content);
        console.log(imgFile);
        alert("title"+ title + "content" + content + "img" + imgFile)
        if (imgFile) {
            imgElement.src = URL.createObjectURL(imgFile);

            // 이미지 파일을 FormData에 추가
            form.append("img", imgFile);
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
            // location.href = "reviewlist";
        });




    }
}





function CheckValueUpdate(htmlForm){
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;

    let check = true;

    let imgFile = null;
    if (htmlForm.img && htmlForm.img.files && htmlForm.img.files[0]) {
        imgFile = htmlForm.img.files[0];
    }


    if(check){

        console.log(title);
        console.log(content);
        console.log(imgFile);


        var form = new FormData();
        form.append("title", title);
        form.append("content", content);


        if (imgFile) {
            form.append("img", imgFile);
        }

        var settings = {
            "url": "/" + reviewNo + "/update",
            "method": "PUT",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form
        };
    }



    $.ajax(settings).done(function (response) {
        console.log(response);
        location.href = "reviewlist";
    });



}

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


function redirectToReviewUpdate() {
    var reviewNoElement = document.getElementById("reviewNo");
    var reviewNo = reviewNoElement.value;

    window.location.href = "../reviewUpdate?reviewNo=" + reviewNo;
}

function goBack() {
    window.scrollTo(0, 0);
    document.documentElement.style.overflow = 'hidden';
    history.back();
    document.documentElement.style.overflow = 'auto';
}


