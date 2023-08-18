<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin-health</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>

<body>
<section>
    <h1>건강정보 글 등록</h1>
    <div class="form_wrap">

    <form  enctype="multipart/form-data">
        <div class="form_container">
            <div class="title_box">
                <label for="title">제목</label>
        <input type="text" name="title" id="title">
            </div>
            <div class="content_box">
                <label for="content">내용</label>
        <textarea id="content" name="content"></textarea>
            </div>
            <div class="img_box">
        <input type="file" id="file" name="file"  accept="image/png, image/jpg, image/jpeg, image.gif" multiple/>
            </div>
        <button type="button" id="btn-health" class="btn" onclick="checkValue(form)">등록</button>
        </div>
    </form>
    </div>
</section>
<script src="script/adminHealth.js"></script>

</body>

</html>