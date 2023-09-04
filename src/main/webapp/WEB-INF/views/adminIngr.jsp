<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>재료 관리</title>
</head>
<body>

<h1 class="admin-title">재료 관리</h1>

<table>
    <thead>
    <tr>
        <th>재료 ID</th>
        <th>재료 이름</th>
        <th><button id="filterToggleAllergy" onclick="allergyFilter()">알러지</button></th>
        <th><button id="filterToggleMonth" onclick="monthFilter()">이달의 재료</button></th>
        <th>수정</th>
        <th>삭제</th>
    </tr>
    </thead>
    <tbody class="admin-ingrList">
    <c:forEach items="${ingredientList}" var="ingrList">
        <tr id="${ingrList.ingrId}">
            <td>${ingrList.ingrId}</td>
            <td class="ingrName"><input type="text" class="ingrName-input" id="input-ingrName-${ingrList.ingrId}" data-id="${ingrList.ingrName}" value="${ingrList.ingrName}" readonly></td>
            <td class="allergy">
                <select class="allergy-sel" id="allergy-val-${ingrList.ingrId}" data-initial-value="${ingrList.allergy ? '예' : '아니오'}" disabled>
                    <option ${ingrList.allergy ? 'selected' : ''}>예</option>
                    <option ${!ingrList.allergy ? 'selected' : ''}>아니오</option>
                </select>

            </td>
            <td class="month">
                <select class="month-sel" id="month-val-${ingrList.ingrId}" data-initial-value="${ingrList.month ? '예' : '아니오'}" disabled>
                    <option ${ingrList.month ? 'selected' : ''}>예</option>
                    <option ${!ingrList.month ? 'selected' : ''}>아니오</option>
                </select>

            </td>
            <td>
                <button class="editBtn" data-id="${ingrList.ingrId}" onclick="editIngr(this)">수정하기</button>
                <button class="updateBtn" data-id="${ingrList.ingrId}" onclick="updateIngr(this)" style="display: none;">수정완료</button>
                <button class="cancelBtn" data-id="${ingrList.ingrId}" onclick="cancelEdit(this)" style="display: none;">수정취소</button>
                <input type="file" id="editImg-${ingrList.ingrId}" name="ingrImg" class="imgBtn" style="display: none;" accept="image/png, image/jpg, image/jpeg, image/gif">
                <button class="imgUpdate" data-id="${ingrList.ingrId}" style="display: none;">사진업로드</button>
            </td>
            <td>
                <button class="removeBtn" data-id="${ingrList.ingrId}" onclick="deleteIngr(this)">삭제하기</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <input type="button" id="addBtn" value="추가하기" onclick="addIngrModal()">
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
                <input type="button" value="추가완료" onclick="addIngr(this)">
            </form>
        </div>
    </div>
</div>
</body>
</html>