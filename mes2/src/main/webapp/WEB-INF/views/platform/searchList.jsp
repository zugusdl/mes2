<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/platform/searchList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<div class="container2">
		<h3 id="h3">발주 품목 등록</h3>
		<section class="section1">
			<form method="post" class="search" id="searchForm">
				<select name="searchType" id="searchType">
					<option value="default">구분</option>
					<option value="품목코드">품목코드</option>
					<option value="품목명">품목명</option>
				</select>
				<input type="text" name="search" id="search" placeholder="검색어를 입력하세요">
				<button type="button" class="btn btn-secondary" onclick="searchProduct();">조회</button>
			</form>

			<!-- 표 -->
			<div class="list">
				<div class="list-box">
					<form class="list-form">
						<c:if test="${!empty mdpDTO }">
							<!-- ajax로 데이터 담아서 해당 데이터를 orderList에 html로 추가? -->
							<table class="table table-hover">
								<thead>
									<tr class="table-success">
										<th></th>
										<th scope="col">품목코드</th>
										<th scope="col">형상정보</th>
										<th scope="col">품목명</th>
										<th scope="col">단가</th>
<!-- 										<th scope="col">수량</th> -->
<!-- 										<th scope="col">합계</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach var="mdpDTO" items="${mdpDTO }" varStatus="loop">
											<tr>
												<td scope="row"><input type="radio" name="idx" value="${loop.index}"></td>
												<td><input type="hidden" name="product_code${loop.index}" value="${mdpDTO.product_code}">${mdpDTO.product_code}</td>
												<td><input type="hidden" name="image${loop.index}" ><img alt="" src=""></td>
												<td><input type="hidden" name="name${loop.index}" value="${mdpDTO.name}">${mdpDTO.name}</td>
												<td><input type="hidden" name="price${loop.index}" value="${mdpDTO.price}"><fmt:formatNumber value="${mdpDTO.price}" />원</td>
<%-- 												<td><input type="number" name="sales_quantity${loop.index}" id="sales_quantity${loop.index}"  class="pQuantity" step="100" min="300"></td> --%>
<%-- 												<td><input type="text" name="sum${loop.index}" id="sum${loop.index}" style="width: 150px" readonly>원</td> --%>
											</tr>
									</c:forEach>
								</tbody>
							</table>
							<button class="btn btn-secondary regist" onclick="registProduct();">등록</button>
							<button class="btn btn-secondary regist" onclick="window.close();">취소</button>
						</c:if>
					</form>
				</div>
			</div>
		</section>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	<script
		src="${pageContext.request.contextPath}/resources/js/platform/searchList.js"></script>
</body>
</html>