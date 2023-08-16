<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>reviewform</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="style/form.css">
    <link rel="stylesheet" type="text/css" href="style/review.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <div class="wrapper">
        <div>게시글 작성</div>

        <div id="reviewform-container">
            <form id="reviewform" enctype="multipart/form-data">
                <div class="reviewform-title">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="title">
                    <p class="error" id="error-title">제목을 입력해주세요.</p>
                </div>
                <div class="reviewform-content">
                    <label for="content">내용</label>
                    <textarea id="content" name="content"></textarea>
                </div>
                <div class="reviewform-img">
                    <input type="file" id="img" name="img" accept="image/png, image/jpg, image/jpeg, image.gif">
                </div>
                <%--            <div class="img-container">--%>
                <%--                <label for="file">이미지</label>--%>
                <%--                <input type="file" id="file" name="file" accept="image/*"/>--%>
                <%--                <div class="select_img">--%>
                <%--                    <img src="data:image/png;base64,${ImageParsor.parseBlobToBase64(review.img)}" id="img" name="img"  alt="Review Image">--%>
                <%--                </div>--%>

                <%--            </div>--%>
                <div class="reviewform-btn">
                    <button type="button" id="submit" name="submit" onclick="checkValueWrite(form)">등록</button>
                </div>
            </form>
        </div>
    </div>
</section>
<script src="script/review.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
