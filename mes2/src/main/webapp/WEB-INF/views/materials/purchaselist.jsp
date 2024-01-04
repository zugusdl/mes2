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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

 <script>



	// 기존 스크립트
	$(document).ready(function () {
    // 페이지 로드 시 상태 초기화
    initializeStatusButtons();

    /* // 버튼 클릭 시 상태 업데이트
    $('.statusButton').on('click', function () {
        var button = $(this);
        var product_code = button.closest('tr').find('.product_code').val();
        updateStatus(button, product_code);
    }); */
});

function initializeStatusButtons() {
    // 각 행의 상태를 가져와서 적용
    $('.statusButton').each(function () {
        var button = $(this);
        var product_code = button.closest('tr').find('.product_code').val();
        updateButtonStatus(button, product_code);
    });
}

function updateStatus(button, product_code) {
    // 서버에 상태 업데이트 요청
    $.ajax({
        type: 'POST',
        url: 'updateStatus',
        data: {
            product_code: product_code,
            status: 'complete'
        },
        success: function (response) {
            if (response > 0) {
                // 성공 시 해당 버튼 상태 업데이트
                updateButtonStatus(button, product_code);
            } else {
                alert('상태 업데이트 실패');
            }
        },
        error: function () {
            alert('AJAX 오류 발생');
        }
    });
}

function updateButtonStatus(button, product_code) {
    // 서버에 상태 요청
    $.ajax({
        type: 'GET',
        url: 'getOrderStatus',
        data: {
            product_code: product_code
        },
        success: function (response) {
            var status = response[0].status; // 가정: purchaselist에 하나의 항목만 있는 경우

            // 받아온 상태에 따라 해당 버튼 업데이트
            if (status === 'complete') {
                button.removeClass('btn-primary').addClass('btn-success').text('완료');
                button.data('status', 'complete'); // 상태 업데이트
            } else {
                button.removeClass('btn-success').addClass('btn-primary').text('대기');
                button.data('status', 'waiting'); // 상태 업데이트
            }
        },
        error: function () {
            alert('상태 조회 오류 발생');
        }
    });
}

var productCodeValue = "product_code"; // 실제 값을 적절히 설정하세요

//AJAX를 사용하여 서버로 HTTP 요청 보내기
$.ajax({
url: 'getOrderStatus',
method: 'GET', // 또는 다른 HTTP 메서드 사용
data: { product_code: product_code },
success: function(response) {
 // 서버 응답 처리
 console.log(response);
},
error: function(error) {
 // 오류 처리
 console.error(error);
}
});
</script> 




</head>
<body>

<form action="/materials/purchaselist" method="get">
	<select name="searchType">
		<option value="category">자재유형</option>
		<option value="name">품목명</option>
	</select>
	<input type="text" name="keyword">
	<input type="submit" value="검색하기">
