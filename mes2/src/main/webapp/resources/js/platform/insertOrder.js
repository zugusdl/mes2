/* Created by Tivotal */

function insertOrder() {
	loadAddOrderPage();
}

function loadAddOrderPage() {
	$.ajax({
		url: "/platform/insertOrder",
		method: "GET",
		success: function(response) {
			$("#bottomContent").html(response);
		},
		error: function() {
			alert("fail");
		}
	});
}