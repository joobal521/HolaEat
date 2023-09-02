
//글 등록
function checkValue(htmlForm) {
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;
    const imgElement = htmlForm.file;

    if (title.trim() === "") {
        console.log("Title is required.");
        alert("제목을 입력해 주세요.")
        return; // 제목이 비어있을 경우 처리 중단
    }

    if (content.trim() === "") {
        console.log("Content is required.");
        alert("내용을 입력해 주세요.")
        return; // 제목이 비어있을 경우 처리 중단
    }


    let check = true;
    let title_space = /[ ]/; /* 공백 */
    console.log(title);
    console.log(content);


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
            "url": "health-write",
            "method": "POST",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
            if(response.result === "") {
                //swal('글 등록 실패','다시 시도 해주세요.','error')
                alert("글 등록 실패");

            } else {
                //swal('글 등록 성공','새로운 글을 또 등록해보세요.','success')
                alert("글 등록 성공");
                location.href="admin";
            }

        });
    }

}

//글 수정
function CheckValueUpdate(htmlForm, healthNo) {
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;
    const imgCheckUrl = htmlForm.imgCheck.value;
    console.log("imgCheckUrl 확인용" + imgCheckUrl);

    let imgFile = null;

    if (htmlForm.file && htmlForm.file.files && htmlForm.file.files[0]) {
        imgFile = htmlForm.file.files[0];
    }

    console.log("imgFile 확인용" + imgFile);

    if (title.trim() === "" && content.trim() === "" && !imgFile) { // 이미지 수정 없을 때 추가된 부분
        alert("수정할 내용이 없습니다.");
        return;
    }

    var form = new FormData();
    form.append("title", title);
    form.append("content", content);

    if (imgFile) {
        form.append("img", imgFile);
    }
   // form.append("imgCheck", imgCheckUrl); // 이미지 수정을 하지 않았어도 이전 이미지 정보 추가

    var settings = {
        "url": "api/v1/health/update/" + healthNo,
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
            alert("글 수정 성공.");
            location.href = "admin";
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error(jqXHR.responseText);
            alert("글 수정 실패.");
        });
}
function redirectToHealthUpdate(healthNo) {
    window.location.href = "healthUpdate?healthNo=" + healthNo;
}


//글 삭제
$(document).ready(function() {

    $(".removeBtn").click(function () {
        var healthNo = $(this).data("id");
        var form = new FormData(); // FormData 객체 생성
        var settings = {
            "url": "api/v1/health/delete/"+healthNo,
            "method": "DELETE",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
            if(response.result === "") {
                //swal('글 삭제 실패','다시 시도 해주세요.','error')
                alert("글 삭제 실패");

            } else {
                //swal('글 삭제 성공','선택한 글이 삭제 되었습니다.','success')
                alert("글 삭제 성공");
               location.href="admin";
            }
        });

    });
})

//이미지 썸네일
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

//최소
function goBack() {
    var confirmation = confirm("취소 하시겠습니까?");

    if (confirmation) {
        // window.scrollTo(0, 0);
        // document.documentElement.style.overflow = 'hidden';
        location.href="admin";
        // document.documentElement.style.overflow = 'auto';
    }
}

//더보기 버튼
$(function() {
    $("tr").hide();
    $("tr").slice(0, 4).show(); // 초기갯수
    $("#moreView-btn").click(function(e) { // 더보기 버튼 클릭
        e.preventDefault();
        $("tr:hidden").slice(0, 4).show(); // 클릭시 리스트 갯수 지정
        if ($("tr:hidden").length == 0) { // 컨텐츠 남아있는지 확인
            $("#moreView-btn").hide(); //더이상의 리스트가 없다면 버튼 사라짐
        }
    });
});

