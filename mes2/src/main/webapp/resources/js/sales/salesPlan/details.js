 function goContent(order_code){
       
	  $.ajax({
		  url:"planContent", 
		  type:"post",
		  dataType:"json",
		  data: {"order_code": order_code }, 
		  success:content,
		  error: function(){alert("error");}
	  });
	  
	  
  }

 function cancle(){
	
		  $("#view2").html("");
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
		 listHtml += "<div>보유수량 : <input type='text' value='"+data.stock_quantity+"' readonly/></div>";
		 if((sales_quantity - data.stock_quantity)<=0){ //재고가 충분한 경우 
			 listHtml += "<div>부족수량 : <input type='text' value='0' readonly/></div>"; 
		 }else{//재고가 부족한 경우 
			 listHtml += "<div>부족수량 : <input type='text' value='"+(sales_quantity - data.stock_quantity )+"' readonly/></div>";	
			 listHtml += "<button type='button' class='btn btn-danger'>재고부족</button>"; 	 
		 }	      
		 $("#sales-modal").html(listHtml);
 }

  function content(data){  
	 
	  var listHtml ="<div class='list-box'>";
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
	 
	
	  $("#view2").html(listHtml);
  }