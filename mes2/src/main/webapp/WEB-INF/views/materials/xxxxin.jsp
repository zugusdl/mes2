<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
							<tr>
								<td>품목코드</td>
								<td>로트번호</td>
								<td>자재유형</td>
								<td>품목명</td>
								<td>수량</td>
								<td>단위</td>
								<td>입고등록일</td>
								<td>입고담당자</td>
							</tr>

							<c:forEach var="in" items="${inList}">
								<tr>
									<td><c:out value="${in.product_code}" /></td>
									<td><c:out value="${in.pd_lot}" /></td>
									<td><c:out value="${in.category}" /></td>
									<td><c:out value="${in.name}" /></td>
									<td><c:out value="${in.in_quantity}" /></td>
									<td><c:out value="${in.unit}" /></td>
										<td><fmt:formatDate value="${in.in_regdate}" pattern="yyyy-MM-dd" /></td>
									<td><c:out value="${in.user_id}" /></td>
									<!-- Add more columns as needed -->
									<td>
									<form action="/materials/in">
											<input type="hidden" name="product_code" value="${in.product_code}"> 
											<button type="submit" value="버튼"> 상세보기 제발</button>
												
									</form>
								</td>
								</tr>
							</c:forEach>

						</table>



						<table border="1">
							<tr>
								<td>품목코드</td>
								<td>로트번호</td>
								<td>자재유형</td>
								<td>품목명</td>
								<td>수량</td>
								<td>단위</td>
								<td>입고등록일</td>
								<td>입고담당자</td>
							

					
								
								
								</tr>
							<c:forEach var="dt" items="${detailList}">
								<tr>
								
									<td><c:out value="${dt.product_code}" /></td>
									<td><c:out value="${dt.pd_lot}" /></td>
									<td><c:out value="${dt.category}" /></td>
									<td><c:out value="${dt.name}" /></td>
									<td><c:out value="${dt.in_quantity}" /></td>
									<td><c:out value="${dt.unit}" /></td>
									<td><c:out value="${dt.in_regdate}" /></td>
									<td><c:out value="${dt.user_id}" /></td>
									
								</tr>
							
							</c:forEach>

						</table>
</body>
</html>