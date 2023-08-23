<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<c:import url="header.jsp"/>

<body>

<c:forEach items="${userMenus}" var="userMenu">
    <p>${userMenu.menuNo} - ${userMenu.userId}</p>
</c:forEach>

<script>
    const userId = "xodn"; // 실제 사용자 ID로 대체
    fetch(`/myMenu?userId=${encodeURIComponent(userId)}`)
        .then(response => response.text())
        .then(html => {
            document.getElementById("menuContainer").innerHTML = html;
        })
        .catch(error => {
            console.error(error);
        });
</script>

</body>
<c:import url="footer.jsp"/>

</html>
