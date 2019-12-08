package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ConnectDB {
Connection conn;
static JLabel lblClock;
	
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
	public static void clock() {
		Thread clock = new Thread() {
			public void run() {

					try {
						for(;;) {
						Calendar calz = new GregorianCalendar();
						
						int second = calz.get(Calendar.SECOND);
						int minute = calz.get(Calendar.MINUTE);
						int hour = calz.get(Calendar.HOUR);
						
						lblClock.setText("Time: " + hour + ";"+ minute + ";" + second);
						sleep(1000);
						}
					} catch(InterruptedException e){
						e.printStackTrace();			
					}
				}
	};
		clock.start();
	}
	

}
