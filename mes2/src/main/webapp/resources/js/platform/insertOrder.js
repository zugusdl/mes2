/* Created by Tivotal */

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

const plModal = document.getElementById('productList');

function openProductList() {
	plModal.style.display = 'block';
}

function closeProductList() {
	plModal.style.display = 'none';
}
