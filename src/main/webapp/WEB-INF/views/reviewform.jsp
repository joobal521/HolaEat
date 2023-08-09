<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-08-08
  Time: 오후 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>review_form</title>


</head>
<body>
<section>
    <div class="wrapper">

        <form method="POST" action="/write" enctype="multipart/form-data">
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
                <input type="submit">
            </div>
        </form>

    </div>
</section>
</body>
</html>
