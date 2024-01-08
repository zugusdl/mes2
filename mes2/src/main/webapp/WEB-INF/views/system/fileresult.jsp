<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드결과확인</title>
</head>
<body>
		<h1>fileResult.jsp</h1>
	 <h2>아이디: <%= request.getAttribute("user_id") %></h2>
    <h2>비밀번호: <%= request.getAttribute("user_pw") %></h2>
		<c:forEach var="fileName" items="${paramMap.fileList }">
			<h2>파일명 : ${fileName }</h2>
		</c:forEach>
			
</body>
</html>