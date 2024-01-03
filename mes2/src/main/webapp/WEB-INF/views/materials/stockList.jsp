<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materials/stockList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<div class="container2">
		<h3 id="h3">${stockList[0].product_code }재고현황</h3>
		<section class="section1">
			<!-- 표 -->
			<div class="list">
				<div class="list-box">
					<form class="list-form">
						<c:if test="${!empty stockList }">
							<table class="table table-hover">
								<thead>
									<tr class="table-success">
										<th></th>
										<th scope="col">로트번호</th>
										<th scope="col">등록일자</th>
										<th scope="col">창고위치</th>
										<th scope="col">현재재고</th>
										<th scope="col">출고수량</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="stockDTO" items="${stockList }" varStatus="loop">
											<tr>
												<td scope="row"><input type="checkbox" name="idx" value="${loop.index}"></td>
												<td><input type="hidden" name="pd_lot${loop.index}" value="${stockDTO.pd_lot}">${mdpDTO.product_code}</td>
												<td><input type="hidden" name="regdate${loop.index}" value="${stockDTO.regdate }">${stockDTO.regdate }</td>
												<td><input type="hidden" name="warehouse_code${loop.index}" value="${stockDTO.warehouse_code }">${stockDTO.warehouse_code }</td>
												<td><input type="hidden" name="quantity${loop.index}" value="${stockDTO.quantity}">${stockDTO.quantity}</td>
												<td><input type="hidden" name="useQuantity${loop.index}"><input type="number" min="0"></td>
											</tr>
									</c:forEach>
								</tbody>
							</table>
							<button class="btn btn-secondary regist" onclick="registProduct();">등록</button>
							<button class="btn btn-secondary regist" onclick="window.close();">취소</button>
						</c:if>
						<c:if test="${empty stockList }">
							<div>현재 재고가 없습니다.</div>
							<button class="btn btn-secondary regist" onclick="window.close();">닫기</button>
						</c:if>
					</form>
				</div>
			</div>
		</section>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/js/materials/out/productList.js"></script>
</body>
</html>