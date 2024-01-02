<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://kit.fontawesome.com/38bf29a217.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/contents/contents.css">  
    
    <style>
    .mo {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
   }
   .box {
        background-color: #95c4a2;
        border: 1px black solid;
        width: 100px;
        height: 40px;
        line-height: 40px;
        font-size: 18px;
        font-weight: bold;
        text-align: center;
        float: left;
        border-top-left-radius: 10px;  /* 왼쪽 위 둥근 테두리 */
        border-bottom-left-radius: 10px;  /* 왼쪽 아래 둥근 테두리 */
      }

      .box2 {
        background-color: white;
        border: 1px black solid;
        width: 100px;
        height: 40px;
        line-height: 40px;
        font-size: 25px;
        font-weight: bold;
        text-align: center;
        float: left;
        border-top-right-radius: 10px; /* 오른쪽 위 둥근 테두리 */
        border-bottom-right-radius: 10px; /* 오른쪽 아래 둥근 테두리 */
      }

      .box3 {
        background-color: white;
        border: 1px black solid;
        width: 100px;
        height: 40px;
        line-height: 40px;
        font-size: 18px;
        font-weight: bold;
        text-align: center;
        float: left;
        
      }
      
    .box2:hover, .box3:hover, .box:hover {
     color: #ffcccc;
    cursor: pointer; 
  
    }

  .container {
    clear: both;
   
  }
    </style>
  </head>
  
  <body>
  
  <script src="/resources/js/shipping/ship/btn.js"></script>
  <script src="/resources/js/shipping/ship/details.js"></script>
  
  
<!-- Modal -->
<div id="modalcon">
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body mo" id="shippng-modal">
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="mo-close" >닫기</button>
       
      </div>
    </div>
  </div>
</div>
</div>


	<!-- 진행현황 바  -->
     <div class="box" onclick="location.href='/shipping/shipping'">
      <span >출하</span>
    </div>
    <div class="box3" onclick="statusList('waiting')">
      <span >대기 ${status.waitingCnt }건</span>
    </div>
    <div class="box3" onclick="statusList('progressing')">
      <span >출하중 ${status.planCnt }건</span>
    </div>
    <div class="box3" onclick="statusList('shipping')">
      <span >배송중  ${status.instructionCnt }건</span>
    </div>
    <div class="box2" onclick="userList()">
      <i class="fa-solid fa-user" ></i>
    </div>

    <!-- 검색창  style="clear: both;"-->
    
    <div class="container" >
    <section class="section1">
      <form action="searchShipping" method="post" class="search" onsubmit="return checkSearchSub()">
      	 <select name="type" id="searchType">
          <option value="">-- 검색선택 --</option>
          <option value="ship_code">출하코드</option>
          <option value="ship_date">출하일자</option>
          <option value="company_name">수주처</option>
          <option value="order_date">납품요청일</option>
        </select>
        
   
		
        <div>
          <span class="search-font">검색시작일</span>
          <input  type="date" min="2023-12-01" name="startDay" />

          <span class="search-font">검색종료일</span>
          <input
            
            type="date"
            max="2030-12-31"
            name="endDay"
          />
        </div>
		

        <input type="text" name="search" id="putSearch" placeholder="검색어를 입력하세요" />
        <input type="submit" value="검색"  />
      </form>

      <!-- 표 -->
      <div class="list">
        <div class="list-btn">
         <button type='button' class='btn btn-secondary' data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="return update()">수정</button>   
          <button type="button" class="btn btn-secondary" id="loadPage" onclick="load()">로드</button>         
        </div>

        <div class="list-box">
          <form class="list-form" id="planListForm" action="updateShipDate" method="post">
          <input type="hidden" id="u_id" name="user_id" value="dd" disabled/>
          <input type="hidden" id="odi" name="order_code" value="dd" disabled/>
            <table class="table table-hover">
              <thead>
                <tr class="table-success">
                  <th scope="col"></th>
                  <th scope="col">출하코드</th>
                  <th scope="col">출하일자</th>
                  <th scope="col">수주처</th>
                  <th scope="col">납품요청일</th>
                  <th scope="col">출하상태</th>               
                </tr>
              </thead>
              <tbody>
              <c:forEach var="data" items="${list }">
                <tr>
                  <td><input type="radio" class="ck" value="${data.order_code }" name="order_code"/></td>  
                  <td><a href="javascript:goContent('${data.order_code }')"> ${data.ship_code } </a></td> 
                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${data.ship_date }"/></td>  
                  <td>${data.company_name }</td>       
                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${data.order_date}"/></td>            
                  <td>${data.progress_status }</td>                                            
                </tr>
             </c:forEach> 
                
              </tbody>
            </table>
          </form>
        </div>
      </div>
    </section>

    <section class="section1" id="view2">
   
      </section>
      </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"
    ></script>
  </body>
</html>