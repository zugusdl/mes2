<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/platform/modifyOrder.css">
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
	<div class="container">
		<section class="section2">
			<hr>
			<h1>발주 수정</h1>
			<form method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<span class="list-btn2">
					<button type="button" class="btn btn-secondary" onclick="modifyOrder('${soiList[0].order_code}','${soiList[0].sales_status}');">수정하기</button>
					<button type="button" class="btn btn-secondary" onclick="cancleModify();">수정 취소</button>
				</span> <br>
				납품 요청일: <input type="date" id="dtIp" name="order_date" min="${minDay }" max="${maxDay }" value="${order_date }" readonly/><br>
				발주 품목 <br>
				<div class="list">
					<div class="list-box">
						<table class="table table-hover">
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
							<tbody id="modifyProductList">
								<c:forEach var="soiList" items="${soiList }">
									<c:forEach var="sopList" items="${soiList.sopList }">
										<tr>
											<td><input type="hidden" name="product_code" value="${sopList.product_code }">${sopList.product_code }</td>
											<td>${sopList.mdpDTO.name }</td>
											<td><input type="hidden" name="price" value="${sopList.mdpDTO.price }"><fmt:formatNumber value="${sopList.mdpDTO.price }"/>원</td>
											<td><input type="number" name="sales_quantity" step="500" min="500" oninput="cal(this);" value="${sopList.sales_quantity }">EA</td>
											<td><input type="text" id="s${sopList.product_code }" value="<fmt:formatNumber value="${sopList.mdpDTO.price * sopList.sales_quantity }"/>"readonly>원</td>
											<td><button type="button" onclick="trRemove(this);" class="deleteBtn">x</button></td>
										</tr>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</section>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/js/platform/modifyOrder.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/modifyOrder2.js"></script>
</body>
</html>