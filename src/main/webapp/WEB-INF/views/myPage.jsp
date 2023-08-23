<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>myPage</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="style/myPage.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <style>



        /* Add your own custom styles here */
    </style>
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
                <a href="" class="menu-link">나의 식단 보기</a>
            </li>
            <li>
                <a href="leave" class="menu-link">회원 탈퇴</a>
            </li>
        </ul>
    </section>
      <section class="section">

      </section>
  </section>
  <script>
  </script>

<script src="script/myPage.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>
