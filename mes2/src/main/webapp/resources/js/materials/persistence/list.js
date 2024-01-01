 var updateBtn = false;
 
 $(document).ready(function(){
	  loadList();
  });


  function loadList(){
	  
	  $.ajax({
		  url:"purchaseList", //목록 담아오기 (리스트 DTO랑 연결)
		  type:"get",
		  dataType:"json",
		  success: list,
		  error: function(){alert("error");}
	  });
	  
	  
  } 


 function list(data) {
	    console.log("Received data:", data);
	    
	    var listHtml = "<table class='table table-hover'>";
		  listHtml += "<thead>";
	    listHtml += "<tr class='table-success'>";
	    listHtml += "<th></th>";
	    listHtml += "<th scope='col'>발주코드</th>";
	    listHtml += "<th scope='col'>품목코드</th>";
	    listHtml += "<th scope='col'>자재유형</th>";
	    listHtml += "<th scope='col'>품목명</th>";
	    listHtml += "<th scope='col'>원가</th>";
	    listHtml += "<th scope='col'>수량</th>";
	    listHtml += "<th scope='col'>발주등록일자</th>";
	    listHtml += "<th scope='col'>담당자</th>";
	    listHtml += "</tr>";
	    listHtml += "</thead>";
	    listHtml += "<tbody>";

	    $.each(data, function (index, obj) {
	        listHtml += "<tr>";
	        listHtml += "<td scope='row'><input type='checkbox' class='ck' name='idx' value='" + obj.idx + "' id='" + obj.idx + "'/></td>";
	        listHtml += "<td>" + obj.orders_code + "</td>";
	        listHtml += "<td><a href='javascript:goContent(\"" + obj.product_code + "\")'>" + obj.product_code + "</a></td>";
	        listHtml += "<td>" + obj.category + "</td>";
	        listHtml += "<td>" + obj.name + "</td>";
	        listHtml += "<td>" + obj.cost + "</td>";
	        listHtml += "<td>" + obj.quantity + "</td>";
	      listHtml += "<td>" + obj.regdate + "</td>";
	        listHtml += "<td>" + obj.user_id + "</td>";
	        listHtml += "</tr>";
	    });
	    listHtml += "</tbody>";
	    listHtml += "</table>";

	    console.log("Generated HTML:", listHtml);

	    $("#view").html(listHtml);
	}