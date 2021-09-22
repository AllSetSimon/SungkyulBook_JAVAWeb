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
		<form method="get" action="./koreanContent.jsp" class="form-inline mt-3">
		<select name="searchType" class="form-control mx-1 mt-2">
			<option value="최신순">최신순</option>
			<option value="추천순" <%if(searchType.equals("추천순")) out.println("selected"); %>>추천순</option>
		</select>
		<input type="text" name="search" class="form-control mx-1 mt-2" placeholder="내용을 입력하세요">
		<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
		<a class="btn btn-primary mx-1 mt-2" data-toggle="modal" href="#registerModal">등록하기</a>
		<a class="btn btn-danger mx-1 mt-2" data-toggle="modal" href="#reportModal">신고하기</a>
		</form>	 
		
		<%
		ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
		bookList = new BookDAO().getKoreanList(searchType, search, pageNumber);
		if(bookList != null)
			for(int i = 0 ; i < bookList.size() ; i++) {
				if(i==5) break;
				BookDTO book = bookList.get(i);
		%>
	
	<div class="card bg-light mt-3">
		<div class="card-header bg-light">
			<div class="row">
				<div class="col-8 text-left"><%=book.getBookName() %>&nbsp; <small><%=book.getBookPublisher() %></small>&nbsp;&nbsp;(<%=book.getCollegeDivide() %>&nbsp;<%=book.getLectureYear() %>학년 <%=book.getSemesterDivide() %> <%=book.getLectureName() %>)</div>
				<div class="col-4 text-right">
					종합<span style="color: red;"><%=book.getTotalScore() %></span>
				</div>
			</div>
		</div>
		<div class="card-body">
			<h5 class="card-title">
			<%=book.getBookPrice() %>원&nbsp;
			</h5>
			<p class="card-text"><%= book.getBookContent() %> </p>
			<div class="row">
				<div class="col-9 text-left">
					책 상태 <span style="color: red;"><%=book.getBookStat() %>&nbsp;&nbsp;</span>
					필기 상태 <span style="color: red;"><%=book.getWritingStat() %>&nbsp;&nbsp;</span>
					청결도 <span style="color: red;"><%=book.getCleanStat() %>&nbsp;&nbsp;</span>
					<span style="color: green;"> (찜 개수 : <%= book.getLikeCount() %>) </span>
					<span style="color: blue;"> (판매 상태 : <%= book.getStatus() %>) </span>
				</div>
				<div class="col-3 text-right">
				
				<%
				if(book.getStatus().equals("판매중")) {				
				%>
					<a class="btn btn-primary mx-1 mt-2" href="./purchaseProcess.jsp?sellBookID=<%=book.getSellBookID()%>&bookName=<%=book.getBookName()%>">구매 요청</a>
					<a onclick="return confirm('찜하실겁니까?')" href="./likeAction.jsp?sellBookID=<%=book.getSellBookID()%>">찜하기</a>
					<a onclick="return confirm('삭제하실겁니까?')" href="./deleteAction.jsp?sellBookID=<%=book.getSellBookID() %>">삭제</a>
				<%
				} else {
				%>			
					<a class="btn btn-danger mx-1 mt-2" href="#">구매 불가</a>
				<%
				}
				%>
				</div>
			</div>
		</div>
	</div>	
	
	<!--  책 구매 Modal -->
	<div class="modal fade" id="purchaseModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">서적 구매 요청 폼</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>				
				
				<div class="modal-body">
					<form action="./purchaseSendAction.jsp?sellBookID=<%=book.getSellBookID() %>" method="post">
						<div class="form-row"> 
							<div class="form-group col-sm-6">
								<label>구매자 아이디</label>&nbsp;&nbsp;&nbsp;
								<%= session.getAttribute("userID") %>						
							</div>
						</div>
						<%-- <div class="form-row"> 
							<div class="form-group col-sm-6">
								<label>구매요청 서적</label>&nbsp;&nbsp;&nbsp;
								<%= book.getBookName() %>						
							</div>
						</div> --%>
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
									<button type="button" class="btn btn-secondary" data-dismiss="modal">요청 취소</button>
									<button type="submit" class="btn btn-primary">요청 메일 보내기</button>
								</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<%
	
			}
	
	%>
	
	</section>
	<ul class="pagination justify-content-center mt-3">
		<li class="page-item">
	<%
	if(pageNumber <= 0 ) {
	%>
	
	<a class="page-link disabled">이전</a>
	
	<%
	} else { 
	%>
	
	<a class="page-link" href="./koreanContent.jsp?lectureDivide=<%= URLEncoder.encode(lectureDivide,"UTF-8")%>&searchType=
	<%= URLEncoder.encode(searchType, "UTF-8")%>&search=<%=URLEncoder.encode(search,"UTF-8")%>&pageNumber=
	<%= pageNumber-1 %>">이전</a>
	
	<%
	}
	%>
		
		</li>
		<li>
	<%
	if(bookList.size() < 6 ) { // 강의평가 개수 (페이지 내)
	%>
	
	<a class="page-link disabled">다음</a>
	
	<%
	} else { 
	%>
	
	<a class="page-link" href="./koreanContent.jsp?lectureDivide=<%= URLEncoder.encode(lectureDivide,"UTF-8")%>&searchType=
	<%= URLEncoder.encode(searchType, "UTF-8")%>&search=<%=URLEncoder.encode(search,"UTF-8")%>&pageNumber=
	<%= pageNumber+1 %>">다음</a>
	
	<%
	}
	%>
		
		</li>		
	</ul>

	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">서적 등록</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>				
				<div class="modal-body">
					<form action="./bookRegisterAction.jsp" method="post">
						<div class="form-row"> 
							<div class="form-group col-sm-6">
								<label>책 이름</label>
								<input type="text" name="bookName" class="form-control" maxlength="20">							
							</div>
							<div class="form-group col-sm-6">
								<label>출판사</label>
								<input type="text" name="bookPublisher" class="form-control" maxlength="20">							
							</div>
							<div class="form-group col-sm-12">
								<label>책 가격 (원 단위 자동 입력)</label>
								<input type="text" name="bookPrice" class="form-control" maxlength="30">
							</div>
						</div>
						
							<div class="form-row"> 
								<div class="form-group col-sm-3">
									<label>수강 학년</label>
									<select name="lectureYear" class="form-control">
										<option value="1" selected>1학년</option>
										<option value="2">2학년</option>
										<option value="3">3학년</option>
										<option value="4">4학년</option>
									</select>
								</div>
								<div class="form-group col-sm-3">
									<label>수강 학기</label>
									<select name="semesterDivide" class="form-control">
										<option value="1학기" selected> 1학기 </option>
										<option value="여름학기"> 여름학기 </option>
										<option value="2학기"> 2학기 </option>
										<option value="겨울학기"> 겨울학기 </option>
									</select>
								</div>
								<div class="form-group col-sm-3">
									<label>강의 구분</label>
									<select name="lectureDivide" class="form-control">
										<option value="전공" selected> 전공 </option>
										<option value="교양" > 교양 </option>
										<option value="기타" > 기타 </option>
									</select>
								</div>		
								<div class="form-group col-sm-3">
									<label>단과 대학</label>
									<select name="collegeDivide" class="form-control">
										<option value="공과대학" selected> 공과대학 </option>
										<option value="인문대학" > 인문대학 </option>
										<option value="신학대학" > 신학대학 </option>
										<option value="예술대학" > 예술대학 </option>
									</select>
								</div>							
							</div>
							<div class="form-row"> 
							<div class="form-group col-sm-12">
								<label>과목 이름</label>
								<input type="text" name="lectureName" class="form-control" maxlength="20">							
							</div>
						</div>
							
							<div class="form-group">
								<label>내용</label>
								<textarea name="bookContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>
							</div>
							<div class="form-row">
								<div class="form-group col-sm-3">
									<label>종합</label>
									<select name="totalScore" class="form-control">
										<option value="A" selected> A </option>
										<option value="B"> B </option>
										<option value="C"> C </option>
										<option value="D"> D </option>
										<option value="F"> F </option>
									</select>
								</div>
								
								<div class="form-group col-sm-3">
									<label>책 상태</label>
									<select name="bookStat" class="form-control">
										<option value="A" selected> A </option>
										<option value="B"> B </option>
										<option value="C"> C </option>
										<option value="D"> D </option>
										<option value="F"> F </option>
									</select>
								</div>
								
								<div class="form-group col-sm-3">
									<label>필기 상태</label>
									<select name="writingStat" class="form-control">
										<option value="A" selected> A </option>
										<option value="B"> B </option>
										<option value="C"> C </option>
										<option value="D"> D </option>
										<option value="F"> F </option>
									</select>
								</div>
								
								<div class="form-group col-sm-3">
									<label>청결도</label>
									<select name="cleanStat" class="form-control">
										<option value="A" selected> A </option>
										<option value="B"> B </option>
										<option value="C"> C </option>
										<option value="D"> D </option>
										<option value="F"> F </option>
									</select>
								</div>
							</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
									<button type="submit" class="btn btn-primary">등록하기</button>
								</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
		<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">신고하기</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>				
				<div class="modal-body">
					<form action="./reportAction.jsp" method="post">
							<div class="form-group">
								<label>신고 제목</label>
								<input type="text" name="reportTitle" class="form-control" maxlength="30">
							</div>
							<div class="form-group">
								<label>신고 내용</label>
								<textarea name="reportContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
								<button type="submit" class="btn btn-danger">신고하기</button>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	
		<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">신고하기</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>				
				<div class="modal-body">
					<form action="./reportAction.jsp" method="post">
							<div class="form-group">
								<label>신고 제목</label>
								<input type="text" name="reportTitle" class="form-control" maxlength="30">
							</div>
							<div class="form-group">
								<label>신고 내용</label>
								<textarea name="reportContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
								<button type="submit" class="btn btn-danger">신고하기</button>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
	 	Copyright &copy; 2019 성결북 박수빈 윤원석 조동영 All Rights Reserved
	</footer>
	


	<!--  제이쿼리 자바스크립트 추가하기 -->
	<script src="./js/jquery.min.js"></script>
	<script src="./js/popper.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>