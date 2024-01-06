//상단 오른쪽 추가 버튼 js
function replaceButton() {
            
	var addbtn = document.getElementById('addbtn');
    var updatebtn = document.getElementById('updatebtn');
            
    addbtn.style.display = 'none';
    updatebtn.style.display = 'none';
            
    var canclebtn = document.getElementById('canclebtn');
    var submitbtn = document.getElementById('submitbtn');
    
    canclebtn.style.display = 'inline-block';
    submitbtn.style.display = 'inline-block';
   
    var row = document.getElementById("insert_hang");
    
    if (row.style.display === "none") {
        row.style.display = "table-row";
    } else {
        row.style.display = "none";
    }
            
}


//  상단 오른쪽 수정 버튼 js
function replaceButton2() {
    
    var addbtn = document.getElementById('addbtn');
    var updatebtn = document.getElementById('updatebtn');
    
    addbtn.style.display = 'none';
    updatebtn.style.display = 'none';
    
    var canclebtn = document.getElementById('canclebtn');
    var submitbtn2 = document.getElementById('submitbtn2');
    var submitbtn3 = document.getElementById('submitbtn3');
    
    submitbtn2.style.display = 'inline-block';
    submitbtn3.style.display = 'inline-block';
    canclebtn.style.display = 'inline-block';    
    
    var checkboxes = document.querySelectorAll(".updatecheckbox");
    
    checkboxes.forEach(function(checkbox) {
    	
    	if (checkbox.style.display === "none") {
            checkbox.style.display = "block";
        }
    	else {
        checkbox.style.display = "none";
        }
     	
	    checkbox.addEventListener('change', () => {
	    	      
	    	checkboxes.forEach(otherCheckbox => {
	    	        
		    	if (otherCheckbox !== checkbox) {
		    	otherCheckbox.checked = false;
		    	}
	    	    	  
	    	});
	    })
    
    });
   
}

//  다른체크박스 클릭시 창닫기 js
function a(checkbox) {
    
	var row = checkbox.closest('tr');

    if (row) {
        var classAElements = row.getElementsByClassName('a');
        var classBElements = row.getElementsByClassName('b');

        if (checkbox.checked) {
            // 체크일 때
            for (var i = 0; i < classAElements.length; i++) {
                classAElements[i].style.display = 'none';
            }
            for (var i = 0; i < classBElements.length; i++) {
                classBElements[i].style.display = 'table-cell';
            }
        } else {
            // 체크가 해제일 때
            for (var i = 0; i < classAElements.length; i++) {
                classAElements[i].style.display = 'table-cell';
            }
            for (var i = 0; i < classBElements.length; i++) {
                classBElements[i].style.display = 'none';
            }
        }
    }    
    //?
    var allRows = document.getElementsByTagName('tr');
    
    for (var j = 0; j < allRows.length; j++) {
        
    	if (allRows[j] !== row) {
            var otherClassAElements = allRows[j].getElementsByClassName('a');
            var otherClassBElements = allRows[j].getElementsByClassName('b');

            // class="a"인 열 보이게 설정
            for (var i = 0; i < otherClassAElements.length; i++) {
                otherClassAElements[i].style.display = 'table-cell';
            }

            // class="b"인 열 감추기
            for (var i = 0; i < otherClassBElements.length; i++) {
                otherClassBElements[i].style.display = 'none';
            }
        }
    }   
}




// 추가 ajax
function submitData() {
    // 폼 데이터 수집
    var data = {
        category: $('#typeSelect').val(),
        pw: $('input[name="pw"]').val(),
        name: $('input[name="name"]').val(),
        manager: $('input[name="manager"]').val(),
        address: $('input[name="address"]').val(),
        call: $('input[name="call"]').val(),
        fax: $('input[name="fax"]').val(),
        email: $('input[name="email"]').val(),
        
    };
    
    $.ajax({
        url: '/business/insertbusiness',
        type: 'POST',
        data: data,
        success: function() {
            alert('추가완료');
            location.reload();
        },
        error: function(error) {
            console.error('에러 발생:', error);
        }
    });
} 



// 수정 ajax
function submitData2(submitbtn2) {
    
	var row = $(submitbtn2).closest('tr');
	
	var company_code = row.find('.b:eq(0)').text();
    var pw = row.find('.b input[name="pw"]').val();
    var name = row.find('.b input[name="name"]').val();
    var manager = row.find('.b input[name="manager"]').val();
    var address = row.find('.b input[name="address"]').val();
    var call = row.find('.b input[name="call"]').val();
    var fax = row.find('.b input[name="fax"]').val();
    var email = row.find('.b input[name="email"]').val();
    var contractStatus = row.find('.b input[name="contract_status"]').val();
    
    var data = {
        
    	company_code: company_code,
        pw: pw,
        name: name,
        manager: manager,
        address: address,
        call: call,
        fax: fax,
        email: email,
        contract_status: contractStatus
    };
    
    $.ajax({
        url: '/business/updatebusiness',
        type: 'POST',
        data: data,
        success: function(response) {
        	alert('수정완료');
            location.reload();
        },
        error: function(error) {
            // 에러 시 처리
            console.error('에러 발생:', error);
        }
    });
}


// 삭제 ajax -->
function submitData3(submitbtn3) {
	
	var row = $(submitbtn3).closest('tr');
    var company_Code = row.find('.b:eq(0)').text();

    $.ajax({
        url: '/business/deletebusiness',
        type: 'POST',
        data: {
            company_code: company_Code,
        },
        success: function(response) {
            alert('삭제완료');
            location.reload();
        },
        error: function(error) {
            console.error(error);
        }
    });
	
}


// 취소버튼 js -->
function redirectToFirstPage() {
    window.location.href = '/business/firstpage';
}

