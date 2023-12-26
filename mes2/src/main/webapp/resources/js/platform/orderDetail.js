/* Created by Tivotal */

function modifyOrder(order_code, order_date) {
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