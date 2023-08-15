<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>

</head>
<body>
    <h1>건강정보 관리</h1>
    <form  enctype="multipart/form-data">
        <input type="text" name="title" id="title">
        <textarea id="content" name="content"></textarea>
        <input multiple="multiple" type="file" id="file" name="multi_file"  accept="image/png, image/jpg, image/jpeg, image.gif">
        <button type="button" id="btn-health" class="btn" onclick="checkValue(form)">등록</button>
    </form>



</body>
