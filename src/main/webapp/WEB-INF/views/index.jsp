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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <%--    차트--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    <style>
        .carousel-inner > .item > img,
        .carousel-inner > .item > a > img {
            width: 50%;
            margin: auto;
        }

        div, h5, p, button, span {
            background-color: unset;
        }

        #carouselExampleCaptions {
            padding: 10% 20%;
        }

        #roullet {
            transition: 2s;
            border-radius: 300px;
        }

        .roullet > button {
            background: #febf00;
            margin-top: 1rem;
            padding: .8rem 1.8rem;
            border: none;
            font-size: 1.5rem;
            font-weight: bold;
            border-radius: 5px;
            transition: .2s;
            cursor: pointer;
        }

        .roullet > button:active {
            background: #444;
            color: #f9f9f9;
        }

        div.roullet {
            width: 380px;
            overflow: hidden;
            display: flex;
            align-items: center;
            flex-direction: column;
            position: relative;
        }

        div.roullet::before {
            content: "";
            position: absolute;
            width: 10px;
            height: 50px;
            border-radius: 5px;
            background: #000;
            top: -20px;
            left: 50%;
            transform: translateX(-50%);
            z-index: 22;
        }
    </style>
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <div class="container">
        <c:choose>
            <%--            로그인 시 --%>
            <c:when test="${not empty log}">
                <p>회원 로그인 메인 페이지 입니다</p>
                <div class="form_wrap" style="border: solid 1px black">
                    <canvas id="pie-chart" width="200" height="200" style="width:200px; height: 200px;"></canvas>
                    <script>
                        new Chart(document.getElementById("pie-chart"), {
                            type   : 'pie',
                            data   : {
                                labels  : ["균형잡힌 식사", "운동 식이조절", "다이어트", "비건", "기타"],
                                datasets: [{
                                    label          : "Population (millions)",
                                    backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
                                    data           : [2478, 5267, 734, 784, 433]
                                }]
                            },
                            options: {
                                title: {
                                    display: true,
                                    text   : 'Holaeat을 이용하는 이유는?'
                                }
                            }
                        });
                    </script>
                    <form action="" method="">
                        <ul>
                            <li>
                                <h2>선호 메뉴</h2>
                                <div class="favorite_menu">
                                        <%--                                <input type="button" value="한식">--%>
                                        <%--                                <input type="button" value="중식">--%>
                                        <%--                                <input type="button" value="일식">--%>
                                        <%--                                <input type="button" value="양식">--%>
                                        <%--                                <input type="button" value="샐러드">--%>
                                        <%--                                <button>한식</button>--%>
                                        <%--                                <button>중식</button>--%>
                                        <%--                                <button>일식</button>--%>
                                        <%--                                <button>양식</button>--%>
                                        <%--                                <button>샐러드</button>--%>

                                </div>
                            </li>
                            <li>
                                <h2>좋아하는 재료</h2>
                            </li>
                            <li>
                                <h2>싫어하는 재료</h2>
                            </li>
                        </ul>
                    </form>
                </div>
            </c:when>
            <%--            비 로그인 시--%>
            <c:otherwise>
                <%--        캐러셀 BootStrap --%>
                <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner" style="height: 50vh; overflow: unset;">
                        <div class="carousel-item active">
                            <img src="img/menu_main.jpg" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block" style="background-color: unset;">
                                <h5>균형잡힌 식단이 중요합니다.</h5>
                                <p>테스트!</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img src="img/protein_main.jpg" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>몸을 만들고 계신가요?</h5>
                                <p>탄수화물, 단백질, 지방은 우리 몸의 3대 영양소 입니다.</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img src="img/vegan_main.jpg" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>당신의 선택을 생각합니다.</h5>
                                <p>비건을 위한 식물성 식단</p>
                            </div>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                            data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                            data-bs-slide="next" style="background-color: unset">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
                <%--        캐러셀 BootStrap 끝 --%>

                <div class="login_2">
                    <button><a href="login">로그인 후 맞춤식단 검색하기</a></button>
                </div>
        <%--        랜덤 룰렛 시작--%>
        <div class="roullet">
            <canvas id="roullet" width="380" height='380'></canvas>
            <button onclick="rotate()">룰렛 돌리기</button>
        </div>

        <%--        랜덤 룰렛 끝--%>
    </div>
            </c:otherwise>
        </c:choose>

</section>
<script src="script/rotate.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>
