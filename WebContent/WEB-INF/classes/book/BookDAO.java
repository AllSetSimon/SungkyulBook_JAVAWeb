package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DatabaseUtil;

public class BookDAO {
	
	public int write (BookDTO bookDTO) { 
		String SQL = "INSERT INTO book VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,0,?)" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bookDTO.getUserID());
			pstmt.setString(2, bookDTO.getBookName());
			pstmt.setString(3, bookDTO.getBookPublisher());
			pstmt.setInt(4, bookDTO.getLectureYear());
			pstmt.setString(5, bookDTO.getSemesterDivide());
			pstmt.setString(6, bookDTO.getLectureDivide());
			pstmt.setString(7, bookDTO.getLectureName());
			pstmt.setString(8, bookDTO.getBookPrice());
			pstmt.setString(9, bookDTO.getBookContent());
			pstmt.setString(10, bookDTO.getTotalScore());
			pstmt.setString(11, bookDTO.getBookStat());
			pstmt.setString(12, bookDTO.getWritingStat());
			pstmt.setString(13, bookDTO.getCleanStat());
			pstmt.setString(14, bookDTO.getCollegeDivide());
			return pstmt.executeUpdate(); 
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return -1;		
	}
	
	//�������� å �Խ��� ����Ʈ
	public ArrayList<BookDTO> getEngineerList (String searchType, String search, int pageNumber) {
		

		ArrayList<BookDTO> bookList = null;
		String SQL = "" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			if(searchType.equals("�ֽż�")) {
				SQL = "SELECT * FROM book WHERE collegeDivide = '��������' AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY sellBookID DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			} else if (searchType.equals("��õ��")) {
				SQL = "SELECT * FROM book WHERE collegeDivide = '��������' AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY likeCount DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			}
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"  + search + "%");
			rs = pstmt.executeQuery(); // ������ ��ȸ�� ���
			bookList = new ArrayList<BookDTO>();
			while(rs.next()) {
				BookDTO book = new BookDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getString(14),
						rs.getInt(15),
						rs.getString(16)					
				);
				bookList.add(book);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return bookList;	
	}
	
	//�ι����� å ����Ʈ
public ArrayList<BookDTO> getKoreanList (String searchType, String search, int pageNumber) {
		

	ArrayList<BookDTO> bookList = null;
	String SQL = "" ;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		if(searchType.equals("�ֽż�")) {
			SQL = "SELECT * FROM book WHERE collegeDivide = '�ι�����' AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
					"? ORDER BY sellBookID DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
		} else if (searchType.equals("��õ��")) {
			SQL = "SELECT * FROM book WHERE collegeDivide = '�ι�����' AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
					"? ORDER BY likeCount DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
		}
		conn = DatabaseUtil.getConnection();			
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, "%"  + search + "%");
		rs = pstmt.executeQuery(); // ������ ��ȸ�� ���
		bookList = new ArrayList<BookDTO>();
		while(rs.next()) {
			BookDTO book = new BookDTO(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11),
					rs.getString(12),
					rs.getString(13),
					rs.getString(14),
					rs.getInt(15),
					rs.getString(16)					
			);
			bookList.add(book);
		}			
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
		try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
		try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
		try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
	}
	return bookList;	
	}

	// ���� ���� å �Խ���
	public ArrayList<BookDTO> getGodList (String searchType, String search, int pageNumber) {

		ArrayList<BookDTO> bookList = null;
		String SQL = "" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			if(searchType.equals("�ֽż�")) {
				SQL = "SELECT * FROM book WHERE collegeDivide = '���д���' AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY sellBookID DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			} else if (searchType.equals("��õ��")) {
				SQL = "SELECT * FROM book WHERE collegeDivide = '���д���' AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY likeCount DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			}
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"  + search + "%");
			rs = pstmt.executeQuery(); // ������ ��ȸ�� ���
			bookList = new ArrayList<BookDTO>();
			while(rs.next()) {
				BookDTO book = new BookDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getString(14),
						rs.getInt(15),
						rs.getString(16)					
				);
				bookList.add(book);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return bookList;	
	}
	
	//�������� å ����Ʈ
	public ArrayList<BookDTO> getArtList(String searchType, String search, int pageNumber) {
		
		ArrayList<BookDTO> bookList = null;
		String SQL = "" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			if(searchType.equals("�ֽż�")) {
				SQL = "SELECT * FROM book WHERE collegeDivide = '��������' AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY sellBookID DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			} else if (searchType.equals("��õ��")) {
				SQL = "SELECT * FROM book WHERE collegeDivide = '��������' AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY likeCount DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			}
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"  + search + "%");
			rs = pstmt.executeQuery(); // ������ ��ȸ�� ���
			bookList = new ArrayList<BookDTO>();
			while(rs.next()) {
				BookDTO book = new BookDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getString(14),
						rs.getInt(15),
						rs.getString(16)					
				);
				bookList.add(book);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return bookList;	
		
		/*ArrayList<BookDTO> bookList = null;
		String SQL = "SELECT * FROM book WHERE collegeDivide = '��������'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		conn = DatabaseUtil.getConnection();			
		pstmt = conn.prepareStatement(SQL);
		rs = pstmt.executeQuery(); // ������ ��ȸ�� ���
		bookList = new ArrayList<BookDTO>();
		while(rs.next()) {
			BookDTO book = new BookDTO(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11),
					rs.getString(12),
					rs.getString(13),
					rs.getString(14),
					rs.getInt(15),
					rs.getString(16)
			);
			bookList.add(book);
		}			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return bookList;*/
	}
	
	
	
	
	//���� ���� ����Ʈ �ҷ����� �޼���
	public ArrayList<BookDTO> getMyList (String userID) {
		
		ArrayList<BookDTO> bookList = null;
		String SQL = "SELECT * FROM book WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		conn = DatabaseUtil.getConnection();			
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, userID);
		rs = pstmt.executeQuery(); // ������ ��ȸ�� ���
		bookList = new ArrayList<BookDTO>();
		while(rs.next()) {
			BookDTO book = new BookDTO(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11),
					rs.getString(12),
					rs.getString(13),
					rs.getString(14),
					rs.getInt(15),
					rs.getString(16)
			);
			bookList.add(book);
		}			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return bookList;	
		
		
		/*try {
			if(searchType.equals("�ֽż�")) {
				SQL = "SELECT * FROM book WHERE lectureDivide LIKE ? AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY sellBookID DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			} else if (searchType.equals("��õ��")) {
				SQL = "SELECT * FROM book WHERE lectureDivide LIKE ? AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY likeCount DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			}
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"  + lectureDivide + "%");
			pstmt.setString(2, "%"  + search + "%");
			rs = pstmt.executeQuery(); // ������ ��ȸ�� ���
			bookList = new ArrayList<BookDTO>();
			while(rs.next()) {
				BookDTO book = new BookDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getString(14),
						rs.getInt(15)						
				);
				bookList.add(book);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		} */
	}
	
	
	public ArrayList<BookDTO> getList (String lectureDivide, String searchType, String search, int pageNumber) {
		if(lectureDivide.equals("��ü")) {
			lectureDivide = "";
		}
		ArrayList<BookDTO> bookList = null;
		String SQL = "" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			if(searchType.equals("�ֽż�")) {
				SQL = "SELECT * FROM book WHERE lectureDivide LIKE ? AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY sellBookID DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			} else if (searchType.equals("��õ��")) {
				SQL = "SELECT * FROM book WHERE lectureDivide LIKE ? AND CONCAT(bookName, bookPublisher, bookPrice, bookContent) LIKE " + 
						"? ORDER BY likeCount DESC LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			}
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"  + lectureDivide + "%");
			pstmt.setString(2, "%"  + search + "%");
			rs = pstmt.executeQuery(); // ������ ��ȸ�� ���
			bookList = new ArrayList<BookDTO>();
			while(rs.next()) {
				BookDTO book = new BookDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getString(14),
						rs.getInt(15),
						rs.getString(16)					
				);
				bookList.add(book);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return bookList;	
	}
	
	public int like(String sellBookID) {
		String SQL = "UPDATE book SET likeCount = likeCount+1 WHERE sellBookID = ?" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(sellBookID));
			return pstmt.executeUpdate();		
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return -1; //DB ����	
	}
	
	public int delete(String sellBookID) {
		String SQL = "DELETE FROM book WHERE sellBookID = ?" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(sellBookID));
			return pstmt.executeUpdate();		
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return -1; //DB ����	
	}
	
	public String getUserID(String sellBookID) {		
		String SQL = "SELECT userID FROM book WHERE sellBookID = ?" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(sellBookID));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if(conn!=null) conn.close();} catch (Exception e) { e.printStackTrace();}
			try{if(pstmt!=null) pstmt.close();} catch (Exception e) { e.printStackTrace();}
			try{if(rs!=null) rs.close();} catch (Exception e) { e.printStackTrace();}
		}
		return null; //��������x		
	}
	
	


}
