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



    let check = true;
    let title_space = /[ ]/; /* 공백 */



    if (check) {

        console.log(title);
        console.log(content);
        console.log(imgFile);

        var form = new FormData();
        form.append("title", title);
        form.append("content", content);
        form.append("img", imgFile);

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
        });



    }
}






function CheckValueUpdate(htmlForm){
    const title = htmlForm.title.value;
    const content = htmlForm.content.value;
    const imgFile = htmlForm.img.files[0];


    let check = true;

    if(check){

        console.log(title);
        console.log(content);
        console.log(imgFile);


        var form = new FormData();
        form.append("title", title);
        form.append("content", content);
        form.append("img", imgFile);

        var settings = {
            "url": "/update",
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
    });



}


