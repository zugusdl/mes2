<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	TestPage
	<form action="/instructions/accept" method="post">
		<h3>수주 번호 : ${instructionDTO.sopCode}</h3><br>
		
		<input type="hidden" name="sopCode" value="${instructionDTO.sopCode}">
		
		<span class="search-font">검색시작일</span>
          <input id="startDate" type="date" min="2023-12-01" max="2024-12-31" name="dueDate"/>
		
		<label> 생산라인
			<select id="boundary" name="line">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
			</select>
		</label><br>
		
		
		<label>
			<input type="text" name="soiCode">
		</label><br>

				
		<input type="submit" value="종료" id="submit">


	</form>


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
</body>
</html>