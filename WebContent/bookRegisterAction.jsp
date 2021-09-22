<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="book.BookDTO" %>
<%@ page import="book.BookDAO" %>
<%@ page import="util.SHA256"%>
<%@ page import="java.io.PrintWriter"%> <!-- 특정 스크립트 구문 출력하고자 할때 사용 -->

<%
	request.setCharacterEncoding("UTF-8");
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
	
	String bookName = null;
	String bookPublisher = null;
	int lectureYear = 0;	
	
	String semesterDivide= null;
	String lectureDivide= null;
	String lectureName= null;
	String bookPrice= null;
	String bookContent= null;
	
	String totalScore= null;
	String bookStat= null;
	String writingStat= null;
	String cleanStat= null;
	
	String collegeDivide = null;

	
	if(request.getParameter("bookName") != null) {
		bookName = request.getParameter("bookName");
	}
	
	if(request.getParameter("bookPublisher") != null) {
		bookPublisher = request.getParameter("bookPublisher");
	}
	
	if(request.getParameter("lectureYear") != null) {
		try{
			lectureYear = Integer.parseInt(request.getParameter("lectureYear"));
		} catch (Exception e) {
			System.out.println("강의 연도 데이터 오류");
		}
	}
	
	if(request.getParameter("semesterDivide") != null) {
		semesterDivide = request.getParameter("semesterDivide");
	}
	
	if(request.getParameter("lectureDivide") != null) {
		lectureDivide = request.getParameter("lectureDivide");
	}
	
	if(request.getParameter("lectureName") != null) {
		lectureName = request.getParameter("lectureName");
	}
	
	if(request.getParameter("bookPrice") != null) {
		bookPrice = request.getParameter("bookPrice");
	}
	
	if(request.getParameter("bookContent") != null) {
		bookContent = request.getParameter("bookContent");
	}
	
	if(request.getParameter("totalScore") != null) {
		totalScore = request.getParameter("totalScore");
	}
	
	if(request.getParameter("bookStat") != null) {
		bookStat = request.getParameter("bookStat");
	}
	
	if(request.getParameter("writingStat") != null) {
		writingStat = request.getParameter("writingStat");
	}
	
	if(request.getParameter("cleanStat") != null) {
		cleanStat = request.getParameter("cleanStat");
	}
	
	if(request.getParameter("collegeDivide") != null) {
		collegeDivide = request.getParameter("collegeDivide");
	}

	
	if(bookName == null || bookPublisher == null || lectureYear == 0 || semesterDivide == null || lectureDivide == null || lectureName == null || 
			bookPrice.equals("") || bookContent.equals("")|| totalScore == null || bookStat == null || writingStat == null || writingStat == null || cleanStat == null || collegeDivide == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력되지 않은 사항이 있습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	BookDAO bookDAO = new BookDAO();
	int result = bookDAO.write(new BookDTO(0,userID,bookName,bookPublisher,lectureYear,semesterDivide,lectureDivide,lectureName,bookPrice,bookContent,totalScore,bookStat,writingStat,cleanStat,0,collegeDivide,"판매중"));
	if(result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('책 판매글 등록 실패.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	} else {
		session.setAttribute("userID", userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href=document.referrer;");
		script.println("</script>");
		script.close();
		return;		
	}		
%>