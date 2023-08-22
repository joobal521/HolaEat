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
    <div class="health-section">
        <h2>건강정보</h2>

        <div class="search_box">
            <form id="searchForm" method="get" action="/healt-list/1">
                <div class="searchTag">
                    <select title="검색 유형 선택" id="searchType" name="searchType">
                        <option name="all" value="all">전체 검색</option>
                        <option name="title" value="title">제목</option>
                    </select>
                    <input type="text" name="keyword" id="keyword" placeholder="검색어를 입력해 주세요." title="검색창"/>
                    <button id="searchBtn" onclick="" class="searchBtn">검색
                    </button>
                </div>
            </form>
        </div>

        <div class="health-container">

                <c:forEach items="${healthList}" var="health" varStatus="loop">
                    <div id="health">
                    <a href="<c:url value='/health/${health.healthNo}'/>">

                            <div class="user_profile">
                                <ul>
                                    <li class="health-profile">NO. ${health.healthNo}</li>
                                    <li class="admin">작성자 : 관리자</li>
                                </ul>
                            </div>

                                <%--이미지 출력--%>
                            <div class="health_img">
                                <c:set var="imageBase64" value="${imageMap[health.healthNo]}"></c:set>
                                <c:choose>
                                    <c:when test="${not empty imageBase64}">
                                        <img src="data:image/jpeg;base64,${imageBase64}" id="img" name="img" alt="Health Image">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="img/reviewlist_thumb.png" id="img" name="img" alt="Review Image">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="health_title">제목 : ${health.title}</div>
                            <div class="health_like"><i class="fa-regular fa-heart"></i></div>

                    </a>
                    </div>
                </c:forEach>

        </div>

        <div class="paging" id="paging">
            <ul class="pagination">
                <c:choose>
                    <c:when test="${pageNumber > 1}">
                        <li><a href="/health-list/1"><i class="fa-solid fa-backward-fast"></i></a></li>
                        <li><a href="/health-list/${pageNumber - 1}"><i class="fa-solid fa-caret-left"></i></a></li>


                    </c:when>
                    <c:otherwise>
                        <li><i class="fa-solid fa-backward-fast"></i></li>
                        <li><i class="fa-solid fa-caret-left"></i></li>

                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pageNumber <= 3}">
                        <c:forEach var="i" begin="1" end="5" step="1">
                            <li><a class="page-link" href="/health-list/${i}" style="
                            <c:if test="${i==pageNumber}">
                                    background: #265037; color: white; font-weight: bold;
                            </c:if>
                                    ">${i}</a></li>
                        </c:forEach>
                    </c:when>
                    <c:when test="${pageNumber > 3 && pageNumber <= totalPages - 2}">
                        <c:forEach var="i" begin="${pageNumber - 2}" end="${pageNumber + 2}" step="1">
                            <li><a class="page-link" href="/health-list/${i}" style="
                            <c:if test="${i==pageNumber}">
                                    background: #265037; color: white; font-weight: bold;
                            </c:if>
                                    ">${i}</a></li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="i" begin="${totalPages - 4}" end="${totalPages}" step="1">
                            <li><a class="page-link" href="/health-list/${i}"style="
                            <c:if test="${i==pageNumber}">
                                    background: #265037; color: white; font-weight: bold;
                            </c:if>
                                    ">${i}</a></li>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pageNumber < totalPages}">
                        <li><a href="/health-list/${pageNumber + 1}"> <i class="fa-solid fa-caret-right"></i></a></li>
                        <li><a href="/health-list/${totalPages}"> <i class="fa-solid fa-forward-fast"></i> </a></li>
                    </c:when>
                    <c:otherwise>
                        <li><i class="fa-solid fa-caret-right"></i></li>
                        <li><i class="fa-solid fa-forward-fast"></i></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>


        <div class="scroll">
            <button class="scrollTop" onclick="scrollToTop();"><i class="fa-solid fa-angle-up"></i></button>
        </div>



    </div>

</body>
<script src="script/health.js"></script>

<script>
    function scrollToTop() {
        $("html, body").animate({
            scrollTop : 0
        }, "slow");
    }


</script>
<c:import url="footer.jsp"/>

</html>
