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
								<td>출고유형<td> 
								<td>출고등록일</td>
								<td>출고담당자</td>
								<td>진행상황</td>
								<td>
							</tr>

							<c:forEach var="out" items="${OutList}">
								<tr>
									<td><c:out value="${out.product_code}" /></td>
									<td><c:out value="${out.pd_lot}" /></td>
									<td><c:out value="${out.category}" /></td>
									<td><c:out value="${out.name}" /></td>
									<td><c:out value="${out.out_quantity}" /></td>
									<td><c:out value="${out.unit}" /></td>
									<td><c:out value="${out.out_type }" /></td>
						<td><fmt:formatDate value="${out_out_regdate}" pattern="yyyy-MM-dd" /></td>
									<td><c:out value="${out.user_id}" /></td>
									<td><c:out value="${out.status}" /></td>
									<!-- Add more columns as needed -->
									<td>
									<form action="/materials/out">
											<input type="hidden" name="product_code" value="${out.product_code}"> 
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
									<td><c:out value="${dt.out_quantity}" /></td>
									<td><c:out value="${dt.unit}" /></td>
									<td><c:out value="${dt.out_type}" /></td>
									<td><c:out value="${dt.out_regdate}" /></td>
									<td><c:out value="${dt.status}" /></td>
									<td><c:out value="${dt.user_id}" /></td>
									
								</tr>
							
							</c:forEach>

						</table>
</body>
</html>