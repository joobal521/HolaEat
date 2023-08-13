<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-08
  Time: 오후 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>reviewlist</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/style/form.css">
    <style>
        #review-container {
            display: flex;
            flex-wrap: wrap;
        }

        #review{
            display: flex;
            flex-direction: column;
            align-items: center;
            width : 200px;
            height: 250px;
            border : solid 1px lightblue;
            padding: 10px;
            margin: 10px;

        }

        #img{
            width: 100px;
            height: 100px;
        }

        /*페이징*/

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            margin: 10px 0;
        }

        #pagination {
            margin-top: 20px;
        }


    </style>

</head>
<c:import url="header.jsp"/>
<body>
<div class="wrapper">
    <div>게시글 목록</div>


    <div>
        <a href="reviewform">
            글쓰기
        </a>
    </div>
<div id="review-container">
    <c:forEach items="${reviewlistPage}" var="review" varStatus="loop">
        <a href="<c:url value='/review/${review.reviewNo}'/>">
            <div id = review >
                <div>NO. ${review.reviewNo}</div>
                <div>작성자 : ${review.userId}</div>
                <div>제목 : ${review.title}</div>
                <c:if test="${imageMapPage[review.reviewNo] != null}">
                    <img src="data:image/png;base64,${imageMap[review.reviewNo]}" id="img" name="img" alt="Review Image">
                </c:if>
<%--                <img src="data:image/png;base64,${blobs[loop.index]}" id="img" name="img" alt="Review Image">--%>
            </div>
        </a>
    </c:forEach>
</div>

<%--    페이징--%>

    <div class="container">
        <h1>Review List</h1>
        <div><input type="text" id="searchInput" placeholder="Search by Title">
        </div>
            <button>검색</button>
        <ul id="reviewList">


        </ul>
        <div id="pagination">

        </div>
    </div>


</div>

</body>
<script src="${path}/resources/js/review.js"></script>
<%--<script src="${path}/resources/js/pagination.js"></script>--%>

<c:import url="footer.jsp"/>
</html>
