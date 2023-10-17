<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-18
  Time: 오후 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spring.holaeat.util.ImageParsor" %>
<html>
<head>
    <title>admin-health</title>
    <link rel="stylesheet" type="text/css" href="/style/health.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<c:import url="header.jsp"/>
<body>
<c:choose>
    <c:when test="${empty authority}">
        <c:redirect url="/" /> <!-- authority가 비어있을 경우 인덱스로 리다이렉션 -->
    </c:when>
</c:choose>
<div class="health-section">
    <h2>건강 정보 글 수정</h2>
    <c:if test="${health!=null}">
        <div id="health-contents">
            <form id="health-update" enctype="multipart/form-data">
                <div class="health_detail_no">
                    <span>No.</span>
                    <input type="text" id="healthNo" name="healthNo" value="${health.healthNo}" readonly>
                </div>
                <div class="health_detail_title">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="title" value="${health.title}">
                </div>
                <div class="health_detail_userId">
                    <label for="admin">작성자</label>
                    <input type="text" id="admin" name="admin" value="관리자" readonly>
                </div>
                <div class="health_detail_content">
                    <label for="content">내용</label>
                    <textarea id="content" name="content">${health.content}</textarea>
                </div>

                <div class="img-container">
                    <label for="file">이미지</label>
                    <input type="file" id="file" name="file" accept="image/*"/>

                    <c:choose>
                        <c:when test="${blob != null}">
                            <img src="data:image/png;base64,${ImageParsor.parseBlobToBase64(health.img)}" id="img" name="img" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="" id="img" name="img" alt="" style="display: none;">
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" id="imgCheck" name="imgCheck" value="${ImageParsor.parseBlobToBase64(health.img)}">
                </div>

<%--                <input type="datetime" id="created_at" name="created_at" readonly>--%>
<%--                <input type="datetime" id="modified_at" name="modified_at" readonly>--%>

                <button type="button" id="update" name="update" onclick="CheckValueUpdate(form, ${health.healthNo})">
                    수정
                </button>
                <button type="button" class="cancel" id="cancel"  onclick="goBack()">수정 취소</button>

            </form>
        </div>
    </c:if>

</div>
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
<script src="script/adminHealth.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>
