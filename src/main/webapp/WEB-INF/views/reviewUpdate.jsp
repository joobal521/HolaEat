<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/style/form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

</head>
<c:import url="header.jsp"/>
<body>
<section id = "review-section">
    <h2>리뷰 게시판 수정</h2>

        <h2>게시글</h2>
        <c:if test="${review!=null}">


            <div id="review-contents">
                <form id="review-detail" enctype="multipart/form-data">
                    <div>
                        <input type="text" id="reviewNo" name="reviewNo" value="${review.get(0).reviewNo}" readonly>
                    </div>
                <div>
                    <input type="text" id="title" name="title" value="${review.get(0).title}" >
                </div>
                <div>
                    <input type="text" id="userId" name="userId" value="${review.get(0).userId}" >
                </div>
                <div>
                    <input type="text" id="content" name="content" value="${review.get(0).content}"  >
                </div>
                <div id="image-container">
                    <img src="${review.get(0).img}" id="img" name="img"  alt="Review Image">


                </div>
                <input type="datetime" id="created_at" readonly>
                <input type="datetime" id="modified_at" readonly>


<%--                    <button type="button" id="update" name="update" onclick="CheckValueUpdate(form)">수정</button>--%>
                    <input type="button" class="cancel" id="cancel" value="취소" onclick="goBack()">


                </form>
            </div>
        </c:if>


</section>
</body>
<script src="${path}/resources/js/review.js"></script>
<c:import url="footer.jsp"/>
</html>
