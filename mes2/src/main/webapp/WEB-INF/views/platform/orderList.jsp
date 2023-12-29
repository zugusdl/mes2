<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/platform/orderList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<%@ include file="sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
			<form class="search" action="/platform/search">
				<select id="boundary">
					<option value="">-- 검색선택 --</option>
					<option value="order_code">주문번호</option>
					<option value="order_date">발주일자</option>
					<option value="sales_status">진행상태</option>
				</select>

				<div>
					<span class="search-font">검색시작일</span>
					<input id="dtIp" type="date" name="startDate" min="2023-12-01" max="2024-12-31" />
					<span class="search-font">검색종료일</span>
					<input id="dtIp" type="date" name="endDate" min="2020-01-01" max="2030-12-31" width="100px" />
				</div>

				<input type="text" name="search" placeholder="검색어를 입력하세요" />
				<input type="submit" class="btn btn-secondary" value="검색" />
			</form>

			<!-- 표 -->
			<div class="list">
				<div class="list-btn">
					<button type="button" class="btn btn-secondary" id="addbtn" onclick="insertOrder()">발주 신청</button>
				</div>

				<div class="list-box">
					<form action="test" class="list-form">
						<table class="table table-hover">
							<thead>
								<tr class="table-success">
									<th scope="col">주문번호</th>
									<th scope="col">발주일자</th>
									<th scope="col">납품요청일</th>
									<th scope="col">진행상태</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="soiDTO" items="${soiDTO }">
									<tr>
										<td onclick="getOrderDetail('${soiDTO.order_code }','${soiDTO.order_date }')" class="selectOrder">${soiDTO.order_code }</td>
										<td>${soiDTO.request_date }</td>
										<td>${soiDTO.order_date }</td>
										<td>
											<c:choose>
												<c:when test="${soiDTO.sales_status eq 'requested' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #ff9924;"></i> 신청중
												</c:when>
												<c:when test="${soiDTO.sales_status eq 'accept' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #577D71;"></i> 생산중
												</c:when>
												<c:when test="${soiDTO.sales_status eq 'deliver' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #416ca4;"></i> 배송중
												</c:when>
												<c:when test="${soiDTO.sales_status eq 'complete' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #6b6b6b;"></i> 완료
												</c:when>
												<c:when test="${soiDTO.sales_status eq 'reject' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #a44141;"></i> 발주 불가
												</c:when>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</section>

		<div id="bottomContent"></div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/orderList.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/insertOrder.js"></script>
</body>
</html>