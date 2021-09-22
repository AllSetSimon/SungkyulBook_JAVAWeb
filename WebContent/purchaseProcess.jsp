<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="user.UserDAO" %>
<%@ page import="book.BookDTO" %>
<%@ page import="book.BookDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.net.URLEncoder" %>

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
		request.setCharacterEncoding("UTF-8");
		String lectureDivide = "전체";
		String searchType="최신순";
		String search="";
		
		int pageNumber = 0;
		if(request.getParameter("lectureDivide") != null) {
			lectureDivide = request.getParameter("lectureDivide");
		}
		if(request.getParameter("searchType") != null) {
			searchType = request.getParameter("searchType");
		}
		if(request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		if(request.getParameter("pageNumber") != null) {
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			} catch (Exception e) {
				System.out.println("검색 페이지 번호 오류");
			}
			
		}
				
		String userID = null;
		if(session.getAttribute("userID") != null) {
			userID = (String)session.getAttribute("userID");
		}
		
		if(userID==null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요');");
			script.println("location.href='userLogin.jsp';");
			script.println("</script>");
			script.close();
			return;
		}
		
		boolean emailChecked = new UserDAO().getUserEmailChecked(userID);
		if(emailChecked==false){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'emailSendConfirm.jsp';");
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
	<section class="container">
					<form action="./purchaseSendAction.jsp?sellBookID=<%=request.getParameter("sellBookID") %>" method="post">
						<div class="form-row"> 
							<div class="form-group col-sm-6">
								<label>구매자 아이디</label>&nbsp;&nbsp;&nbsp;
								<%= session.getAttribute("userID") %>						
							</div>
						</div>
						<div class="form-row"> 
							<div class="form-group col-sm-6">
								<label>구매요청 서적</label>&nbsp;&nbsp;&nbsp;
								<%= request.getParameter("bookName") %>						
							</div>
						</div>
						
							<div class="form-row"> 
							<div class="form-group col-sm-12">
								<label>요청 제목</label>
								<input type="text" name="purchaseTitle" class="form-control" maxlength="20">							
							</div>
						</div>
							<div class="form-group">
								<label>내용</label>
								<textarea name="purchaseContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>
							</div>
							<div class="form-row"> 
							<div class="form-group col-sm-6">
								<label>연락받을 번호</label>
								<input type="text" name="callNumber" class="form-control" maxlength="20">							
							</div>
							<div class="form-group col-sm-6">
								<label>카카오톡 아이디</label>
								<input type="text" name="kakaoID" class="form-control" maxlength="20">							
							</div>
						</div>		
								<div class="modal-footer">
								<a class="btn btn-secondary" href="./index.jsp">요청 취소(메인으로)</a>
								<button type="submit" class="btn btn-primary">요청 메일 보내기</button>
								</div>
					</form>
	</section>

	
	
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
	 	Copyright &copy; 2019 성결북 박수빈 윤원석 조동영 All Rights Reserved
	</footer>
	


	<!--  제이쿼리 자바스크립트 추가하기 -->
	<script src="./js/jquery.min.js"></script>
	<script src="./js/popper.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>