<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>myPage</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="style/myPage.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        .wrap {
            display: grid;
            grid-template-columns: 20% 80%;
        }

        .aside ul, .aside li, .aside a {
            display: inline-block;
            width: 100%;
        }
        .aside a {
            background: #f0f0f0;
            height: 60px;
            text-align: center;
            line-height: 60px;
        }
        .aside a:hover{
            color: #1e6b7b;
        }

        .aside a.active {
            background: #265037;
            color: white;
        }

        .menu-link {
            display: block;
            margin-bottom: 10px;
            text-decoration: none;
            color: #333;
        }

        .menu-link:hover {
            color: #e74c3c;
        }

        .section {
            flex: 3;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        /* Add your own custom styles here */
    </style>
</head>
<c:import url="header.jsp"/>

<body>

  <section class="wrap">
    <section class="aside">
        <ul>
            <li>
                <a href="myInfo" class="menu-link">나의 정보</a>
            </li>
            <li>
                <a href="update" class="menu-link">회원 정보 수정</a>
            </li>
            <li>
                <a href="" class="menu-link">나의 식단 보기</a>
            </li>
            <li>
                <a href="leave" class="menu-link">회원 탈퇴</a>
            </li>
        </ul>
    </section>
      <section class="section">

      </section>
  </section>
  <script>
      $(document).ready(function() {
          $("a.menu-link").click(function(event) {
              event.preventDefault();

              $("a.menu-link").removeClass("active");

              $(this).addClass("active");

              var pageUrl = $(this).attr("href");
              var pageTitle = $(this).text();

              $.ajax({
                  url: pageUrl,
                  success: function(response) {
                      $(".section").html(response);
                      history.pushState(null, pageTitle, pageUrl);
                  },
                  error: function() {
                      alert("페이지 로드에 실패했습니다.");
                  }
              });
          });
      });

      // 브라우저 뒤로가기/앞으로 가기 시 이벤트 처리
      window.onpopstate = function(event) {
          if (event.state) {
              $(".section").html(event.state.content);
              document.title = event.state.pageTitle;
          }
      };
  </script>

<script src="script/admin.js"></script>

</body>
<c:import url="footer.jsp"/>
</html>
