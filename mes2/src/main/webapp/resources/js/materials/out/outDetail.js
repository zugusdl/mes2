// 출고 상세 페이지
function getOutDetail(out_index) {
	$.ajax({
		url : "/materials/outDetail?out_index=" + out_index,
		method : "GET",
		success : function(response) {
			$("#bottomContent").html(response);
		},
		error : function() {
			alert("fail");
		}
	});
}