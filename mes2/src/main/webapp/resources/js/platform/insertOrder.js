// 팝업창 중간으로
var _width = '600';
var _height = '1000';
var _left = Math.ceil(( window.screen.width - 600 )/2);
var _top = Math.ceil(( window.screen.height - 1000 )/2);

function openProductList() {
	var productList = window.open("/platform/searchList", "_blank", "width=600, height=1000, left=" + _left + ", top="+ _top);
}

function cal(ths) {
	var row = $(ths).closest("tr");
	
	var price = row.find('[name="price"]').val();
	var sq = row.find('[name="sales_quantity"]').val();
	var total = row.find('[id^="s"]');
	
	var sum = new Intl.NumberFormat('ko-KR').format(price * sq);
	total.val(sum);
}

//var sq = document.querySelector('[name="sales_quantity"]');
//
//$(document).on('keyup', sq, function(){
//	var pCode = document.querySelector().value;
//	var priceText = document.getElementById("price").innerHTML;
//	var price = parseInt(priceText.replace(/\D/g, ''), 10);
//	console.log('price : ' + price)
//	
//	var sq = $('.sales_quantity').val();
//	console.log('sq: ' + sq);
//	
//	var sum = new Intl.NumberFormat('ko-KR').format(price * sq);
//	console.log(sum);
//	
//	$('.sum').val(sum);
//});