<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>발주 신청 폼</title>
    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materials/inputOrder.css">
   
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
</head>
<body>
    <form action="/materials/save" method="post">
     <!--    <label for="orderCode">발주코드</label>
        <input type="text" id="orderCode" name="orderCode"> -->

        <label for="category">자재유형</label>
        <select id="category" name="category">
			<option >자재유형</option>	
			<option value="원재료">원자재</option>	
			<option value="부재료">부재료</option>	
		</select>
		
		<label for="product_code">품목코드</label>
        <select id="selectProductCode" name="product_code">
		</select>

        <label for="itemName">품목명</label>
        <input type="text" id="name" name="name" readonly="readonly">

        <label for="cost">원가</label>
        <input type="text" id="cost" name="cost" readonly="readonly">

        <label for="unitPrice">단가</label>
        <input type="text" id="unitPrice" name="price"  readonly="readonly">
		
        <label for="orderQuantity">발주수량</label>
        <input type="text" id="orderQuantity" name="quantity" required>
        
        <button type="submit">발주신청</button>
    </form>

<script type="text/javascript">

	var selectCategory = document.getElementById("category");

	selectCategory.addEventListener("change", function(){
		var category = document.getElementById("category").value;
		getMetaProductCode(category);
	});

	
	function getMetaProductCode(category){
		
		$.ajax({
			type:"GET",
			url:"/materials/getProductCode",
			data :{'category' : category },
			success:function(data){
	            let productCode = $("#selectProductCode"); 
	            productCode.empty();
	            for (let i = 0; i < data.length; i++) {
	                let option = $("<option>").val(data[i]).text(data[i]);
	                productCode.append(option);
	            }
			},
			error:function(){
				alert("그냥 실패");
			}
			
		});
		
	};
	
	var selectProductCode = document.getElementById("selectProductCode");
	
	selectProductCode.addEventListener("change", function(){
		var productCode = selectProductCode.value;
		getProductInfo(productCode);
	});
	
	function getProductInfo(productCode){
		
		
		$.ajax({
			type:"GET",
			url:"/materials/productInfo",
			data:{'productCode' : productCode},
			success:function(data){
				console.log(data);
				console.log(data.name);
				document.getElementById("name").value = data.name;
				document.getElementById("cost").value = data.cost;
				document.getElementById("unitPrice").value =  data.price;								
			}
		});
	};
	
</script>

</body>
</html>