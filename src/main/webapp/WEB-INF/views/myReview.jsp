<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-23
  Time: 오후 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>my review</title>
    <link rel="stylesheet" type="text/css" href="/style/my.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

</head>
<body>
<div class="my-review">
<table class="board-table">
    <thead>
    <tr>
        <th>후기글 No</th>
        <th>후기글 이름</th>
        <th>작성자</th>
        <th>내용</th>
        <th></th>
        <th></th>

    </tr>
    </thead>
    <tbody>
    <c:if test="${empty myReviewList }">
        <div style="font-size: 17px; font-weight: bold;">작성글이 없습니다.</div>
    </c:if>
    <c:forEach items="${myReviewList}" var="myReview">
        <tr>

            <td>${myReview.reviewNo }</td>
            <td><a href="<c:url value='/review/${myReview.reviewNo}'/>">${myReview.title}</a></td>
            <td>${myReview.userId }</td>
            <td>${myReview.content }</td>
            <td>
                <button type="button" class="updateBtn" data-id="${myReview.reviewNo}"  onclick="redirectToHealthUpdate(${myReview.reviewNo})" >수정</button>
            </td>
            <td>
                <button class="removeBtn" data-id="${myReview.reviewNo}">삭제</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--    <div class="paging" id="paging">--%>
<%--        <ul class="pagination">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${pageNumber > 1}">--%>
<%--                    <li><a href="/myReviewPage/1"><i class="fa-solid fa-backward-fast"></i></a></li>--%>
<%--                    <li><a href="/myReviewPage/1/"${pageNumber - 1}"><i class="fa-solid fa-caret-left"></i></a></li>--%>


<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <li><i class="fa-solid fa-backward-fast"></i></li>--%>
<%--                    <li><i class="fa-solid fa-caret-left"></i></li>--%>

<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${pageNumber <= 3}">--%>
<%--                    <c:forEach var="i" begin="1" end="5" step="1">--%>
<%--                        <li><a class="page-link" href="/myReviewPage/${i}" style="--%>
<%--                        <c:if test="${i==pageNumber}">--%>
<%--                                background: #265037; color: white; font-weight: bold;--%>
<%--                        </c:if>--%>
<%--                                ">${i}</a></li>--%>
<%--                    </c:forEach>--%>
<%--                </c:when>--%>
<%--                <c:when test="${pageNumber > 3 && pageNumber <= totalPages - 2}">--%>
<%--                    <c:forEach var="i" begin="${pageNumber - 2}" end="${pageNumber + 2}" step="1">--%>
<%--                        <li><a class="page-link" href="/myReviewPage/${i}" style="--%>
<%--                        <c:if test="${i==pageNumber}">--%>
<%--                                background: #265037; color: white; font-weight: bold;--%>
<%--                        </c:if>--%>
<%--                                ">${i}</a></li>--%>
<%--                    </c:forEach>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <c:forEach var="i" begin="${totalPages - 4}" end="${totalPages}" step="1">--%>
<%--                        <li><a class="page-link" href="/myReviewPage/${i}"style="--%>
<%--                        <c:if test="${i==pageNumber}">--%>
<%--                                background: #265037; color: white; font-weight: bold;--%>
<%--                        </c:if>--%>
<%--                                ">${i}</a></li>--%>
<%--                    </c:forEach>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${pageNumber < totalPages}">--%>
<%--                    <li><a href="/myReviewPage/${pageNumber + 1}"> <i class="fa-solid fa-caret-right"></i></a></li>--%>
<%--                    <li><a href="/myReviewPage/${totalPages}"> <i class="fa-solid fa-forward-fast"></i> </a></li>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <li><i class="fa-solid fa-caret-right"></i></li>--%>
<%--                    <li><i class="fa-solid fa-forward-fast"></i></li>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </ul>--%>
<%--    </div>--%>
</div>
<script src="/script/myPage.js"></script>
</body>
</html>
