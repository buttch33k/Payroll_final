package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectDB {
Connection conn;
	
	public static Connection doConnect(){
		
		String host = "jdbc:mysql://localhost:3306/payroll_final"; 
        String uName = "root";
        String uPass = "root";
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(host, uName, uPass);
		//	JOptionPane.showMessageDialog(null, "Connection SuccessFully");
			System.out.println("Connected");
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
			return null;
		} 
	}

}
