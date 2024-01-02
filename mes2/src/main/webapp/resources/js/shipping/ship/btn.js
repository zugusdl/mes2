var selectedOrder; 
$(document).ready(function() {
  

  
  $('.ck').on('change', function() {
   
    selectedOrder = $('input[name="order_code"]:checked').val();

   alert(selectedOrder);
  });

  
});
 

function statusList(progress_status) {
    location.href = "/shipping/shippingCheck?progress_status=" + progress_status;
}

function userList(){
	location.href="/shipping/userShipList";
}

 function reg(order_code){
	 Swal.fire({
		  title: "출하지시등록",
		  text: "주문번호 "+order_code+"의 출하지시등록을 진행하시겠습니까?",
		  icon: "info",
		  showCancelButton: true,
		  cancelButtonText: "취소",
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "진행"
		}).then((result) => {
		  if (result.isConfirmed) {
			  $.ajax({
	 			  url:"updateIdCheck", 
	 			  type:"post",
	 			  dataType:"json",
	 			  data: {"order_code":order_code},
	 			  success: function(data) {
	 				
	 				 $('#exampleModal').modal('show');

	 				    moReg(data,order_code);
	 			  }, 
	 			  error: function(){alert(" 지시등록모달error");}
	 		  });
		  }
		});
 }
 
 
 function moReg(data,order_code){
	 $("#exampleModalLabel").html('비밀번호 확인');
	 var listHtml = "<div>담당자 아이디 : <input type='text' id='reg_id' value='"+data.user_id+"' disabled/> </div>";
	 listHtml += "<div>담당자 이름 : <input type='text' id='reg_name' value='"+data.user_name+"' disabled/> </div>";
	 listHtml += "<div>비밀번호: <input type='password' id='reg_pw'/></div>"
	 listHtml += "<button type='button' class='btn btn-secondary' onclick='return regPw(\"" + order_code + "\")'>비밀번호 확인</button>";

		 $("#shippngPlan-modal").html(listHtml);
 }
 
 function regPw(order_code){
	 var user_id = $("#reg_id").val();
	 var user_pw = $("#reg_pw").val();
	 $.ajax({
		  url: "updatePwCheck",
		  type:"post",
		  dataType: "json",
		  data: {"user_pw": user_pw, "order_code":order_code, "user_id": user_id},
		  success: function(data) {
			    register(data,order_code);
		  }, 
		  error: function() {
		    alert(" regupdatePW모달오류 ");
		  }
		});
	 
 }
 
 function register(data,order_code){
	 $("#mo-close").trigger('click');
	 
	 var schedule = new Date(data.scheduled_date);
	 var options = { year: 'numeric', month: '2-digit', day: '2-digit' };
	 var date = schedule.toLocaleDateString('ko-KR', options).replace(/\./g, '-').slice(0, -1);
	 
	 if(data.scheduled_date == null ){
		 Swal.fire({
			  title: "출하예정일 등록필요",
			  text: "의 출하예정일이 등록되지 않았습니다. 출하예정일을 등록해주세요",
			  icon: "warning"
			});
	 }
	 else{
	 Swal.fire({
		  title: "출하지시등록",
		  text: order_code+" 의 출하예정일은"+ date+"입니다. 출하지시등록을 진행하시겠습니까?",
		  icon: "info",
		  showCancelButton: true,
		  cancelButtonText: "취소",
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "등록"
		}).then((result) => {
		  if (result.isConfirmed) {
			  
			  $.ajax({
				  url:"regShipping", 
				  type:"post",
				  dataType:"text",
				  data: {"order_code":order_code},
				  success: function(data) {
					 Swal.fire({
					    title: "지시완료",
					    text: "주문번호 " + data + "의 출하지시가 완료되었습니다.",
					    icon: "success"
					}).then(() => {
					    
						location.href="shipPlan";
					});

					
				  }, 
				  error: function(){
					 Swal.fire({
						  title: "등록오류",
						  text: "등록오류 발생, 관계자에게 문의하세요.",
						  icon: "warning"
						});
				  }
			  });

		  }
		});
	 }
 }
 


 function update(){
	 var ckArr = $(".ck");
	 var count = ckArr.filter(":checked").length; 
	 
	 if(count==0){	 	
		 Swal.fire({
			  title: "선택된 항목이 없습니다.",
			  icon: "warning"
			});
	 	
	 }else{
	 	 if(count==1){
	 		Swal.fire({
	 			  title: "출하일정을 수정하시겠습니까?",
	 			  text: "이미 예약출하가 지시된 상태입니다. 출하일정을 수정을 진행하시겠습니까?",
	 			  icon: "warning",
	 			  showCancelButton: true,
	 			  confirmButtonColor: "#3085d6",
	 			  cancelButtonColor: "#d33",
	 			  confirmButtonText: "진행"
	 			}).then((result) => {
	 			  if (result.isConfirmed) {
	 				  $.ajax({
	 					 url:"updateIdCheck", 
			 			  type:"get",
			 			  dataType:"json",
			 			  data: {"order_code":selectedOrder},
			 			  success:moUpdate,
			 			  error: function(){
			 				 Swal.fire({
			 					  title: "수정오류",
			 					  text: "해당 주문 건의 관리자에게 문의하십시오.",
			 					  icon: "warning"
			 					});

	 				  }	 
	 				 });
	 		 
	 	 }
	 });
 }
 
	 }
 
 }
 function moUpdate(data){
	// 모달을 직접 보이게 하기
	 //$("#exampleModal").css("display", "block");

	 //$("#exampleModalLabel").show();
	 $("#exampleModalLabel").html('비밀번호 확인');
	 var listHtml = "<div>담당자 아이디 : <input type='text' id='user_id' value='"+data.user_id+"' disabled/> </div>";
	 listHtml += "<div>담당자 이름 : <input type='text' id='user_name' value='"+data.user_name+"' disabled/> </div>";
	 listHtml += "<div>비밀번호: <input type='password' id='user_pw'/></div>"
	 listHtml += "<button type='button' class='btn btn-secondary' onclick='return updatePw()'>비밀번호 확인</button>";
		 $("#shippng-modal").html(listHtml);
		// $("#exampleModalLabel").show();
 }
 
 function updatePw(){
	 var user_id = $("#user_id").val();
	 var user_pw = $("#user_pw").val();
	 $.ajax({
		  url: "updatePwCheck",
		  type:"post",
		  dataType: "json",
		  data: {"user_pw": user_pw, "order_code":selectedOrder, "user_id": user_id},
		  success: function(data) {
			    moUpdateCheck(data);
		  }, 
		  error: function() {
		    alert(" updatePW모달오류 ");
		  }
		});
	 
 }
 
 function moUpdateCheck(data){
	 var ship_date = data.ship_date;
	 alert("출하일자"+ship_date);
	 
	 if(data.check == "true"){	
		 $("#mo-close").trigger('click');
		 // 오늘 날짜
		 var today = new Date();

		 // 시작일 설정 (신청일 3주후)
		 	var requestDate = new Date(data.request_date);
		 	 alert(requestDate);
		 	var minDate = new Date(requestDate);
		    minDate.setDate(requestDate.getDate() + 21);
		    alert(minDate);
		    
		// 마지막일 설정 (납품요청일 4일전)    
		    var orderDate = new Date(data.order_date);
             alert(orderDate);
		    var maxDate = new Date(orderDate);
		    maxDate.setDate(orderDate.getDate() - 4);
		    alert(maxDate);
		
		 // 만약 오늘 기준 신청일3주후(B)가 과거면 오늘이 min
		// 오늘 기준 B가 미래면 B가 min
		    var startDate;
		    
		    if (today < minDate) {
		        startDate = minDate;
		        alert("오늘이 과거");
		    } else if (today >minDate) {
		    	startDate = today; //오늘이 이후 
		    	alert("오늘이 미래");
		    } else {
		        startDate = minDate; // 같은경우
		    }
		    
		    
		 Swal.fire({
			  title: "출하예정일을 입력하세요.",
			  input: "date",
			  didOpen: () => {
		            // date input의 max 속성 설정
		            const input = $('input[type="date"]');
		            
		            // maxDate를 'YYYY-MM-DD' 형식의 문자열로 변환하여 설정
		            input.attr('max', maxDate.toISOString().split('T')[0]);
		            
		         // date input의 min 속성 설정
		            input.attr('min', startDate.toISOString().split('T')[0]);
		        }
		    }).then((result) => {
			  if (result.value) {
			    const scheduled_date = result.value; 
			    
			    $.ajax({
					  url: "checkSchedule",
					  type: "post",
					  dataType: "json",
					  data: {"scheduled_date": scheduled_date},
					  success: function(data) {
						  scheduleCheck(data,scheduled_date,ship_date);
					  },
					  error: function() {
					    alert(" updatePW모달오류 ");
					  }
					});
			  }
			});
			
			
	 }else if(data.check == "false"){
		 Swal.fire({
			  title: "비밀번호 오류",
			  text: "담당자 비밀번호를 확인하세요.",
			  icon: "error"
			});
	 }
 }
 
 function scheduleCheck(data,scheduled_date,ship_date){
	 var date = new Date(ship_date);
	 var sd =date.toISOString().split('T')[0];
	 Swal.fire({
		  title: "예약출하 일정을 변경하시겠습니까?",
		  text: "해당 주문건의 예약출하일정은 "+sd+" 입니다.",
		  icon: "info",
		  showCancelButton: true,
		  cancelButtonText: "취소",
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "확인"
		}).then((result) => {
		  if (result.isConfirmed) {
				 Swal.fire({
					  title: "예약출하 일정을 변경",
					  text: "현재 "+scheduled_date+" 에 예정된 출하건은 "+data+"개 입니다. 해당일에 추가하시겠습니까?",
					  icon: "info",
					  showCancelButton: true,
					  cancelButtonText: "취소",
					  confirmButtonColor: "#95c4a2",
					  cancelButtonColor: "#d33",
					  confirmButtonText: "확인"
					}).then((result) => {
					  if (result.isConfirmed) {
						  $.ajax({
							  url: "changeShipSchedule",
							  dataType: "text",
							  type: "post",
							  data: {"scheduled_date": scheduled_date, "order_code":selectedOrder},
							  success: scheduledUpdateSuccess,
							  error: function() {
							    alert(" 다시 시도하세요 ");
							  }
							});
					  }
					});
		  }
		});

 }
 
function scheduledUpdateSuccess(data){
	Swal.fire({
	    title: "수정완료",
	    text: "주문번호 " + data + "의 출하예정일이 변경되었습니다.",
	    icon: "success"
	}).then(() => {
	    
	    $("#loadPage").trigger('click');
	});
}
 function registration(user_id){
	 $("#u_id").val(user_id).prop("disabled", false);
	 $('#planListForm').submit();
 }


 
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
	
	location.href="shipping";
}

function checkSearchSub(e){
	
	if($("#searchType").val() === ""){
		alert("검색타입을 선택하세요.");
		$("#searchType").focus();
		return false;
	}
	
	if($("#searchType").val() == "ship_code" && $("#putSearch").val() == ""){
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
