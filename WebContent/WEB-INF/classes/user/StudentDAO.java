package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import user.UserDTO;

import util.DatabaseUtil;

public class StudentDAO {
	
	public int join (StudentDTO student) { // 정보를 받아 가입 실행
		String SQL = "SELECT * FROM studentInfo WHERE studentNumber=? AND studentName=?" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, student.studentNumber);
			pstmt.setString(2, student.studentName);
			rs = pstmt.executeQuery(); // 데이터 조회시 사용
			
			if(rs.next()) {
				return 1;
			} 
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return -2;			}
	

	

}
