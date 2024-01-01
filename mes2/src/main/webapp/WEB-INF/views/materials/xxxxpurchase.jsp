<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/materials/insertOrder.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>



	<script>
$(document).ready(function() {
    $('.dropdown-item').on('click', function() {
        // Get the data attributes from the clicked item
        var product_code = $(this).data('product_code');
        var name = $(this).data('name');

        // Set the values in the corresponding input fields
        $('input[name="product_code"]').val(productCode);
        $('input[name="name"]').val(productName);
    });
});
</script>

</head>
<body>


<form action="/materials/purchaselist" method="post">
    <a href="/materials/purchaselist" class="btn btn-secondary">발주 리스트</a>
</form>

	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-bs-toggle="modal"
		data-bs-target="#exampleModal">발주신청</button>


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
									name="orders_code" ></td>
							</tr>
							<tr>
								<td>품목코드</td>
								<td><input type="text" class="form-control"
									name="product_code" required></td>
							</tr>
							<tr>
								<td>자재유형</td>
								<td><input type="text" class="form-control" name="category"
									required></td>
							</tr>

							<tr>
								<td>품목명</td>
								<td><input type="text" class="form-control-sm" name="name" required>
									<div class="dropdown">
										<button class="btn btn-secondary-sm dropdown-toggle"
											type="button" data-bs-toggle="dropdown" aria-expanded="false">
											--- ----</button>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" >정제수</a></li>
											<li><a class="dropdown-item" >글리세린</a></li>
											<li><a class="dropdown-item" >비타민</a></li>
										</ul>
										
									</div>
							</tr>
							
							<tr>
								<td>원가</td>
								<td><input type="text" class="form-control" name="cost"
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
								<td><input type="text" class="form-control" name="user_id" required></td>
							</tr>
						</table>
						<button type="submit" class="btn btn-secondary">신청</button>
						
				</div>
			</div>
		</div>
	</div>
</form>







	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	
	
<script>
    // 드롭다운 메뉴의 항목을 클릭했을 때 이벤트 처리
    $('.dropdown-item').on('click', function() {
        // 클릭한 항목의 텍스트를 가져와서 input에 설정
        $('input[name="name"]').val($(this).text());
        
        function selectItem(item, code, name) {
            document.getElementById('categoryInput').value = item;
            document.getElementById('itemCode').value = code;
            document.getElementById('itemName').value = name;
        }

        
    });

</script>
</body>
</html>