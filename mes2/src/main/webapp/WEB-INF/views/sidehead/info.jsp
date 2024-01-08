<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

 .profile-img {
    width: 100px; /* 원하는 크기로 조절합니다. */
    height: 100px; /* 가로 크기에 따라 세로 크기를 자동 조절합니다. */
  }


table {
	   width: 100%;
	   border: 2px solid black; 
	 }

  th{
	 border: 2px solid black; 
	}
	
 td{
	 border: 2px solid black; 
	}	

</style>

</head>
<body>
		
	<form action="" method="post">
		<table>
	   	<tr>
			<th>프사</th>
			<td><img src="${pageContext.request.contextPath}/resources/img/${memberDTO.user_img}" class="profile-img"></td>		
	 	</tr>
	 
		<tr>
			<th>사원번호</th>
			<td>${memberDTO.user_num}</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${memberDTO.user_id}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${memberDTO.user_name}</td>
		</tr>
		<tr>
			<th>주민번호</th>
			<td>${memberDTO.user_jumin}</td>
		</tr>
		<tr>
			<th>부서</th>
			<td>${memberDTO.user_department}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${memberDTO.user_address}</td>
		</tr>
		<tr>
			<th>직급</th>
			<td>${memberDTO.user_position}</td>
		</tr>
		<tr>
			<th>입사일</th>
			<td>${memberDTO.user_joindate}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${memberDTO.user_tel}</td>
		</tr>
		
	</table>
</form>	




	<a href="/system/sidehead"> 메인페이지로 </a>

</body>
</html>