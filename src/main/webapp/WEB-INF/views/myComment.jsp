<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-24
  Time: 오전 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>myComment</title>
    <link rel="stylesheet" type="text/css" href="/style/my.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>
<div class="my-review">
    <table class="board-table">
        <thead>
        <tr>
            <th>댓글 No</th>
            <th>작성자</th>
            <th>내용</th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:if test="${empty myCommentList }">
            <div style="font-size: 17px; font-weight: bold;">작성글이 없습니다.</div>
        </c:if>
        <c:forEach items="${myCommentList}" var="myComment">
            <tr>

                <td>${myComment.commentId}</td>
                <td>${myComment.userId}</td>
                <td>${myComment.content}</td>
                <td>
                    <button class="removeBtn" data-id="${myComment.commentId}">삭제</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="more_btn">
        <button class="more-btn" id="moreView-btn">더보기</button>
    </div>
</div>
<script>
    $(document).ready(function() {

        $(".removeBtn").click(function () {
            var commentId = $(this).data("id");// FormData 객체 생성
            Swal.fire({
                title: '정말 삭제하시겠습니까?',
                text: '댓글 삭제 후 복구 불가합니다.',
                icon: 'warning',
                showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                confirmButtonColor: '#265037', // confrim 버튼 색깔 지정
                confirmButtonText: '확인', // confirm 버튼 텍스트 지정
                cancelButtonText: '취소', // cancel 버튼 텍스트 지정
            }).then((result) => {

                if (result.isConfirmed) {
                    $.ajax({
                        type: 'DELETE',
                        url: "/comment/"+commentId+"/delete",
                        success: function (response) {
                            if (response.message === 'success') {
                                const reviewNo = $('#reviewNo').val();
                                location.href = "mypage";
                                loadComments(reviewNo);

                            }
                            // else {
                            //     alert(response.message);
                            // }
                        },
                        error: function (error) {
                            console.error(error);
                        }
                    });
                }
            });

        });
    })

    //더보기 버튼
    $(function() {
        $("tr").hide();
        $("tr").slice(0, 4).show(); // 초기갯수
        $("#moreView-btn").click(function(e) { // 더보기 버튼 클릭
            e.preventDefault();
            $("tr:hidden").slice(0, 4).show(); // 클릭시 리스트 갯수 지정
            if ($("tr:hidden").length == 0) { // 컨텐츠 남아있는지 확인
                $("#moreView-btn").hide(); //더이상의 리스트가 없다면 버튼 사라짐
            }
        });
    });

</script>

</body>
</html>
