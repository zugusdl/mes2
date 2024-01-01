function goContent(product_code) {

	$.ajax({
		url : "purchaseList", // 
		type : "post",
		dataType : "json", // Jackson Databind사용해서 json으로 받기
		data : {
			"product_code" : product_code
		}, // 받은 매개변수 전달
		success : function(data) {
			content(data, product_code);
		},
		error : function(xhr, status, error) {
			/*console.error("AJAX 에러:", xhr.responseText);
			alert("AJAX 에러: " + status);*/
		}
	});

	console.log("Clicked on product code:", product_code);

}

function changeStatus(product_code, status) {
    $.ajax({
        url: "updateStatus",
        type: "post",
        dataType: "json",
        data: {
            "status": status.getValue(),
            "product_code": product_code
        },
        success: function(response) {
            if (response) {
                var button = $("#yourButtonId"); // 실제 버튼 선택기로 업데이트해주세요
                if (response.status === "WAITING") {
                    button.text("대기").removeClass("btn-success").addClass("btn-danger");
                } else if (response.status === "COMPLETE") {
                    button.text("완료").removeClass("btn-danger").addClass("btn-success");
                }
            } else {
                alert("상태 업데이트 실패.");
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX 에러:", xhr.responseText);
            alert("AJAX 에러: " + status);
        }
    });
}

function content(data) { // 에이젝스에서 받은 값으로 출력하기

	var listHtml = "<div class='list-box'>";
	listHtml += "<table class='table table-hover'>";
	listHtml += "<thead>";
	listHtml += "<tr class='table-success' >";
	// listHtml += "<th scope='col'></th>";
	listHtml += "<th scope='col'>발주코드</th>";
	listHtml += "<th scope='col'>품목코드</th>";
	listHtml += "<th scope='col'>자재유형</th>";
	listHtml += "<th scope='col'>품목명</th>";
	listHtml += "<th scope='col'>원가</th>";
	listHtml += "<th scope='col'>발주수량</th>";
	listHtml += "<th scope='col'>발주등록일자</th>";
	listHtml += "<th scope='col'>담당자</th>";
	listHtml += "<th scope='col'>처리상태</th>";
	listHtml += "</tr>";
	listHtml += "</thead>";
	listHtml += "<tbody>";

	$.each(data, function(index, obj) {
		listHtml += "<tr>";
		listHtml += "<td>" + obj.orders_code + "</td>";
		listHtml += "<td>" + obj.product_code + "</td>";
        listHtml += "<td>" + obj.category + "</td>";
        listHtml += "<td>" + obj.name + "</td>";
        listHtml += "<td>" + obj.cost + "</td>";
        listHtml += "<td>" + obj.quantity + "</td>";
       listHtml += "<td>" + obj.regdate + "</td>";
        listHtml += "<td>" + obj.user_id + "</td>";
        listHtml += "<td> <button id='statusBtn' class='btn btn-danger'>대기</button></td>";
	listHtml += "</tr>";
	});
	
	listHtml += "</tbody>";
	listHtml += "</table>";


	
	// 위 표가 들어갈 자리의 아이디값 가져와서 입력하기
	$("#view2").html(listHtml);
}

