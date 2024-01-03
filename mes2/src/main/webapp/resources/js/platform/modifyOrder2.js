//var header = "X-CSRF-TOKEN";
var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");

// 수정하기
function modifyOrder(order_code, sales_status) {
	var allProduct_code = document.querySelectorAll('[name="product_code"]');

	// 품목 1개 이상 선택 제어
	if(allProduct_code.length === 0) {
		Swal.fire({
			text: "품목추가 버튼을 통해 품목을 1개 이상 선택하세요",
			confirmButtonColor: "#577D71"
		});
		return false;
	}
	
	// DTO 값 json으로 변경
	var productTable = document.getElementById('modifyProductList');
	var sopList = [];
	for(var i = 0; i<productTable.rows.length; i++) {
		var row = productTable.rows[i];
		var sopDTO = {
			order_code: order_code,
			product_code: row.querySelector('[name="product_code"]').value,
			sales_quantity: row.querySelector('[name="sales_quantity"]').value
		};
		
		sopList.push(sopDTO);
	}
	
	var jsonSopList = JSON.stringify(sopList);
	
	console.log(jsonSopList);
	
	if(sopList != null) {
		$.ajax({
			url : "/platform/modifyOrder",
			method : "post",
			data : jsonSopList,
			contentType : 'application/json; charset=utf-8',
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token)
			},
			async: false,
			success : function(data) {
				alert('발주 수정이 완료되었습니다.');
				location.reload();
			},
			error : function() {
				alert("발주 수정에 실패했습니다.");
				location.reload();
			}
		});
	}
}

// 취소하기
function cancleModify() {
	var cancle = confirm('수정을 취소하시겠습니까?');
	if(!cancle) {
		return false;
	} else {
		location.reload();
	}
}

function trRemove(ths) {
	var $tr = $(ths).parents("tr");
	$tr.remove();
}