<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<link rel="stylesheet" href="/resources/css/production/instructionList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
</head>

<body>
	<%@ include file="../sidehead/sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
			<form class="search" action="/instructions/search" onsubmit="return validateSearchForm()">
				
				<select id="state" name="searchState">
					<option value="">-- 검색선택 --</option>
					<option value="WAITING">작업대기</option>
					<option value="PROGRESSING">진행중</option>
					<option value="COMPLETE">완료</option>
				</select>
				
				<select id="boundary" name="searchType" >
					<option value="">-- 검색선택 --</option>
					<option value="isCode">작업지시코드</option>
					<option value="soiCode">수주번호</option>
					<option value="mdpCode">제품번호</option>
				</select>
				
				<input type="text" name="code" placeholder="작업지시코드" />
				<div>
					<span class="search-font">검색시작일</span>
					<input id="dtIp" type="date" name="searchStartDate" min="2023-12-01" max="2024-12-31" value="${searchStartDate}"/>
					<span class="search-font">검색종료일</span>
					<input id="dtIp" type="date" name="searchEndDate" min="2020-01-01" max="2030-12-31" width="100px" value="${searchEndDate}"/>
				</div>

				
				<input type="submit" value="검색" />
			</form>

			<!-- 표 -->
			<div class="list">
			
				<div class="list-btn">
					<button type="button" class="btn btn-secondary" id="deletebtn">긴급탈출버튼</button>
				</div>

				<div class="list-box">
					<form action="test" class="list-form">
						<table class="table table-hover">
						
							
							<colgroup>
								<col style="width: 3%" /> 
                    			<col style="width: 10%" /> 
                    			<col style="width: 3%" />
                    			<col style="width: 8%" />
                    			<col style="width: 15%" />
                    			<col style="width: 10%" />
                    			<col style="width: 15%" />
                    			<col style="width: 5%" />
								<col style="width: 5%" />
								<col style="width: 5%" />
                			</colgroup>
							<thead>
								<tr class="table-success">
									<th></th>
									<th scope="col">작업지시번호</th>
									<th scope="col">라인</th>
									<th scope="col">제품번호</th>
									<th scope="col">수주번호</th>
									<th scope="col">시작시간</th>
									<th scope="col">종료시간</th>
									<th scope="col">양품</th>
									<th scope="col">불량품</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${instructions}">
									<tr>
										<td scope="row"><input type="checkbox" class="ck" /></td>
										<td><a href="">${item.code}</a></td>
										<td>${item.line}</td>
										<td>${item.mdpCode}</td>
										<td>${item.sopCode}</td>
										<td>${item.startTime}</td>
										<td>${item.endTime}</td>
										<td>${item.quantity}</td>
										<td>${item.fault}</td>
										<td>
										<c:if test="${item.state.equals('WAITING')}">
											<i class="fa-solid fa-circle fa-2xs" style="color: #577D71;"></i><button type="button" onclick="updateProgressing('${item.code}');" style="border:none; background-color:none;">생산시작</button>
										</c:if>
										<c:if test="${item.state.equals('COMPLETE')}">
											<i class="fa-solid fa-circle fa-2xs" style="color: #416ca4;"></i>생산완료
										</c:if>
										<c:if test="${item.state.equals('PROGRESSING')}">
											<i class="fa-solid fa-circle fa-2xs" style="color: #577D71;"></i><button type="button" onclick="window.open('/instructions/resultInfo/${item.code}','result','width=800px, height=640px')" style="border:none">작업 종료</button>
										</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
			<!-- 페이징 -->
			<div class="box-footer clearfix">
				<div style="margin: 0 auto; width: fit-content;">
				<ul class="pagination pagination-sm no-margin pull-right">
				
					<c:if test="${pageVO.prev }">
						<li><a href="/instructions/search?page=${pageVO.startPage - 1 }&searchType=${searchType }&searchStartDate=${searchStartDate }&searchEndDate=${searchEndDate}">«</a></li>
					</c:if>
					
					<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
						<li><a href="/instructions/search?page=${i }&searchType=${searchType}&searchStartDate=${searchStartDate }&searchState=${searchState}&searchEndDate=${searchEndDate}">${i }</a></li>
					</c:forEach>
					
					<c:if test="${pageVO.next }">
						<li><a href="/instructions/search?page=${pageVO.endPage + 1 }&searchType=${searchType}&searchState=${searchState}&searchStartDate=${searchStartDate}&searchEndDate=${searchEndDate}">»</a></li>
					</c:if>
				</ul>
				</div>
			</div>
			<!-- 페이징 끝 -->
			
			
		</section>

		<div id="bottomContent"></div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
	</script>
	<script src="/resources/js/platform/orderList.js"></script>
	
	<script type="text/javascript">
	var isCode = '${item.code}'
	
    function openIsSave(){
        window.open("/instructions/save","save","width=800px, height=640px")
    }
/* 	
    function openIsResult(){
        window.open("/instructions/result?isCode"+isCode,"result","width=800px, height=640px")
    }
    */ 

	
	function validateSearchForm() {
        var searchType = document.getElementById("boundary").value;
        
        if (searchType === "") {
            alert("좋은말로 할 때 검색 타입을 선택하세요.");
            return false; 
        }
        
        return true; 
    }
    
    var searchType = '${searchType}'; 
    var searchState = '${searchState}'; 
    // JavaScript를 통해 동적으로 선택된 값을 설정
    document.getElementById('boundary').value = searchType;
    document.getElementById('state').value = searchState;
		
	
	</script>
	<script src="/resources/js/instructions/instructionList.js"></script>
		
</body>
</html>