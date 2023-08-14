<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div>

        <div id="image-container">
            <img src="data:image/png;base64,${blob}" id="ingrImg" name="ingrIm"  alt="ingr Img">
        </div>
        <h1>레시피</h1>
        <c:forEach items="${recipe}" var="foodrecipe">
            <span id="step1">${foodrecipe.step_01}</span>
            <span id="step2">${foodrecipe.step_02}</span>
            <span id="step3">${foodrecipe.step_03}</span>
            <span id="step4">${foodrecipe.step_04}</span>
            <span id="step5">${foodrecipe.step_05}</span>
        </c:forEach>

    </div>
