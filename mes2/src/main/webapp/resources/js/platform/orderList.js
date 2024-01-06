var header = "X-CSRF-TOKEN";
var token = $("meta[name='_csrf']").attr("content");

// 검색
function searchList() {
	
}

// 발주 추가
function insertOrder() {
	$.ajax({
		url : "/platform/insertOrder",
		method : "GET",
		success : function(response) {
			$("#bottomContent").html(response);
		},
		error : function() {
			alert("fail");
		}
	});
}

// 발주 상세 페이지
function getOrderDetail(order_code, order_date) {
	$.ajax({
		url : "/platform/orderDetail?order_code=" + order_code,
		method : "GET",
		data : {
			"order_date" : order_date
		},
		success : function(response) {
			$("#bottomContent").html(response);
		},
		error : function() {
			alert("fail");
		}
	});
}

//수령 완료
function completeOrder(order_code) {
	console.log(order_code);
	
	var completeConfirm = confirm('수령 완료 처리 하시겠습니까?');
	
	if(completeConfirm) {
		$.ajax({
			url : "/restPlatform/receiveDelivery",
			type : "POST",
			data : {
				"order_code" : order_code
			},
			success : function() {
				alert('수령 완료 처리 하였습니다.');
				location.href = '/platform/orderList';
			},
			error : function() {
				alert('수령 완료 처리에 실패하였습니다.')
			}
		});
	}
}

//수령 완료 처리
//function completeOrder(order_code) {
//	var complete = window.open("/platform/completeOrder?order_code="+order_code, "_blank","height=400, width=500");
//}