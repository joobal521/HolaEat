<%--
  Created by IntelliJ IDEA.
  User: 주바리
  Date: 2023-08-05
  Time: 오후 7:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <style>
        .carousel-inner > .item > img,
        .carousel-inner > .item > a > img {
            width: 50%;
            margin: auto;
        }
        div, h5, p, button, span{
            background-color: unset;
        }
        #carouselExampleCaptions{
            padding: 10% 20%;
        }
    </style>
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <div class="container">
<%--        캐러셀 BootStrap --%>
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
<%--        <div class="carousel-indicators" style="background-color: unset; ">--%>
<%--            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>--%>
<%--            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>--%>
<%--            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>--%>
<%--        </div>--%>
        <div class="carousel-inner" style="height: 50vh; overflow: unset;">
            <div class="carousel-item active">
                <img src="resources/img/menu_main.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block" style="background-color: unset;">
                    <h5>균형잡힌 식단이 중요합니다.</h5>
                    <p>테스트!</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="resources/img/protein_main.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>몸을 만들고 계신가요?</h5>
                    <p>탄수화물, 단백질, 지방은 우리 몸의 3대 영양소 입니다.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="resources/img/vegan_main.jpg" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>당신의 선택을 생각합니다.</h5>
                    <p>비건을 위한 식물성 식단</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next" style="background-color: unset">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    <%--        캐러셀 BootStrap 끝 --%>
    </div>
</section>
</body>
<c:import url="footer.jsp"/>
</html>
