<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <h2>리뷰 게시판 상세</h2>
    <form enctype="multipart/form-data">
        <div>
            <input type="text" id="title" name="title">
        </div>
        <div>
            <textarea id="content" name="content" ></textarea>
        </div>
        <div>
            <input type="file" id="img" name="img" accept="image/png, image/jpg, image/jpeg, image.gif">
        </div>
        <div>
            <button type="button" id="submit" name="submit" onclick="checkValueWrite(form)">등록</button>
        </div>
    </form>



</section>
</body>
<c:import url="footer.jsp"/>
</html>
