<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin-health</title>
    <link rel="stylesheet" type="text/css" href="">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>

    .health-box{
        width: 70%;
        padding: 20px;
        text-align: center;
        margin: 0 auto;
    }
    .health-title {
        margin: 5px auto;
        font-size: 30px;
        padding: 10px;
    }
    .health-table {
        width: 100%;
        border-collapse: collapse;
    }

    .health-table td {
        padding: 10px;
        border: 1px solid rgb(160, 156, 156);
        text-align: center;
    }

    .health-table tr:first-child{
        border-top:1px solid rgb(160, 156, 156);
    }
    .health-table label {
        font-weight: bold;
    }
    .health-table textarea {
        width: 100%;
        height: 500px;
        resize: none;
        padding: 5px;
    }
    .health-table input[type="text"] {
        width: 100%;
        padding: 5px;
        height: 40px;
    }

    /*이미지*/
    .write_select_img {
        margin-top: 10px;
    }

    .write_select_img #img {
        max-width: 100%;
        max-height: 200px;
        display: block;
        margin: 10px auto;
    }
    .health-write-btn button {
        background-color:  #265037;
        color: #DCE4CF;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        margin-right: 10px;
    }

    .health-write-btn button:hover {
        background-color: #4e9666;
    }


</style>

</head>

<c:import url="header.jsp"/>
<body>


    <div class="health-box">
        <h2 class="health-title">건강 정보 글 등록</h2>
    <form  enctype="multipart/form-data">
        <table class="health-table">
            <tr>
                <td><label for="title">제목</label></td>
     <td><input type="text" name="title" id="title"></td>
            </tr>
            <tr>
            <td>  <label for="content">내용</label></td>
            <td><textarea id="content" name="content"></textarea></td>
            </tr>
            <tr>
                <td><label for="file">이미지</label></td>
                <td>
        <input type="file" id="file" name="file"  accept="image/*" onchange="writeThumbnail()" multiple/>
                    <div class="write_select_img" id="image-preview">
                        <img src="" id="img" name="img" alt="">
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="health-write-btn">
                    <button type="button" id="submit" name="submit" onclick="checkValue(form)">등록</button>
                    <button type="button" id="cancel" name="cancel" onclick="goBack()">취소</button>
                </td>
            </tr>
        </table>
    </form>
    </div>



<script src="script/adminHealth.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>