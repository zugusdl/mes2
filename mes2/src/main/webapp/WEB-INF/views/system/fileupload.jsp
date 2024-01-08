<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일업로드창</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var cnt = 1;
		
		//버튼 클릭시 파일입력 버튼 추가(input태그 이름변경)
		$("#addBtn").click(function(){
			$("#fileDiv").append("<input type='file' name='file"+cnt+"' accept='image/*,application/pdf'>");
			cnt++;
		});
		
	});


</script>

</head>
<body>
	<h1>/views/fileUpload.jsp</h1>
	
		<fieldset>
			<legend>다중파일 업로드</legend>
				<form action="" method="post" enctype="multipart/form-data">
					아이디 : <input type="text" name="user_id"> <br>
					비밀번호 : <input type="password" name="user_pw"> <br>
					<input type="button" value="파일추가" id="addBtn">
						<div id="fileDiv"></div>
						<hr>
					<input type="submit" value="파일업로드">
				</form>
		</fieldset>
	
	
	
</body>
</html>