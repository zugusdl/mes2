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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/materials/outDetail.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
			<!-- 표 -->
			<div class="list">
				<div class="list-btn">
<!-- 					<button type="button" class="btn btn-secondary" id="addbtn" onclick="insertOrder()">발주 신청</button> -->
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
									<tr onclick="getOutDetail('${oList.out_index }')" class="selectOrder">
										<td>${oList.out_code }</td>
										<td>${oList.product_code }</td>
										<td>${oList.quantity } ${oList.pdto.unit}</td>
										<td>
											<c:choose>
												<c:when test="${oList.status eq 'waiting' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #ff9924;"></i> 요청
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
		</section>

		<div id="bottomContent"></div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/materials/out/outDetail.js"></script>
</body>
</html>