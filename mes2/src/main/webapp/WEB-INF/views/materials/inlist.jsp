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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
	
</script>

</head>
<body>

	
	<!-- Button trigger modal -->
	<div class="col-md-13 text-end">
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#exampleModal">신청</button>
	</div>

	<!-- Modal -->
	<form action="/materials/in" method="post">
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">입고 등록</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<table class="table">
							<tr>
								<td>입고코드</td>
								<td><input type="text" class="form-control"
									name="in_code"></td>
							</tr>
							<tr>
								<td>품목코드</td>
								<td><input type="text" class="form-control"
									name="product_code" required></td>
							</tr>
							<tr>
								<td>자재유형</td>
								<td>
									<div class="input-group">
										<input type="text" class="form-control" name="category"
											id="categoryInput" required>
										<div class="input-group-append">
											<button class="btn btn-secondary dropdown-toggle"
												type="button" data-bs-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false">
												<span class="caret"></span>
											</button>
											<div class="dropdown-menu">
												<a class="dropdown-item" href="#" data-value="완제품"
													data-input="categoryInput">완제품</a> <a class="dropdown-item"
													href="#" data-value="원재료" data-input="categoryInput">원재료</a>
												<a class="dropdown-item" href="#" data-value="부자재"
													data-input="categoryInput">부자재</a>
											</div>
										</div>
									</div>
								</td>
							</tr>

							<tr>
								<td>품목명</td>
								<td>
									<div class="input-group">
										<input type="text" class="form-control" name="name"
											id="nameInput" required>
										<div class="input-group-append">
											<button class="btn btn-secondary dropdown-toggle"
												type="button" data-bs-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false">
												<span class="caret"></span>
											</button>
											<div class="dropdown-menu">
												<a class="dropdown-item" href="#" data-value="옵션 1"
													data-input="nameInput">옵션 1</a> <a class="dropdown-item"
													href="#" data-value="옵션 2" data-input="nameInput">옵션 2</a>
												<a class="dropdown-item" href="#" data-value="옵션 3"
													data-input="nameInput">옵션 3</a>
											</div>
										</div>
									</div>
								</td>
							</tr>

							 <tr>
								<td>단위</td>
								<td><input type="text" class="form-control" name="unit"
									required></td>
							</tr>
							<tr>
								<td>수량</td>
								<td><input type="number" class="form-control"
									name="quantity" required></td>
							</tr>
							<!-- <tr>
								<td>입고등록일</td>
								<td><input type="date" class="form-control" name="in_regdate"
									required></td>
							</tr> -->
							<tr>
								<td>입고담당자</td>
								<td><input type="text" class="form-control" name="user_id"
									required></td>
							</tr>
						</table>
						<button type="submit" class="btn btn-secondary">신청</button>

					</div>
				</div>
			</div>
		</div>
	</form>



	<a href="/materials/in"></a>
	<table class="table table-hover">
		<tr>
			<td></td>
			<td>입고코드</td>
			<td>로트번호</td>			
			<td>품목명</td>
			<td>수량</td>
			<td>단위</td>
			<td>자재유형</td>
			<td>입고등록일</td>
			<td>입고담당자</td>
		</tr>

		<c:forEach var="in" items="${inlist}">
			<tr>
				<td><input type="hidden" class="product_code" value="${in.product_code}" /></td>
				<td><c:out value="${in.in_code}" /></td>
				<td><c:out value="${in.pd_lot}" /></td>
				<td><c:out value="${in.name}" /></td>
				<td><c:out value="${in.quantity}" /></td>
				<td><c:out value="${in.unit}" /></td>
				<td><c:out value="${in.category}" /></td>
				<td><fmt:formatDate value="${in.in_regdate}" pattern="yyyy-MM-dd" /></td>
				<td><c:out value="${in.user_id}" /></td>

				<!-- 	<button type="button" class="btn btn-primary" onclick="buttonClick()">대기</button> -->
			<!-- 	<td><button type="button" class="btn btn-primary statusButton"
						onclick="updateStatus(this)" data-status="waiting">대기</button></td> -->

			</tr>
		</c:forEach>
	</table>

	<div class="box-footer clearfix">
		<ul class="pagination pagination-sm no-margin pull-right">
			
			<c:if test="${pageVO.prev }">
				<li><a href="/materials/inlist?page=${pageVO.startPage - 1 }">«</a></li>
			</c:if>
			
			<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
				<li ${pageVO.cri.page == i?  "class='active'":"" }>
					<a href="/materials/inlist?page=${i }">
						${i }
					</a>
				</li>
			</c:forEach>
			
			<c:if test="${pageVO.next }">
				<li><a href="/materials/inlist?page=${pageVO.endPage + 1 }">»</a></li>
			</c:if>
		</ul>
	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>



</body>
</html>