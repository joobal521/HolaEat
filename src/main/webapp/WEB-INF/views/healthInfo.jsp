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
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<c:import url="header.jsp"/>

<body>
<section>
    <div class="wrap">
        <div class="container">
<%--            <div id="health-box">--%>
<%--                <c:forEach items="${health}" var="health" varStatus="loop">--%>
<%--                    <a href="<c:url value='/review/${review.reviewNo}'/>">--%>
<%--                        <div id = review >--%>
<%--                            <div>NO. ${health.healthNo}</div>--%>
<%--                            <div>작성자 : ${review.userId}</div>--%>
<%--                            <div>제목 : ${review.title}</div>--%>
<%--                            <c:if test="${imageMap[review.reviewNo] != null}">--%>
<%--                                <img src="data:image/png;base64,${imageMap[review.reviewNo]}" id="img" name="img" alt="Review Image">--%>
<%--                            </c:if>--%>
<%--                                &lt;%&ndash;                <img src="data:image/png;base64,${blobs[loop.index]}" id="img" name="img" alt="Review Image">&ndash;%&gt;--%>
<%--                        </div>--%>
<%--                    </a>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
            <div class="con_1">
                <h2>음식이 싱거울 땐, 소금을 넣어라.</h2>
                <p>내용 1</p>
            </div>
            <div class="con_2">
                <h2>콜라가 미지근 할때, 냉장고에 넣어두면 좋다.</h2>
                <p>내용 2</p>
            </div>
            <div class="con_3">
                <h2>배가 고플 땐, 뭐라도 먹는 것이 도움이 된다.</h2>
                <p>내용 3</p>
            </div>
        </div>
    </div>
</section>
</body>
<c:import url="footer.jsp"/>

</html>
