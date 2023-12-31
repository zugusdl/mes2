<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>품목관리</title>

<!-- jqery js입니다. (ajax랑 js에 있는 여러가지 js라이브러리 쓸려고)-->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

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

<!-- 추가 css 입니다. -->
<link rel="stylesheet" href="/resources/css/metadata/product.css">

<!-- 추가 js 입니다. -->
<script src="/resources/js/metadata/productinfo.js"></script>


<!-- 품목 저장 버튼 js 입니다. -->
<script type="text/javascript">
 	$(document).ready(function(){
 		
 		var formObj = $('form[role="insert"]');
 		
 		console.log(formObj);
 		
 		// 추가 후 저장 버튼 클릭시, 추가 품목 정보를 가지고 submit
 		// 이동하는 페이지주소 변경, 전달방식 POST
 		$("#submitbtn").click(function(){
 					
 			formObj.submit(); 			
 		});

 		
 	});

</script>


<!-- 저장버튼을 클릭하면 (추,수,삭)3개버튼은 사라지고 저장,취소 버튼이 나오게 되는 js 입니다.  -->
<!--  밑부분은  추가되는 행이 보이게 하거나 숨기는 js입니다. -->
<script>
		function replaceButton() {
            
            var addbtn = document.getElementById('addbtn');
            var updatebtn = document.getElementById('updatebtn');
            var eletebtn = document.getElementById('eletebtn');
            addbtn.style.display = 'none';
            updatebtn.style.display = 'none';
            deletebtn.style.display = 'none';

            // 두 번째 버튼을 보임
            var canclebtn = document.getElementById('canclebtn');
            var submitbtn = document.getElementById('submitbtn');
            
            canclebtn.style.display = 'inline-block'; // 또는 'block' 등을 사용할 수 있음
            submitbtn.style.display = 'inline-block'; // 또는 'block' 등을 사용할 수 있음
       
            var row = document.getElementById("inserthang");
            
         // 현재 행의 상태를 확인하고 반대로 설정
            if (row.style.display === "none") {
                row.style.display = "table-row";
            } else {
                row.style.display = "none";
            }
            
        }
</script>








</head>
<body>

<%@ include file="../../sidehead/sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="son_container">
			
		<div class="son_serch">
			
			
			<form id="dateForm" action="/meta_data/filter" onsubmit="" method="POST">
				<span class="son_search-font">등록기간</span>
				<input id="dtIp" type="date" name="startDate" min="2023-12-01" max="2024-12-31" />
				<input id="dtIp" type="date" name="endDate" min="2020-01-01" max="2030-12-31" width="100px"/>					
				<input type="text" name="search" placeholder="검색어를 입력하세요" />
				<input type="submit" value="검색" onchange="submitForm()"/>
			</form>
			
		
		<!-- 추가 수정 삭제 form 버튼 -->
		<div class="son_list-btn">
			<form  action="">
				<button type="button" class="btn btn-secondary" id="addbtn" name="" onclick="replaceButton()">추가</button>
			</form>
		</div>	
		<div>
			<form  action="">	
				<button type="button" class="btn btn-secondary" id="updatebtn" onclick="">수정</button>
			</form>
		</div>
		<div>
			<form  action="">	
				<button type="button" class="btn btn-secondary" id="deletebtn" onclick="">삭제</button>
			</form>
		</div>	
		<div>
			<form  action="">	
				<button type="button" class="btn btn-secondary" id="submitbtn" onclick="" style="display: none;">저장</button>
			</form>
		</div>
		<div>
			<form action="/meta_data/firstpage">	
				<button type="submit" class="btn btn-secondary" id="canclebtn" onclick="" style="display: none;">취소</button>
			</form>
		</div>

			
			
		</div>		
			
			

			<!-- 표 -->
			<div class="list">
				

				<div class="son_list-box">
						<form action="/meta_data/insertproduct" role="insert" method="post">
						<table class="table table-hover">
							<thead>
								<tr class="table-success">
									<th></th> <!-- 체크박스 -->
									<th scope="col">품목코드</th>
									<th scope="col">품명</th>
									<th scope="col">카테고리</th>
									<th scope="col">재고단위</th>
									<th scope="col">원가</th>
									<th scope="col">단가</th>
									<th scope="col">취급유무</th>
									<th scope="col">등록일</th>
									<th scope="col">사진</th>
								</tr>
							</thead>
							
							<tbody >
								
								
								<!-- 품목추가버튼 누를시 나옴 -->
								<tr id="inserthang" style="display: none;">	
									<td></td>							
									<td><input type="text" name="product_code" size="5"></td>
									<td><input type="text" name="name" size="5"></td>
									<td><input type="text" name="category" size="5"></td>
									<td><input type="text" name="unit" size="5"></td>
									<td><input type="text" name="cost" size="5"></td>
									<td><input type="text" name="price" size="5"></td>
									<td><input type="text" name="production_status" size="5"></td>
									<td></td>
									<td>업로드 링크</td>
								</tr>	
								
								
								
								<!-- 모든물품 검색하기 productList를 가져오면 실행됨 -->
								<c:if test="${!empty productList }">
								<c:forEach var="plist" items="${productList }">
								<tr>
									<td scope="row"><input type="checkbox" class="ck" /></td>
									<td>${plist.product_code }</td>
									<td>${plist.name }</td>
									<td>${plist.category }</td>
									<td>${plist.unit }</td>
									<td>${plist.cost }</td>
									<td>${plist.price }</td>
									<td>${plist.production_status }</td>
									<td>${plist.regdate }</td>
									<td>${plist.regdate }</td> <!-- 사진 -->
								</tr>
								</c:forEach>
								</c:if>
								<!-- 모든물품 검색하기 productList를 가져오면 실행됨 -->
							</tbody>
							
						</table>
					</form>
				</div>
			</div>

	
	
	
	</div>
	
	

<br><br><br><br>

</body>
</html>