
 function regCheck(){
	 var ckArr = $(".ck");
	 var count = ckArr.filter(":checked").length; 

	 if(count==0){
	 	alert("선택된 항목이 없습니다.");
	 	return false;
	 	
	 }else{
	 	var result = confirm('삭제하시겠습니까?');	
	 	 if(result){
	 		$('#salesForm').submit();
	 	 }else{
	 		return false;
	 	 }
	 }
 }

 function reject(order_code){
	 alert("함수호출 거절");
	 $.ajax({
		  
		  url:"rejectSales",
		  type:"post",
		  dataType:"json",
		  data: {"order_code": order_code },
		  success: function (data) {
	            loadList();	    
	            $('#closeBtn').trigger('click');
	        },
		  error: function(){alert("거절error");}
	        
	  });
 }
 
  function save(idx){
	  var title = $("#titlec").val();
	  var contents = $("#contentsc").val();
	  
	  $.ajax({
		  
		  url:"testUpdate.do",
		  type:"post",
		  dataType:"json",
		  data: {"idx":idx,"title": title , "contents":contents },
		  success: function (data) {
			
	            loadList();
	            
	            var receivedIdx = data.idx;
	            goContent(receivedIdx);
	            updateBtn = false;
	            
	        },
		  error: function(){alert("error");}
	  });
	 
	  
  }


 function upCon(data){
	  content(data);
	  
  }


function del(){
	  var ckArr = $(".ck");
	    var count = ckArr.filter(":checked").length; 

	   if (count < 1) {
	       alert("최소 하나는 선택하세여");
		   return false; // 선택된 항목이 없을 때 폼 제출을 막기 위해 false 반환
	    }
	    	 else {
	    	        let result = confirm('삭제하시겠습니까?');
	    	        if(result){
	    	        	$('#delForm').submit();
	    	        }
	    	    }   
		       
  }
