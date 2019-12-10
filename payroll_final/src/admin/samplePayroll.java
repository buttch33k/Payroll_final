package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class samplePayroll {
	static	Connection conn = null;
	static PreparedStatement pst = null;
	static PreparedStatement pst1 = null;
	static CallableStatement cs = null;
	static ResultSet rs = null;
	static ResultSet rs1 = null;
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("enter rate");
		
		
		try {
			double grossincome_PHIL = in.nextInt();
			
			double taxrate_PHIL = 0;
			String taxTable_PHIL[][] = new String[32][6];
			int cPHIL = 0;
			int rPHIL = 0;
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
			/* select table */			
			 cs = conn.prepareCall("{call allTax_PHIL()}");			 
			 cs.execute();			 
			 rs = cs.executeQuery();			 		 
			 while(rs.next()) { 

				 for( rPHIL = 0; rPHIL <= 4;rPHIL++) {
					 
					 if(rPHIL == 0) {
						 taxTable_PHIL[cPHIL][rPHIL] = rs.getString(1);
					 }else if(rPHIL == 1) {
						 taxTable_PHIL[cPHIL][rPHIL] = rs.getString(2);
					 }else if(rPHIL == 2) {
						 taxTable_PHIL[cPHIL][rPHIL] = rs.getString(3);
					 }else if(rPHIL == 3) {
						 taxTable_PHIL[cPHIL][rPHIL] = rs.getString(4);
					 }else if(rPHIL == 4) {
						 taxTable_PHIL[cPHIL][rPHIL] = rs.getString(5);
					 }
				 }
				 cPHIL = cPHIL+ 1;

			 }	

			 System.out.println(taxTable_PHIL[0][0]+" "+taxTable_PHIL[0][1]+" "+taxTable_PHIL[0][4]);
			 System.out.println(taxTable_PHIL[1][0]+" "+taxTable_PHIL[1][1]+" "+taxTable_PHIL[1][4]);
			 System.out.println(taxTable_PHIL[2][0]+" "+taxTable_PHIL[2][1]+" "+taxTable_PHIL[2][4]);
			 System.out.println(taxTable_PHIL[3][0]+" "+taxTable_PHIL[3][1]+" "+taxTable_PHIL[3][4]);
			 System.out.println(taxTable_PHIL[4][0]+" "+taxTable_PHIL[4][1]+" "+taxTable_PHIL[4][4]);
			 System.out.println(taxTable_PHIL[5][0]+" "+taxTable_PHIL[5][1]+" "+taxTable_PHIL[5][4]);
			 System.out.println(taxTable_PHIL[6][0]+" "+taxTable_PHIL[6][1]+" "+taxTable_PHIL[6][4]);
			 System.out.println(taxTable_PHIL[7][0]+" "+taxTable_PHIL[7][1]+" "+taxTable_PHIL[7][4]);
			 System.out.println(taxTable_PHIL[8][0]+" "+taxTable_PHIL[8][1]+" "+taxTable_PHIL[8][5]);
			 System.out.println(taxTable_PHIL[9][0]+" "+taxTable_PHIL[9][1]+" "+taxTable_PHIL[9][5]);
			 System.out.println(taxTable_PHIL[10][0]+" "+taxTable_PHIL[10][1]+" "+taxTable_PHIL[10][4]);
			 System.out.println(taxTable_PHIL[11][0]+" "+taxTable_PHIL[11][1]+" "+taxTable_PHIL[11][4]);
			 System.out.println(taxTable_PHIL[12][0]+" "+taxTable_PHIL[12][1]+" "+taxTable_PHIL[12][4]);
			 System.out.println(taxTable_PHIL[13][0]+" "+taxTable_PHIL[13][1]+" "+taxTable_PHIL[13][4]);
			 System.out.println(taxTable_PHIL[14][0]+" "+taxTable_PHIL[14][1]+" "+taxTable_PHIL[14][4]);
			 System.out.println(taxTable_PHIL[15][0]+" "+taxTable_PHIL[15][1]+" "+taxTable_PHIL[15][4]);
			 System.out.println(taxTable_PHIL[16][0]+" "+taxTable_PHIL[16][1]+" "+taxTable_PHIL[16][4]);
			 System.out.println(taxTable_PHIL[17][0]+" "+taxTable_PHIL[17][1]+" "+taxTable_PHIL[17][4]);
			 System.out.println(taxTable_PHIL[18][0]+" "+taxTable_PHIL[18][1]+" "+taxTable_PHIL[18][4]);
			 System.out.println(taxTable_PHIL[19][0]+" "+taxTable_PHIL[19][1]+" "+taxTable_PHIL[19][4]);
			 System.out.println(taxTable_PHIL[20][0]+" "+taxTable_PHIL[20][1]+" "+taxTable_PHIL[20][4]);
			 System.out.println(taxTable_PHIL[21][0]+" "+taxTable_PHIL[21][1]+" "+taxTable_PHIL[21][4]);
			 System.out.println(taxTable_PHIL[22][0]+" "+taxTable_PHIL[22][1]+" "+taxTable_PHIL[22][4]);
			 System.out.println(taxTable_PHIL[23][0]+" "+taxTable_PHIL[23][1]+" "+taxTable_PHIL[23][4]);
			 System.out.println(taxTable_PHIL[24][0]+" "+taxTable_PHIL[24][1]+" "+taxTable_PHIL[24][4]);
			 System.out.println(taxTable_PHIL[25][0]+" "+taxTable_PHIL[25][1]+" "+taxTable_PHIL[25][4]);
			 System.out.println(taxTable_PHIL[26][0]+" "+taxTable_PHIL[26][1]+" "+taxTable_PHIL[26][4]);
			 System.out.println(taxTable_PHIL[27][0]+" "+taxTable_PHIL[27][1]+" "+taxTable_PHIL[27][4]);
			 System.out.println(taxTable_PHIL[28][0]+" "+taxTable_PHIL[28][1]+" "+taxTable_PHIL[28][4]);
			 System.out.println(taxTable_PHIL[29][0]+" "+taxTable_PHIL[29][1]+" "+taxTable_PHIL[29][4]);
			 System.out.println(taxTable_PHIL[30][0]+" "+taxTable_PHIL[30][1]+" "+taxTable_PHIL[30][4]);
			 System.out.println(taxTable_PHIL[31][0]+" "+taxTable_PHIL[31][1]+" "+taxTable_PHIL[31][4]);
			 
			 
			 if (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[0][1])) {
		            taxrate_PHIL = Double.parseDouble(taxTable_PHIL[0][4]);
		     }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[1][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[1][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[1][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[2][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[2][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[2][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[3][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[3][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[3][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[4][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[4][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[4][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[5][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[5][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[5][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[6][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[6][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[6][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[7][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[7][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[7][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[8][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[8][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[8][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[9][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[9][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[9][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[10][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[10][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[10][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[11][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[11][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[11][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[12][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[12][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[12][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[13][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[13][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[13][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[14][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[14][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[14][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[15][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[15][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[15][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[16][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[16][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[16][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[17][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[17][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[17][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[18][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[18][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[18][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[19][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[19][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[19][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[20][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[20][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[20][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[21][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[21][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[21][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[22][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[22][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[22][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[23][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[23][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[23][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[24][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[24][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[24][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[25][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[25][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[25][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[26][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[26][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[26][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[27][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[27][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[27][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[28][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[28][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[28][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[24][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[24][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[24][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[25][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[25][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[25][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[26][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[26][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[26][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[27][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[27][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[27][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[28][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[28][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[28][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[29][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[29][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[29][4]);
			 }
			 else if((grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[30][0])) && (grossincome_PHIL <= Double.parseDouble(taxTable_PHIL[30][1]))){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[30][4]);
			 }
			 else if(grossincome_PHIL >= Double.parseDouble(taxTable_PHIL[31][1])){
				 taxrate_PHIL = Double.parseDouble(taxTable_PHIL[31][4]);
			 
			 }
			 System.out.println("");
			 System.out.println(taxrate_PHIL);

			  
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
