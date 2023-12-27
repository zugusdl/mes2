/* Created by Tivotal */
function modifyOrder() {
	var order_date = document.querySelector('[name="order_date"]').value;
	var allProduct_code = document.querySelectorAll('[name="product_code"]');

	// 날짜 선택 제어
	if(order_date == undefined || order_date === null || order_date === '') {
		alert('납품 요청일을 선택하세요');
		return false;
	}
	// 품목 1개 이상 선택 제어
	if(allProduct_code.length === 0) {
		alert('품목추가 버튼을 통해 품목을 1개 이상 선택하세요');
		return false;
	}
	
	// DTO 값 json으로 변경
	var productTable = document.getElementById('insertProductList');
	var sopList = [];
	for(var i = 0; i<productTable.rows.length; i++) {
		var row = productTable.rows[i];
		var sopDTO = {
			product_code: row.querySelector('[name="product_code"]').value,
			name: row.querySelector('[name="name"]').value,
			price: row.querySelector('[name="price"]').value,
			sales_quantity: row.querySelector('[name="sales_quantity"]').value
		};
		
		sopList.push(sopDTO);
	}
	
//	var jsonSopList = JSON.stringify(sopList);
	console.log(order_date, sopList);
	
	if(sopList != null) {
		$.ajax({
			url : "/platform/insertOrder",
			method : "post",
			data : JSON.stringify({
					"sopList": sopList,
					"order_date" : order_date
					}),
			contentType : 'application/json; charset=utf-8',
			async: false,
			success : function(data) {
				alert('발주 신청이 완료되었습니다.');
				location.reload();
			},
			error : function() {
				alert("fail");
			}
		});
	}
}

function trRemove(ths) {
	var $tr = $(ths).parents("tr");
	$tr.remove();
}