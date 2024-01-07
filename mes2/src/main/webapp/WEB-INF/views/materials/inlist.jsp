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

	
	<form action="/materials/inlist" method="get">
		<select name="searchType">
			<option value="status">입고현황</option>
		</select> <input type="text" name="keyword"> <input type="submit"
			value="검색하기">
	</form>


	
	
	

	<a href="/materials/in"></a>
	<table class="table table-hover">
		<tr>
			<td></td>
			<td>로트번호</td>			
			<td>품목명</td>
			<td>수량</td>
			<td>단위</td>
			<td>자재유형</td>
			<td>입고등록일</td>
			<td>입고담당자</td>
			<td>진행상황</td>
		</tr>

		<c:forEach var="in" items="${inlist}">
			<tr>
				<td><input type="hidden" class="product_code" value="${in.product_code}" /></td>
				<td><c:out value="${in.pd_lot}" /></td>
				<td><c:out value="${in.name}" /></td>
				<td><c:out value="${in.quantity}" /></td>
				<td><c:out value="${in.unit}" /></td>
				<td><c:out value="${in.category}" /></td>
				<td><fmt:formatDate value="${in.in_regdate}" pattern="yyyy-MM-dd" /></td>
				<td><c:out value="${in.user_id}" /></td>

				<!-- 	<button type="button" class="btn btn-primary" onclick="buttonClick()">대기</button> -->
			<td>
				<c:if test="${in.status.equals('waiting')}">
						<form action="/materials/updateInStatus" method="post">
							<input type ="hidden" value="${in.pd_lot}" name="in_pd_lot">
							<button type="submit" class="btn btn-primary statusButton" >대기</button>
						</form>
					</c:if>
					<c:if test="${in.status.equals('complete')}">
						<button type="button" class="btn btn-primary statusButton" >완료</button>
					</c:if>
				</td>
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