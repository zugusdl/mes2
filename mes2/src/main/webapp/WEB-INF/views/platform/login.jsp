<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>LoginForm</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="/resources/css/platform/login.css">
</head>

<body>

	<header class="header">
		<nav class="nav">
			<a href="#" class="logo" style="text-decoration: none;">Awsometic</a>
			<button class="btn" id="show-form">Login</button>
		</nav>
	</header>

	<section class="home">
		<div class="form-container">
			<i class="fas fa-xmark close-form"></i>

			<div class="form login-form">
				<form method="post">
					<h2>로그인</h2>
					<div class="input-box">
						<input type="text" name="mdb_code" placeholder="아이디를 입력하세요" />
						<i class="fas fa-envelope email"></i>
					</div>

					<div class="input-box">
						<input type="password" name="mdb_pw" placeholder="패스워드를 입력하세요" />
						<i class="fas fa-lock password"></i>
						<i class="fas fa-eye-slash pass-hide"></i>
					</div>

					<div class="options-field">
						<a href="#" class="forgot-pass" style="color: black;">아이디 찾기</a>
						<a href="#" class="forgot-pass" style="color: black;">비밀번호 찾기</a>
					</div>

					<input type="submit" value="로그인하기" class="btn">
				</form>
			</div>
		</div>
	</section>

	<script src="/resources/js/platform/login.js"></script>
	<script>
		var result = "${result}";
		if(result == "loginFail") {
			alert('로그인 정보를 확인하세요');
		}
	</script>

</body>
</html>