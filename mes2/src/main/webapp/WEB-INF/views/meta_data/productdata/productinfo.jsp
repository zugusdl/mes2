<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>품목관리</title>

<!-- jqery js입니다. (ajax랑 js에 있는 여러가지 js라이브러리 쓸려고)-->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<!-- 부트스트랩 css cdn입니다. -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<!--  부트스트랩 js cdn입니다. -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous">
</script>

<!-- 추가 css 입니다. -->
<link rel="stylesheet" href="/resources/css/metadata/product.css">

<!-- 추가 js 입니다. -->
<script src="/resources/js/metadata/productinfo.js"></script>

</head>
<body>

<%@ include file="../../sidehead/sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="son_container">
			
		<div class="son_serch">
			
			
			<form id="dateForm" action="" onsubmit="delete_all()" method="POST">
				<span class="son_search-font">등록기간</span>
				<input id="dtIp" type="date" name="startDate" min="2023-12-01" max="2024-12-31" />
				<input id="dtIp" type="date" name="endDate" min="2020-01-01" max="2030-12-31" width="100px" onchange="submitForm()" />		
			</form>
			<form action="">				
				<input type="text" name="search" placeholder="검색어를 입력하세요" />
				<input type="submit" value="검색" />
			</form>
			<form class="son_list-btn" action="">
				<button type="button" class="btn btn-secondary" id="addbtn">추가</button>
				<button type="button" class="btn btn-secondary" id="updatebtn">수정</button>
				<button type="button" class="btn btn-secondary" id="deletebtn">삭제</button>
			</form>
			
			
		</div>		
			
			

			<!-- 표 -->
			<div class="list">
				

				<div class="son_list-box">
					<form action="test" class="list-form">
						<table class="table table-hover">
							<thead>
								<tr class="table-success">
									<th></th> <!-- 체크박스 -->
									<th scope="col">품목코드</th>
									<th scope="col">품명</th>
									<th scope="col">카테고리</th>
									<th scope="col">재고단위</th>
									<th scope="col">원가</th>
									<th scope="col">단가</th>
									<th scope="col">취급유무</th>
									<th scope="col">등록일</th>
								</tr>
							</thead>
							<tbody>
								
								
								<!-- 모든물품 검색하기 productList를 가져오면 실행됨 -->
								<c:if test="${!empty productList }">
								<c:forEach var="plist" items="${productList }">
								<tr>
									<td scope="row"><input type="checkbox" class="ck" /></td>
									<td>${plist.product_code }</td>
									<td>${plist.name }</td>
									<td>${plist.category }</td>
									<td>${plist.unit }</td>
									<td>${plist.cost }</td>
									<td>${plist.price }</td>
									<td>${plist.production_status }</td>
									<td>${plist.regdate }</td>
								</tr>
								</c:forEach>
								</c:if>
								<!-- 모든물품 검색하기 productList를 가져오면 실행됨 -->
							</tbody>
						</table>
					</form>
				</div>
			</div>
	</div>
	
	

<br><br><br><br>

</body>
</html>