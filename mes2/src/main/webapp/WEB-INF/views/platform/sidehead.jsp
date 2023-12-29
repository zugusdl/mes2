<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  페이지도 인코딩 해줘야함 -->
<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
<head>
<meta charset="UTF-8">
<title>Drop Down Sidebar Menu | CodingLab</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/platform/sideheadstyle.css">
<!-- Boxiocns CDN Link -->
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>

<!-- 부트스트랩 cdn 추가-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<!-- Font Awesome 라이브러리 추가 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<div class="sidebar close">
		<div class="logo-details">
			<i class="fa-solid fa-bars"></i> <span class="logo_name">AWESOMETIC</span>
		</div>
		<ul class="nav-links">
			<li>
				<div class="iocn-link">
					<a href="/platform/orderList"> <i
						class="fa-solid fa-cart-shopping"></i> <span class="link_name">발주관리</span>
					</a>
				</div>
			</li>
			<li>
				<div class="iocn-link">
					<a href="#"> <i class="fa-solid fa-check"></i> <span
						class="link_name">공지사항</span>
					</a>
				</div>
			</li>
			<li>
				<div class="profile-details">
					<div class="profile-content">
						<img src="" alt="프로필 사진 자리">
					</div>
					<div class="name-job">
						<div class="profile_name">Prem Shahi</div>
						<div class="job">Web Desginer</div>
					</div>
					<i class="fa-brands fa-google-play"></i>
				</div>
			</li>
		</ul>
	</div>
	<section class="home-section">
		<div class="home-content">
			<span class="text"></span>

			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					data-bs-toggle="dropdown" aria-expanded="false">
					<i class="fa-solid fa-user-tie"
						style="color: #e8edf7; font-size: 21px;"></i>
				</button>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="#updateModal" rel="modal:open">정보 수정</a></li>
					<li><a class="dropdown-item" href="#">로그아웃</a></li>
				</ul>
			</div>
		</div>
	</section>

	<!-- 정보 수정 모달 -->
	<div id="updateModal" class="modal">
		<div>
			<div>
				회사명: <input type="text" name="name" value="${mdto.name }" readonly><br>
			</div>
			<div>
				현재 비밀번호: <input type="password" name="pw" placehoder="현재 비밀번호를 입력하세요"><br>
			</div>
			<div>
				수정 비밀번호: <input type="password" name="newPw" placehoder="수정할 비밀번호를 입력하세요"><br>
			</div>
			<div>
				비밀번호 확인: <input type="password" name="checkPw" placehoder="수정 비밀번호 한번 더 입력하세요"><br>
			</div>
			<div>
				담당자: <input type="text" name=manager value="${mdto.manager }" readonly><br>
			</div>
			<div>
				주소: <input type="text" name="address" value="${mdto.address }" readonly><br>
			</div>
		</div>
		<a href="#" rel="modal:close">닫기</a>
	</div>

	<script
		src="${pageContext.request.contextPath}/resources/js/platform/sideheadscript.js"></script>
	<!-- 사이드바 script -->

	<!-- 부트스트랩 js 추가-->

</body>
</html>
