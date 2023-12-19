<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<link rel="stylesheet" href="/resources/css/platform/insertOrder.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
			<h1>발주 신청</h1>
			<form method="post">
				<button type="submit">신청</button>
				<button type="button">취소</button>
				<br> 납품 요청일: <input id="dtIp" type="date" name="startDate" min="2023-12-01" max="2024-12-31" /><br>
				발주 품목
				<button type="button" id="addBtn" onclick="openProductList()">품목 추가</button><br>
				형상정보 이름 수량 가격
			</form>
		</section>
	</div>

	<!-- 품목 선택 모달 -->
	<div id="productList" class="modal">
		<span onclick="closeProductList()">닫기</span>

		<!-- 모달 내용 -->
		<h3 id="h3">발주 품목 등록</h3>
		<select>
			<option>검색 구분</option>
			<option>품목명</option>
			<option>품목코드</option>
		</select>
		<input type="text" placeholder="검색어를 입력하세요">
		<button onclick="inqueryList()">조회</button>
		<button onclick="registProduct()">등록</button>

	</div>
	<!-- 품목 선택 모달 종료 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	<script src="/resources/js/platform/insertOrder.js"></script>
</body>
</html>