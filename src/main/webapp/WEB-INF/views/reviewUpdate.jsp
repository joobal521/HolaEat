<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.spring.holaeat.util.ImageParsor" %>

<html>
<head>
    <title>Title</title>
<%--    <c:set var="path" value="${pageContext.request.contextPath}"/>--%>
    <link rel="stylesheet" type="text/css" href="/style/review.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<c:import url="header.jsp"/>
<body>
<div class="review-section">
    <h2>REVIEW 수정</h2>
    <div class="review-contents-all">
    <c:if test="${review!=null}">

        <div id="review-contents">
            <form id="review-update" enctype="multipart/form-data">
                <div class="review_detail_no">
                    <span>No.</span>
                    <input type="text" id="reviewNo" name="reviewNo" value="${review.reviewNo}" readonly>
                </div>
                <div class="review_detail_title">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="title" value="${review.title}">
                </div>
                <div class="review_detail_userId">
                    <label for="userId">작성자</label>
                    <input type="text" id="userId" name="userId" value="${review.userId}" readonly>
                </div>
                <div class="review_detail_content">
                    <label for="content">내용</label>
                    <textarea id="content" name="content">${review.content}</textarea>
<%--                    <input type="text" id="content" name="content" value="${review.content}">--%>
                </div>

                <div id="img-container">
                    <label for="file">이미지</label>
                    <input type="file" id="file" name="file" accept="image/*" />

                    <c:choose>
                        <c:when test="${blob != null}">
                            <img src="data:image/png;base64,${ImageParsor.parseBlobToBase64(review.img)}" id="img" name="img" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="" id="img" name="img" alt="" style="display: none;">
                        </c:otherwise>
                    </c:choose>

                    <input type="hidden" id="imgCheck" name="imgCheck" value="${ImageParsor.parseBlobToBase64(review.img)}">
                </div>

<%--                <input style="width: 100px" type="datetime" id="created_at" name="created_at" readonly>--%>
<%--                <input style="width: 100px" type="datetime" id="modified_at" name="modified_at" readonly>--%>

                <br/>
                <button type="button" id="update" name="update" onclick="CheckValueUpdate(form, ${review.reviewNo})">
                    수정
                </button>
                <button type="button" class="cancel" id="cancel"  onclick="goBack()">수정 취소</button>

            </form>
        </div>
    </c:if>
    </div>
</div>


</body>
<%--수정페이지에서 오류나서 추가--%>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('file').addEventListener('change', function() {
            var imgElement = document.getElementById('img');
            var imgCheckInput = document.getElementById('imgCheck');

            if (this.files && this.files[0]) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    imgElement.src = e.target.result;
                    imgElement.style.display = 'block';
                    imgCheckInput.value = e.target.result;
                };

                reader.readAsDataURL(this.files[0]);
            } else {
                imgElement.style.display = 'none';
                imgCheckInput.value = '';
            }
        });
    });
</script>

<script src="/script/review.js"></script>

<c:import url="footer.jsp"/>
</html>
