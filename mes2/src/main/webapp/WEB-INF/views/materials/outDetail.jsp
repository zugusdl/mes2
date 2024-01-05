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
	<div class="container">
		<section class="section1">
			<div class="list">
				<form action="/insertOut" method="post" class="list-form">
					<div class="list-btn">
					</div>
					<div class="list-box">
						<table class="table table-hover">
							<thead>
								<tr class="table-success">
									<th scope="col"></th>
									<th scope="col">로트번호</th>
									<th scope="col">품목코드</th>
									<th scope="col">출고수량</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody id="outProductList">
								<c:if test="${!empty opList }">
										<c:forEach var="opDTO" items="${opList }">
											<tr>
												<td></td>
												<td>${opDTO.pd_lot }</td>
												<td>${opDTO.product_code }</td>
												<td>${opDTO.out_quantity }</td>
												<td></td>
											</tr>
										</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</section>

		<div id="bottomContent"></div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/materials/out/outDetail.js"></script>
</body>
</html>