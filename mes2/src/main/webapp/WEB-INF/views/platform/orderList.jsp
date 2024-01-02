<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<sec:csrfMetaTags/>
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
			<form class="search">
				<select id="sales_status" name="sales_status">
					<option value="">-- 진행상태 --</option>
					<option value="requested">신청중</option>
					<option value="accept">생산중</option>
					<option value="deliver">배송중</option>
					<option value="complete">완료</option>
					<option value="reject">발주 불가</option>
				</select>
				
				<div>
					<span class="search-font">발주일자: </span>
					<input id="dtIp" type="date" name="startDate" />
					<span> ~ </span>
					<input id="dtIp" type="date" name="endDate" />
				</div>
				
<!-- 				<button type="button" class="btn btn-secondary" onclick="searchList();">검색</button> -->
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
			
			<!-- 페이징 -->
			<div class="box-footer clearfix">
				<div style="margin: 0 auto; width: fit-content;">
				<ul class="pagination pagination-sm no-margin pull-right">
				
					<c:if test="${pageVO.prev }">
						<li><a href="/platform/orderList?page=${pageVO.startPage - 1 }">«</a></li>
					</c:if>
					
					<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
						<li ${pageVO.cri.page == i?  "class='active'":"" }>
							<a href="/platform/orderList?page=${i }">
								${i }
							</a>
						</li>
					</c:forEach>
					
					<c:if test="${pageVO.next }">
						<li><a href="/platform/orderList?page=${pageVO.endPage + 1 }">»</a></li>
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
	<script src="${pageContext.request.contextPath}/resources/js/platform/orderList.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/insertOrder.js"></script>
<%-- 	<script src="${pageContext.request.contextPath}/resources/js/platform/sideheadscript.js"></script> --%>
</body>
</html>