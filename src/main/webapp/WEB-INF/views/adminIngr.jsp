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
                <input type="file" id="editImg" name="ingrImg" accept="image/png, image/jpg, image/jpeg, image.gif">
                <button class="imgUpdate" data-id="${ingrList.ingrImg}" style="display: none;">사진업로드</button>
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
    <div id="addModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form id="addForm">
                <label for="ingrName">재료 이름:</label>
                <input type="text" id="ingrName" name="ingrName" required><br><br>

                <label for="month">이달의 재료:</label>
                <input type="checkbox" id="month" name="month"><br><br>

                <label for="allergy">알러지:</label>
                <input type="checkbox" id="allergy" name="allergy"><br><br>

                <label for="addImg">사진:</label>
                <input type="file" id="addImg" name="ingrImg" accept="image/png, image/jpg, image/jpeg, image/gif">
                <input type="submit" value="추가완료">
            </form>
        </div>
    </div>
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
                url: "adminIngr/" + ingrId,
                method: "PUT",
                contentType: "application/json",
                data: JSON.stringify({
                    ingrName: ingrName,
                    allergy: allergy,
                    month: month
                }),
                success: function(response) {
                    $(".section").html(response);
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

            $.ajax({
                url: "adminIngr/delete/" + ingrId,
                method: "DELETE",
                success: function(response) {
                    // Handle success if needed
                    alert("삭제하기: " + ingrId);
                },
                error: function() {
                    alert("삭제에 실패했습니다.");
                }
            });
        });



        $("#addBtn").click(function() {
            $("#addModal").css("display", "block");
        });

        $(".close").click(function() {
            $("#addModal").css("display", "none");
        });

        $(window).click(function(event) {
            if (event.target == document.getElementById("addModal")) {
                $("#addModal").css("display", "none");
            }
        });

        // Handle the form submission
        $("#addForm").submit(function(event) {
            event.preventDefault();

            var formData = new FormData();
            formData.append("ingrName", $("#ingrName").val());
            formData.append("month", $("#month").prop("checked"));
            formData.append("allergy", $("#allergy").prop("checked"));
            formData.append("ingrImg", $("#addImg")[0].files[0]);


            // Send the data to the backend using AJAX
            $.ajax({
                url: "adminIngr/create",
                method: "POST",
                contentType: false, // Set to false when using FormData
                processData: false, // Set to false when using FormData
                data: formData,
                success: function(response) {
                    alert("추가 완료");
                    $("#addModal").css("display", "none");
                    // TODO: Update the ingredient list if needed
                },
                error: function() {
                    alert("추가 실패");
                }

        });

    });
    });
</script>
</body>
</html>
