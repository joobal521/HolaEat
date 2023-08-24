<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-18
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spring.holaeat.util.ImageParsor" %>
<html>
<head>
    <title>myInfo</title>
  <link rel="stylesheet" type="text/css" href="/style/myInfo.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>
<body>
<div class="container">

<div class="card">

  <c:if test="${empty log}">
    <c:url var="login" value="/login"></c:url>
    <c:redirect url="${login}"></c:redirect>
  </c:if>
  <c:choose>
    <c:when test="${empty profileImg}">
      <img src="img/belle2.jpg" width="200px"><br/>
    </c:when>
    <c:otherwise>
      <img src="data:image/png;base64,${ImageParsor.parseBlobToBase64(profileImg)}" style="max-width: 50%; height: auto;"> <br />
    </c:otherwise>
  </c:choose>
<%--  <img src="${profileImgUrl}" class="card-img" alt="프로필 이미지"><br/>--%>
  <form id="profileForm" enctype="multipart/form-data">
    <input type="file" id="file" name="userProfileImg" accept="image/*" onchange="writeThumbnail()">
    <div class="write_select_img" id="image-preview">
      <img src="" id="img" name="img" alt="">
    </div>
    <input type="hidden" name="userId" id="userId" value="${sessionScope.log}">
    <button type="button" class="profile-btn" onclick="updateImg(form, ${profileNo})">프로필 사진 변경</button>
  </form>
  <div class="my-info">
    <ul>
      <li><Span>아이디 : </Span>${sessionScope.log}</li>
      <li><span>이름 : </span>${userResponseDto.userName}</li>
      <li><span>이메일 : </span>${userResponseDto.userEmail}</li>
<%--      <li><h3>나의 건강 정보</h3></li>--%>
<%--      <li><span>성별 :</span></li>--%>
<%--      <li><span>나이 : </span></li>--%>
<%--      <li><span>키 : </span></li>--%>
<%--      <li><span>몸무게 : </span></li>--%>

    </ul>
  </div>
</div>

</div>
<script src="/script/myPage.js"></script>
<script src="/script/myInfo.js"></script>
</body>
</html>
