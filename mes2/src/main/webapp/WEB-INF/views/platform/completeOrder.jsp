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
	href="${pageContext.request.contextPath}/resources/css/platform/completeOrder.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<div class="container2">
		<h3 id="h3">수령 확인</h3>
		<section class="section1">
			<form method="post">
				<span>* 성함을 정자로 작성하세요</span>
				<div class="canvas">
					<canvas id="canvas"></canvas>
				</div>
				<button type="button" class="btn btn-secondary" onclick="completeOrder();">수령 완료</button>
			</form>
		</section>
	</div>

	<script src="${pageContext.request.contextPath}/resources/js/platform/completeOrder.js"></script>
</body>
</html>