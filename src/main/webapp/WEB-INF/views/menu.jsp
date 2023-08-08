<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-08-07
  Time: 오후 3:47
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
    <h2> ${userName}님을 위한 식단이 준비되어 있습니다! </h2>
    <h3>정확한 식단 제공을 위해, ${userName}님에 대해 더욱 자세히 알려주세요!</h3>
    <div>
        <form action="" method="">
            <ul>
                <li>
                    <h2>성별</h2>
                    <select name="gender" id="gender">
                        <option value="male" selected>선택하세요</option>
                        <option value="male">남자</option>
                        <option value="female">여자</option>
                    </select>
                </li>
                <li>
                    <h2>나이</h2>
                    <input type="text" id="age" name="age">
                </li>
                <li>
                    <h2>키</h2>
                    <input type="text" id="height" name="height">
                </li>
                <li>
                    <h2>몸무게</h2>
                    <input type="text" id="weight" name="weight">
                </li>
                <li>
                    <h2>알레르기</h2>
                    <select name="alergy" id="alergy">
                        <option value="">선택하세요</option>
                        <option value="1">유제품</option>
                        <option value="2">갑각류</option>
                        <option value="3">과일류</option>
                        <option value="4">견과류</option>
                    </select>
                </li>
                <input type="button" id="save_btn" name="save_btn" value="저장" onclick="">
            </ul>
        </form>
    </div>
</section>
</body>
<c:import url="footer.jsp"/>
</html>
