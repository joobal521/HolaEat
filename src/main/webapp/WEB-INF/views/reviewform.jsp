<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>reviewform</title>
<%--    <c:set var="path" value="${pageContext.request.contextPath}"/>--%>
    <link rel="stylesheet" type="text/css" href="/style/review.css">
<%--    <link rel="stylesheet" type="text/css" href="../style/review.css">--%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>



</head>
<c:import url="header.jsp"/>
<body>
<div class="review-section">
    <div class="wrapper">
        <div>게시글 작성</div>

        <div id="review-contents">
            <form id="review-write" enctype="multipart/form-data">
                <div class="review_write_title">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="title">
                    <p class="error" id="error-title">제목을 입력해주세요.</p>
                </div>
                <div class="review_write-content">
                    <label for="content">내용</label>
                    <textarea id="content" name="content"></textarea>
                </div>
                <div id="img-container">
                    <label for="file">이미지</label>
                    <input type="file" id="file" name="file" accept="image/png, image/jpg, image/jpeg, image.gif">
                    <div class="write_select_img">
                        <img src="" id="img" name="img"  alt="Review Image">
                    </div>
                </div>
                <%--            <div class="img-container">--%>
                <%--                <label for="file">이미지</label>--%>
                <%--                <input type="file" id="file" name="file" accept="image/*"/>--%>
                <%--                <div class="select_img">--%>
                <%--                    <img src="data:image/png;base64,${ImageParsor.parseBlobToBase64(review.img)}" id="img" name="img"  alt="Review Image">--%>
                <%--                </div>--%>

                <%--            </div>--%>
                <div class="review-write-btn">
                    <button type="button" id="submit" name="submit" onclick="checkValueWrite(form)">등록</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="script/review.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
