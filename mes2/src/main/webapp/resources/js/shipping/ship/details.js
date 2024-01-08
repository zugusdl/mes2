
function goContent(order_code){
       
	  $.ajax({
		  url:"shipContent",  
		  type:"post",
		  dataType:"json", 
		  data: {"order_code": order_code }, 
		  success: function(data) {
			  content(data,order_code);
		  },
		  error: function(){alert("error");}
	  });
	  
	  
  }

 function cancle(){
	
		  $("#view2").html("");
	  }
 
 function info(order_code){
	 
	 
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
				  title: "정보입력중",
				  text: "잠시 후 다시 시도하세요",
				  icon: "warning"
				}).then(() => {
					 $("#mo-close").trigger("click");
				});
				
		  }
	  });
 }
 
 function moInfo(data, order_code){
	 var title = "<div>주문번호 : "+order_code+"</div>";
	 $("#exampleModalLabel").html(title);
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
		 
	      
		 $("#shippng-modal").html(listHtml);
 }
 
 function productInfo(order_code,sales_code) {

	  $.ajax({
		  url:"productInfo", 
		  type:"post",
		  dataType:"text", 
		  data: {"sales_code" : sales_code}, 
		  success: function (data) {
			  
		      moproduct(data); 
		    },
		  error: function(){
			  $("#mo-close").trigger("click");
			  Swal.fire({
				  title: "정보입력중",
				  text: "잠시 후 다시 시도하세요",
				  icon: "warning"
				}).then(() => {
					 $("#mo-close").trigger("click");
				});
		  }
	  });
 }
 
 function moproduct(data){
	 
		 
	 Swal.fire({
		  title: "창고위치",
		  input: "text",
		  inputValue: data,  
		  inputAttributes: {
		    readonly: true
		  }
		});



 }
 
 
  function content(data,order_code){ 

	  var listHtml = "<div class='list-box'>";
	  listHtml += "<div>";
	  listHtml += "<p>주문번호: "+order_code+"</p>";
	  listHtml += "<button type='button' class='btn btn-danger'  data-bs-toggle='modal' data-bs-target='#exampleModal' onclick='info(\""+order_code+"\")'>상세</button>";
	  listHtml += "</div>";
	  listHtml += "<div>";
	  listHtml +="<button type='button' class='btn btn-secondary' id='closeBtn' onclick='cancle()'>닫기</button>";
	  listHtml += "</div>";
	  listHtml += "<table class='table table-hover'>";
	  listHtml += "<thead>";
	  listHtml += "<tr class='table-success' >";
	  listHtml += "<th scope='col'>수주번호</th>";
	  listHtml += "<th scope='col'>제품명</th>";
	  listHtml += "<th scope='col'>출하량</th>";
	  listHtml += "<th scope='col'>처리등록</th>";
	  listHtml += "<th scope='col'></th>";
	  listHtml += "</tr>";
	  listHtml += "</thead>";
	  
	  listHtml += "<tbody>";
	  
	  $.each(data,function(index,obj){
		  listHtml += "<tr>";
		  listHtml += "<td>"+obj.sales_code+"</td>";				  
		  listHtml += "<td>"+obj.product_name+"</td>";
		  listHtml += "<td>"+obj.sales_quantity+"</td>";
		  listHtml += "<td>"
		  if(obj.processing_reg=="stock"){
			  listHtml += "재고출하"; 
		  }else if(obj.processing_reg=="production"){
		  listHtml += "생산처리";  
		  }else if(obj.processing_reg==" mult"){
		  listHtml += "복합처리";  
		  }
		  listHtml += "</td>"
		  listHtml += "<td><button type='button' class='btn btn-danger' onclick='productInfo(\""+obj.order_code+"\", \""+obj.sales_code+"\")'>상세</button></td>";
		  listHtml += "</tr>";
	  });
    

	  listHtml += "</tbody>";
	  listHtml += "</table>";
	 
	
	  $("#view2").html(listHtml);
  }