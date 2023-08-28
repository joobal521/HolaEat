<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>reviewform</title>
    <link rel="stylesheet" type="text/css" href="/style/review.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<c:import url="header.jsp"/>
<body>
<div class="review-section">

    <div id="review-detail-contents">
        <h2>게시글 작성</h2>

        <form id="review-write" enctype="multipart/form-data">
            <table class="review-write-table">
                <tr>
                    <td><label for="title">제목</label></td>
                    <td><input type="text" id="title" name="title"></td>
                </tr>
                <tr>
                    <td><label for="content">내용</label></td>
                    <td><textarea id="content" name="content"></textarea></td>
                </tr>
                <tr>
                    <td><label for="file">이미지</label></td>
                    <td>
                        <input type="file" id="file" name="file"
                               accept="image/*" onchange="writeThumbnail()">
                        <div class="write_select_img" id="image-preview">
                            <img src="" id="img" name="img" alt="">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="review-write-btn">
                        <button type="button" id="submit" name="submit" onclick="checkValueWrite(form)">등록</button>
                        <button type="button" id="cancel" name="cancel" onclick="goBack()">취소</button>
                    </td>
                </tr>
            </table>
        </form>


    </div>

</div>
<script src="/script/review.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
