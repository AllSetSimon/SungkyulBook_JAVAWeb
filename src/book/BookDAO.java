package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DatabaseUtil;

public class BookDAO {
	
public ArrayList<BookDTO> addLikeList (String sellBookID, String userID) {
		

		ArrayList<BookDTO> bookList = null;
		String SQL = "SELECT * FROM book WHERE sellBookID = ?" ;
		String SQLinsert = "INSERT INTO likelist VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtadd = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmtadd = conn.prepareStatement(SQLinsert);
			pstmt.setInt(1, Integer.parseInt(sellBookID));
			rs = pstmt.executeQuery(); // ������ ��ȸ�� ���
			bookList = new ArrayList<BookDTO>();
			while(rs.next()) {
					pstmtadd.setInt(1, Integer.parseInt(sellBookID));
					pstmtadd.setString(2, rs.getString(2));
					pstmtadd.setString(3, rs.getString(3));
					pstmtadd.setString(4, rs.getString(4));
					pstmtadd.setInt(5, rs.getInt(5));
					pstmtadd.setString(6, rs.getString(6));
					pstmtadd.setString(7, rs.getString(7));
					pstmtadd.setString(8, rs.getString(8));
					pstmtadd.setString(9, rs.getString(9));
					pstmtadd.setString(10, rs.getString(10));
					pstmtadd.setString(11, rs.getString(11));
					pstmtadd.setString(12, rs.getString(12));
					pstmtadd.setString(13, rs.getString(13));
					pstmtadd.setString(14, rs.getString(14));
					pstmtadd.setInt(15, rs.getInt(15));
					pstmtadd.setString(16, rs.getString(16));
					pstmtadd.setString(17, userID);
					pstmtadd.setString(18, "�Ǹ���");
					pstmtadd.executeUpdate();
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

	public int chgStatus (String sellBookID) { 
		String SQL = "UPDATE book SET status = '�ŷ� ������' WHERE sellBookID = ?" ;
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
	
	public int chgLikeStatus (String sellBookID) { 
		String SQL = "UPDATE likelist SET status = IF (sellBookID = ? , '�ŷ� ������', '�Ǹ���') WHERE sellbookID = ?" ;
	/*	UPDATE TABLE SET A = IF (C = 'D', 'B', 'F') WHERE C = 'D'*/
	/*	Select Exists(Select * From tableName Where column = ?); */
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(sellBookID));
			pstmt.setInt(2, Integer.parseInt(sellBookID));
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
	
	public int chgLikeStatusPurchase (String sellBookID) { 
		String SQL = "UPDATE likelist SET status = IF (sellBookID = ? , '�Ǹ���', '�ŷ� ������')" ;
	/*	UPDATE TABLE SET A = IF (C = 'D', 'B', 'F') WHERE C = 'D'*/
	/*	Select Exists(Select * From tableName Where column = ?); */
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
	
	
	public int chgStatusCancel (String sellBookID) { 
		String SQL = "UPDATE book SET status = '�Ǹ���' WHERE sellBookID = ?" ;
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
	
	public int write (BookDTO bookDTO) { 
		String SQL = "INSERT INTO book VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,0,?,?)" ;
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
			pstmt.setString(15, bookDTO.getStatus());
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
						rs.getString(16),					
						rs.getString(17)					
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
					rs.getString(16),					
					rs.getString(17)					
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
						rs.getString(16),					
						rs.getString(17)					
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
						rs.getString(16),					
						rs.getString(17)				
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
	
public ArrayList<BookDTO> getMyLikeList (String userID) {
		
		ArrayList<BookDTO> bookList = null;
		String SQL = "SELECT * FROM likelist WHERE myID = ?";
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
					rs.getString(16),					
					rs.getString(18)
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
					rs.getString(16),					
					rs.getString(17)
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
						rs.getString(16),					
						rs.getString(17)				
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
	
	public int unlike(String sellBookID) {
		String SQL = "UPDATE book SET likeCount = likeCount-1 WHERE sellBookID = ?" ;
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
		String SQLlike = "DELETE FROM likelist WHERE sellBookID = ?" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtlike = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmtlike = conn.prepareStatement(SQLlike);
			pstmt.setInt(1, Integer.parseInt(sellBookID));
			pstmtlike.setInt(1, Integer.parseInt(sellBookID));
			pstmtlike.executeUpdate();	
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
	
	public int deleteLike(String userID, String sellBookID) {
		String SQL = "DELETE FROM likelist WHERE myID = ? AND sellBookID = ?" ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			pstmt.setString(2,sellBookID);
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
