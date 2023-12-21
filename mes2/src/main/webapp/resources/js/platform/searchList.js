function registProduct() {
	alert('호출@');
	var product_code = document.querySelector('input[name="product_code"]:checked').value;
	alert(product_code);
	
	$.ajax({
		url : "/restPlatform/registProduct",
		method : "POST",
		data : {"product_code" : product_code},
		dataType: "json",
		success : function(response) {
			alert("success");
//			let product = data;
//			console.log(product);
//			opener.call(product);
//			window.close();
		},
		error : function() {
			alert("fail");
		}
	});
}
