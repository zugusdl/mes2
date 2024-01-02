
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
 

 
 function register(){
	 var ckArr = $(".ck");
	 var count = ckArr.filter(":checked").length; 
	 
	 if(count==0){
	 	
	 	$("#exampleModalLabel").html('등록');
		 var listHtml = "<div>선택된 항목이 없습니다. </div>";
		
		 $("#sales-modal").html(listHtml);
	 	//$("#mo-close").trigger("click");
		//$("#exampleModal").modal('hide');
	 	
	 }else{
		// $("#reg-mo-btn").attr("data-bs-toggle", "modal");
		//  $("#reg-mo-btn").attr("data-bs-target", "#exampleModal");
	 	//var result = confirm('등록하시겠습니까?');	
	 	 if(count>=1){
	 		//$("#exampleModalLabel").html('비밀번호 확인');
	 		//var listHtml = "<div>아이디 : <input type</div>";
	 		//$('#planListForm').submit();
	 		 alert("에이젝스 실행");
	 		 $.ajax({
	 			  url:"regIdCheck", // 
	 			  type:"get",
	 			  dataType:"text", // Jackson Databind사용해서 json으로 받기	 		
	 			  success:moReg,
	 			  error: function(){alert(" 등록모달error");}
	 		  });
	 		 
	 	 }//else{
	 		//return false;
	 	 //}
	 }
 }
 
 function moReg(data){
	 $("#exampleModalLabel").html('비밀번호 확인');
	 var listHtml = "<div>아이디 : <input type='text' id='user_id' value='"+data+"' disabled/> </div>";
	 listHtml += "<div>비밀번호: <input type='password' id='user_pw'/></div>"
	 listHtml += "<button type='button' class='btn btn-secondary' onclick='return regPw(\""+data+"\")'>비밀번호 확인</button>";

	 $("#sales-modal").html(listHtml);
 }
 
 function regPw(user_id){
	 var user_pw = $("#user_pw").val();

	 $.ajax({
		  url: "regPwCheck",
		  type: "post",
		  dataType: "text",
		  data: {"user_pw": user_pw},
		  success: function(data) {
			    moRegPwCheck(data, user_id);
		  }, 
		  error: function() {
		    alert(" 등록모달비번error");
		  }
		});
	 
 }
 
 function moRegPwCheck(data, user_id){
	 //$("#mo-close").trigger('click');
	 
	 if(data == "true"){
		 
		 $("#exampleModalLabel").html('수주등록');
		 var listHtml = "<div>등록하시겠습니까?</div>"; 
		 listHtml += "<button type='button' class='btn btn-danger' onclick='return registration(\""+user_id+"\")'>등록</button>";
		 $("#sales-modal").html(listHtml);
		 
		 //var result = confirm('등록하시겠습니까?');
		 //if(result){
		//	 $('#planListForm').submit();
		// }//else{
			// return false;
		 //}
	 }else if(data == "false"){
		alert("비밀번호 오류");
		//return false;
	 }
 }
 
 function registration(user_id){
	 $("#u_id").val(user_id).prop("disabled", false);
	 $('#planListForm').submit();
 }

// function reject(order_code){
//	 alert("함수호출 거절");
//	 $.ajax({
//		  
//		  url:"rejectSales",
//		  type:"post",
//		  data: {"order_code": order_code },
//		  success: function () {
//			  alert("거절되었습니다.");
//			  location.href="/sales/salesPlanTest";
//	           // $('#closeBtn').trigger('click');
//	        },
//		  error: function(){alert("거절error");}
//	        
//	  });
// }
 
// function reject(order_code){
//	 $('#odi').prop('disabled', false);
//	 $('#odi').val(order_code);
//	 var ckArr = $(".ck");
//	 var count = ckArr.filter(":checked").length; 
//
//	 if(count>0){
//	 	alert("체크박스 선택을 해제하십시오.");
//	 	return false;
//	 }else {
//		 $('#planListForm').submit();
//	 }
//	 
// }
 
 function reject() {
	    alert("함수 호출 거절");
	    // $('#odi').prop('disabled', false);
	    //$('#odi').val(order_code);
	    var ckArr = $(".ck");
	    var count = ckArr.filter(":checked").length;

	    if(count==0){
		 	
		 	$("#exampleModalLabel").html('거절');
			 var listHtml = "<div>선택된 항목이 없습니다. </div>";
			
			 $("#sales-modal").html(listHtml);
	        //return false;
	    } else {
	        alert("에이젝스 시작");
	        //$('#planListForm').submit();
	        $.ajax({
	            url: "regIdCheck",
	            type: "get",
	            dataType: "text",
	            success: moRej,
	            error: function () { alert(" 등록거부모달error"); }
	        });
	    }
	}

	function moRej(data) {
	    $("#exampleModalLabel").html('비밀번호 확인');
	    //var listHtml = "<form action='rejectSales' id='reject-fm' method='post'>";
	   // listHtml += "<input type='hidden' id='order_code' name='order_code' value='"+order_code+"'/>"
	    var listHtml = "<div>아이디 : <input type='text' id='user_id' value='" + data + "' disabled/> </div>";
	    listHtml += "<div>비밀번호: <input type='password' id='user_pw'/></div>"
	    //listHtml += "<button type='button' class='btn btn-secondary' onclick='return rejPw('"+order_code+"')'>비밀번호 확인</button>";
	    listHtml += "<button type='button' class='btn btn-secondary' onclick='return rejPw()'>비밀번호 확인</button>";
	    $("#sales-modal").html(listHtml);
	}

	function rejPw() {
	    var user_pw = $("#user_pw").val();

	    $.ajax({
	        url: "regPwCheck",
	        type: "post",
	        dataType: "text",
	        data: { "user_pw": user_pw },
	        success: moRejPwCheck,
	        error: function () {
	            alert(" 등록모달비번error");
	        }
	    });
	}

	function moRejPwCheck(data) {
	    if (data === "true") {
	      //  var result = confirm('거부하시겠습니까?');
	       // if (result) {
	         //   alert("이동");
	           // $("#reject-fm").submit();
	        //    var newAction = "rejectSales"; //**
	         //   $("#planListForm").attr("action", newAction); //**
	         //   $('#planListForm').submit(); //**
	            //return true;
	      //  } //else {
	          //  return false;
	       // }
	    	 $("#exampleModalLabel").html('수주거절');
			 var listHtml = "<div>거절하시겠습니까?</div>"; 
		     listHtml += "<button type='button' class='btn btn-danger' onclick='return refuse()'>거절</button>";
			 $("#sales-modal").html(listHtml);
	    } else if (data === "false") {
	        alert("비밀번호 오류");
	       //return false;
	    }
	}

 function refuse(){
	 var newAction = "rejectSales";
	 $("#planListForm").attr("action", newAction); 
	 $('#planListForm').submit();
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

function load(){
	
	location.href="salesPlanTest";
}

function checkSearchSub(e){
	
	if($("#searchType").val() === ""){
		alert("검색타입을 선택하세요.");
		$("#searchType").focus();
		return false;
	}
	
	if($("#searchType").val() == "order_code" && $("#putSearch").val() == ""){
		alert("검색어를 입력하세요.");
		$("#putSearch").focus();
		return false;
	}
	
	if($("#searchType").val() == "company_name" && $("#putSearch").val() == ""){
		alert("검색어를 입력하세요.");
		$("#putSearch").focus();
		return false;
	}
}
