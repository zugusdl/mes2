<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/materials/outList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<%@ include file="../sidehead/sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
			<form class="search">
				<select id="status" name="sales_status">
					<option value="">-- 진행상태 --</option>
					<option value="waiting">대기</option>
					<option value="complete">완료</option>
				</select>
				
				<div>
					<span class="search-font">요청일자: </span>
					<input id="dtIp" type="date" name="startDate" />
					<span> ~ </span>
					<input id="dtIp" type="date" name="endDate" />
				</div>
				
				<div>
					<span class="search-font">품목코드: </span>
					<input type="text" name="product_code" />
				</div>
				
				<input type="submit" class="btn btn-secondary" value="검색" />
			</form>
			<!-- 표 -->
			<div class="list">
				<div class="list-btn">
				</div>

				<div class="list-box">
					<form action="test" class="list-form">
						<table class="table table-hover">
							<thead>
								<tr class="table-success">
									<th scope="col">출고코드</th>
									<th scope="col">출고품목</th>
									<th scope="col">출고수량</th>
									<th scope="col">출고유형</th>
									<th scope="col">출고요청일</th>
									<th scope="col">출고등록일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="oList" items="${oList }">
									<tr onclick="getOutDetail('${oList.out_index }','${oList.out_code }');" class="selectOrder">
										<td>${oList.out_code }</td>
										<td>${oList.product_code }</td>
										<td>${oList.quantity } ${oList.pdto.unit}</td>
										<td>
											<c:choose>
												<c:when test="${oList.status eq 'waiting' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #ff9924;"></i> 대기
												</c:when>
												<c:when test="${oList.status eq 'complete' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #6b6b6b;"></i> 완료
												</c:when>
											</c:choose>
										</td>
										<td>${oList.out_request_date }</td>
										<td>${oList.out_regdate }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
			<!-- 페이징 -->
<!-- 			<div class="box-footer clearfix"> -->
<!-- 				<div style="margin: 0 auto; width: fit-content;"> -->
<!-- 				<ul class="pagination pagination-sm no-margin pull-right"> -->
				
<%-- 					<c:if test="${pageVO.prev }"> --%>
<%-- 						<li><a href="/platform/orderList?page=${pageVO.startPage - 1 }&sales_status=${sDTO.sales_status }&startDate=${sDTO.startDate }&endDate=${sDTO.endDate}">«</a></li> --%>
<%-- 					</c:if> --%>
					
<%-- 					<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1"> --%>
<%-- 						<li><a href="/platform/orderList?page=${i }&sales_status=${sDTO.sales_status }&startDate=${sDTO.startDate }&endDate=${sDTO.endDate}">${i }</a></li> --%>
<%-- 					</c:forEach> --%>
					
<%-- 					<c:if test="${pageVO.next }"> --%>
<%-- 						<li><a href="/platform/orderList?page=${pageVO.endPage + 1 }&sales_status=${sDTO.sales_status }&startDate=${sDTO.startDate }&endDate=${sDTO.endDate}">»</a></li> --%>
<%-- 					</c:if> --%>
<!-- 				</ul> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<!-- 페이징 끝 -->
		</section>

		<div id="bottomContent"></div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/materials/out/outList.js"></script>
</body>
</html>