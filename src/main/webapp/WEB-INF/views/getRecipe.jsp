<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dongtuta
  Date: 2023/08/10
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div>
        <h1>레시피</h1>
        <c:forEach items="${recipe}" var="foodrecipe">
            <span id="step1">${foodrecipe.step_01}</span>
            <span id="step2">${foodrecipe.step_02}</span>
            <span id="step3">${foodrecipe.step_03}</span>
            <span id="step4">${foodrecipe.step_04}</span>
            <span id="step5">${foodrecipe.step_05}</span>
        </c:forEach>

    </div>
