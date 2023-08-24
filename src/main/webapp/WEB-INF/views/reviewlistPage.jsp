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
    <link rel="stylesheet" type="text/css" href="/style/review.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/5d67eb2efc.js" crossorigin="anonymous"></script>
    <style>
        .scroll {
            background: none;
            width: auto;
            position: fixed;
            bottom: 5%;
            right: 5%;
        }


        .scroll button {
            background: none;
            border: 2px solid #265037;
            width: 50px;
            height: 50px;
            border-radius: 30px;
            outline: none;
        }

        .scroll button:active {
            box-shadow: 1px 1px 0 rgb(0, 0, 0, 0.5);
            position: relative;
            top: 2px;

        }

        .scroll button:hover {
            background: white;
            color: rgb(146, 175, 205);

            transition: 0.3s ease-in;
            cursor: grab;
        }

    </style>


</head>
<c:import url="header.jsp"/>
<body>
<div class="review-section">
    <h2>REVIEW</h2>
    <%--    pageNumber 확인용 : ${}/${pageNumber}--%>


    <div class="search_box">
        <form id="searchForm" method="get" action="/reviewlist/1">
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


    <div class="list-write-btn">
        <c:choose>
            <c:when test="${empty log}">
                <button class="list-write-btn" onclick="goToLogin()">글쓰기</button>
            </c:when>
            <c:otherwise>
                <a href="reviewform">글쓰기</a>
            </c:otherwise>
        </c:choose>
    </div>


    <div id="review-container">
        <c:forEach items="${reviewlistPage}" var="review" varStatus="loop">

            <div id=review>
                <a href="<c:url value='/review/${review.reviewNo}'/>">
                    <div class="user_profile">
                        <ul>
                            <li class="review-profile">NO. ${review.reviewNo}</li>
                            <li class="userId">작성자 : ${review.userId}</li>
                        </ul>
                    </div>

                        <%--이미지 출력--%>
                    <div class="review_img">
                        <c:set var="imageBase64" value="${imageMapPage[review.reviewNo]}"></c:set>
                        <c:choose>
                            <c:when test="${not empty imageBase64}">
                                <img src="data:image/jpeg;base64,${imageBase64}" id="img" name="img" alt="Review Image">
                            </c:when>
                            <c:otherwise>
                                <img src="img/reviewlist_thumb.png" id="img" name="img" alt="Review Image">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div id="review_title_check" class="review_title">제목 : ${review.title}</div>
                </a>
                <div class="review_like">
                    <c:choose>
                        <c:when test="${not empty log}">
<%--                            <button class="likeUp-btn" data-id="${review.reviewNo}">--%>
<%--                <span class="heart">--%>
<%--                    <c:forEach items="${likedList}" var="like">--%>
<%--                        <c:if test="${review.reviewNo == like.reviewNo}">--%>
<%--                            <c:set var="heart" value="1"></c:set>--%>
<%--                        </c:if>--%>
<%--                    </c:forEach>--%>
<%--                    <input type="hidden" value="${heart}" id="result">--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${heart == 1}">--%>
<%--                            <i class="fa-solid fa-heart"></i>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <i class="fa-regular fa-heart"></i>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </span>--%>
<%--                            </button>--%>
                            <button class="likeUp-btn" data-id="${review.reviewNo}"
                                    onclick="toggleHeart(${review.reviewNo})">
            <span class="heart">
                <i id="heart-icon-${review.reviewNo}"
                   class="fa ${heartMapPage[review.reviewNo] == 1 ? 'fa-solid fa-heart' : 'fa-regular fa-heart' }"></i>
            </span>
                                <input type="hidden" value="${heartMapPage[review.reviewNo]}"
                                       id="result-${review.reviewNo}">
                            </button>

                        </c:when>
                        <c:otherwise>
                            <button class="likeUp-logout" data-id="">
                                <i class="fa-regular fa-heart"></i>
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div>하트 총 개수 : ${review.reviewLike}</div>

            </div>

        </c:forEach>

    </div>
    <div class="paging" id="paging">
        <ul class="pagination">
            <c:choose>
                <c:when test="${pageNumber > 1}">
                    <li><a href="/reviewlist/1"><i class="fa-solid fa-backward-fast"></i></a></li>
                    <li><a href="/reviewlist/${pageNumber - 1}"><i class="fa-solid fa-caret-left"></i></a></li>


                </c:when>
                <c:otherwise>
                    <li><i class="fa-solid fa-backward-fast"></i></li>
                    <li><i class="fa-solid fa-caret-left"></i></li>

                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pageNumber <= 3}">
                    <c:forEach var="i" begin="1" end="5" step="1">
                        <li><a class="page-link" href="/reviewlist/${i}" style="
                        <c:if test="${i==pageNumber}">
                                background: #265037; color: white; font-weight: bold;
                        </c:if>
                                ">${i}</a></li>
                    </c:forEach>
                </c:when>
                <c:when test="${pageNumber > 3 && pageNumber <= totalPages - 2}">
                    <c:forEach var="i" begin="${pageNumber - 2}" end="${pageNumber + 2}" step="1">
                        <li><a class="page-link" href="/reviewlist/${i}" style="
                        <c:if test="${i==pageNumber}">
                                background: #265037; color: white; font-weight: bold;
                        </c:if>
                                ">${i}</a></li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="i" begin="${totalPages - 4}" end="${totalPages}" step="1">
                        <li><a class="page-link" href="/reviewlist/${i}" style="
                        <c:if test="${i==pageNumber}">
                                background: #265037; color: white; font-weight: bold;
                        </c:if>
                                ">${i}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pageNumber < totalPages}">
                    <li><a href="/reviewlist/${pageNumber + 1}"> <i class="fa-solid fa-caret-right"></i></a></li>
                    <li><a href="/reviewlist/${totalPages}"> <i class="fa-solid fa-forward-fast"></i> </a></li>
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
<script src="script/review.js"></script>
<script src="script/reviewList.js"></script>


<c:import url="footer.jsp"/>
</html>