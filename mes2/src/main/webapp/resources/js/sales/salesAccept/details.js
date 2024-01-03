 function goContent(order_code){
       
	  $.ajax({
		  url:"acceptContent",
		  type:"post",
		  dataType:"json", 
		  data: {"order_code": order_code }, 
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
		  url:"stockCheck", 
		  type:"post",
		  dataType:"json",
		  data: {"product_code": product_code , "order_code" : order_code}, 
		  success: function (data) {
		      moStock(data, sales_quantity);
		    },
		  error: function(){
			  $("#salesModalLabel").html('재고조회');
			  var listHtml = "<div>창고에 보유 재고 없음</div>";			
			  listHtml += "<div>부족수량 : <input type='text' value='"+sales_quantity+"' readonly/></div>"; 
			  listHtml += "<button type='button' class='btn btn-danger'>재고부족</button>";
			  $("#sales-modal").html(listHtml);
				
		  }
	  });
 }
 
 function moStock(data, sales_quantity) {
	 $("#salesModalLabel").html('재고조회');
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
 

 function save(index){
	
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
	 Swal.fire({
		  title: "처리 변경",
		  text: "이미 처리된 수주입니다. 변경하시겠습니까?",
		  icon: "question",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "변경", 
		  cancelButtonText: "취소"
		}).then((result) => {
		  if (result.isConfirmed) {

		  }
		});
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
			  alert("정보겟오류");
				
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
	 // <i class="fa-solid fa-rectangle-xmark"></i>
//	  var listHtml ="<div class='list-box'>";
//	  listHtml += " <i class='fa-solid fa-rectangle-xmark' id='closeBtn' onclick='cancle()'></i>"
//	  listHtml +="<input type='hidden' id='order_code' value='"+order_code+"'>";
	  var listHtml = "<div class='list-box'>";
	  listHtml += "<div>";
	  listHtml += "<p>주문번호: "+order_code+"</p>";
	  listHtml += "<button type='button' class='btn btn-danger'  data-bs-toggle='modal' data-bs-target='#salesModal' onclick='info(\""+order_code+"\")'>상세</button>";
	  listHtml += "</div>";
	  listHtml += "<div>";
	  listHtml +="<i class='fa-solid fa-rectangle-xmark'id='closeBtn' onclick='cancle()'></i>";
	 // listHtml +="<button type='button' class='btn btn-secondary' id='closeBtn' onclick='cancle()'>닫기</button>";
	  listHtml += "</div>";
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
		  if(obj.product_name == null){
			  listHtml += "<td>미정</td>"; 
		  }if(obj.product_name != null){
			  listHtml += "<td>"+obj.product_name+"</td>";
		  }
		  listHtml +="<input type='hidden' name='product_code' class='product_code' value='"+obj.product_code+"'>";
		  listHtml += "<td>"+obj.sales_quantity+"</td>";
		  listHtml +="<input type='hidden' name='product_code' class='sales_quantity' value='"+obj.sales_quantity+"'>";
		  listHtml += "<td><button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#salesModal' onclick=\"mo('" + obj.product_code + "','"+obj.order_code+"','"+obj.sales_quantity+"')\">재고조회</button></td>";
		  listHtml += "<td>"+obj.product_status+"</td>";

		  if(obj.processing_reg == 'N'){
			  listHtml += "<td class='back'><select name='processing_reg' class='product-processing'>";
			  listHtml += "<option value='N' class='check-processing'>N</option>";
			  listHtml += "<option value='stock' class='check-processing'>재고출하</option>";
			  listHtml += "<option value='production' class='check-processing'>생산계획</option>";
			  listHtml += "<option value='multi' class='check-processing'>복합처리</option>";  
			  listHtml += "</select></td>";
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
	 

	  $("#salesAcceptContent").html(listHtml);
	  
	 
     
  }