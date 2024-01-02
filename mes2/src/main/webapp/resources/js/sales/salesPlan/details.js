 function goContent(order_code){
       
	  $.ajax({
		  url:"planContent", // 
		  type:"post",
		  dataType:"json", // Jackson Databind사용해서 json으로 받기
		  data: {"order_code": order_code }, // 받은 매개변수 전달
		  success:content,
		  error: function(){alert("error");}
	  });
	  
	  
  }

 function cancle(){
	
		  $("#view2").html("");
	  }
 
 function mo(product_code,order_code,sales_quantity){
	 
	  $.ajax({
		  url:"stockCheck", // 
		  type:"post",
		  dataType:"json", // Jackson Databind사용해서 json으로 받기
		  data: {"product_code": product_code , "order_code" : order_code}, // 받은 매개변수 전달
		  success: function (data) {
		      moStock(data, sales_quantity); // Pass sales_quantity to moStock
		    },
		  error: function(){
			  $("#exampleModalLabel").html('재고조회');
			  var listHtml = "<div>창고에 보유 재고 없음</div>";
			  //listHtml = "<div>재고 오류(창고에 상품 있는지 확인필요)</div>";
			  listHtml += "<div>부족수량 : <input type='text' value='"+sales_quantity+"' readonly/></div>"; 
			  listHtml += "<button type='button' class='btn btn-danger'>재고부족</button>";
			  $("#sales-modal").html(listHtml);
				
		  }
	  });
 }
 
 function moStock(data, sales_quantity){
	 $("#exampleModalLabel").html('재고조회');
	 var listHtml = "<div>제품명 : <input type='text' value='"+data.product_name+"' readonly/></div>";
		 listHtml += "<div>제품코드 : <input type='text' value='"+data.product_code+"' readonly/></div>";
		 listHtml += "<div>보유수량 : <input type='text' value='"+data.stock_quantity+"' readonly/></div>";
		 if((sales_quantity - data.stock_quantity)<=0){
			 listHtml += "<div>부족수량 : <input type='text' value='0' readonly/></div>"; 
		 }else{
			 listHtml += "<div>부족수량 : <input type='text' value='"+(sales_quantity - data.stock_quantity )+"' readonly/></div>";	
			 listHtml += "<button type='button' class='btn btn-danger'>재고부족</button>"; // 수정된 라인

			 
		 }
	      
		 $("#sales-modal").html(listHtml);
 }

  function content(data){ //에이젝스에서 받은 값으로 출력하기 

	  var listHtml ="<div class='list-box'>";
	  listHtml +="<button type='button' class='btn btn-secondary' id='closeBtn' onclick='cancle()'>닫기</button>";
	  listHtml += "<table class='table table-hover'>";
	  listHtml += "<thead>";
	  listHtml += "<tr class='table-success' >";
	  listHtml += "<th scope='col'>제품코드</th>";
	  listHtml += "<th scope='col'>제품명</th>";
	  listHtml += "<th scope='col'>수량</th>";
	  listHtml += "<th scope='col'>재고조회</th>";
	  listHtml += "</tr>";
	  listHtml += "</thead>";
	  
	  listHtml += "<tbody>";
	  
	  $.each(data,function(index,obj){
		  listHtml += "<tr>";
		 // listHtml += "<td scope='row'><input type='checkbox' class='ck' name='idx' value='"+obj.product_code+"' id='"+obj.product_code+"'/></td>";
		  listHtml += "<td>"+obj.product_code+"</td>";				  
		  listHtml += "<td>"+obj.product_name+"</td>";
		  listHtml += "<td>"+obj.sales_quantity+"</td>";
		  listHtml += "<td><button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick=\"mo('" + obj.product_code + "','"+obj.order_code+"','"+obj.sales_quantity+"')\">재고조회</button></td>";
		  listHtml += "</tr>";
	  });
    

	  listHtml += "</tbody>";
	  listHtml += "</table>";
	 
	//위 표가 들어갈 자리의 아이디값 가져와서 입력하기 
	  $("#view2").html(listHtml);
  }