$(".pQuantity").click(function(){
	var selectedIdx = document.querySelector("input[name='idx']:checked");
	
	if(selectedIdx) {
		var idx = selectedIdx.value;
		var price = document.querySelector("input[name='price" + idx + "']").value;
		var quantityId = $(this).attr('id');
		var quantityIdx = quantityId.substring(quantityId.length - idx.length);
		if(idx == quantityIdx) {
			$("#" + quantityId).on("input", function(){
				var quantity = $("#" + quantityId).val();
				var total = quantity * price;
				var formattedTotal = new Intl.NumberFormat('ko-KR').format(total);
				$('#sum' + idx).val(formattedTotal);
			});
		} else {
			alert('선택한 제품만 입력 가능합니다');
		}
	} else {
		alert('제품을 먼저 선택하세요');
	}
});

function registProduct() {
	var idx = document.querySelector("input[name='idx']:checked");
	console.log(idx);

	if (idx) {
		idx = idx.value;
		var product_code = document.querySelector("input[name='product_code" + idx + "']").value;
	    var name = document.querySelector("input[name='name" + idx + "']").value;
	    var price = document.querySelector("input[name='price" + idx + "']").value;
	    var sales_quantity = document.querySelector("input[name='sales_quantity" + idx + "']").value;
	    var sum = document.querySelector("input[name='sum" + idx + "']").value;
	    
	    var formattedPrice = new Intl.NumberFormat('ko-KR').format(price);
	    
		$("#insertProductList", opener.document).append(
			"<tr><td><input type='hidden' name='product_code' value='"+product_code+"'>"+product_code+"</td>" +
			"<td><input type='hidden' name='name' value='"+name+"'>"+name+"</td>" +
			"<td><input type='hidden' name='price' value='"+price+"'>"+formattedPrice+"원</td>" +
			"<td><input type='hidden' name='sales_quantity' value='"+sales_quantity+"'>"+sales_quantity+"EA</td>" +
			"<td>"+sum+"원</td>" +
			"<td><button type='button' onclick='trRemove(this);' class='btn btn-secondary'>x</button></td></tr>"
		);
		window.close();

//		var product_code = selectedProduct.value;
//		console.log(product_code);
//		$.ajax({
//			url : "/restPlatform/registProduct",
//			method : "post",
//			data : {"product_code" : product_code},
//			dataType : "json",
//			async: false,
//			success : function(data) {
//				console.log(data);
//				$("#insertProductList", opener.document).append(
//						"<tr><td name='product_code'>"+data.product_code+"</td>" +
//						"<td>"+data.name+"</td>" +
//						"<td><span id='price'>"+new Intl.NumberFormat('ko-KR').format(data.price)+"</span>원</td>" +
//						"<td name='sales_quantity'><input type='number' class='sales_quantity'>EA</td>" +
//				"<td><input type='text' class='sum' readonly>원</td></tr>");
//				window.close();
//			},
//			error : function() {
//				alert("fail");
//			}
//		});
	} else {
		alert('제품을 선택하세요');
	}
}
