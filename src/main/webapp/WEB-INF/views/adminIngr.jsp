<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>재료 관리</title>
</head>
<body>
<h1>재료 관리</h1>

<table>
    <thead>
    <tr>
        <th>재료 ID</th>
        <th>재료 이름</th>
        <th><button id="filterToggleAllergy">알러지</button></th>
        <th><button id="filterToggleMonth">이달의 재료</button></th>
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
                <input type="file" id="editImg-${ingrList.ingrId}" name="ingrImg" class="imgBtn" style="display: none;" accept="image/png, image/jpg, image/jpeg, image/gif">
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
    <div id="addModal" class="modal" style="display: none">
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
<script src="script/adminIngr.js"></script>
</body>
</html>