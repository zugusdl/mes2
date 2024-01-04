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

<!-- 추가 페이징 css 입니다. -->
<link rel="stylesheet" href="/resources/css/metadata/paging.css">


<!--  추가 버튼 스크립트 -->
<!-- 저장버튼을 클릭하면 (추,수,삭)3개버튼은 사라지고 취소 버튼이 나오게 되는 js 입니다.  -->
<!--  밑부분은  추가되는 행이 보이게 하거나 숨기는 js입니다. -->
<script>
		function replaceButton() {
            
            var addbtn = document.getElementById('addbtn');
            var updatebtn = document.getElementById('updatebtn');
            
            addbtn.style.display = 'none';
            updatebtn.style.display = 'none';
            

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


<!--  수정 버튼 스크립트 -->
<!-- 저장버튼을 클릭하면 (추,수,삭)3개버튼은 사라지고 취소 버튼이 나오게 되는 js 입니다.  -->
<!--  밑부분은  추가되는 체크박스를 보이게 하거나 숨기는 js입니다. -->
<script>
		function replaceButton2() {
            
            var addbtn = document.getElementById('addbtn');
            var updatebtn = document.getElementById('updatebtn');
            
            addbtn.style.display = 'none';
            updatebtn.style.display = 'none';
            

            
            var canclebtn = document.getElementById('canclebtn');
            var submitbtn2 = document.getElementById('submitbtn2');
            var submitbtn3 = document.getElementById('submitbtn3');
            
            submitbtn2.style.display = 'inline-block'; // 또는 'block' 등을 사용할 수 있음
            submitbtn3.style.display = 'inline-block';
            canclebtn.style.display = 'inline-block';
            
            
            //체크박스 보이게 하는 법!
            var checkboxes = document.querySelectorAll(".updatecheckbox");
            
            
            checkboxes.forEach(function(checkbox) {
            	
            	if (checkbox.style.display === "none") {
                    checkbox.style.display = "block";
                }
            	else {
                checkbox.style.display = "none";
                }
           
            	
            	checkbox.addEventListener('change', () => {
            	      
            	      checkboxes.forEach(otherCheckbox => {
            	        
            	    	  if (otherCheckbox !== checkbox) {
            	          otherCheckbox.checked = false;
            	          
            	    	  }
            	    	  
            	      
            	      });
            	})
            });
           
        }
</script>




<!--  다른체크박스 클릭시 창닫는 js -->
<script>
    function a(checkbox) {
        var row = checkbox.closest('tr');

        if (row) {
            var classAElements = row.getElementsByClassName('a');
            var classBElements = row.getElementsByClassName('b');

            if (checkbox.checked) {
                // 체크될 때
                for (var i = 0; i < classAElements.length; i++) {
                    classAElements[i].style.display = 'none';
                }
                for (var i = 0; i < classBElements.length; i++) {
                    classBElements[i].style.display = 'table-cell'; // 또는 다른 display 값으로 설정
                }
            } else {
                // 체크가 해제될 때
                for (var i = 0; i < classAElements.length; i++) {
                    classAElements[i].style.display = 'table-cell'; // 또는 다른 display 값으로 설정
                }
                for (var i = 0; i < classBElements.length; i++) {
                    classBElements[i].style.display = 'none';
                }
            }
        }
        
        var allRows = document.getElementsByTagName('tr');
        for (var j = 0; j < allRows.length; j++) {
            if (allRows[j] !== row) {
                var otherClassAElements = allRows[j].getElementsByClassName('a');
                var otherClassBElements = allRows[j].getElementsByClassName('b');

                // class="a"인 열 보이게 설정
                for (var i = 0; i < otherClassAElements.length; i++) {
                    otherClassAElements[i].style.display = 'table-cell';
                }

                // class="b"인 열 감추기
                for (var i = 0; i < otherClassBElements.length; i++) {
                    otherClassBElements[i].style.display = 'none';
                }
            }
        }
        
        
    }
</script>



<!--  추가 ajax -->
<script>
function submitData() {
    // 입력 필드의 값을 가져오기
    var productCode = $('input[name="product_code"]').val();
    var name = $('input[name="name"]').val();
    var category = $('input[name="category"]').val();
    var unit = $('input[name="unit"]').val();
    var cost = $('input[name="cost"]').val();
    var price = $('input[name="price"]').val();
    var productionStatus = $('input[name="production_status"]').val();
    
    var formData = new FormData();
	formData.append('product_code', productCode);
	formData.append('name', name);
	formData.append('category', category);
	formData.append('unit', unit);
	formData.append('cost', cost);
	formData.append('price', price);
	formData.append('production_status', productionStatus);
	
	// 파일 업로드를 위한 코드
	  var fileInput = $('#addBtn')[0];
	  var file = fileInput.files[0];
	  formData.append('file', file);
	  
	  
    // Ajax를 사용하여 서버에 데이터 전송
    $.ajax({
        url: '/meta_data/insertproduct',
        type: "POST",
        enctype: 'multipart/form-data',
        
        data: formData,
        
        processData: false,
        contentType: false,

        success: function() {
            

            // 추가적인 동작 수행
            // 예를 들어, 페이지 리로드 등
            alert('추가완료');
            location.reload();
            
        },
        error: function(error) {
            // 에러 처리
        	console.error('에러 발생:', error);
        }
    });
}    
</script>


<!-- 수정 ajax -->
<script>
    function submitData2(submitbtn2) {
		
    	var row = $(submitbtn2).closest('tr');

        // 수정된 값 가져오기
        var hiddenProductCode = row.find('.b:eq(0)').text();
        var hiddenName = row.find('.b input[name="name"]').val();
        var hiddenCategory = row.find('.b input[name="category"]').val();
        var hiddenUnit = row.find('.b input[name="unit"]').val();
        var hiddenCost = row.find('.b input[name="cost"]').val();
        var hiddenPrice = row.find('.b input[name="price"]').val();
        var hiddenProductionStatus = row.find('.b input[name="production_status"]').val();
		
        var formData = new FormData();
    	formData.append('product_code', hiddenProductCode);
    	formData.append('name', hiddenName);
    	formData.append('category', hiddenCategory);
    	formData.append('unit', hiddenUnit);
    	formData.append('cost', hiddenCost);
    	formData.append('price', hiddenPrice);
    	formData.append('production_status', hiddenProductionStatus);
    	
    	// 파일 업로드를 위한 코드
	  	var fileInput = row.find('#addBtn2')[0];
	  	var file = fileInput.files[0];
	  	formData.append('file', file);
    	
        // AJAX를 사용하여 서버로 데이터 전송
        $.ajax({
            url: '/meta_data/updateproduct', // 실제 서버 엔드포인트로 변경해야 합니다.
            type: 'POST',
            data: formData,
            
            processData: false,
            contentType: false,
            
            success: function(response) {
                // 서버로부터의 응답 처리
                alert('수정완료');
                location.reload();
            },
            error: function(error) {
                // 에러 처리
                console.error(error);
            }
        });
    	
    }
</script>

<!-- 삭제 ajax -->
<script>
    function submitData3(submitbtn3) {
		
    	var row = $(submitbtn3).closest('tr');
        var hiddenProductCode = row.find('.b:eq(0)').text();

        $.ajax({
            url: '/meta_data/deleteproduct',
            type: 'POST',
            data: {
                product_code: hiddenProductCode,
            },
            success: function(response) {
                alert('삭제완료');
                location.reload();
            },
            error: function(error) {
                console.error(error);
            }
        });
    	
    }
</script>

<!-- 취소버튼 js -->
<script>
function redirectToFirstPage() {
    window.location.href = '/meta_data/firstpage';
}
</script>


<!--  이미지 미리보기 js -->
<script type="text/javascript">
    //이미지 미리보기
    var sel_file;
 
    $(document).ready(function() {
        $("#addBtn, #addBtn2").on("change", handleImgFileSelect);
    });
 
    function handleImgFileSelect(e) {
        var files = e.target.files;
        var filesArr = Array.prototype.slice.call(files);
 
        var reg = /(.*?)\/(jpg|jpeg|png|bmp)$/;
 
        filesArr.forEach(function(f) {
            if (!f.type.match(reg)) {
                alert("확장자는 이미지 확장자만 가능합니다.");
                return;
            }
 
            sel_file = f;
 
            var reader = new FileReader();
            reader.onload = function(e) {
                $("#img, #img2").attr("src", e.target.result);
            }
            reader.readAsDataURL(f);
        });
    }
</script>




</head>
<body>

<%@ include file="../../sidehead/sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="son_container">
			
		<div class="son_serch">
			
			
			<form id="dateForm" action="/meta_data/firstpage" onsubmit="" method="POST">
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
				<button type="button" class="btn btn-secondary" id="updatebtn" onclick="replaceButton2()">수정</button>
			</form>
		</div>

		<div>
			<button type="button" class="btn btn-secondary" id="canclebtn" onclick="redirectToFirstPage()" style="display: none;">취소</button>
		</div>

			
			
		</div>		
			
			

			<!-- 표 -->
			<div class="list">
				

				<div class="son_list-box">	
						<table class="table table-hover">
							<thead>
								<tr class="table-success" style="text-align: center;" >
									<th scope="col">C</th> <!-- 체크박스 -->
									<th scope="col">품목코드</th>
									<th scope="col">품명</th>
									<th scope="col">카테고리</th>
									<th scope="col">재고단위</th>
									<th scope="col">원가</th>
									<th scope="col">단가</th>
									<th scope="col">취급유무</th>
									<th scope="col">등록일</th>
									<th scope="col" width="100px">사진</th>
									<th scope="col"></th>
									
								</tr>
							</thead>
							
							<tbody>
								
								
								<!-- 품목추가버튼 누를시 나옴 -->
								<tr id="inserthang" style="display: none; text-align: center; vertical-align: middle;">	
									
									<td></td>							
									<td><input type="text" name="product_code" size="5"></td>
									<td><input type="text" name="name" size="5"></td>
									<td><input type="text" name="category" size="5"></td>
									<td><input type="text" name="unit" size="5"></td>
									<td><input type="text" name="cost" size="5"></td>
									<td><input type="text" name="price" size="5"></td>
									<td><input type="text" name="production_status" size="5"></td>
									<td></td>
									<td>
									<img id="img" width="250px"/> 						
									<input type="file"  value="사진 추가" id="addBtn" style="width: 200px;">									
									</td>
									<td>
									<button type="button" class="btn btn-secondary" id="submitbtn" onclick="submitData()" style="display: none;">저장</button>
									</td>									
								</tr>	
								
								
								
								<!-- 모든물품 검색하기 productList를 가져오면 실행됨 -->
								<c:if test="${!empty productList }">
								<c:forEach var="plist" items="${productList }">
								<tr style="text-align: center; vertical-align: middle; ">
									<td><input type="checkbox" class="updatecheckbox" style="display: none;" onchange="a(this)"/></td>									
									
									
									<td class="a">${plist.product_code }</td>
									<td class="a">${plist.name }</td>
									<td class="a">${plist.category }</td>
									<td class="a">${plist.unit }</td>
									<td class="a">${plist.cost }</td>
									<td class="a">${plist.price }</td>
									<td class="a">${plist.production_status }</td>
									<td class="a">${plist.regdate }</td>
									<td class="a">																	  							     								
									<img src="../../../../resources/img/metadata/${plist.ofileName }" width="200px" alt="">
									</td>
									<td class="a" style="content: '\00a0'"></td>
									
									
															
									<td class="b" style="display: none;">${plist.product_code }</td>
									<td class="b" style="display: none;"><input type="text" name="name" size="5" value="${plist.name }"></td>
									<td class="b" style="display: none;"><input type="text" name="category" size="5" value="${plist.category }"></td>
									<td class="b" style="display: none;"><input type="text" name="unit" size="5" value="${plist.unit }"></td>
									<td class="b" style="display: none;"><input type="text" name="cost" size="5" value="${plist.cost }"></td>
									<td class="b" style="display: none;"><input type="text" name="price" size="5" value="${plist.price }"></td>
									<td class="b" style="display: none;"><input type="text" name="production_status" size="5" value="${plist.production_status }"></td>
									<td class="b" style="display: none;">${plist.regdate }</td>
									<td class="b" style="display: none; width: 80px;">								
									<img id="img2" src="../../../../resources/img/metadata/${plist.ofileName }" width="200px" alt="123">						
									<input type="file" id="addBtn2" style="width: 200px;">
									</td>
									<td class="b" style="display: none; width: 80px; ">
									<button type="button" style="margin: 10px 0;" class="btn btn-secondary" id="submitbtn2" onclick="submitData2(this)" >수정</button>
									<button type="button" style="margin: 10px 0;" class="btn btn-secondary" id="submitbtn3" onclick="submitData3(this)" >삭제</button>																		 									
									</td>
									
								
								
								
								
								</tr>
								
								</c:forEach>
								</c:if>
								<!-- 모든물품 검색하기 productList를 가져오면 실행됨 -->
							</tbody>
							
						</table>
				</div>
			</div>
		<!--  페이징 -->
		<div class="box-footer clearfix">
		<div style="margin: 0 auto; width: fit-content;" class="pagination-container">
		<ul class="pagination pagination-sm no-margin pull-right">
			
			<c:if test="${pageVO.prev }">
				<li><a href="/meta_data/firstpage?page=${pageVO.startPage - 1 }">«</a></li>
			</c:if>
			
			<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
				<li class=${pageVO.cri.page == i ? "active":"" }>
					<a href="/meta_data/firstpage?page=${i }&search=${aDTO.search }&startDate=${aDTO.startDate }&endDate=${aDTO.endDate }">
						${i }
					</a>
				</li>
			</c:forEach>
			
			<c:if test="${pageVO.next }">
				<li><a href="/meta_data/first?page=${pageVO.endPage + 1 }">»</a></li>
			</c:if>
		</ul>
		</div>
		</div>
		<!-- 페이징 끝 -->
	</div>
	



</body>
</html>