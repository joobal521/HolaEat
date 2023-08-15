<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>마이페이지</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>
<body>
<body>
<section>
   <h1>마이페이지</h1>
    <img src="data:image/png;base64,${base64ImageData}" style=" max-width: 50%;  height: auto;"> <br />
    <div class="card">
        <form enctype="multipart/form-data">
            <input type="file" name="userProfileImg" accept="image/png, image/jpg, image/jpeg, image.gif">
            <input type="hidden" name="userId" id="userId" value="${login.userId}">
            <button type="button" class="profile-btn" onclick="updateImg(form)">프로필 사진 변경</button>
        </form>
    </div>
    <button type="button" id="update-btn" class="my-btn" onclick="location.href='update';">회원정보수정</button>
    <button type="button" id="leave-btn" class="my-btn" onclick="location.href='leave';" >회원탈퇴</button>

</section>
<script src="${path}/resources/js/mypage.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
