<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>
<section>
    <h1>건강정보 관리</h1>
    <form  enctype="multipart/form-data">
        <input type="text" name="title" id="title">
        <textarea id="content" name="content"></textarea>
        <input type="file" id="img" name="img"  accept="image/png, image/jpg, image/jpeg, image.gif" multiple/>
        <button type="button" id="btn-health" class="btn" onclick="checkValue(form)">등록</button>
    </form>
</section>
<script src="script/adminHealth.js"></script>

</body>
