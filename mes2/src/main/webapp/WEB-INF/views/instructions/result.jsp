<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<br><br>
	<span>작업결과 입력</span>
	<br>
	
	<form action="/instructions/result" method="post">
		
		<h2>작업결과</h2>

		<br>
		<label>작업코드 : ${instruction.code}</label>		
		<br>
		<label>생산품 코드 : ${instruction.mdpCode}</label>
		<br>
		<label>수주번호 : ${instruction.sopCode}</label>
		<br>
		<label>대표작업자 : ${instruction.empNum}</label>
		<br>
		<br>
		<label>양품 수량 입력 <input type="number" name ="quantity"></label>
		<label>불량품 수량 입력 <input type="number" name ="fault"></label>
		<input type="hidden" name="isCode" value="${instruction.code}">
				
		<input type="submit" value="종료" id="submit">

	</form>


	<script type="text/javascript">
	</script>
</body>
</html>