
//글 등록
function checkValue(htmlForm) {
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;
    const imgElement = htmlForm.file;

    if (title.trim() === "") {
        Swal.fire({
            title: '제목을 입력해주세요.',
            icon: 'warning'
        });
        return; // 제목이 비어있을 경우 처리 중단
    }

    if (content.trim() === "") {
        Swal.fire({
            title: '내용을 입력해주세요.',
            icon: 'warning'
        });
        return; // 제목이 비어있을 경우 처리 중단
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
            "url": "write-health",
            "method": "POST",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form
        };

        $.ajax(settings).done(function (response) {
            if(response.result === "") {
                alert("글 등록 실패");

            }else{
                Swal.fire({
                    title: '등록하시겠습니까?',
                    //icon: 'warning',
                    showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                    confirmButtonText: '확인', // confirm 버튼 텍스트 지정
                    cancelButtonText: '취소', // cancel 버튼 텍스트 지정
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.href="admin";
                    }
                });

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
        "url": "/update-health/" + healthNo,
        "method": "PUT",
        "timeout": 0,
        "processData": false,
        "mimeType": "multipart/form-data",
        "contentType": false,
        "data": form,
    };

    $.ajax(settings)
        .done(function (response) {
            Swal.fire({
                title: '수정되었습니다.',
                icon: 'success',
                confirmButtonColor: '#265037', // confrim 버튼 색깔 지정

            }).then((result) => {
                if (result.isConfirmed) {
                    location.href = "admin";
                }
            });

        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error(jqXHR.responseText);
            Swal.fire({
                title: '수정실패하였습니다.',
                icon: 'warning',
                confirmButtonColor: '#265037', // confrim 버튼 색깔 지정

            });
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
            "url": "/delete-health/"+healthNo,
            "method": "DELETE",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form
        };

        $.ajax(settings).done(function (response) {
            if(response.result === "") {
                alert("글 삭제 실패");

            } else {
                Swal.fire({
                    title: '정말 삭제하시겠습니까?',
                    text: '게시글 삭제 후 복구 불가합니다.',
                    icon: 'warning',
                    showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                    confirmButtonColor: '#265037', // confrim 버튼 색깔 지정
                    confirmButtonText: '확인', // confirm 버튼 텍스트 지정
                    cancelButtonText: '취소', // cancel 버튼 텍스트 지정
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.href="admin";
                    }
                });

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
    Swal.fire({
        title: '취소하시겠습니까?',
        icon: 'warning',
        showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
        confirmButtonColor: '#265037', // confrim 버튼 색깔 지정
        confirmButtonText: '확인', // confirm 버튼 텍스트 지정
        cancelButtonText: '취소', // cancel 버튼 텍스트 지정
    }).then((result) => {
        if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
            history.back();
        }


    });
}

//더보기 버튼
$(function() {
    $("tr").hide();
    $("tr").slice(0, 4).show(); // 초기개수
    $("#moreView-btn").click(function(e) { // 더보기 버튼 클릭
        e.preventDefault();
        $("tr:hidden").slice(0, 4).show(); // 클릭시 리스트 갯수 지정
        if ($("tr:hidden").length == 0) { // 컨텐츠 남아있는지 확인
            $("#moreView-btn").hide(); //더이상의 리스트가 없다면 버튼 사라짐
        }
    });
});

//목록으로 되돌아가기
function goBackToList() {
    location.href = "../health-list/1";
}

