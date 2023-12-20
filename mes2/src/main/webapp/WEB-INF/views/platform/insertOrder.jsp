<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<button type="button" id="addBtn" onclick="openProductList()">품목 추가</button>
				<br> 형상정보 이름 수량 가격
			</form>
		</section>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	<script src="/resources/js/platform/insertOrder.js"></script>
</body>
</html>