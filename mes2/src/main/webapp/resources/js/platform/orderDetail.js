/* Created by Tivotal */

// 수정하기
function modifyOrder(order_code, order_date, sales_status) {
	console.log(sales_status);

	if (sales_status != 'requested') {
		alert('신청중인 발주만 수정 가능합니다.');
		return false;
	}

	$.ajax({
		url : "/platform/modifyOrder?order_code=" + order_code,
		data : {
			"order_date" : order_date
		},
		method : "GET",
		success : function(response) {
			$("#bottomContent").html(response);
		},
		error : function() {
			alert("fail");
		}
	});
}

// 취소하기
function deleteOrder(order_code, sales_status) {
	console.log(sales_status);

	if (sales_status != 'requested') {
		alert('신청중인 발주만 취소 가능합니다.');
		return false;
	}

	var deleteCheck = confirm('발주를 취소하시겠습니까?');

	if (deleteCheck == false) {
		alert('발주 현황을 유지합니다.');
		return false;
	}
	
	location.href = "/platform/deleteOrder?order_code=" + order_code;
	
//	$.ajax({
//		url : "/platform/deleteOrder?order_code=" + order_code,
//		method : "GET",
//		success : function(response) {
//			location.reload();
//		},
//		error : function() {
//			alert("fail");
//		}
//	}); // 굳이 ajax 안 쓰고 location.href로 delete 페이지로 보내도 될듯?? 컨트롤러에서 redirect
}