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

function inqueryList() {
	// 품목 조회
	var searchType = document.getElementById('searchType').value;
	var search = document.getElementById('search').value;
	console.log('품목 조회!!');
	$.ajax({
		url : "/restPlatform/inqueryProduct",
		method : "GET",
		data : {"searchType" : searchType, "search" : search},
		dataType : 'json',
		success : function(data) {
			console.log(data);
		},
		error : function() {
			alert("fail");
		}
	});
}

function registProduct() {
	// 품목 등록
	
}
