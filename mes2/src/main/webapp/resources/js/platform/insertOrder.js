/* Created by Tivotal */

function openProductList() {
	var productList = window.open("/platform/searchList", "_blank","height=600, width=1000");
}

$(document).on('keyup', '.sales_quantity', function(){
	var priceText = document.getElementById("price").innerHTML;
	var price = parseInt(priceText.replace(/\D/g, ''), 10);
	console.log('price : ' + price)
	
	var sq = $('.sales_quantity').val();
	console.log('sq: ' + sq);
	
	var sum = new Intl.NumberFormat('ko-KR').format(price * sq);
	console.log(sum);
	
	$('.sum').val(sum);
});