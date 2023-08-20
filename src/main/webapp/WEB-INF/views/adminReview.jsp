<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <h1>후기 게시판 관리</h1>
    <div class="admin-menu">
        <table>
            <thead>
            <tr>
                <th>후기글 No</th>
                <th>후기글 이름</th>
                <th>작성자</th>
                <th>내용</th>
<%--                <th>좋아요</th>--%>
                <th>삭제</th>
            </tr>
            </thead>
            <tbody class="admin-reviewList">
            <c:forEach items="${reviewList}" var="review">
                <tr>
                    <td>${review.reviewNo}</td>
                    <td class="reviewTitle">${review.title}</td>
                    <td class="reviewUserId">${review.userId}</td>
                    <td class="reviewContent">${review.content}</td>
<%--                    <td class="reviewContent">${review.reviewLike}</td>--%>

                    <td>
                        <button class="removeBtn" data-id="${review.reviewNo}">삭제하기</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>

        $(document).ready(function() {

            $(".removeBtn").click(function () {
                var reviewNo = $(this).data("id");

                $.ajax({
                    url: "adminReview/delete/" + reviewNo,
                    method: "DELETE",
                    success: function (response) {
                        // Handle success if needed
                        alert("삭제하기: " + reviewNo);
                    },
                    error: function () {
                        alert("삭제에 실패했습니다.");
                    }
                });
            });
        })




        // $(document).ready(function() {
        //     $("#filterToggleAllergy").click(function() {
        //         toggleFilter(".allergy", "예", $(this));
        //     });
        //
        //     $("#filterToggleMonth").click(function() {
        //         toggleFilter(".month", "예", $(this));
        //     });
        //
        //
        //     function toggleFilter(columnClass, targetValue, buttonElement) {
        //         var isActive = buttonElement.hasClass("active");
        //
        //         $(".admin-ingrList tr").each(function() {
        //             var cell = $(this).find(columnClass);
        //             var cellValue = cell.text();
        //
        //             if (isActive && cellValue !== targetValue) {
        //                 $(this).hide();
        //             } else {
        //                 $(this).show();
        //             }
        //         });
        //
        //         buttonElement.toggleClass("active");
        //     }
        //
        // });
    </script>
</body>

