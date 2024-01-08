 function goContent(order_code){
       
	  $.ajax({
		  url:"planContent", 
		  type:"post",
		  dataType:"json",
		  data: {"order_code": order_code }, 
		  success: function(data) {
			  content(data, order_code);
		  },
		  error: function(){
			  Swal.fire({
				  title: "관계자에게 문의하십시오.",
				  icon: "warning"
				});
		  }
	  });
	  
	  
  }

 function cancle(){
	
		  $("#salesPlanContent").html("");
	  }
 
 function mo(product_code,order_code,sales_quantity){
	 
	  $.ajax({
		  url:"stockCheck", 
		  type:"post",
		  dataType:"json",
		  data: {"product_code": product_code , "order_code" : order_code}, 
		  success: function (data) {
		      moStock(data, sales_quantity); 
		    },
		  error: function(){
			  $("#salesModal").modal("show");
			  $("#salesModalLabel").html('재고조회');
			  var listHtml = "<div>창고에 보유 재고 없음</div>";			
			  listHtml += "<div>부족수량 : <input type='text' value='"+sales_quantity+"' readonly/></div>"; 
			  listHtml += "<button type='button' class='btn btn-danger'>재고부족</button>";
			  $("#sales-modal").html(listHtml);
				
		  }
	  });
 }
 
 function moStock(data, sales_quantity){
	 $("#salesModal").modal("show");
	 $("#salesModalLabel").html('재고 확인');
	 var listHtml = "<div>제품명 : <input type='text' value='"+data.product_name+"' readonly/></div>";
		 listHtml += "<div>제품코드 : <input type='text' value='"+data.product_code+"' readonly/></div>";
		 if(data.stock_quantity <0){
			 listHtml += "<div>보유수량 : <input type='text' value='0' readonly/></div>"; 
		 }
		 else{
			 listHtml += "<div>보유수량 : <input type='text' value='"+data.stock_quantity+"' readonly/></div>"; 
		 }
		 if((sales_quantity - data.stock_quantity)<=0){ //재고가 충분한 경우 
			 listHtml += "<div>부족수량 : <input type='text' value='0' readonly/></div>"; 
		 }else{//재고가 부족한 경우 
			 listHtml += "<div>부족수량 : <input type='text' value='"+(sales_quantity - data.stock_quantity )+"' readonly/></div>";	
			 listHtml += "<button type='button' class='btn btn-danger'>재고부족</button>"; 	 
		 }	      
		 $("#sales-modal").html(listHtml);
 }
function info(order_code){
	 
	 alert(order_code);
	  $.ajax({
		  url:"getOrderInfo", 
		  type:"post",
		  dataType:"json", 
		  data: {"order_code" : order_code}, 
		  success: function (data) {
			  
		      moInfo(data,order_code); 
		    },
		  error: function(){
			  
			  Swal.fire({
				  title: "관계자에게 문의하십시오.",
				  icon: "warning"
				}).then((result) => {
					$("#mo-close").trigger("click");
				});
			  
				
		  }
	  });
 }
 
 function moInfo(data, order_code){
	 var title = "<div>주문번호 : "+order_code+"</div>";
	 $("#salesModalLabel").html(title);
	 var listHtml = "<p>&lt;회사정보></p>"
		 
	     listHtml += "<div>회사명 : <input type='text' value='"+data.company_name+"' readonly/></div>";
		 listHtml += "<div>회사코드 : <input type='text' value='"+data.company_code+"' readonly/></div>";
		 listHtml += "<div>회사주소 : <input type='text' value='"+data.company_address+"' readonly/></div>";
		 listHtml += "<div>회사전화번호 : <input type='text' value='"+data.company_call+"' readonly/></div>";
		 listHtml += "<hr>"
		 listHtml += "<p>&lt;담당자정보></p>"
		 listHtml += "<div>담당자id : <input type='text' value='"+data.user_id+"' readonly/></div>";
		 listHtml += "<div>담당자이름 : <input type='text' value='"+data.user_name+"' readonly/></div>";
		 listHtml += "<div>담당자부서 : <input type='text' value='"+data.user_department+"' readonly/></div>";
		 listHtml += "<div>담당자직책: <input type='text' value='"+data.user_position+"' readonly/></div>";
		 listHtml += "<div>담당자부서 : <input type='text' value='"+data.user_auth+"' readonly/></div>";	 
		 
	      
		 $("#sales-modal").html(listHtml);
 }
  function content(data, order_code){  
	
	  var listHtml = "<div class='list-box'>";
	  listHtml += "<div>";
	  listHtml += "<p>주문번호: "+order_code+"</p>";
	  
	  listHtml += "<button type='button' class='btn btn-danger'  data-bs-toggle='modal' data-bs-target='#salesModal' onclick='info(\""+order_code+"\")'>상세</button>";
	
	  listHtml += "</div>";
	//  var listHtml ="<div class='list-box'>";
	  listHtml += " <i class='fa-solid fa-rectangle-xmark' id='closeBtn' onclick='cancle()'></i>"
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
		  listHtml += "<td>"+obj.product_code+"</td>";				  
		  listHtml += "<td>"+obj.product_name+"</td>";
		  listHtml += "<td>"+obj.sales_quantity+"</td>";
		  listHtml += "<td><button type='button' class='btn btn-primary'  onclick=\"mo('" + obj.product_code + "','"+obj.order_code+"','"+obj.sales_quantity+"')\">재고조회</button></td>";
		  listHtml += "</tr>";
	  });
    

	  listHtml += "</tbody>";
	  listHtml += "</table>";
	 
	
	  $("#salesPlanContent").html(listHtml);
  }