/**
 * 
 */

  var updateBtn = false;
 
 $(document).ready(function(){
	  loadList();
  })


  function loadList(){
	  
	 alert("ㅇㅇ");
	 
	  $.ajax({
		  url:"/materials/inlist", //목록 담아오기 (리스트 DTO랑 연결)
		  type:"get",
		  dataType:"json",
		  success: list,
		  error: function(){alert("error");}
	  });
	  
	  
  } 


 function list(data){
	 // loadList()에서 가져온 데이터로 리스트만들기
	 
	  var listHtml = "<table class='table table-hover'>";
	  listHtml += "<thead>";
	  listHtml += "<tr class='table-success'>";
	  listHtml += "<th></th>";
	  listHtml += "<th scope='col'>입고코드</th>";
	  listHtml += "<th scope='col'>입고수량</th>";
	  listHtml += "<th scope='col'>상품코드</th>";
	  listHtml += "<th scope='col'>상품등록일</th>";
	  listHtml += "<th scope='col'>로트번호</th>";
      
	  listHtml += "</tr>";
	  listHtml += "</thead>";
	  listHtml += "<tbody>";
	  

	// 반복문 돌면서 가져온 데이터 넣어주기 obj.변수명으로 입력하면 된다. 
	  $.each(data,function(index,obj){
		  listHtml += "<tr>";
		  listHtml += "<td scope='row'><input type='checkbox' class='ck' name='idx' value='"+obj.in_index+"' id='"+obj.idx+"'/></td>";
		  listHtml += "<td>"+obj.in_code+"</td>";
		  listHtml += "<td>"+obj.in_quantity+"</td>";
		  listHtml += "<td>"+obj.product_code+"</td>";
		  listHtml += "<td>"+obj.in_regdate+"</td>";
		  listHtml += "<td>"+obj.pd_lot+"</td>";
	// 상세보기 클릭하면 goContetn함수 호출 
	// 매개변수로 고유값을 넣어주면 된다.(obj.idx) 		  
listHtml += "<td><a href='javascript:goContent("+obj.idx+")'>" + obj.title + "</a></td>";
		  listHtml += "<td>"+obj.writer+"</td>";
		  
		  listHtml += "</tr>";
	  });
	  listHtml += "</tbody>"; 
	  listHtml += "</table>";
	 
	// 위 리스트가 들어갈 곳의 아이디를 가져와서 넣어주기 
	  
	  $("#view").html(listHtml);
  } 
  }