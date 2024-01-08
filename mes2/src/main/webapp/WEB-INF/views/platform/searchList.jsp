<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" href="/resources/css/platform/searchList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<div id="productList" class="section1">
		<div class="content">
			<h3 id="h3">발주 품목 등록</h3>
			<form method="post">
				<select name="searchType" id="searchType">
					<option value="default">구분</option>
					<option value="품목코드">품목코드</option>
					<option value="품목명">품목명</option>
				</select> <input type="text" name="search" id="search"
					placeholder="검색어를 입력하세요">
				<button type="submit" class="btn btn-secondary">조회</button>
			</form>
			<c:if test="${!empty mdpDTO }">
				<form action="/platform/insertProduct" method="post" class="pList">
						<div class="listTitle">
							<span class="pCode">품목코드</span>
							<span class="pImage">형상정보</span>
							<span class="pName">품목명</span>
							<span class="pPrice">단가</span>
						</div>
						<c:forEach var="mdpDTO" items="${mdpDTO }">
							<div>
							<hr>
							<input type="radio" name="product_code" value="${mdpDTO.product_code} ">
							<span class="pCode">${mdpDTO.product_code} </span>						
							<span class="pImage"><img alt="" src=""> </span>					
							<span class="pName">${mdpDTO.name}	</span>					
							<span class="pPrice"> <fmt:formatNumber value="${mdpDTO.price}"/>원</span>					
							</div>
						</c:forEach>
						<button type="submit" class="btn btn-secondary regist">등록</button>
				</form>
			</c:if>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
		
	</script>
	<script src="/resources/js/platform/searchList.js"></script>
</body>
</html>