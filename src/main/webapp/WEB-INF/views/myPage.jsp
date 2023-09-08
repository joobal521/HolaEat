<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setDateHeader("Expires", 0); %>
<html>
<head>
    <title>myPage</title>
    <link rel="stylesheet" type="text/css" href="style/myPage.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

</head>
<c:import url="header.jsp"/>

<body>
  <section class="wrap">
    <section class="aside">
        <ul>
            <li>
                <a href="myInfo" class="menu-link">나의 정보</a>
            </li>
            <li>
                <a href="update" class="menu-link">회원 정보 수정</a>
            </li>
            <li>
                <a href="myReviewPage" class="menu-link">내가 쓴 글</a>
            </li>
            <li>
                <a href="myCommentPage" class="menu-link">내가 쓴 댓글</a>
            </li>
            <li>
                <a href="leave" class="menu-link">회원 탈퇴</a>
            </li>
        </ul>
    </section>
      <section class="section">

      </section>
  </section>

<script src="script/myPage.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>
