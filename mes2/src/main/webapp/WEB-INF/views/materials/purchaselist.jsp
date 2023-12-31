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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materials/searchList.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materials/purchaselist.css">

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<%@ include file="../sidehead/sidehead.jsp"%>

	<div class="container">
		<section class="section1">
			<form action="/materials/purchaselist" method="get" class="search">

				<select name="searchType" id="searchType" class="form-select"
					aria-label="Default select example">
					<option value="name">품목명</option>
					<option value="category">자재유형</option>
				</select>
				
				<div class="input-group searchSub" style="width: 50%;">
					<input type="text" name="keyword" id="keyword"
						class="form-control fm" aria-label="Recipient's username"
						aria-describedby="button-addon2">
					<button class="btn btn-secondary" type="submit" id="button-addon2">검색</button>
				</div>
			</form>

			<div class="col-md-13" style="text-align: right;">
				<button type="button" class="btn submitOrderButton"
					onclick="openInputOrder();">발주신청</button>
				<a href="/materials/materials" class="btn submitOrderButton">Excel Download</a>
			</div>



			<div class="list-box">
				<a href="/materials/purchase"></a>
				<table class="table table-hover">


					<thead>
						<tr>
							<td>발주코드</td>
							<td>품목명</td>
							<td>자재유형</td>
							<td>원가</td>
							<td>단가</td>
							<td>발주수량</td>
							<td>발주등록일</td>
							<td>진행상황</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pl" items="${purchaselist}">
							<tr>
								<td><c:out value="${pl.orders_code}" /></td>
								<td><c:out value="${pl.name}" /></td>
								<td><c:out value="${pl.category}" /></td>
								<td><c:out value="${pl.cost}" /></td>
								<td><c:out value="${pl.price}" /></td>
								<td><c:out value="${pl.quantity}" /></td>
								<td><fmt:formatDate value="${pl.regdate}"
										pattern="yyyy-MM-dd" /></td>

								<td><c:if test="${pl.status.equals('waiting')}">
										<form action="/materials/updateStatus" method="post">
											<input type="hidden" value="${pl.orders_index}"
												name="orders_index">
											<button type="submit" class="btn statusButton waiting">대기</button>
										</form>
									</c:if> <c:if test="${pl.status.equals('complete')}">
										<button type="button" class="btn statusButton complete">완료</button>
									</c:if></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test="${pageVO.prev }">
						<li class="page-item"><a class="page-link"
							href="/materials/purchaselist?page=${pageVO.startPage - 1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>

					<c:forEach var="i" begin="${pageVO.startPage }"
						end="${pageVO.endPage }" step="1">
						<li class="page-item ${pageVO.cri.page == i ? 'active' : ''}">
							<a class="page-link" href="/materials/purchaselist?page=${i}">${i}</a>
						</li>
					</c:forEach>

					<c:if test="${pageVO.next }">
						<li class="page-item"><a class="page-link"
							href="/materials/purchaselist?page=${pageVO.endPage + 1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>
			</nav>

		</section>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>

	<script>
		function openInputOrder() {
			window.open("/materials/inputOrder", "order",
					"width=500,height=720");
		}
	
	</script>


</body>
</html>