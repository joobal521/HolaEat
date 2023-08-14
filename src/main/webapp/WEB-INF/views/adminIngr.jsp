<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>재료 관리</title>
</head>
<body>
<h1>재료 관리</h1>

<button id="sortButton">정렬</button>
<select id="sortSelect">
    <option value="ingrId">재료 ID</option>
    <option value="ingrName">재료 이름</option>
    <option value="allergy">알러지</option>
    <option value="month">이달의 재료</option>
</select>

<table>
    <thead>
    <tr>
        <th>재료 ID</th>
        <th>재료 이름</th>
        <th>알러지</th>
        <th>이달의 재료</th>
        <th>수정</th>
        <th>삭제</th>
    </tr>
    </thead>
    <tbody class="admin-ingrList">
    <c:forEach items="${ingredientList}" var="ingrList">
        <tr>
            <td>${ingrList.ingrId}</td>
            <td class="ingrName">${ingrList.ingrName}</td>
            <td class="allergy">${ingrList.allergy ? '예' : '아니오'}</td>
            <td class="month">${ingrList.month ? '예' : '아니오'}</td>
            <td>
                <button class="editBtn" data-id="${ingrList.ingrId}">수정하기</button>
                <button class="updateBtn" data-id="${ingrList.ingrId}" style="display: none;">수정완료</button>
                <button class="cancelBtn" data-id="${ingrList.ingrId}" style="display: none;">수정취소</button>
            </td>
            <td>
                <button class="removeBtn" data-id="${ingrList.ingrId}">삭제하기</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <input type="button" id="addBtn" value="추가하기">
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

    $(document).ready(function() {
        $(".editBtn").click(function() {
            var row = $(this).closest("tr");
            var ingrNameCell = row.find(".ingrName");
            var allergyCell = row.find(".allergy");
            var monthCell = row.find(".month");

            // 현재 데이터를 input 요소로 변경
            ingrNameCell.html("<input type='text' class='editIngrName' value='" + ingrNameCell.text() + "'>");
            allergyCell.html("<input type='checkbox' class='editAllergy' " + (allergyCell.text() === "예" ? "checked" : "") + ">");
            monthCell.html("<input type='checkbox' class='editMonth' " + (monthCell.text() === "예" ? "checked" : "") + ">");

            // 수정/취소 버튼 토글
            $(this).hide();
            row.find(".updateBtn, .cancelBtn").show();
        });

        $(".updateBtn").click(function() {
            var row = $(this).closest("tr");
            var ingrId = $(this).data("id");
            var ingrName = row.find(".editIngrName").val();
            var allergy = row.find(".editAllergy").prop("checked");
            var month = row.find(".editMonth").prop("checked");

            // TODO: 변경된 데이터를 백엔드로 보내고 처리하는 로직을 추가
            $.ajax({
                url: "adminIngr/" + ingrId, // 백엔드 엔드포인트 주소
                method: "PUT",
                data: {
                    ingrName: ingrName,
                    allergy: allergy,
                    month: month
                },
                success: function(response) {
                    $(".section").html(response); // .section에 응답 페이지 삽입
                },
                error: function() {
                    alert("데이터 업데이트에 실패했습니다.");
                }
            });
        });



        $(".cancelBtn").click(function() {
            var row = $(this).closest("tr");
            var ingrName = row.find(".editIngrName").val();
            var allergy = row.find(".editAllergy").prop("checked");
            var month = row.find(".editMonth").prop("checked");

            row.find(".ingrName").text(ingrName);
            row.find(".allergy").text(allergy ? '예' : '아니오');
            row.find(".month").text(month ? '예' : '아니오');

            // 수정/취소 버튼 토글
            $(this).hide();
            row.find(".editBtn").show();
        });

        $(".removeBtn").click(function() {
            var ingrId = $(this).data("id");
            // TODO: 삭제 로직을 여기에 추가
            alert("삭제하기: " + ingrId);
        });

        $("#addBtn").click(function() {
            // TODO: 추가 로직을 여기에 추가
            alert("추가하기");

        });
    });
</script>
</body>
</html>