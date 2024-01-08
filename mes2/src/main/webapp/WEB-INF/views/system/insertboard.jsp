<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

 <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>

<!-- 부트스트랩 css cdn입니다. -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<!--  부트스트랩 js cdn입니다. -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous">
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>

<form method="post" action="/system/insertBoard">
		    	<div class="modal-body" style="max-width: 1100px;">
		    		<br>
		    		  <div class="card mb-3" style="max-width: 1100px;"> 
		    		  	 <div class="row g-0">
		    		   				<div class="col-md-4">
            						    <img src="${pageContext.request.contextPath}/resources/img/join/joining.jpg" class="img-fluid rounded-start" alt="...">
          							</div>
		    		          <div class="col-md-8">
		    		               <div class="card-body">
		    		     <label for="exampleInputEmail1" class="form-label">글제목</label>
						      <div class="input-box">
							      <input type="text" placeholder="글제목입력" name="title" id="join-id">
				   			 </div>
		    		   	 <label for="exampleInputEmail1" class="form-label">글쓴이</label>
						   <div class="input-box">
				              <input type="text" name="writer" placeholder="작성자입력" />
				           </div>
					      <div class="mb-3">
						    <label for="exampleInputPassword1" class="form-label">글내용</label><br>
						    <textarea class="form-control" rows="3" placeholder="내용을 입력하세요" name="content"></textarea>
						  </div>  
						
								   </div>
							   </div>
							</div>
						</div>			 
					</div>	
	                <!-- Modal footer -->
	                 <div class="modal-footer" align="center">
	                    <button type="button" class="btn btn-info" id=insertBoardButton">등록하기</button>
		    		</div>
		    </form>	











</body>
</html>