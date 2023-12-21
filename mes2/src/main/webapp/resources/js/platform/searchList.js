function registProduct() {
	$.ajax({
		url : "/restPlatform/registProduct",
		method : "GET",
		dataType: "json",
		success : function(response) {
			console.log(response);
		},
		error : function() {
			alert("fail");
		}
	});
}
