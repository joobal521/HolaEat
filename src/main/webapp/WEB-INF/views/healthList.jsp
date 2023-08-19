<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-09
  Time: 오후 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>healthInfo</title>
    <link rel="stylesheet" type="text/css" href="/style/healthList.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>

<body>
<section>
    <div class="wrap">
        <div class="container">
         <div id="health-box">
        <c:forEach items="${healthList}" var="health" varStatus="loop">
            <a href="<c:url value='/health/${health.healthNo}'/>">
                <div id="health">
                    <div class="user_profile">
                        <ul>
                            <li class="healthprofile">NO. ${health.healthNo}</li>
                            <li class="admin">작성자 : 관리자</li>
                        </ul>
                    </div>

                        <%--이미지 출력--%>
            <div class="health_img">
                <c:set var="imageBase64" value="${imageMap[health.healthNo]}"></c:set>
                <c:if test="${not empty imageBase64}">
                    <img src="data:image/jpeg;base64,${imageBase64}" id="img" name="img" alt="Health Image" style=" max-width: 50%;  height: auto;">
                </c:if>
            </div>
            <div class="health_title">제목 : ${health.title}</div>
            <div class="health_like"><i class="fa-regular fa-heart"></i></div>
        </div>
        </a>
        </c:forEach>
    </div>
        </div>
    </div>
</section>
</body>
<c:import url="footer.jsp"/>

</html>