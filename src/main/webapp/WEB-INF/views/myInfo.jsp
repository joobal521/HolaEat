<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-18
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty log}">
  <c:url var="login" value="/login"></c:url>
  <c:redirect url="${login}"></c:redirect>
</c:if>
<c:choose>
  <c:when test="${empty profileImg}">
    <img src="img/belle2.jpg" width="200px"><br/>
  </c:when>
  <c:otherwise>
    <img src="data:image/png;base64,${ImageParsor.parseBlobToBase64(profileImg)}" style=" max-width: 50%;  height: auto;"> <br />
  </c:otherwise>
</c:choose>
<div class="container">
<div class="card">
  <form enctype="multipart/form-data">
    <input type="file" name="userProfileImg" accept="image/png, image/jpg, image/jpeg, image.gif">
    <input type="hidden" name="userId" id="userId" value="${sessionScope.log}">
    <button type="button" class="profile-btn" onclick="updateImg(form)">프로필 사진 변경</button>
  </form>
  <div class="my-info">
    <ul>
      <li><Span>아이디 : </Span>${sessionScope.log}</li>
      <li><span>이름 : </span>${sessionScope.userName}</li>
      <li><span>이메일 : </span>${sessionScope.userEmail}</li>
    </ul>
  </div>
</div>

</div>

</body>
</html>
