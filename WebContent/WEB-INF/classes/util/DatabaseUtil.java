package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	
	public static Connection getConnection() {
		
		try {
			String dbURL = "jdbc:mysql://cdy235.iptime.org:3306/sungkyulBook?serverTimezone=UTC";
			String dbID = "sku_manager";
			String dbPassword = "h0t$ix";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
