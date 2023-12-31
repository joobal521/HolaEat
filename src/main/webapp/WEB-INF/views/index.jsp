<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/style/index.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <%--    차트--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    <link rel="icon" type="image/png" sizes="32x32" href="img/pavicon.png">
</head>
<c:import url="header.jsp"/>
<body>
<section>
    <div class="container">
        <c:choose>
            <%--            로그인 시 --%>
        <c:when test="${not empty log}">

        <div class="form_div">
            <form id="myform" action="/saveCalories" method="POST">
                <h2 class="title">내 하루 권장 칼로리는?</h2>
                <ul id="formSteps">
                    <li class="step" data-step="1">
                        <h3>성별을 선택하세요</h3>
                        <div class="gender_box">
                            <label>
                                <input type="radio" id="male" name="gender"
                                       value="male" ${userResponseDto.userGender eq 'male' ? 'checked' : ''}>
                                <span>남자</span>
                            </label>
                            <label>
                                <input type="radio" id="female" name="gender"
                                       value="female" ${userResponseDto.userGender eq 'female' ? 'checked' : ''}>
                                <span>여자</span>
                            </label>
                            <input type="button" class="next-btn" id="nextBtn" value="다음">
                        </div>
                    </li>
                    <li class="step" data-step="2">

                        <h3>나이를 입력하세요</h3>

                        <input type="text" id="age" name="age" value="${userResponseDto.userAge}">
                        <span>세</span>
                        <div class="age_box">
                            <input type="button" class="button backBtn" value="이전">
                            <input type="button" class="next-btn" id="nextBtn" value="다음">
                        </div>
                    </li>
                    <li class="step" data-step="3">
                        <h3>키(cm)를 입력하세요</h3>

                        <input type="text" id="height" name="height" value="${userResponseDto.userHeight}">
                        <span>cm</span>
                        <div class="h_box">
                            <input type="button" class="button backBtn" value="이전">
                            <input type="button" class="next-btn" id="nextBtn" value="다음">
                        </div>
                    </li>
                    <li class="step" data-step="4">
                        <h3>몸무게(kg)를 입력하세요</h3>
                        <input type="text" id="weight" name="weight" value="${userResponseDto.userWeight}">
                        <span>kg</span>
                        <div class="w_box">
                            <input type="button" class="button backBtn" value="이전">
                            <input type="button" class="next-btn" id="nextBtn" value="다음">
                        </div>
                    </li>
                    <li class="step" data-step="5">
                        <h3>거의 다 왔습니다!<br>알레르기가 있으신가요?</h3>
                        <select name="allergy" id="allergy">
                            <option value="0">없음</option>
                            <option value="1" ${userResponseDto.userAllergy eq '1' ? 'selected' : ''}>유제품</option>
                            <option value="2" ${userResponseDto.userAllergy eq '2' ? 'selected' : ''}>갑각류</option>
                            <option value="3" ${userResponseDto.userAllergy eq '3' ? 'selected' : ''}>과일류</option>
                            <option value="4" ${userResponseDto.userAllergy eq '4' ? 'selected' : ''}>견과류</option>
                        </select>
                        <div class="a_box">
                            <input type="button" class="button backBtn" value="이전">
                            <input type="button" class="next-btn" id="nextBtn" value="다음">
                        </div>
                    </li>
                    <li class="step" data-step="6">
                        <h3>드시고 싶은 식단의 종류를</br>선택하세요</h3>
                        <div class="k_box">
                            <select name="menu_type" id="menu_type">
                                <option value="1">일반균형식</option>
                                <option value="2">체중조절식</option>
                                <option value="3">비건</option>
                            </select>
                            <input type="button" class="cal-btn" id="calculate" name="calculate" value="계산하기">
                        </div>
                    </li>
                    <li class="step" data-step="7">
                        <div class="result-box">
                            <h2>필요 열량</h2>
                            <p class="r-text">귀하께서 하루에 필요한 열량은,<br/> <span
                                    id="recCaloriesValue">${userResponseDto.userRecCalories}</span>kcal입니다.</p>
                            <p class="r-text2">${userResponseDto.userName}님에게 맞는 식단을 찾으러 가볼까요?</p>
                            <input type="button" class="save-btn" id="idx_save_btn" name="idx_save_btn"
                                   value="내 칼로리 정보 저장">
                        </div>
                        <p>
                            <a href="menu" class="btn3">
                                맞춤식단<br/>보러가기
                            </a>
                        </p>

                    </li>
                </ul>
            </form>

        </div>
            <%--            맞춤식단 이동 --%>
        <div class="menu_btn">
            <div class="graph_t_box">
                    <%--                <h3 class="you-text">당신의 선택은 옳았습니다.</h3>--%>
                <h3>이렇게 많은 회원들이 Holaeat과 함께합니다.</h3>
            </div>
            <canvas id="pie-chart"></canvas>
            <script>
                new Chart(document.getElementById("pie-chart"), {
                    type: 'bar',
                    data: {
                        labels: ["균형잡힌 식사", "운동 식이조절", "다이어트", "비건", "기타"],
                        datasets: [{
                            label: "단위(명)",
                            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
                            data: [942, 1241, 734, 784, 433]
                        }]
                    },
                    options: {
                        title: {
                            display: true,
                            text: 'Holaeat을 이용하는 이유는?'
                        }
                    }
                });

                $(".hover").mouseleave(
                    function () {
                        $(this).removeClass("hover");
                    }
                );
            </script>
        </div>
        </c:when>
            <%--            비 로그인 시--%>
        <c:otherwise>
        <svg width='600' height='200'>
            <filter id='money'>
                <feMorphology in='SourceGraphic' operator='dilate' radius='2' result='expand'/>

                <feOffset in='expand' dx='1' dy='1' result='shadow_1'/>
                <feOffset in='expand' dx='2' dy='2' result='shadow_2'/>
                <feOffset in='expand' dx='3' dy='3' result='shadow_3'/>
                <feOffset in='expand' dx='4' dy='4' result='shadow_4'/>
                <feOffset in='expand' dx='5' dy='5' result='shadow_5'/>
                <feOffset in='expand' dx='6' dy='6' result='shadow_6'/>
                <feOffset in='expand' dx='7' dy='7' result='shadow_7'/>

                <feMerge result='shadow'>
                    <feMergeNode in='expand'/>
                    <feMergeNode in='shadow_1'/>
                    <feMergeNode in='shadow_2'/>
                    <feMergeNode in='shadow_3'/>
                    <feMergeNode in='shadow_4'/>
                    <feMergeNode in='shadow_5'/>
                    <feMergeNode in='shadow_6'/>
                    <feMergeNode in='shadow_7'/>
                </feMerge>

                <feFlood flood-color='#ebe7e0'/>
                <feComposite in2='shadow' operator='in' result='shadow'/>

                <feMorphology in='shadow' operator='dilate' radius='1' result='border'/>
                <feFlood flood-color='#35322a' result='border_color'/>
                <feComposite in2='border' operator='in' result='border'/>

                <feOffset in='border' dx='1' dy='1' result='secondShadow_1'/>
                <feOffset in='border' dx='2' dy='2' result='secondShadow_2'/>
                <feOffset in='border' dx='3' dy='3' result='secondShadow_3'/>
                <feOffset in='border' dx='4' dy='4' result='secondShadow_4'/>
                <feOffset in='border' dx='5' dy='5' result='secondShadow_5'/>
                <feOffset in='border' dx='6' dy='6' result='secondShadow_6'/>
                <feOffset in='border' dx='7' dy='7' result='secondShadow_7'/>
                <feOffset in='border' dx='8' dy='8' result='secondShadow_8'/>
                <feOffset in='border' dx='9' dy='9' result='secondShadow_9'/>
                <feOffset in='border' dx='10' dy='10' result='secondShadow_10'/>
                <feOffset in='border' dx='11' dy='11' result='secondShadow_11'/>

                <feMerge result='secondShadow'>
                    <feMergeNode in='border'/>
                    <feMergeNode in='secondShadow_1'/>
                    <feMergeNode in='secondShadow_2'/>
                    <feMergeNode in='secondShadow_3'/>
                    <feMergeNode in='secondShadow_4'/>
                    <feMergeNode in='secondShadow_5'/>
                    <feMergeNode in='secondShadow_6'/>
                    <feMergeNode in='secondShadow_7'/>
                    <feMergeNode in='secondShadow_8'/>
                    <feMergeNode in='secondShadow_9'/>
                    <feMergeNode in='secondShadow_10'/>
                    <feMergeNode in='secondShadow_11'/>
                </feMerge>

                <feImage x='0' y='0' width='600' height='200'
                         xlink:href='https://s3-us-west-2.amazonaws.com/s.cdpn.io/78779/stripes.svg'/>
                <feComposite in2='secondShadow' operator='in' result='secondShadow'/>

                <feMerge>
                    <feMergeNode in='secondShadow'/>
                    <feMergeNode in='border'/>
                    <feMergeNode in='shadow'/>
                    <feMergeNode in='SourceGraphic'/>
                </feMerge>
            </filter>

            <text dominant-baseline='middle' text-anchor='middle' x='50%' y='50%'>
                Basic of
            </text>
            <br/>
            <text dominant-baseline='middle' text-anchor='middle' x='80%' y='80%'>
                Healthy Life,
            </text>
            <br/>
            <text dominant-baseline='middle' text-anchor='middle' x='50%' y='50%'>
                HOLAEAT!
            </text>
        </svg>
            <%--            끝--%>

