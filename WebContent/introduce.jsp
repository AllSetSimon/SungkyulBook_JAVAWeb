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
			<li class="nav-item dropdown">
				<a class="nav-link dropdown toggle" id="dropdown" data-toggle="dropdown">
				성결북이란?
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown">
					<a class = "dropdown-item" href="introduce.jsp">성결북이란?</a>
					<a class = "dropdown-item" href="howtoSeller.jsp">성결북 이용방법(판매자)</a>
					<a class = "dropdown-item" href="howtoCustomer.jsp">성결북 이용방법(구매자)</a>
				</div>
			</li>	
				<li class="nav-item dropdown">
				<a class="nav-link dropdown toggle" id="dropdown" data-toggle="dropdown">
				단과대학 별 서적
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown">
					<a class = "dropdown-item" href="engineerContent.jsp">공과대학</a>
					<a class = "dropdown-item" href="koreanContent.jsp">인문대학</a>
					<a class = "dropdown-item" href="godContent.jsp">신학대학</a>
					<a class = "dropdown-item" href="artContent.jsp">예술대학</a>
				</div>
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
					<a class = "dropdown-item" href="myContent.jsp">내가 올린 글</a>
						<a class = "dropdown-item" href="myLikeList.jsp">내가 찜한 글</a>
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
	
		<img alt="성결북소개" src="img/introduce.jpg" margin-left="100" align="center">		
	
	
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
	 	Copyright &copy; 2019 박수빈 윤원석 조동영 All Rights Reserved
	</footer>
	


	<!--  제이쿼리 자바스크립트 추가하기 -->
	<script src="./js/jquery.min.js"></script>
	<script src="./js/popper.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>