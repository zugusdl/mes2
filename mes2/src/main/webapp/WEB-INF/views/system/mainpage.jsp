<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Jquery 라이브러리 추가 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link href="${pageContext.request.contextPath}/resources/fullcalander/main.css" rel='stylesheet' />
<script src="${pageContext.request.contextPath}/resources/fullcalander/main.js"></script>
<script src="${pageContext.request.contextPath}/resources/fullcalander/locales-all.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main/weather.js"></script>


 
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<!-- Font Awesome CDN 사용 예시 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>


<style text="text/css">
#calendar {
	width: 50%;
	max-width: 50%;
	margin-left: 830px;
	margin-bottom: 10px;
}

.container {
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	text-align: right;
}

div p {
	display: inline; /* 또는 display: inline-block; */
	margin: 0; /* 기본 마진 제거 */
}

.weather-container.offset-sm-3 {
	margin-left : -100px;
	margin-right: 130px;
	margin-top: 20px;
	height: 350px;
	width: 900px;
}



#weather-card-body {
	display: flex;
	flex-direction: column;
	margin-right: 20px;
	text-align: right;
}

#weather-card-frame{
	margin-left : 50px;
	background-image: url('/resources/img/mainpage/people.png');
 	background-size: contain; /* 배경 이미지를 요소에 맞춰서 보여주도록 설정 */
    background-repeat: no-repeat;
    background-position: center bottom;	
    height: 350px;
	
}


#card-body-first-mother{
	margin-top: 20;
	margin-left: 100;

}

.card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }

  /* Hover 효과 */
  .card:hover {
    transform: scale(1.05); /* 크기 확대 */
    box-shadow: 0 0 15px rgba(0, 128, 0, 0.9); /* 그림자 효과 강화 */
  }



</style>


</head>
<body>

	<%@ include file="sidehead.jsp"%>



<div class="row">


<div class="col-sm-6" id="status">

  <div class="row" style="margin-left: 50px;">
  	<!-- 첫 번째 card -->
		 <div class="col-md-3" id="card-odd" style="margin-top: 20px;">
            <div class="offset-sm-3 card border-success" style="width: 20rem; box-shadow: 0 0 10px rgba(60, 179, 113);">
			  <div class="card-body" id="card-body-first">
			    <h5 class="card-title"><img src="${pageContext.request.contextPath}/resources/img/icons/professionals.png" style="width:35px; height:35px;"></h5>
			    <h3>Employees</h3><br>
			    <p class="card-text" style="font-weight: bold;">${total}명</p>
			  </div>
			</div>
		  </div>
	  	
		 <div class="col-md-5" style="margin-top: 20px; margin-left: 50px;">
		   <!-- 두 번째 card -->
            <div class="offset-sm-5 card border-warning" style="width: 20rem; box-shadow: 0 0 10px rgba(255, 165, 0, 0.8);">
                <div class="card-body" id="card-body-first">
                    <h5 class="card-title"><img src="${pageContext.request.contextPath}/resources/img/icons/factory.png" style="width:35px; height:35px;"></h5>
                    <h3>Productions</h3><br>
                    <p class="card-text" style="font-weight: bold;">${totalProduct}개</p>
                </div>
            </div>
        </div>
	</div>
	
	<div class="row" style="margin-left: 50px;">
        <!-- 세 번째 card -->
        <div class="col-md-3" id="card-odd" style="margin-top: 20px;">
            <div class="offset-sm-3 card border-primary"" style="width: 20rem; box-shadow: 0 0 10px rgba(29, 117, 211, 1);">
                <div class="card-body" id="card-body-first">
                    <h5 class="card-title"><img src="${pageContext.request.contextPath}/resources/img/icons/stock.png" style="width:35px; height:35px;"></h5>
                   	<h3>Stocks</h3><br>
                    <p class="card-text" style="font-weight: bold;">${totalIn}개</p>
                </div>
            </div>
        </div>

  
        <div class="col-md-5" style="margin-top: 20px; margin-left: 50px;">
           <!-- 네 번째 card -->
            <div class="offset-sm-5 card border-danger " style="width: 20rem; box-shadow: 0 0 10px rgba(235, 62, 10, 1); ">
                <div class="card-body" id="card-body-first">
                    <h5 class="card-title" ><img src="${pageContext.request.contextPath}/resources/img/icons/out-of-stock.png" style="width:35px; height:35px;"></h5>
                     <h3 >Out of Stocks</h3><br>
                    <p class="card-text" style="font-weight: bold;">${totalOut}개</p>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
    
	
	
</div>	
	

  <div class="col-sm-6 order-sm-3" id="weather">	
	<div class="offset-sm-3 weather-container">
		<div class="card mb-3" id="weather-card-frame">
			<div class="row g-0">
				<div class="col-md-4">
				</div>
				<div class="col-md-8">
					<div class="card-body" id="weather-card-body">
						<h3 class="location"></h3><br>	
						<h5 class="current-time"></h5>
						<p class="current-temp"><br>
						<p class="weather-like"><br>								
					</div>
				</div>
			</div>
		</div>
	
		

	</div>
  </div>		
