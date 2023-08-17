<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div>

    <c:if test="${not empty blob }">
        <div id="image-container">
            <img src="data:image/png;base64,${blob}" id="ingrImg" name="ingrIm"  alt="ingr Img">
            <img src="data:image/jpeg;base64,${blob}" id="img" name="img" alt="Ingredient Image">

        </div>
    </c:if>

        <h1>레시피</h1>
            <ul>
                <c:forEach items="${recipe}" var="foodrecipe">
                    <li id="step1">${foodrecipe.step_01}</li>
                    <li id="step2">${foodrecipe.step_02}</li>
                    <li id="step3">${foodrecipe.step_03}</li>
                    <li id="step4">${foodrecipe.step_04}</li>
                    <li id="step5">${foodrecipe.step_05}</li>
                </c:forEach>
            </ul>


    </div>
