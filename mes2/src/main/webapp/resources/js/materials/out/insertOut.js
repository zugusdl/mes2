function insertOutProduct(product_code) {
	var productList = window.open("/materials/stockList?product_code=" + product_code, "_blank","height=600, width=600");
}

//출고 등록
function insertOut() {
	// 수량 비교해서 제어
	var useQuantityList = [];
	var sum = 0;
	var quantity = document.querySelector("input[name='quantity']").value;
	
	$("input[name='useQuantity']").each(function(){
		useQuantityList.push($(this).val());
	});
	
	$.each(useQuantityList, function(idx, val){
		sum += Number(val);
	});
	
	if(sum != quantity) {
		alert('요청 수량과 출고 수량이 다릅니다');
		return false;
	}
	
	document.getElementById('frm').submit();
	
}