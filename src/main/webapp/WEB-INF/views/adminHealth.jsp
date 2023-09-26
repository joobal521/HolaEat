
<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-18
  Time: 오후 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin-health</title>
    <link rel="stylesheet" type="text/css" href="/style/admin.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<body>
<h1 class="admin-title">건강 정보 글 관리</h1>
<button type="button" id="write-btn"> <a href="healthForm">글쓰기</a></button>
        <div class="admin-menu">

            <table>
                <thead>
                <tr>
                    <th>건강 정보 No</th>
                    <th>건강 정보 제목</th>
                    <th>내용</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
                </thead>
                <tbody class="admin-healthList">
                <c:forEach items="${adminHealth}" var="health">
                    <tr>
                        <td>${health.healthNo}</td>
                        <td class="healthTitle">${health.title}</td>
                        <td class="healthContent">${health.content}</td>
                        <td>
                            <button type="button" class="updateBtn" data-id="${health.healthNo}"  onclick="redirectToHealthUpdate(${health.healthNo})" >수정</button>
                        </td>
                        <td>
                            <button type="button" class="removeBtn" data-id="${health.healthNo}" >삭제</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
           <div class="paging" id="paging">
            <ul class="pagination">
                <c:choose>
                    <c:when test="${pageNumber > 1}">
                        <li><a href="/adminHealth/1"><i class="fa-solid fa-backward-fast"></i></a></li>
                        <li><a href="/adminHealth/${pageNumber - 1}"><i class="fa-solid fa-caret-left"></i></a></li>


                    </c:when>
                    <c:otherwise>
                        <li><i class="fa-solid fa-backward-fast"></i></li>
                        <li><i class="fa-solid fa-caret-left"></i></li>

                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pageNumber <= 3}">
                        <c:forEach var="i" begin="1" end="5" step="1">
                            <li><a class="page-link" href="/adminHealth/${i}" style="
                            <c:if test="${i==pageNumber}">
                                    background: #265037; color: white; font-weight: bold;
                            </c:if>
                                    ">${i}</a></li>
                        </c:forEach>
                    </c:when>
                    <c:when test="${pageNumber > 3 && pageNumber <= totalPages - 2}">
                        <c:forEach var="i" begin="${pageNumber - 2}" end="${pageNumber + 2}" step="1">
                            <li><a class="page-link" href="/adminHealth/${i}" style="
                            <c:if test="${i==pageNumber}">
                                    background: #265037; color: white; font-weight: bold;
                            </c:if>
                                    ">${i}</a></li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="i" begin="${totalPages - 4}" end="${totalPages}" step="1">
                            <li><a class="page-link" href="/adminHealth/${i}"style="
                            <c:if test="${i==pageNumber}">
                                    background: #265037; color: white; font-weight: bold;
                            </c:if>
                                    ">${i}</a></li>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pageNumber < totalPages}">
                        <li><a href="/aminHealth/${pageNumber + 1}"> <i class="fa-solid fa-caret-right"></i></a></li>
                        <li><a href="/adminHealth/${totalPages}"> <i class="fa-solid fa-forward-fast"></i> </a></li>
                    </c:when>
                    <c:otherwise>
                        <li><i class="fa-solid fa-caret-right"></i></li>
                        <li><i class="fa-solid fa-forward-fast"></i></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

        </div>
<script src="/script/adminHealth.js"></script>
</body>

</html>
