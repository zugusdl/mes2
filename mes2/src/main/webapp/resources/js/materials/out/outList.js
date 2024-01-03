// 출고 상세 페이지
function getOutDetail(out_index, out_code) {
//	if(out_code === undefined) {
//		$.ajax({
//			url : "/materials/outDetail?out_index=" + out_index,
//			method : "GET",
//			success : function(response) {
//				console.log(out_index, out_code);
//				$("#bottomContent").html(response);
//			},
//			error : function() {
//				alert("fail");
//			}
//		});
//	} else {
		$.ajax({
			url : "/materials/outDetail?out_index=" + out_index + "&out_code=" + out_code,
			method : "GET",
			success : function(response) {
				console.log(out_index, out_code);
				$("#bottomContent").html(response);
			},
			error : function() {
				alert("fail");
			}
		});
//	}
}