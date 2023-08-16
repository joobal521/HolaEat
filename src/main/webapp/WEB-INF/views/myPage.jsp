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
    <c:if test="${empty log}">
        <c:url var="login" value="/login"></c:url>
        <c:redirect url="${login}"></c:redirect>
    </c:if>
    <c:choose>
        <c:when test="${empty log.profileImg}">
            <img src="/img/belle.jpg" width="200px"><br/>
        </c:when>
        <c:otherwise>
            <img src="data:image/png;base64,${profileImg}" style=" max-width: 50%;  height: auto;"> <br />
        </c:otherwise>
    </c:choose>
    <div class="card">
        <form enctype="multipart/form-data">
            <input type="file" name="userProfileImg" accept="image/png, image/jpg, image/jpeg, image.gif">
            <input type="hidden" name="userId" id="userId" value="${sessionScope.log}">
            <button type="button" class="profile-btn" onclick="updateImg(form)">프로필 사진 변경</button>
        </form>
    </div>
    <button type="button" id="update-btn" class="my-btn" onclick="location.href='update';">회원정보수정</button>
    <button type="button" id="leave-btn" class="my-btn" onclick="location.href='leave';" >회원탈퇴</button>

</section>
<script src="script/mypage.js"></script>
</body>
<c:import url="footer.jsp"/>
</html>
