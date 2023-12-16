<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/css/main/main.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
<title>MainPage</title>
</head>
<body>
	<section class="content">
		<main>
			<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>Recent Orders</h3>
						<i class="fas fa-search"></i> <i class="fas fa-filter"></i>
					</div>

					<table>
						<thead>
							<tr>
								<th>User</th>
								<th>Order Date</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status pending">Pending</span></td>
							</tr>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status pending">Pending</span></td>
							</tr>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status process">Process</span></td>
							</tr>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status process">Process</span></td>
							</tr>
							<tr>
								<td><img src="/resources/img/main/profile.png" alt="" />
									<p>User Name</p></td>
								<td>07-05-2023</td>
								<td><span class="status complete">Complete</span></td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="chart">
					<div class="head">
						<h3>Sales Report</h3>
						<i class="fas fa-search"></i> <i class="fas fa-filter"></i>
					</div>
					<div>
						<canvas id="sales-chart" width="432" height="216"
						style="display: block; height: 173px; width: 346px;"
						class="chartjs-render-monitor"></canvas>
					</div>
				</div>
				
				<div class="todo">
					<div class="head">
			
						<h3>Todos</h3>
						<i class="fas fa-plus"></i> <i class="fas fa-filter"></i>
					</div>

					<ul class="todo-list">
						<li class="not-completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
						<li class="not-completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
						<li class="completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
						<li class="completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
						<li class="completed">
							<p>Todo List</p> <i class="fas fa-ellipsis-vertical"></i>
						</li>
					</ul>
				</div>
			</div>
		</main>
	</section>
	
	<script>
	// js 파일에 따로 넣으니까 적용이 안 됨ㅠㅠ
	const xValues = ["Italy", "France", "Spain", "USA", "Argentina"];
	const yValues = [55, 49, 44, 24, 15];
	
	new Chart("sales-chart", {
	  type: "bar",
	  data: {
	    labels: xValues,
	    datasets: [{
	      backgroundColor: "#a2c6a8",
	      data: yValues
	    }]
	  },
	  options: {
	    legend: {display: false},
	    title: {
	      display: true,
	      text: "World Wine Production 2018"
	    }
	  }
	});
	</script>
	<script src="/resources/js/main/main.js"></script>

</body>
</html>