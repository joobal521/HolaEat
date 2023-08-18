function checkValue(htmlForm) {
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;
    const imgFile = htmlForm.file.files[0];

    if (title.trim() === "") {
        console.log("Title is required.");
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
        const imgElement = document.getElementById('file');
        const imgFile = imgElement.files[0]; // 이미지 파일 가져오기


        if (imgFile) {
            imgElement.src = URL.createObjectURL(imgFile);

            // 이미지 파일을 FormData에 추가
            form.append("file", imgFile);
        } else {
            imgElement.src = ''; // 이미지 없을 때 빈 상태로 설정
        }

        var settings = {
            "url": "api/v1/health/health-write",
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
                alert("글 등록 실패");

            } else {
                alert("글 등록 성공");
                // location.href=""
            }

        });
    }

}