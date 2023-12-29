<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/platform/completeOrder.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/platform/jquery.signaturepad.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<div class="container2">
		<h3 id="h3">수령 확인</h3>
		<section class="section1">
			<form method="post" onsubmit="completeOrder();"
				enctype="mulipart/form-data">
				<span>* 성함을 정자로 작성하세요</span>

				<!-- 				<div class="sigPad" id="smoothed" style="width: 100%; padding-bottom: 20px !important;"> -->
				<!-- 					<div class="sig sigWrapper current" style="text-align: center; height: auto; display: block;"> -->
				<!-- 						<div class="typed" style="display: none;"></div> -->
				<%-- 						<canvas class="pad" id="cy_sig" height="250"></canvas> --%>
				<!-- 						<input type="hidden" name="output" class="output" value=""> -->
				<!-- 					</div> -->
				<!-- 					<input type="hidden" name="sign_image" id="sign_image"> -->
				<!-- 				</div> -->

				 
				<div id="signature-pad" class="m-signature-pad">
					<div class="m-signature-pad--body">
						<canvas></canvas>
					</div>
					<div class="m-signature-pad--footer">
						<div class="description">사인해 주세요~</div>
						<button type="button" class="button clear" data-action="clear">지우기</button>
						<button type="button" class="button save" data-action="save">저장</button>
					</div>
				</div>

				<button type="submit" class="btn btn-secondary">수령 완료</button>
				<!-- 				<button type="button" class="btn btn-secondary" onclick="completeOrder();">수령 완료</button> -->
			</form>
		</section>
	</div>

	<script>
		$(document).ready(function() {
			$('.sigPad').signaturePad();
		});
	</script>
	<script>
		        var canvas = $("#signature-pad canvas")[0];
		var sign = new SignaturePad(canvas, {
			minWidth : 5,
			maxWidth : 10,
			penColor : "rgb(66, 133, 244)"
		});
		$("[data-action]").on("click", function() {
			if ($(this).data("action") == "clear") {
				sign.clear();
			} else if ($(this).data("action") == "save") {
				if (sign.isEmpty()) {
					alert("사인해 주세요!!");
				} else {
					$.ajax({
						url : "save.jsp",
						method : "post",
						dataType : "json",
						data : {
							sign : sign.toDataURL()
						},
						success : function(r) {
							alert("저장완료 : " + r.filename);
							sign.clear();
						},
						error : function(res) {
							console.log(res);
						}
					});
				}
			}
		});
		function resizeCanvas() {
			var canvas = $("#signature-pad canvas")[0];
			var ratio = Math.max(window.devicePixelRatio || 1, 1);
			canvas.width = canvas.offsetWidth * ratio;
			canvas.height = canvas.offsetHeight * ratio;
			canvas.getContext("2d").scale(ratio, ratio);
		}
		$(window).on("resize", function() {
			resizeCanvas();
		});
		resizeCanvas();    
	</script>

	<!-- 	<script -->
	<%-- 		src="${pageContext.request.contextPath}/resources/js/platform/completeOrder.js"></script> --%>
	<script
		src="${pageContext.request.contextPath}/resources/js/platform/jquery.signaturepad.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/platform/json2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/platform/flashcanvas.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/platform/numeric-1.2.6.min.js"></script>
</body>
</html>