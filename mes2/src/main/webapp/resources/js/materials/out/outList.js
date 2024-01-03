// 출고 상세 페이지
function getOutDetail(out_index, product_code, out_code) {
	
//	if(out_code === '') {
//		$.ajax({
//			url : "/materials/outDetail",
//			method : "GET",
//			data : {
//				"out_index" : out_index,
//				"product_code" : product_code
//			},
//			success : function(response) {
//				console.log(out_index, product_code, out_code);
//				$("#bottomContent").html(response);
//			},
//			error : function() {
//				console.log(out_index, product_code, out_code);
//				alert("fail");
//			}
//		});
//	} else {
		$.ajax({
			url : "/materials/outDetail",
			method : "GET",
			data : {
				"out_index" : out_index,
				"product_code" : product_code,
				"out_code" : out_code
			},
			success : function(response) {
				$("#bottomContent").html(response);
			},
			error : function() {
				console.log(out_index, product_code, out_code);
				alert("fail");
			}
		});
//	}
}