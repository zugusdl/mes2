<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>발주 신청 폼</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            margin: auto;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
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

        <label for="orderManager">발주담당자</label>
        <input type="text" id="orderManager" name="orderManager">
        
        <button type="submit">발주 신청</button>
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