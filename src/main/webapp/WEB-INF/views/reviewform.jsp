<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-08
  Time: 오후 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>reviewform</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="../style/form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

</head>
<c:import url="header.jsp"/>
<body>
<section>
    <div class="wrapper">
        <div>게시글 작성</div>

        <form enctype="multipart/form-data">
            <div>
                <input type="text" id="title" name="title">
                <p class="error" id="error-title">제목을 입력해주세요.</p>
            </div>
            <div>
                <textarea id="content" name="content" ></textarea>
            </div>
            <div>
                <input type="file" id="img" name="img" accept="image/png, image/jpg, image/jpeg, image.gif">
            </div>
            <div>
                <button type="button" id="submit" name="submit" onclick="checkValueWrite(form)">등록</button>
            </div>
        </form>

    </div>
</section>
<script src="${path}/resources/js/review.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
