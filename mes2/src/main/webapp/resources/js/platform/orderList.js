var header = "X-CSRF-TOKEN";
var token = $("meta[name='_csrf']").attr("content");

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

//수령 완료 처리
function completeOrder(order_code) {
	var complete = window.open("/platform/completeOrder?order_code="+order_code, "_blank","height=400, width=500");
}