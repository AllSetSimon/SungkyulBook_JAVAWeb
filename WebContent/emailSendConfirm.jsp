<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>성결북</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/custom.css">
</head>
<body>

	<%
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String)session.getAttribute("userID");
		}
		
		if(userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요');");
			script.println("location.href='userLogin.jsp';");
			script.println("</script>");
			script.close();
			return;
		}
	%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class = "navbar-brand" href="index.jsp">성결북</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div id="navbar" class="collapse navbar-collapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="index.jsp">메인</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="index.jsp">성결북이란?</a>
			</li>	
			<li class="nav-item dropdown">
				<a class="nav-link dropdown toggle" id="dropdown" data-toggle="dropdown">
				회원관리
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown">
				
				<%
				if(userID==null) {				
				%>
					<a class = "dropdown-item" href="userLogin.jsp">로그인</a>
					<a class = "dropdown-item" href="userJoin.jsp">회원가입</a>
				<%
				} else {
				%>
					<a class = "dropdown-item" href="userLogout.jsp">로그아웃</a>
				<%
				}
				%>
				</div>
			</li>
		</ul>
		<form action="./index.jsp" method = "get" class="form-inline my-2 my-lg-0">
			<input type="text" name="search" class="form-control mr-sm-2" types="search" placeholder="내용을 입력하세요" aria-label="Search">
			<Button class="btn btn-outline-success my-2 my-sm-0" type="summit">검색</Button>
		</form>
	</div>	
	</nav>
	<section class="container mt-3" style="max-width: 560px;">
		<div class="alert alert-warning mt-4" role="alert">
			앗! 아직 이메일 인증을 하지 않으셨네요! 이메일 인증 하시고 다시 만나요!		
		</div>
		<a href="emailSendAction.jsp" class="btn btn-primary">인증메일 다시 받기</a>
	</section>
	
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
	 	Copyright &copy; 2019 박수빈 윤원석 조동영 All Rights Reserved
	</footer>
	


	<!--  제이쿼리 자바스크립트 추가하기 -->
	<script src="./js/jquery.min.js"></script>
	<script src="./js/popper.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>