<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="java.io.PrintWriter"%> <!-- 특정 스크립트 구문 출력하고자 할때 사용 -->

<%
	request.setCharacterEncoding("UTF-8");
	String userID = null;
	String userPassword = null;

	if(request.getParameter("userID") != null) {
		userID = request.getParameter("userID");
	}

	if(request.getParameter("userPassword") != null) {
		userPassword = request.getParameter("userPassword");
	}

	if(userID==null || userPassword==null ) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력되지 않은 사항이 있습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}

	UserDAO userDAO = new UserDAO();
	int result = userDAO.login(userID, userPassword);
	if(result == 1) {
		session.setAttribute("userID", userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
		return;
	} else if (result == 0) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 일치하지 않습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;		
	} else if (result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('존재하지 않는 아이디입니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;		
	} else if (result == -2) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터 베이스에서 오류가 발생하였습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;		
	}




%>