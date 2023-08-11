// $(document).ready(function() {
//     const blob = $('#blob').val();
//     console.log("blob : ", blob);
//     $('#img').attr('src', URL.createObjectURL(blob));
// })

//썸네일 출력
$('#file').change(e => {
    // console.log("file is changed.");
    loadthumb();
});

$('#title').on('change', e => {
    if ($('#title').val() !== "") {
        $('#error-title').hide();
        $('#title').parent().css('border-color', 'lightgrey');
        $('#title').parent().css('border-top', 'none');
    }
});

//게시글 등록
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

        var form = new FormData();
        form.append("title", title);
        form.append("content", content);

        const imgElement = document.getElementById('img');
        const imgFile = imgElement.files[0]; // 이미지 파일 가져오기

        console.log(title);
        console.log(content);
        console.log(imgFile);
        alert("title" + title + "content" + content + "img" + imgFile)
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
            location.href = "reviewlist";
        });

    }
}

//게시글 수정
function CheckValueUpdate(htmlForm, reviewNo) {
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;

    let check = true;
    let imgFile = null;

    if (htmlForm.file && htmlForm.file.files && htmlForm.file.files[0]) {
        imgFile = htmlForm.file.files[0];
    }


    if (check) {

        console.log(title);
        console.log(content);
        console.log(imgFile);


        var form = new FormData();
        form.append("title", title);
        form.append("content", content);


        if (imgFile) {
            form.append("img", imgFile);
        }
        // else{
        //     const currentImg = $(".select_img img").attr("src");
        //     form.append("img", currentImg);
        // }

        var settings = {
            "url": "/" + reviewNo + "/update",
            "method": "PUT",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form,
            // 동기식으로 처리
            // "async": false
        };
    }

    $.ajax(settings)
        .done(function (response) {
            console.log(response);
            alert("글 수정이 성공했습니다.");
            location.href = "review/" + reviewNo;
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error(jqXHR.responseText);
            alert("글 수정에 실패했습니다.");
        });
}

//이미지 썸네일 로드, 이미지 파일을 선택하면 해당 이미지를 읽어들여 화면에 표시하는 역할
function loadthumb() {
    var reader = new FileReader;
    reader.onload = function (data) {
        $(".select_img img").attr("src", data.target.result).width(500);
    }
    reader.readAsDataURL($('#file').prop("files")[0]);
}

//게시글 삭제
function CheckValueDelete(htmlForm, reviewNo) {

    const title = htmlForm.title.value;
    const content = htmlForm.content.value;

    let check = true;
    let imgFile = null;

    if (htmlForm.img && htmlForm.img.files && htmlForm.img.files[0]) {
        imgFile = htmlForm.img.files[0];
    }

    if (check) {

        console.log("title " + title);
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


