<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/style/review.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

    <c:if test="${not empty log}">
        <input type="hidden" id="logVal" value="${log}">
    </c:if>
</head>
<c:import url="header.jsp"/>
<body>
<div class="review-section">
    <h2>REVIEW 상세</h2>
    <div class="review-contents-all">
        <c:if test="${review!=null}">
            <div id="review-contents">
                <form id="review-detail" enctype="multipart/form-data">
                    <div class="review_detail_no">
                        <span>No.</span>
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
                        <textarea id="content" name="content" readonly>${review.content}</textarea>
                            <%--                        <input type="text" id="content" name="content" value="${review.content}">--%>
                    </div>
                    <div id="image-container">
                        <c:if test="${blob !=null}">
                            <img src="data:image/png;base64,${blob}" id="img" name="img" alt="Review Image">
                        </c:if>
                    </div>

                    <c:if test="${review.userId == log}">
                        <button type="button" id="update" name="update" onclick="redirectToReviewUpdate(reviewNo)">수정
                        </button>
                        <button type="button" id="delete" name="delete"
                                onclick="CheckValueDelete(document.getElementById('review_detail'), ${review.reviewNo})">
                            삭제
                        </button>
                    </c:if>
                    <button type="button" id="backToList" name="backToList" onclick="goBackToList()">목록</button>
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
                            onclick="redirectToLogin()"
                            placeholder="댓글을 작성하시려면 로그인을 해주세요." readonly
                        </c:when>
                        <c:otherwise>
                            placeholder="주제와 무관한 댓글, 악플은 삭제될 수 있습니다."
                        </c:otherwise>
                    </c:choose>></textarea>
                    <div class="commentBtn">
                        <c:if test="${not empty log}">
                            <input type="button" id="commentBtn" value="등록하기" onclick="addComment()">
                            <input type="button" id="commentDelBtn" value="취소하기" onclick="delComment()">
                        </c:if>
                    </div>
                </form>
            </div>
            <!-- 댓글 출력할 공간 -->
            <div id="comment-container">
                <form class="comment-item-${commentId}">

                </form>
            </div>
        </div>
    </div>

</div>
</body>
<script src="/script/review.js"></script>
<script src="/script/comment.js"></script>


<c:import url="footer.jsp"/>
</html>