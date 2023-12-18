<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>MemberlistPage</title>
</head>
	<body>
	 <h1 style ="text-align : center;">사원목록</h1>
		<div class="container">
			<table class="table" style="text-align: center; border : 1px solid #dddddd">
				<thead>
					<th style="background-color: #fafafa; text-align: center;">아이디</th>
					<th style="background-color: #fafafa; text-align: center;">비밀번호</th>
					<th style="background-color: #fafafa; text-align: center;">이름</th>
					<th style="background-color: #fafafa; text-align: center;">부서</th>
					<th style="background-color: #fafafa; text-align: center;">직급</th>
					<th style="background-color: #fafafa; text-align: center;">주민번호</th>
					<th style="background-color: #fafafa; text-align: center;">입사일</th>
					<th style="background-color: #fafafa; text-align: center;">전화번호</th>
					<th style="background-color: #fafafa; text-align: center;">수정</th>
					<th style="background-color: #fafafa; text-align: center;">삭제</th>
				</thead>
				<c:forEach var="member" items="${memberlist}">
				<tbody>
					<td>${member.emp_id}</td>
					<td>${member.emp_pw}</td>
					<td>${member.emp_name}</td>
					<td>${member.emp_department}</td>
					<td>${member.emp_position}</td>
					<td>${member.emp_jumin}</td>
					<td>${member.emp_joindate}</td>
					<td>${member.emp_tel}</td>
					   <form method="post">
					<td>
						<a href="/login/adminupdate?emp_id=${member.emp_id}">
							<button type="button" class="btn btn-primary">수정</button>
						</a>
					</td>
					<td>
						<a href="/login/admindelete?emp_id=${member.emp_id}">
							<button type="button" class="btn btn-danger">삭제</button>
						</a>					
					</td>
						</form>
				</tbody>
				</c:forEach>
			</table>
		</div>		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
	</body>
</html>