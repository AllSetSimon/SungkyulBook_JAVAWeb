<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<%@ page import="book.BookDAO"%>
<%@ page import="likey.LikeyDTO"%>
<%@ page import="java.io.PrintWriter"%> <!-- 특정 스크립트 구문 출력하고자 할때 사용 -->

<%
	String userID = null;

	if(session.getAttribute("userID")!=null){
		userID = (String) session.getAttribute("userID");
	}
	
	if(userID==null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주시기 바랍니다.');");
		script.println("location.href = 'userLogin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
	request.setCharacterEncoding("UTF-8");
	String sellBookID = null;
	if(request.getParameter("sellBookID") != null) {
		sellBookID = request.getParameter("sellBookID");
	}
	
	BookDAO bookDAO = new BookDAO();
	if(userID.equals(bookDAO.getUserID(sellBookID))) {
		int result = new BookDAO().delete(sellBookID);
		if(result == 1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('삭제 완료되었습니다.');");
			script.println("location.href=document.referrer");
			script.println("</script>");
			script.close();
			return;		
		} else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생하였습니다.');");
			script.println("history.back()");
			script.println("</script>");
			script.close();
			return;
		} 
	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('자신이 쓴 글만 삭제 가능합니다.');");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;
	}
%>