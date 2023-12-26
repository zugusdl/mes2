<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>자재 발주 폼</title>

<style type="text/css">
.table-container {
	width: 80%;
	height: 200px; box-shadow : 0 0 10px rgba( 0, 0, 0, 0.1);
	/* 음영 스타일 조절 가능 */
	padding: 10px; /* 선택적으로 패딩 추가 가능 */
	margin: 10px; /* 선택적으로 마진 추가 가능 */
	overflow: scroll;
	overflow-x: hidden;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	font-size: 15px;
}

.btn-info { 
color : #fff;
background-color: #36b9cc; }

.form-control {
        width: 60%; /* 원하는 크기로 조절합니다 */
    }
    
</style>


</head>
<body>
	<!--  -->
	<h3> 이거는 밑에 상세 </h3>
	<div class="table-container">
		<table class="table table-hover">
			<thead>
				<tr>
					<th></th>
					<th>발주코드</th>
					<th>품목코드</th>
					<th>자재코드</th>
					<th>자재명</th>
					<th>자재</th>
					<th>단가</th>
					<th>발주수량</th>
					<th>진행상황</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<th>발주코드</th>
					<th>자재코드</th>
					<th>자재명</th>
					<th>자재</th>
					<th>단가</th>
					<th>발주수량</th>
					<th>진행상황</th>
				  <th><button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">상세조회</button></th>
				</tr>
    </td>
</tbody>
<!-- 모달 창 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">발주조회</h5>
                <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            
                <!-- 모달 내용 -->
            <div class="modal-body">
           <div class="form-group">
    <label for="inputInCode">입고코드</label>
    <input type="text" class="form-control" id="inputInCode" readonly>
</div>

<div class="form-group">
    <label for="inputOrderCode">발주코드</label>
    <input type="text" class="form-control" id="inputOrderCode" readonly>
</div>

<div class="form-group">
    <label for="inputMaterialCode">자재코드</label>
    <input type="text" class="form-control" id="inputMaterialCode" readonly>
</div>

<div class="form-group">
    <label for="inputMaterialName">자재명</label>
    <input type="text" class="form-control" id="inputMaterialName" readonly>
</div>

<div class="form-group">
    <label for="inputMaterial">자재</label>
    <input type="text" class="form-control" id="inputMaterial" readonly>
</div>

<div class="form-group">
    <label for="inputUnitPrice">단가</label>
    <input type="text" class="form-control" id="inputUnitPrice" readonly>
</div>

<div class="form-group">
    <label for="inputOrderQuantity">발주수량</label>
    <input type="text" class="form-control" id="inputOrderQuantity" readonly>
</div>

<div class="form-group">
    <label for="inputProgress">진행상황</label>
    <input type="text" class="form-control" id="inputProgress" readonly>
</div>
            </div>
                <!-- 모달 내용 -->
                
                
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-info">발주신청</button>
            </div>
        </div>
    </div>
</div>

</table>
<!-- 필요한 JavaScript 및 부트스트랩 스크립트 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>