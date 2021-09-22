<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="user.StudentDTO"%>
<%@ page import="user.StudentDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="java.io.PrintWriter"%> <!-- 특정 스크립트 구문 출력하고자 할때 사용 -->

<%
	request.setCharacterEncoding("UTF-8");
	String userID = null;
	if(session.getAttribute("userID") != null) {
		userID = (String)session.getAttribute("userID");
	}

	if(userID != null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인이 된 상태입니다.');");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
	String userPassword = null;
	String userEmail = null;
	
	String studentNumber = null; // 학번 인증
	String studentName = null; // 학번 인증

	if(request.getParameter("userID") != null) {
		userID = request.getParameter("userID");
	}

	if(request.getParameter("userPassword") != null) {
		userPassword = request.getParameter("userPassword");
	}

	if(request.getParameter("userEmail") != null) {
		userEmail = request.getParameter("userEmail");
	}
	
	if(request.getParameter("studentNumber") != null) {
		studentNumber = request.getParameter("studentNumber");
	}
	
	if(request.getParameter("studentName") != null) {
		studentName = request.getParameter("studentName");
	}
	if(userID==null || userPassword==null || userEmail == null || studentNumber == null || studentName == null) { // 학번인증
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력되지 않은 사항이 있습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}

	UserDAO userDAO = new UserDAO();
	StudentDAO studentDAO = new StudentDAO();
	int studentresult = studentDAO.join(new StudentDTO(studentNumber,studentName));
	if(studentresult == 1) {
		int result = userDAO.join(new UserDTO(userID, userPassword, userEmail, SHA256.getSHA256(userEmail),false,studentNumber,studentName));
		if(result == -1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 존재하는 아이디입니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		} else {
			session.setAttribute("userID", userID);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href='emailSendAction.jsp'");
			script.println("</script>");
			script.close();
			return;		
		}		
	}  else if(studentresult == -2) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('해당하는 학번이 없습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;	
	}
%>