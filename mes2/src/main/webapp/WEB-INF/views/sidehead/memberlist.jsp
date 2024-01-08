<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">


<!-- jqery js입니다. (ajax랑 js에 있는 여러가지 js라이브러리 쓸려고)-->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">


<!-- 부트스트랩 css cdn입니다. -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<!--  부트스트랩 js cdn입니다. -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous">
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>



<style text="text/css">

  .list {
            margin-top: 100px;
            margin-left: 100px;
            margin-right: 20px;
        }

 .table th, .table td {
          text-align: center;
          vertical-align: middle; 
          border : none;
       }


.table tr td {
    border-bottom: 1px solid #dee2e6;  /* td 아랫쪽에만 border 추가 */
}


</style>





</head>

<body>

<%@ include file="sidehead.jsp" %>	
			<!-- 표 -->
			<div class="list">
				<div class="son_list-box">	
						<table class="table table-hover" >
							<thead>
								<tr class="table-success" >
									<th scope="col">사번</th> <!-- 체크박스 -->
									<th scope="col">사진</th>
									<th scope="col">아이디</th>
									<th scope="col">비밀번호</th>
									<th scope="col">이름</th>
									<th scope="col">부서</th>
									<th scope="col">주소</th>
									<th scope="col">직급</th>
									<th scope="col">주민번호</th>
									<th scope="col">입사일</th>
									<th scope="col">전화번호</th>
									<th scope="col">수정</th>
									<th scope="col">삭제</th>
								</tr>
						</thead>	
				<c:forEach var="member" items="${memberlist}">
                    <tbody>
	                    <td>${member.user_num}</td>
						<td><img src="${pageContext.request.contextPath}/resources/img/${member.user_img}" class="profile-img" width="20" height="20"></td>
						<td>${member.user_id}</td>
						<td>${member.user_pw}</td>
						<td>${member.user_name}</td>
						<td>${member.user_department}</td>
						<td>${member.user_address}</td>
						<td>${member.user_position}</td>
						<td>${member.user_jumin}</td>
						<td>${member.user_joindate}</td>
						<td>${member.user_tel}</td>
						 <form method="post">
					<td>
						<a href="/system/adminupdate?user_id=${member.user_id}">
							<button class="btn btn-success" type="button"><i class="bi bi-pencil-square"></i></button>
						</a>
					</td>
					<td>	
						<a href="#" class="delete-link" data-user-id="${member.user_id}">Delete</a>
							<button class="btn btn-danger" type="button"><i class="bi bi-trash"></i></button>
						</a>	
					</td>
						</form>
                    </tbody>
				</c:forEach>		
			</table>
		</div>
	</div>


<!-- sidehead.jsp 의 로그아웃/상세정보보기 토글이 안되는거 보완 -->
<script>
    $(document).ready(function(){
        $('.dropdown-toggle').dropdown();
    });
    
    $(document).ready(function(){
        $('.dropdown-toggle').dropdown();

        // "회원탈퇴" 버튼에 대한 클릭 이벤트 핸들러
        $("#deleteButton").click(function(){
            $("#deleteForm").modal("show");
        });

        // <a> 태그에 대한 클릭 이벤트 핸들러
        $("a[data-target='#deleteForm']").click(function(){
            $("#deleteForm").modal("show");
        });

        // "탈퇴하기" 버튼에 대한 클릭 이벤트 핸들러
        $("#deleteForm .btn-danger").click(function(){
            $("#deleteForm form").submit();
        });

        // 모달이 닫힐 때 폼의 입력 필드 초기화
        $("#deleteForm").on("hidden.bs.modal", function () {
            $("#deleteForm form")[0].reset();
        });
    });
    
    
    
    
</script>





</body>
</html>