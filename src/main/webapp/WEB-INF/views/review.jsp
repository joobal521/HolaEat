<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="../style/grid.css">
    <link rel="stylesheet" type="text/css" href="../style/review.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>


    <style>
        #img {
            width: 500px;
            height: 500px;
        }
    </style>
</head>
<c:import url="header.jsp"/>
<body>
<section id = "review-section">
    <h2>리뷰 게시판 상세</h2>
        <h2>게시글</h2>
        <c:if test="${review!=null}">
            <div id="review-contents">
                <form id="review_detail" enctype="multipart/form-data">
                    <div class="review_detail_no">
                        <label for="title">No.</label>
                        <input type="text" id="reviewNo" name="reviewNo" value="${review.reviewNo}" readonly>
                    </div>
                <div class="review_detail_title">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="title" value="${review.title}" readonly>
                </div>
                <div class="review_detail_userId">
                    <label for="userId">작성자</label>
                    <input type="text" id="userId" name="userId" value="${review.userId}" readonly>
                </div>

                <div class="review_detail_content">
                    <label for="content">내용</label>
                    <input type="text" id="content" name="content" value="${review.content}" readonly >
                </div>
                <div id="image-container">
                    <img src="data:image/png;base64,${blob}" id="img" name="img"  alt="Review Image">

                </div>
<%--                    <c:when test="${not empty review}">--%>
<%--                <input type="datetime" id="created_at" readonly value="${review.createdAt}"> --%>
<%--                <input type="datetime" id="modified_at" readonly value="${review.modifiedAt}">--%>
<%--                    </c:when>--%>

                    <button type="button" id="update" name="update" onclick="redirectToReviewUpdate(reviewNo)">수정</button>
                    <button type="button" id="delete" name="delete" onclick="CheckValueDelete(document.getElementById('review_detail'), ${review.reviewNo})">삭제</button>

                </form>
            </div>
        </c:if>



    <!-- 댓글 부분 -->
    <div class="comment-section">
        <div class="comment-write">
            <span>댓글쓰기 </span>
            <form method="POST" class="comment">
            <textarea cols="80" rows="10" id="msg-box" name="msg"
                    <c:choose>
                        <c:when test="${empty log}">
                            placeholder="댓글을 작성하시려면 로그인을 해주세요." readonly
                        </c:when>
                        <c:otherwise>
                            placeholder="주제와 무관한 댓글, 악플은 삭제될 수 있습니다."
                        </c:otherwise>
                    </c:choose>></textarea>
                <div class="commentBtn">
                    <input type="button" id="commentBtn" value="등록하기" onclick="addComment()">
                </div>
            </form>
        </div>
        <!-- 댓글 출력할 공간 -->
        <div id="comment-container">
            <c:if test="${not empty reviewComment}">
                <c:forEach var="li" items="${reviewComment}">
                    <div class="comment-item">
                        <p>${li.userId}</p>
                        <br>
                        <p>${li.content}</p>
<%--                        <p>${li.createdAt}</p>--%>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>


</section>
</body>
<script src="script/review.js"></script>
<script src="script/comment.js"></script>
<c:import url="footer.jsp"/>
</html>
