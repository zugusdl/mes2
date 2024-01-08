<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>shipPlan</title>
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
    
    <script type="text/javascript">
    //페이지번호클릭시이동하기 
    $(document).ready(function() {
        var pageFrm = $("#pageForm");

        $(".page-item a").on("click", function(e) {
            
            e.preventDefault(); //a태그기능막기
            var page = $(this).attr("href"); //페이지번호
            pageFrm.find("#page").val(page);
            pageFrm.submit();
        });
    });
</script>

  </head>
  
  <body>
  
  <script src="/resources/js/shipping/shipPlan/btn.js"></script>
  <script src="/resources/js/shipping/shipPlan/details.js"></script>
  
  
<!-- Modal -->
<div id="modalcon">
<div class="modal fade" id="shippngPlanModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body mo" id="shippngPlan-modal">
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="mo-close" >닫기</button>
       
      </div>
    </div>
  </div>
</div>
</div>


	<!-- 진행현황 바  -->
     <div class="box" onclick="location.href='/shipping/shipPlan'">
      <span >출하</span>
    </div>
    <div class="box3" onclick="location.href='/shipping/shipPlan?shipStatus=plan'">
      <span >계획 ${status.planCnt }건</span>
    </div>
    <div class="box3" onclick="location.href='/shipping/shipPlan?shipStatus=waiting'">
      <span >대기 ${status.waitingCnt }건</span>
    </div>
    <div class="box3" onclick="location.href='/shipping/shipPlan?shipStatus=instruction'">
      <span >가능  ${status.instructionCnt }건</span>
    </div>
    <div class="box2" onclick="location.href='/shipping/shipPlan?user=true'">
      <i class="fa-solid fa-user" ></i>
    </div>

   <!-- 페이징 -->
    
  <nav aria-label="Page navigation example">
    <ul class="pagination">
    <!-- 이전페이지 -->
    <c:if test="${pm.prev }">
        <li class="page-item">
            <a class="page-link" href="${pm.startPage-1 }" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
</c:if>
		<!-- 페이지번호처리  -->
        <c:forEach var="pageNum" begin="${pm.startPage}" end="${pm.endPage}">
            <c:if test="${pm.cri.page != pageNum}">
                <li class="page-item"><a class="page-link" href="${pageNum}">${pageNum}</a></li>
            </c:if>
            <c:if test="${pm.cri.page == pageNum}">
                <li class="active page-item"><a class="page-link" href="${pageNum}">${pageNum}</a></li>
            </c:if>
        </c:forEach>

<!-- 다음페이지 -->
<c:if test="${pm.next }">
        <li class="page-item">
            <a class="page-link" href="${pm.endPage+1}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
        </c:if>
    </ul>
</nav>

		<form id="pageForm" action="shipPlan" method="post">
			<input type="hidden" id="page" name="page" value="${pm.cri.page }"/>
		    <input type="hidden" id="prePageNum" name="perPageNum" value="${pm.cri.perPageNum }"/>
		    <input type="hidden" id="type" name="type" value="${pm.cri.type }"/>
		    <input type="hidden" id="search" name="search" value="${pm.cri.search }"/>
		    <input type="hidden" id="userId" name="userId" value="${pm.cri.userId }"/>
		    <input type="hidden" id="shipStatus" name="shipStatus" value="${pm.cri.shipStatus }"/>
		</form>
    
    <div class="container">
    <section class="section1">
      <form action="shipPlan" method="post" class="search" id="sfrm" onsubmit="return checkSearchSub()">
      	   <input type="hidden"  id="frmId" name="userId" value="${pm.cri.userId }"/>
      	   <input type="hidden"  name="shipStatus" value="${pm.cri.shipStatus }"/>      	  
      	   <input type="hidden" id="shipSta" name="shipSta" value=""/>
      	 <select name="type" id="searchType">
          <option value="">-- 검색선택 --</option>
          <option value="order_code" ${pm.cri.type=='order_code' ? 'selected' : ''}>주문번호</option>
          <option value="company_name" ${pm.cri.type=='company_name' ? 'selected' : ''}>수주처</option>
          <option value="order_date" ${pm.cri.type=='order_date' ? 'selected' : ''}>납품요청일</option>
          <option value="order_date" ${pm.cri.type=='scheduled_date' ? 'selected' : ''}>출하예정일</option>
        </select>
        
   
		
        <div>
          <span class="search-font">검색시작일</span>
          <input  type="date" min="2023-12-01" name="startDay" />

          <span class="search-font">검색종료일</span>
          <input type="date" name="endDay" />
        </div>
		

        <input type="text" name="search" id="putSearch" placeholder="검색어를 입력하세요" value="${pm.cri.search }" />
        <input type="submit" value="검색"  />
      </form>

      <!-- 표 -->
      <div class="list">
        <div class="list-btn">
         <c:if test="${not empty pm.cri.userId}">
      	 <i class="fa-solid fa-truck" onclick="showStatus()"></i>
      	 </c:if>
         <button type='button' class='btn btn-secondary'  data-bs-toggle='modal' data-bs-target='#shippngPlanModal' onclick="return update()">수정</button>        
        </div>

        <div class="list-box">
          <form class="list-form" id="planListForm"  method="post">
          <!-- <input type="hidden" id="u_id" name="user_id" value="dd" disabled/> -->
          <!-- <input type="hidden" id="odi" name="order_code" value="dd" disabled/> -->
            <table class="table table-hover">
              <thead>
                <tr class="table-success">
                  <th scope="col"></th>
                  <th scope="col">주문번호</th>
                  <th scope="col">수주처</th>
                  <th scope="col">납품요청일</th>
                  <th scope="col">출하예정일</th>
                  <th scope="col">출하상태</th>  
                  <th scope="col">출하지시</th>             
                </tr>
              </thead>
              <tbody>
              <c:forEach var="dto" items="${list }">
                <tr>
                  <td><input type="radio" class="ck" value="${dto.order_code }" name="order_code"/></td>  
                  <td><a href="javascript:goContent('${dto.order_code }')"> ${dto.order_code } </a></td> 
                  <td>${dto.company_name }</td>         
                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.order_date}"/></td>            
                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.scheduled_date }"/></td>                                              
                  <c:if test="${dto.ship_status eq 'plan'}">
                  <td>계획</td>
                  <td><button type='button' class='btn btn-secondary' >불가능</button></td>
                  </c:if>
                  <c:if test="${dto.ship_status eq 'waiting'}">
                  <td>대기</td>
                  <td><button type='button' class='btn btn-secondary' >불가능</button></td>
                  </c:if> 
                  <c:if test="${dto.ship_status eq 'instruction'}">
                  <td>준비완료</td>
                  <td><button type='button' class='btn btn-primary'  onclick="return reg('${dto.order_code }')">가능</button></td>
                  </c:if>               
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