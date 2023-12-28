 function goContent(order_code){
       
	  $.ajax({
		  url:"acceptContent", // 
		  type:"post",
		  dataType:"json", // Jackson Databind사용해서 json으로 받기
		  data: {"order_code": order_code }, // 받은 매개변수 전달
		  success: function(data) {
			  content(data, order_code);
		  },
		  error: function(){alert("error");}
	  });
	  
	  
  }

 function cancle(){
	
		  $("#salesAcceptContent").html("");
	  }
 
 function mo(product_code,order_code,sales_quantity){
	 
	  $.ajax({
		  url:"stockCheck", // 
		  type:"post",
		  dataType:"json", // Jackson Databind사용해서 json으로 받기
		  data: {"product_code": product_code , "order_code" : order_code}, // 받은 매개변수 전달
		  success:moStock,
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
 
 function moStock(data){
	 $("#exampleModalLabel").html('재고조회');
	 var listHtml = "<div>제품명 : <input type='text' value='"+data.product_name+"' readonly/></div>";
		 listHtml += "<div>제품코드 : <input type='text' value='"+data.product_code+"' readonly/></div>";
		 listHtml += "<div>보유수량 : <input type='text' value='"+data.stock_quantity+"' readonly/></div>";
		 if((data.sales_quantity - data.stock_quantity)<0){
			 listHtml += "<div>부족수량 : <input type='text' value='0' readonly/></div>"; 
		 }else{
			 listHtml += "<div>부족수량 : <input type='text' value='"+(data.sales_quantity - data.stock_quantity )+"' readonly/></div>";	
			 listHtml += "<button type='button' class='btn btn-danger'>재고부족</button>"; // 수정된 라인

			 
		 }
	      
		 $("#sales-modal").html(listHtml);
 }
 

 function save(index){
	 
	 
	 //var in = index; // 변수로 받은 인덱스
	 //var productCodeAtIndex = $(".product_code").eq(index).val();
	 
	 var order = $("#order_code").val();
	 var scode = $(".sales_code").eq(index).val();
	 var productCode = $(".product_code").eq(index).val();
	 var salesQuantity = $(".sales_quantity").eq(index).val();
	 var processingReg = $(".product-processing").eq(index).val();
	 
	 
	 alert(scode);
	 alert(order);
	 alert(productCode);
	 alert(salesQuantity);
	 alert(processingReg);
	 
	 console.log($(".product-processing").eq(index));
	 
	 var data = {
			 order_code: order,
			 sales_code: scode,
			 product_code: productCode,
			 sales_quantity: salesQuantity,
			 processing_reg: processingReg
			};
	 
	 $.ajax({
		  url: "acceptSave",
		  method: "POST",
		  dataType:"text",
		  data: data,
		  success: function(data) {
			  goContent(data);
			 
			 
			},
		 
			error: function(){alert("error 저장");}
		});
 }

 function checkProReg(processing_reg,sales_code){
	 confirm("이미 처리하셨습니다. 변경을 원하시나욥?");
 }
  function content(data, order_code){ //에이젝스에서 받은 값으로 출력하기 
	  
	  var listHtml ="<div class='list-box'>";
	  listHtml +="<button type='button' class='btn btn-secondary' id='closeBtn' onclick='cancle()'>닫기</button>";
	  listHtml +="<button type='button' class='btn btn-secondary' id='saveBtn' onclick='save()'>저장</button>";
	  listHtml +="<input type='hidden' id='order_code' value='"+order_code+"'>";
	  listHtml += "<table class='table table-hover'>";
	  listHtml += "<thead>";
	  listHtml += "<tr class='table-success' >";
	  listHtml += "<th scope='col'>수주번호</th>";
	  listHtml += "<th scope='col'>제품명</th>";
	  listHtml += "<th scope='col'>수량</th>";
	  listHtml += "<th scope='col'>재고조회</th>";
	  listHtml += "<th scope='col'>상태</th>";
	  listHtml += "<th scope='col'>처리</th>";
	  listHtml += "<th scope='col'></th>";
	  listHtml += "</tr>";
	  listHtml += "</thead>";
	  
	  listHtml += "<tbody>";
	  
	  $.each(data,function(index,obj){
		  
		  listHtml += "<tr>";
		  listHtml +="<input type='hidden' name='sales_code' class='sales_code' value='"+obj.sales_code+"'>";
		  listHtml += "<td>"+obj.sales_code+"</td>";				  
		  listHtml += "<td>"+obj.product_name+"</td>";
		  listHtml +="<input type='hidden' name='product_code' class='product_code' value='"+obj.product_code+"'>";
		  listHtml += "<td>"+obj.sales_quantity+"</td>";
		  listHtml +="<input type='hidden' name='product_code' class='sales_quantity' value='"+obj.sales_quantity+"'>";
		  listHtml += "<td><button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#exampleModal' onclick=\"mo('" + obj.product_code + "','"+obj.order_code+"','"+obj.sales_quantity+"')\">재고조회</button></td>";
		  listHtml += "<td>"+obj.product_status+"</td>";

		  if(obj.processing_reg == 'N'){
			  listHtml += "<td class='back'><select name='processing_reg' class='product-processing'>";
			  listHtml += "<option value='N' class='check-processing'>N</option>";
			  listHtml += "<option value='stock' class='check-processing'>재고출하</option>";
			  listHtml += "<option value='production' class='check-processing'>생산계획</option>";
			  listHtml += "<option value='multi' class='check-processing'>복합처리</option>";  
			  listHtml += "</select></td>";
			 // listHtml += "<td><button type='button' class='btn btn-primary' onclick='save(\""+obj.sales_code+"\")'>저장</button></td>";
			  //listHtml += "<td><button type='button' class='btn btn-primary' onclick='save(\""+index+"\")'>저장</button></td>";
			  listHtml += "<td><button type='button' class='btn btn-primary' onclick='save(" + index + ")'>저장</button></td>";
		  }  
		  if(obj.processing_reg != 'N'){
			  
			  listHtml += "<td><button type='button' class='btn btn-success product-processing' onclick=\"checkProReg('" + obj.processing_reg + "','" + obj.sales_code + "')\">" + obj.processing_reg + "</button></td>";
			  listHtml += "<td></td>";

		  }
		  listHtml += "</tr>";
        	  
      
	  });
    

	  listHtml += "</tbody>";
	  listHtml += "</table>";
	 
	//위 표가 들어갈 자리의 아이디값 가져와서 입력하기 
	  $("#salesAcceptContent").html(listHtml);
	  
	 
     
  }