function registProduct() {
	var selectedProduct = document.querySelector('input[name="product_code"]:checked');

	if (selectedProduct) {
		var product_code = selectedProduct.value;
		console.log(product_code);
		$.ajax({
			url : "/restPlatform/registProduct",
			method : "post",
			data : {"product_code" : product_code},
			dataType : "json",
			async: false,
			success : function(data) {
				console.log(data);
				$("#insertProductList", opener.document).append(
						"<tr><td>"+data.product_code+"</td>" +
						"<td>"+data.name+"</td>" +
						"<td>"+data.price+"원</td>" +
						"<td><input type='number'></td>" +
						"<td><input type='text' readonly></td></tr>");
				window.close();
			},
			error : function() {
				console.log('fail');
				alert("fail");
			}
		});
	} else {
		alert('제품을 선택하세요');
	}
}
