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
	
	request.setCharacterEncoding("UTF-8");
	String purchaseTitle = null;
	String purchaseContent = null;
	String callNumber = null;
	String kakaoID = null;
	if(request.getParameter("purchaseTitle") != null) {
		purchaseTitle = request.getParameter("purchaseTitle");
	}
	if(request.getParameter("purchaseContent") != null) {
		purchaseContent = request.getParameter("purchaseContent");
	}
	if(request.getParameter("callNumber") != null) {
		callNumber = request.getParameter("callNumber");
	}
	if(request.getParameter("kakaoID") != null) {
		kakaoID = request.getParameter("kakaoID");
	}
	if(purchaseTitle == null || purchaseContent == null || callNumber == null){
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('입력되지 않은 사항이 있습니다.');");
	script.println("history.back()");
	script.println("</script>");
	script.close();
	return;
	}
	
	String host = "http://cdy235.iptime.org:8080/sungkyulBook/";
	String from = "sungkyulbook@gmail.com";
	String to = userDAO.getSellerEmail(sellBookID);
	String subject = "성결북에서 회원님의 게시글에 대한 구매요청 메일입니다.";
	String content = "구매자 : " + userID + "<br>구매를 원하는 서적 이름 : " + userDAO.getBookName(sellBookID) + "<br>제목 : " + purchaseTitle + "<br>내용 : " + purchaseContent + "<br>연락처 : " + callNumber + "<br>카카오톡 아이디 : " + kakaoID;
	Properties p = new Properties();
	p.put("mail.smtp.user", from); // 구글 SMTP 서버를 이용하기 위한 코드
	p.put("mail.smtp.host", "smtp.googlemail.com"); // 구글 SMTP 서버를 이용하기 위한 코드
	p.put("mail.smtp.port", "465"); // 구글 SMTP 서버를 이용하기 위한 코드
	p.put("mail.smtp.starttls.enable", "true"); // 구글 SMTP 서버를 이용하기 위한 코드
	p.put("mail.smtp.auth", "true"); // 구글 SMTP 서버를 이용하기 위한 코드
	p.put("mail.smtp.debug", "true"); // 구글 SMTP 서버를 이용하기 위한 코드
	p.put("mail.smtp.socketFactory.port", "465"); // 구글 SMTP 서버를 이용하기 위한 코드
	p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // 구글 SMTP 서버를 이용하기 위한 코드
	p.put("mail.smtp.socketFactory.fallback", "false"); // 구글 SMTP 서버를 이용하기 위한 코드

	try {
		Authenticator auth = new Gmail();
		Session ses = Session.getInstance(p,auth);
		ses.setDebug(true);
		MimeMessage msg = new MimeMessage(ses);
		msg.setSubject(subject);
		Address fromAddr = new InternetAddress(from);
		msg.setFrom(fromAddr);
		Address toAddr = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO, toAddr);
		msg.setContent(content,"text/html;charset=UTF-8");
		Transport.send(msg);
	} catch (Exception e){
		e.printStackTrace();
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('오류가 발생했습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	bookDAO.chgStatus(sellBookID);
	bookDAO.chgLikeStatus(sellBookID);
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('구매예약이 완료되었습니다.');");
	script.println("location.href='index.jsp'");
	script.println("</script>");
	script.close();
%>