</div>
	<!-- <i class="fa-solid fa-cloud"></i> -->
	<!-- <i class="fa-solid fa-sun"></i> -->
	<!-- <i class="fa-solid fa-umbrella"></i> -->



<div class="row-second">
        <div id='calendar' style="float: left;"></div>
        <div id="piechart" style="width: 680px; height: 540px; margin-left: 140px; margin-top:10px;"></div>
        
</div>









	<!-- 달력 -->
	<script>
		//full calander 	
		document
				.addEventListener(
						'DOMContentLoaded',
						function() {
							var calendarEl = document
									.getElementById('calendar');
							var calendar = new FullCalendar.Calendar(
									calendarEl,
									{
										height : '500px',
										width : '900px',
										headerToolbar : {
											left : 'prev,next',
											center : 'title',
											right : 'dayGridMonth,dayGridWeek,dayGridDay'
										},

										selectable : true,
										selectMirror : true,

										navLinks : true, // can click day/week names to navigate views
										editable : true,
										locale : 'ko',
										select : function(arg) {
											Swal
													.fire(
															{
																html : "<div class='mb-7'>Create new event?</div><div class='fw-bold mb-5'>Event Name:</div><input type='text' class='form-control' name='event_name' />",
																icon : "info",
																showCancelButton : true,
																buttonsStyling : false,
																confirmButtonText : "Yes, create it!",
																cancelButtonText : "No, return",
																customClass : {
																	confirmButton : "btn btn-primary",
																	cancelButton : "btn btn-active-light"
																}
															})
													.then(
															function(result) {
																if (result.value) {
																	var title = document
																			.querySelector("input[name=;event_name']").value;
																	if (title) {
																		calendar
																				.addEvent({
																					title : title,
																					start : arg.start,
																					end : arg.end,
																					allDay : arg.allDay
																				})
																	}
																	calendar
																			.unselect()
																} else if (result.dismiss === "cancel") {
																	Swal
																			.fire({
																				text : "Event creation was declined!.",
																				icon : "error",
																				buttonsStyling : false,
																				confirmButtonText : "Ok, got it!",
																				customClass : {
																					confirmButton : "btn btn-primary",
																				}
																			});
																}
															});
										},

										// Delete event
										eventClick : function(arg) {
											Swal
													.fire(
															{
																text : "Are you sure you want to delete this event?",
																icon : "warning",
																showCancelButton : true,
																buttonsStyling : false,
																confirmButtonText : "Yes, delete it!",
																cancelButtonText : "No, return",
																customClass : {
																	confirmButton : "btn btn-primary",
																	cancelButton : "btn btn-active-light"
																}
															})
													.then(
															function(result) {
																if (result.value) {
																	arg.event
																			.remove()
																} else if (result.dismiss === "cancel") {
																	Swal
																			.fire({
																				text : "Event was not deleted!.",
																				icon : "error",
																				buttonsStyling : false,
																				confirmButtonText : "Ok, got it!",
																				customClass : {
																					confirmButton : "btn btn-primary",
																				}
																			});
																}
															});
										},
										dayMaxEvents : true, // allow "more" link when too many events
										// 이벤트 객체 필드 document : https://fullcalendar.io/docs/event-object
										events : [ {
											title : '신승민휴가',
											start : '2024-01-18'
										}, {
											title : '김진수휴가',
											start : '2024-01-19',
											end : '2022-01-20'
										}, {
											groupId : 999,
											title : 'Repeating Event',
											start : '2022-07-09T16:00:00'
										}, {
											groupId : 999,
											title : 'Repeating Event',
											start : '2022-07-16T16:00:00'
										}, {
											title : '손보성휴가,정수아휴가',
											start : '2024-01-02',
											end : '2024-01-02'
										}, {
											title : 'Meeting',
											start : '2022-07-12T10:30:00',
											end : '2022-07-12T12:30:00'
										}, {
											title : 'Lunch',
											start : '2022-07-12T12:00:00'
										}, {
											title : 'Meeting',
											start : '2022-07-12T14:30:00'
										}, {
											title : 'Happy Hour',
											start : '2022-07-12T17:30:00'
										}, {
											title : '손보성휴가',
											start : '2024-01-17'
										}, {
											title : '종강',
											start : '2024-01-16'
										}, {
											title : '2차프로젝트',
											start : '2024-01-02',
											end : '2024-01-16'
										}

										],
										eventColor : '#378006'
										
									});

							calendar.render();
						});
	</script>
	
	
	
	
	
	
	
   <!-- 구글차트 js -->
   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
		
    	var totalOkValue = ${totalOk != null && !totalOk.equals('[null]') ? totalOk : 0};  	
      	var totalNoValue = ${totalNo != null && !totalNo.equals('[null]') ? totalNo : 0};  	  
    	  
    	  
        var data = google.visualization.arrayToDataTable([
          ['인사', 'Hours per Day'],
          ['양품',    totalOkValue],
          ['불량품',      totalNoValue]
        
 
        ]);

        var options = {
          title: '불량품비율',
          backgroundColor: '#F5FBF0',
          is3D: true
       	  
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>










</body>
</html>
