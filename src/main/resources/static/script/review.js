// $(document).ready(function() {
//     const blob = $('#blob').val();
//     console.log("blob : ", blob);
//     $('#img').attr('src', URL.createObjectURL(blob));
// })

//썸네일 출력
// $('#file').change(e => {
//     console.log("file is changed.");
//     loadthumb();
// });





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
    const imgElement = htmlForm.file; // 이미지 파일 입력 필드

    if (title.trim() === "") {
        console.log("Title is required.");
        alert("!제목을 입력해주세요.")
        return; // 제목이 비어있을 경우 처리 중단
    }

    if (content.trim() === "") {
        console.log("Title is required.");
        alert("!내용을 입력해주세요.")
        return; // 내용이 비어있을 경우 처리 중단
    }

    if (!confirm("등록하시겠습니까?")) {
        return;
    }


    let check = true;
    let title_space = /[ ]/; /* 공백 */

    if (check) {

        var form = new FormData();
        form.append("title", title);
        form.append("content", content);

        if (imgElement && imgElement.files && imgElement.files[0]) {
            const imgFile = imgElement.files[0];
            form.append("img", imgFile);

            const imgPreview = document.getElementById('img');
            imgPreview.src = URL.createObjectURL(imgFile);
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
            location.href = "reviewlist/1";
        });

    }
}


//게시글 수정


function CheckValueUpdate(htmlForm, reviewNo) {
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;
    const imgCheckUrl = htmlForm.imgCheck.value;
    console.log("imgCheckUrl 확인용"+imgCheckUrl);

    let imgFile = null;

    if (htmlForm.file && htmlForm.file.files && htmlForm.file.files[0]) {
        imgFile = htmlForm.file.files[0];
    }

    console.log("imgFile 확인용"+imgFile);

    if (title.trim() === "" && content.trim() === "" && imgFile !== imgCheckUrl) {
        alert("수정할 내용이 없습니다.");
        return;
    }

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
        "data": form,
    };

    $.ajax(settings)
        .done(function (response) {
            console.log(response);
            alert("수정되었습니다.");
            location.href = "review/" + reviewNo;
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error(jqXHR.responseText);
            alert("수정실패하였습니다.");
        });
}

//이미지 썸네일 로드, 이미지 파일을 선택하면 해당 이미지를 읽어들여 화면에 표시하는 역할
// function loadthumb() {
//     var reader = new FileReader;
//     reader.onload = function (data) {
//         $(".select_img img").attr("src", data.target.result).width(500);
//     }
//     reader.readAsDataURL($('#file').prop("files")[0]);
// }

//게시글 삭제

function CheckValueDelete(htmlForm, reviewNo) {
    const confirmed = window.confirm("정말 삭제하시겠습니까?");

    if (confirmed) {
        var form = new FormData();

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
            location.href = "reviewlist/1";
        });
    }
}





function redirectToReviewUpdate() {
    var reviewNoElement = document.getElementById("reviewNo");
    var reviewNo = reviewNoElement.value;

    window.location.href = "../reviewUpdate?reviewNo=" + reviewNo;




}

function goBack() {
    var confirmation = confirm("취소하시겠습니까?");

    if (confirmation) {
        // window.scrollTo(0, 0);
        // document.documentElement.style.overflow = 'hidden';
        history.back();
        // document.documentElement.style.overflow = 'auto';
    }
}

//목록으로 되돌아가기
function goBackToList() {
    location.href = "reviewlist/1";
}

//글쓰기 썸네일
function writeThumbnail() {
    const fileInput = document.getElementById('file');
    const imgElement = document.getElementById('img');
    const imagePreview = document.getElementById('image-preview');

    const file = fileInput.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            imgElement.src = e.target.result;
            imagePreview.style.display = 'block'; // 이미지 썸네일을 보여줌
        };
        reader.readAsDataURL(file);
    } else {
        imgElement.src = '';
        imagePreview.style.display = 'none'; // 이미지 썸네일을 숨김
    }
}



