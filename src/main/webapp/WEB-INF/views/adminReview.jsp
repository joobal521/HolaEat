<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <h1 class="admin-title">후기 게시판 관리</h1>
    <div class="pagination">
        <button id="prevBtn" onclick="preBtn()">이전</button>
        <button id="nextBtn" onclick="nextBtn()">다음</button>
    </div>

    <div class="admin-menu">
        <table>
            <thead>
            <tr>
                <th>후기글 No</th>
                <th>후기글 이름</th>
                <th>작성자</th>
                <th>내용</th>
                <th>좋아요</th>
                <th>삭제</th>
            </tr>
            </thead>
            <tbody class="admin-reviewList">
            <c:forEach items="${reviewList}" var="review">
                <tr id="${review.reviewNo}">
                    <td>${review.reviewNo}</td>
                    <td class="reviewTitle">${review.title}</td>
                    <td class="reviewUserId">${review.userId}</td>
                    <td class="reviewContent">${review.content}</td>
                    <td class="reviewContent">${review.reviewLike}</td>
                    <td>
                        <button class="removeBtn" id="${review.reviewNo}" data-id="${review.reviewNo}" onclick="removeReview(this)">삭제하기</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>


</body>

