<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-18
  Time: 오후 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin-health</title>
</head>

<body>
<h1>건강 정보 글 관리 목록</h1>
<div class="wrap">
    <div class="container">
        <button type="button" id="write-btn"> <a href="HealthForm">글쓰기</a></button>
        <div id="health-box">
            <c:forEach items="${healthInfo}" var="health" varStatus="loop">
                <a href="<c:url value='/health/${health.healthNo}'/>">
                    <div id="health">
                        <div class="admin_profile">
                            <ul>
                                <li class="healthprofile">NO. ${health.healthNo}</li>
                                <li class="admin">작성자 : 관리자</li>
                            </ul>
                        </div>

                            <%--이미지 출력--%>
                        <div class="health_img">
                            <c:set var="imageBase64" value="${imageMapPage[health.healthNo]}"></c:set>
                            <c:if test="${not empty imageBase64}">
                                <img src="data:image/jpeg;base64,${imageBase64}" id="img" name="img" alt="Review Image">
                            </c:if>
                        </div>
                        <div class="review_title">제목 : ${health.title}</div>
                        <div class="review_like"><i class="fa-regular fa-heart"></i></div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>

</body>

</html>
