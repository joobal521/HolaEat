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
    <link rel="stylesheet" type="text/css" href="style/form.css">
    <link rel="stylesheet" type="text/css" href="style/review.css">
    <script src="https://kit.fontawesome.com/5d67eb2efc.js" crossorigin="anonymous"></script>


</head>
<c:import url="header.jsp"/>
<body>
<div class="wrapper">
    <div>게시글 목록</div>
    pageNumber 확인용 : ${pageNumber}

    <div>
        <c:choose>
            <c:when test="${empty log}">
                <a href="login">글쓰기</a>
            </c:when>
            <c:otherwise>
                <a href="reviewform">글쓰기</a>
            </c:otherwise>
        </c:choose>
    </div>
    <div id="review-container">
        <c:forEach items="${reviewlistPage}" var="review" varStatus="loop">
            <a href="<c:url value='/review/${review.reviewNo}'/>">
                <div id=review>
                    <div class="user_profile">
                        <ul>
                            <li class="reviewprofile">NO. ${review.reviewNo}</li>
                            <li class="userId">작성자 : ${review.userId}</li>
                        </ul>
                    </div>

                        <%--이미지 출력--%>
                    <div class="review_img">
                    <c:set var="imageBase64" value="${imageMapPage[review.reviewNo]}"></c:set>
                    <c:if test="${not empty imageBase64}">
                        <img src="data:image/jpeg;base64,${imageBase64}" id="img" name="img" alt="Review Image">
                    </c:if>
                        </div>
                    <div class="review_title">제목 : ${review.title}</div>
                    <div class="review_like"><i class="fa-regular fa-heart"></i></div>
                </div>
            </a>
        </c:forEach>
    </div>

    <%--    페이징--%>

    <div class="container">
        <h1>Review List</h1>

        <div class="paging" id="paging">

            <ul class="pagination">
                <li><a href="/reviewlist/1"><<</a></li>

                <li><a href="/reviewlist/1">1</a></li>
                <c:forEach var="i" begin="2" end="${totalPages}" step="1">
                    <li><a href="/reviewlist/${i}">${i}</a></li>
                </c:forEach>
                <li><a href="/reviewlist/${totalPages}"> >> </a></li>
            </ul>

        </div>

        <div class="search_box">
            <form id="searchForm"  method="get" action="/reviewlist/1">
                <%--            <form id="searchForm" onsubmit="return false;" autocomplete="off">--%>
                <div class="searchTag">
                    <select title="검색 유형 선택" id="searchType" name="searchType">
                        <option name="all" value="all">전체 검색</option>
                        <option name="title" value="title">제목</option>
                        <option name="author" value="author">작성자</option>
                    </select>
                    <input type="text" name="keyword" id="keyword" placeholder="검색어를 입력해 주세요." title="검색창"/>
                    <button id="searchBtn" onclick="" class="searchBtn">검색
                    </button>
                </div>
            </form>
        </div>


    </div>

</body>
<script src="script/review.js"></script>

<%--<script src="${path}/resources/js/pagination.js"></script>--%>

<c:import url="footer.jsp"/>
</html>