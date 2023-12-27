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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/platform/orderDetail.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<div class="container">
		<section class="section2">
			<hr>
			<h1>상세 조회</h1>
				<span class="list-btn2">
					<button type="button" class="btn btn-secondary" id="addBtn" onclick="modifyOrder('${soiList[0].order_code}', '${soiList[0].order_date }', '${soiList[0].sales_status }')">발주 수정</button>
					<button type="button" class="btn btn-secondary" id="addBtn" onclick="deleteOrder('${soiList[0].order_code}', '${soiList[0].sales_status }')">발주 취소</button>
				</span> <br>
				납품 요청일: <input type="date" id="dtIp" name="order_date" value="${order_date }" readonly/><br>
				<br>
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
								</tr>
							</thead>
							<tbody>
								<c:forEach var="soiList" items="${soiList }">
									<c:forEach var="sopList" items="${soiList.sopList }">
									<tr>
										<td>${sopList.product_code }</td>
										<td>${sopList.mdpDTO.name }</td>
										<td><fmt:formatNumber value="${sopList.mdpDTO.price }"/> 원</td>
										<td>${sopList.sales_quantity }EA</td>										
										<td><fmt:formatNumber value="${sopList.mdpDTO.price * sopList.sales_quantity }"/>원</td>										
									</tr>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
		</section>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/orderDetail.js"></script>
</body>
</html>