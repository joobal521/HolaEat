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
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .wrap {
            display: flex;
            min-height: 100vh;
        }

        .aside {
            flex: 1;
            background-color: #f5f5f5;
            padding: 20px;
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
            background-color: #fff;
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
                <a href="myinfo" class="menu-link">나의 정보</a>
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
              event.preventDefault(); // 기본 링크 동작 방지

              var pageUrl = $(this).attr("href"); // 클릭한 링크의 URL
              var pageTitle = $(this).text(); // 클릭한 링크의 텍스트를 페이지 제목으로 사용
              // AJAX 요청
              $.ajax({
                  url: pageUrl,
                  success: function(response) {
                      $(".section").html(response); // .section에 응답 페이지 삽입
                      // 브라우저 주소 표시줄 업데이트
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
