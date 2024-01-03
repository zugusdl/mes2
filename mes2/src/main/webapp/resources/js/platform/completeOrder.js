//const canvas = document.querySelector("#cy_sig");
//const ctx = canvas.getContext("2d");
//canvas.width = innerWidth;
//canvas.height = innerHeight;
//
//const width = innerWidth - 100;
//const height = innerHeight - 200;
//
//canvas.width = width;
//canvas.height = height;
//canvas.style.margin = "20px";
//canvas.style.border = "1px solid #757575";
//canvas.style.cursor = 'pointer';
//
//let painting = false;
//
//function stopPainting(event) {
//	painting = false;
//}
//
//function startPainting() {
//	painting = true;
//}
//
//ctx.lineWidth = 3;
//function onMouseMove(event) {
//	const x = event.offsetX;
//	const y = event.offsetY;
//	if (!painting) {
//		ctx.beginPath();
//		ctx.moveTo(x, y);
//	} else {
//		ctx.lineTo(x, y);
//		ctx.stroke();
//	}
//}
//
//if (canvas) {
//	canvas.addEventListener("mousemove", onMouseMove);
//	canvas.addEventListener("mousedown", startPainting);
//	canvas.addEventListener("mouseup", stopPainting);
//	canvas.addEventListener("mouseleave", stopPainting);
//}

//function completeOrder() {
//	const canvas = document.getElementById('canvas');
//	const imgDataUrl = canvas.toDataURL('image/png');
//	console.log(imgDataUrl);
//	const blobBin = atob(imgDataUrl.split(',')[1]); // imgBase64 데이터 디코딩
//	
//	var array = [];
//    for (var i = 0; i < blobBin.length; i++) {
//        array.push(blobBin.charCodeAt(i));
//    }
//    console.log(array);
//    
//    var file = new Blob([new Uint8Array(array)], {type: 'image/png'});	// Blob 생성
//    var formdata = new FormData();	// formdata 생성
//    formdata.append("file", file);	// file data 추가
//
//    var signature = document.getElementById('signature');
//    signature.value(formdata);
//}

function completeOrder() {
	const canvas = document.getElementById('canvas');
	const imgDataUrl = canvas.toDataURL('image/png');
	console.log(imgDataUrl);
	const blobBin = atob(imgDataUrl.split(',')[1]); // imgBase64 데이터 디코딩
	
	var array = [];
	for (var i = 0; i < blobBin.length; i++) {
		array.push(blobBin.charCodeAt(i));
	}
	console.log(array);
	
	var file = new Blob([new Uint8Array(array)], {type: 'image/png'});	// Blob 생성
	var formdata = new FormData();	// formdata 생성
	formdata.append("file", file);	// file data 추가
	
	$.ajax({
		type : "POST",
		url : "/platform/testCompleteOrder",
		data : formdata,
		cache : false,
		contentType : false, // application/x-www-form-urlencoded; 방지
		processData : false, // data 파라미터 강제 string 변환 방지
		async : false,
		success : function(response) {
			alert(response["result"]);
		},
		error : function() {
			alert("실패~");
		}
	});
}