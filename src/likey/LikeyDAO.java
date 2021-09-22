package likey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import book.BookDTO;
import util.DatabaseUtil;

public class LikeyDAO {
	
	public int unlike(String userID, String evaluationID) {
		String SQL = "DELETE FROM likey WHERE userID = ? AND evaluationID = ?" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, evaluationID);
			return pstmt.executeUpdate(); // insert, update, delete µî
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return -1;
	}
	

	
	public int like(String userID, String evaluationID, String userIP) {
		String SQL = "INSERT INTO LIKEY VALUES (?,?,?)" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, evaluationID);
			pstmt.setString(3, userIP);
			return pstmt.executeUpdate(); // insert, update, delete µî
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return -1;
	}
	
	
}