</form>


	<!-- Button trigger modal -->
	<div class="col-md-13 text-end">
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#exampleModal">등록</button>
	</div>

	<!-- Modal -->
	<form action="/materials/purchase" method="post">
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">발주 신청</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<table class="table">
							<tr>
								<td>발주코드</td>
								<td><input type="text" class="form-control"
									name="orders_code"></td>
							</tr>
					<tr>
								<td>품목코드</td>
								<td><input type="text" class="form-control"
									name="product_code"></td>
							</tr>
							<tr>
								<td>원재료코드</td>
								<td><input type="text" class="form-control"
									name="material_code" required></td>
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
												<a class="dropdown-item" href="#" data-value="${pl.name}"
													data-input="nameInput">옵션 1</a> 
											</div>
										</div>
									</div>
								</td>
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
								<td>원가</td>
								<td><input type="text" class="form-control" name="cost"
									required></td>
							</tr>
							
							<tr>
								<td>단가</td>
								<td><input type="text" class="form-control" name="price"
									required></td>
							</tr>
							<tr>
								<td>용량</td>
								<td><input type="text" class="form-control" name="amount"
									required></td>
							</tr>
							
							<tr>
								<td>용량 단위</td>
								<td><input type="text" class="form-control" name="amount_unit"
									required></td>
							</tr>
						
							<tr>
								<td>발주수량</td>
								<td><input type="number" class="form-control"
									name="quantity" required></td>
							</tr>
							<!-- <tr>
								<td>발주등록일</td>
								<td><input type="date" class="form-control" name="regdate"
									required></td>
							</tr> -->
							<tr>
								<td>발주담당자</td>
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



	<a href="/materials/purchase"></a>
	<table class="table table-hover">
		<tr>
			<td></td>
			<td>발주코드</td>
			<td>원재료코드</td>
			<td>품목명</td>
			<td>자재유형</td>
			<td>원가</td>
			<td>단가</td>
			<td>용량</td>
			<td>발주수량</td>
			<td>발주등록일</td>
			<td>발주담당자</td>
			<td>진행상황</td>
		</tr>

		<c:forEach var="pl" items="${purchaselist}">
			<tr>
				<td><c:out value="${pl.product_code}" /></td>
				<td><c:out value="${pl.orders_code}" /></td>
				<td><c:out value="${pl.material_code}" /></td>
				<td><c:out value="${pl.name}" /></td>
				<td><c:out value="${pl.category}" /></td>
				<td><c:out value="${pl.cost}" /></td>
				<td><c:out value="${pl.price}" /></td>
		<td><c:out value="${pl.amount}${pl.amount_unit}" /></td>
				<td><c:out value="${pl.quantity}" /></td>
				<td><fmt:formatDate value="${pl.regdate}" pattern="yyyy-MM-dd" /></td>
				<td><c:out value="${pl.user_id}" /></td>

				<!-- 	<button type="button" class="btn btn-primary" onclick="buttonClick()">대기</button> -->
				<td><button type="button" class="btn btn-primary statusButton"
						onclick="updateStatus(this)" data-status="waiting">대기</button></td>

			</tr>
		</c:forEach>
	</table>


<div class="box-footer clearfix">
		<ul class="pagination pagination-sm no-margin pull-right">
			
			<c:if test="${pageVO.prev }">
				<li><a href="/materials/purchaselist?page=${pageVO.startPage - 1 }">«</a></li>
			</c:if>
			
			<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
				<li ${pageVO.cri.page == i?  "class='active'":"" }>
					<a href="/materials/purchaselist?page=${i }">
						${i }
					</a>
				</li>
			</c:forEach>
			
			<c:if test="${pageVO.next }">
				<li><a href="/materials/purchaselist?page=${pageVO.endPage + 1 }">»</a></li>
			</c:if>
		</ul>
	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>

<script>
		function updateStatus(button) {
			var product_code = $(button).closest('tr').find('.product_code')
					.val();
			var currentStatus = $(button).data('status');

			$.ajax({
				type : 'POST',
				url : 'updateStatus',
				data : {
					product_code : product_code,
					status : 'complete'
				},
				success : function(response) {
					if (response > 0) {
						// 성공 시 버튼 상태를 업데이트합니다.
						$(button).data('status', 'complete');
						$(button).removeClass('btn-primary').addClass(
								'btn-success').text('완료');
					} else {
						// 실패 시 상태를 원래대로 돌려놓습니다.
						$(button).data('status', 'waiting');
						$(button).removeClass('btn-success').addClass(
								'btn-primary').text('대기');
						alert('상태 업데이트 실패');
					}
				},
				error : function() {
					// 에러 시 상태를 원래대로 돌려놓습니다.
					$(button).data('status', 'waiting');
					$(button).removeClass('btn-success')
							.addClass('btn-primary').text('대기');
					alert('AJAX 오류 발생');
				}
			});
		} 

	
	</script>
	
</body>
</html>