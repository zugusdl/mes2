<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
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
    
    <script>
		var canvas = $("#signature-pad canvas")[0];
		var sign = new SignaturePad(canvas, {
			minWidth: 5,
			maxWidth: 10,
			penColor: "rgb(66, 133, 244)"
		});
		
		$("[data-action]").on("click", function(){
			if ( $(this).data("action")=="clear" ){
				sign.clear();
			}
			else if ( $(this).data("action")=="save" ){
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
						success : function(r){
							alert("저장완료 : " + r.filename);
							sign.clear();
						},
						error : function(res){
							console.log(res);
						}
					});
				}
			}
		});
		
		
		function resizeCanvas(){
			var canvas = $("#signature-pad canvas")[0];
	
			var ratio =  Math.max(window.devicePixelRatio || 1, 1);
			canvas.width = canvas.offsetWidth * ratio;
			canvas.height = canvas.offsetHeight * ratio;
			canvas.getContext("2d").scale(ratio, ratio);
		}
	    
	    $(window).on("resize", function(){
			resizeCanvas();
		});

		resizeCanvas();
    </script>
</body>
</html>