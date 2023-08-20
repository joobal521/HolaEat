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
    <title>myInfo</title>
  <style>
    /* 스타일 초기화 */
    body, html {
      margin: 0;
      padding: 0;
    }

    /* 전체 컨테이너 스타일링 */
    .container {
      display: flex;
      background-color: #f7f7f7;
      justify-content: center

    }

    /* 카드 스타일링 */
    .card {
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      padding: 20px;
      text-align: center;
      width: 300px;
      margin-top: 30px;
    }

    /* 프로필 이미지 스타일링 */
    .card img {
      width: 200px;
      height: auto;
      border-radius: 10px;
      margin-bottom: 10px;
    }

    /* 프로필 버튼 스타일링 */
    .profile-btn {
      background-color: #265037;
      color:#DCE4CF;
      border: none;
      border-radius: 4px;
      padding: 8px 16px;
      cursor: pointer;
      margin-top: 10px;
      margin-bottom: 20px;
    }

    .profile-btn:hover {
      background-color: aliceblue;
      color: #1e6b7b;
    }

    /* 정보 목록 스타일링 */
    .my-info ul {
      list-style-type: none;
      padding: 0;
      margin: 0;
    }

    .my-info li {
      margin-bottom: 10px;
    }

    .my-info span {
      font-weight: bold;
    }

  </style>
</head>
<body>
<div class="container">
<div class="card">
  <form enctype="multipart/form-data">
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
    <input type="file" name="userProfileImg" accept="image/png, image/jpg, image/jpeg, image.gif">
    <input type="hidden" name="userId" id="userId" value="${sessionScope.log}">
    <button type="button" class="profile-btn" onclick="updateImg(form)">프로필 사진 변경</button>
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

</body>
</html>
