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
			<form method="post" class="search">
				<select name="searchType" id="searchType">
					<option value="default">구분</option>
					<option value="품목코드">품목코드</option>
					<option value="품목명">품목명</option>
				</select> <input type="text" name="search" id="search"
					placeholder="검색어를 입력하세요">
				<button type="submit" class="btn btn-secondary">조회</button>
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
									</tr>
								</thead>
								<tbody>
									<c:forEach var="mdpDTO" items="${mdpDTO }">
										<tr>
											<td scope="row"><input type="radio"
												name="product_code" value="${mdpDTO.product_code}"></td>
											<td>${mdpDTO.product_code}</td>
											<td><img alt="" src=""></td>
											<td>${mdpDTO.name}</td>
											<td><fmt:formatNumber value="${mdpDTO.price}" />원</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<button class="btn btn-secondary regist"
								onclick="registProduct();">등록</button>
							<button class="btn btn-secondary regist">취소</button>
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