<%--        <div class="slide_box">--%>

<%--                &lt;%&ndash;                            캐러셀 BootStrap &ndash;%&gt;--%>
<%--            <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">--%>
<%--                <div class="carousel-inner" style="height: 50vh; overflow: unset;">--%>
<%--                    <div class="carousel-item active">--%>
<%--                        <img src="img/menu_main.jpg" class="d-block w-100" alt="...">--%>
<%--                        <div class="carousel-caption d-none d-md-block" style="background-color: unset;">--%>
<%--                            <h5>균형잡힌 식단이 중요합니다.</h5>--%>
<%--                            <p>일반균형식</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="carousel-item">--%>
<%--                        <img src="img/protein_main.jpg" class="d-block w-100" alt="...">--%>
<%--                        <div class="carousel-caption d-none d-md-block">--%>
<%--                            <h5>몸을 만들고 계신가요?</h5>--%>
<%--                            <p>체중조절식</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="carousel-item">--%>
<%--                        <img src="img/vegan_main.jpg" class="d-block w-100" alt="...">--%>
<%--                        <div class="carousel-caption d-none d-md-block">--%>
<%--                            <h5>당신의 선택을 생각합니다.</h5>--%>
<%--                            <p>비건을 위한 식물성 식단</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"--%>
<%--                        data-bs-slide="prev">--%>
<%--                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
<%--                    <span class="visually-hidden">Previous</span>--%>
<%--                </button>--%>
<%--                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"--%>
<%--                        data-bs-slide="next" style="background-color: unset">--%>
<%--                    <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
<%--                    <span class="visually-hidden">Next</span>--%>
<%--                </button>--%>
<%--            </div>--%>
                <%--                    캐러셀 BootStrap 끝 --%>
                <%--                인덱싱 시작--%>

                <%--                인덱싱 끝--%>


                <%--        랜덤 룰렛 시작--%>
                <%--        <div class="roullet">--%>
                <%--            <canvas id="roullet" width="380" height='380'></canvas>--%>
                <%--            <button onclick="rotate()">룰렛 돌리기</button>--%>
                <%--        </div>--%>

                <%--        랜덤 룰렛 끝--%>
        </div>
        <div class="login_2">
            <button><a href="../login" class="login_txt">로그인 후 맞춤식단</br>검색하기</a></button>
        </div>
        </c:otherwise>
        </c:choose>

</section>
</body>
<%--<script src="script/rotate.js"></script>--%>
<script src="script/cal.js"></script>
<script src="script/index.js"></script>

<c:import url="footer.jsp"/>
</html>