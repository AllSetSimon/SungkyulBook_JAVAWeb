<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "javax.mail.*" %>
<%@ page import= "java.util.Properties" %>
<%@ page import="user.UserDAO"%>
<%@ page import="book.BookDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="util.Gmail"%>
<%@ page import="java.io.PrintWriter"%> <!-- 특정 스크립트 구문 출력하고자 할때 사용 -->

<%
UserDAO userDAO = new UserDAO();
BookDAO bookDAO = new BookDAO();
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
	
	bookDAO.chgStatusCancel(sellBookID);
	bookDAO.chgLikeStatusPurchase(sellBookID);
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('거래가 철회되었습니다');");
	script.println("location.href= document.referrer");
	script.println("</script>");
	script.close();
%>