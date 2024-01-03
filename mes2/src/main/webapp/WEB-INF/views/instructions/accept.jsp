<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/production/accept.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>

	<div class="container">
		<form action="/instructions/accept" method="post">
			<h3>수주 번호 : ${instructionDTO.sopCode}</h3><br>
		
			<input type="hidden" name="sopCode" value="${instructionDTO.sopCode}">
		
			<span class="search-font">작업 날짜</span>
          	<input id="startDate" type="date" min="2023-12-01" max="2024-12-31" name="dueDate"/>
		
			<label> 생산 가능한 라인
				<select id="selectLine" name="line">
				</select>
			</label><br>
			
			<span>수주물량 : ${instructionDTO.salesQuantity}</span><br>
			<span>
			생산 목표량 : 
			<fmt:formatNumber value="${instructionDTO.salesQuantity + (instructionDTO.salesQuantity/10) }" pattern=""/>
			
			[수주물량 + 예비물량(10%)]
			</span><br>
			
			호우호우
				
			<label>
				<input type="text" name="soiCode">
			</label><br>
			호우호우
			호우호우
			<input type="submit" value="종료" id="submit">
		</form>
	</div>
	
	<script type="text/javascript">

	const sbutton = document.getElementById("submit")

	function end(){
		opener.document.location.reload();		
		self.close();
	}
	
	function lazyEnd(){
		setTimeout(end, 1000);
	}
	sbutton.addEventListener('click', () => {
		setTimeout(end, 1000);
		
   });
	
	</script>
	
	<script src="/resources/js/instructions/accept.js"></script>
</body>
</html>