<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/platform/orderModify.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<div class="container">
		<section class="section2">
			<hr>
			<h1>발주 수정</h1>
			<form method="post">
				<span class="list-btn2">
					<button type="button" class="btn btn-secondary" id="addBtn" onclick="openProductList()">품목 추가</button>
					<button type="button" class="btn btn-secondary" onclick="insertOrder(${order_code});">수정</button>
					<button type="button" class="btn btn-secondary">취소</button>
				</span> <br>
				납품 요청일: <input type="date" id="dtIp" name="order_date" min="${minDay }" max="${maxDay }" value="${order_date }"/><br>
				발주 품목 <br>
				<div class="list">
					<div class="list-box">
						<table class="table table-hover" >
							<thead>
								<tr class="table-success">
									<th scope="col">품목코드</th>
									<th scope="col">품목명</th>
									<th scope="col">단가</th>
									<th scope="col">수량</th>
									<th scope="col">합계</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody id="insertProductList">
								<c:forEach var="sopDTO" items="${sopDTO }">
									<tr>
										<td>${sopDTO.product_code }</td>
										<td>${sopDTO.mdpDTO.name }</td>
										<td><fmt:formatNumber value="${sopDTO.mdpDTO.price }"/> 원</td>
										<td>${sopDTO.sales_quantity }EA</td>										
										<td><fmt:formatNumber value="${sopDTO.mdpDTO.price * sopDTO.sales_quantity }"/>원</td>
										<td><button type='button' onclick='trRemove(this);' class='btn btn-secondary'>x</button></td>								
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</section>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/modifyOrder.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/modifyOrder2.js"></script>
</body>
</html>