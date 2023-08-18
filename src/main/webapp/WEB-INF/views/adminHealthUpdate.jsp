<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-18
  Time: 오후 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin-health</title>
</head>
<body>
<section>
    <h2>리뷰 게시판 수정</h2>

    <c:if test="${review!=null}">

        <div id=health-contents">
            <form id="health-detail" enctype="multipart/form-data">
                <div>
                    <input type="text" id="healthNo" name="healthNo" value="${health.healthNo}" readonly>
                </div>
                <div>
                    <input type="text" id="title" name="title" value="${health.title}">
                </div>
                <div>
                    <input type="text" id="admin" name="admin" value="관리자" readonly>
                </div>
                <div>
                    <input type="text" id="content" name="content" value="${health.content}">
                </div>

                <div class="img-container">
                    <label for="file">이미지</label>
                    <input type="file" id="file" name="file" accept="image/*"/>
                    <div class="select_img">
                        <img src="data:image/png;base64,${ImageParsor.parseBlobToBase64(review.img)}" id="img" name="img"  alt="Review Image">
                    </div>

                </div>

                <input type="datetime" id="created_at" name="created_at" readonly>
                <input type="datetime" id="modified_at" name="modified_at" readonly>

                <button type="button" id="update" name="update" onclick="CheckValueUpdate(form, ${health.reviewNo})">
                    수정
                </button>
                <input type="button" class="cancel" id="cancel" value="수정 취소" onclick="goBack()">

            </form>
        </div>
    </c:if>

</section>


</body>
</html>
