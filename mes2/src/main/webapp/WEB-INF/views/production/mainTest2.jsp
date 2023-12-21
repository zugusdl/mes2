<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
  </head>
  <style>
    .search {
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 30px;
      padding: 2px;
      margin-top: 40px;
    }

    .section1 {
      padding: 1px;
      background-color: #e6e6e6;
      width: 1000px;
      height: 500px;
      margin: 0 auto;
      margin-top: 30px;

      
    }

    .list-box {
      width: 800px;
      height: 300px;
      margin: 0 auto;
      overflow-y: auto; /* auto로 설정하여 필요할 때만 수직 스크롤바를 표시합니다 */
      overflow-x: hidden; /* hidden으로 설정하여 수평 스크롤바를 숨깁니다 */
    }

    .list-btn {
      display: flex;
      justify-content: flex-end;
      gap: 20px;
      margin-right: 30px;
      margin-top: 10px;
      margin-bottom: 10px;
    }

    .search-font {
      margin-right: 5px;
      margin-left: 10px;
    }
  </style>
  <body>
    <!-- 검색창 -->

    <section class="section1">
      <form action="/product/searchTest2" class="search" method="GET">
        <select id="boundary">
          <option value="">-- 검색선택 --</option>
          <option value="ship">수주번호</option>
          <option value="order">주문번호</option>
          <option value="order">거래항목</option>
          <option value="order">수주처</option>
          <option value="order">수주일</option>
          <option value="order">납기요청일</option>
        </select>

        <div>
          <span class="search-font">검색시작일</span>
          <input id="dtIp" type="date" min="2023-12-01" max="2024-12-31" name="pd_searchStartDate"/>

          <span class="search-font">검색종료일</span>
          <input
            id="dtIp"
            type="date"
            min="2020-01-01"
            max="2030-12-31"
            width="100px"
            name="pd_searchEndDate"
          />
        </div>

        <input type="text"  placeholder="검색어를 입력하세요" name="name"/>
        <input type="submit" value="검색" />
      </form>

      <!-- 표 -->
      <div class="list">
        <div class="list-btn">
          <button type="button" class="btn btn-secondary">추가</button>
          <button type="button" class="btn btn-secondary">삭제</button>
          <button type="button" class="btn btn-secondary">수정</button>
        </div>

        <div class="list-box">
          <form action="test" class="list-form">
            <table class="table table-hover">
              <thead>
                <tr class="table-success">
                  <th></th>
                  <th scope="col">LOT No.</th>
                  <th scope="col">생산품</th>
                  <th scope="col">수주번호</th>
                  <th scope="col">수량</th>
                  <th scope="col">사용기한</th>
                  <th scope="col">생산날짜</th>
              
                </tr>
              </thead>
              <tbody>
              <c:forEach var="product" items="${productList}">
              	<tr>
                  <td scope="row"><input type="checkbox" class="ck" /></td>
                  <td><a href="상세보기확인">${product.pd_lot}</a></td>
                  <td>${product.pd_mdp_code}</td>
                  <td>${product.pd_soi_id}</td>
                  <td>${product.pd_quantity}</td>
                  <td>${product.pd_period}</td>
                  <td>${product.pd_date}</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </form>
        </div>
      </div>
    </section>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"
    ></script>
  </body>
</html>