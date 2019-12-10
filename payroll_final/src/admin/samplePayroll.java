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
		String taxTable_sss[][] = new String[37][6];
		int cSSS = 0;
		int rSSS = 0;
		System.out.println("enter rate");
		double grossincome_sss = in.nextInt();
		
		double taxrate_sss = 0;
		
		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
			/* select table */			
			 cs = conn.prepareCall("{call allTax_sss()}");			 
			 cs.execute();			 
			 rs = cs.executeQuery();			 		 
			 while(rs.next()) { 

				 for( rSSS = 0; rSSS <= 5;rSSS++) {
					 
					 if(rSSS == 0) {
						 taxTable_sss[cSSS][rSSS] = rs.getString(1);
					 }else if(rSSS == 1) {
						 taxTable_sss[cSSS][rSSS] = rs.getString(2);
					 }else if(rSSS == 2) {
						 taxTable_sss[cSSS][rSSS] = rs.getString(3);
					 }else if(rSSS == 3) {
						 taxTable_sss[cSSS][rSSS] = rs.getString(4);
					 }else if(rSSS == 4) {
						 taxTable_sss[cSSS][rSSS] = rs.getString(5);
					 }else if(rSSS == 5) {
						 taxTable_sss[cSSS][rSSS] = rs.getString(6);
					 } 
				 }
				 cSSS = cSSS+ 1;

			 }	

			 System.out.println(taxTable_sss[0][0]+" "+taxTable_sss[0][1]+" "+taxTable_sss[0][5]);
			 System.out.println(taxTable_sss[1][0]+" "+taxTable_sss[1][1]+" "+taxTable_sss[1][5]);
			 System.out.println(taxTable_sss[2][0]+" "+taxTable_sss[2][1]+" "+taxTable_sss[2][5]);
			 System.out.println(taxTable_sss[3][0]+" "+taxTable_sss[3][1]+" "+taxTable_sss[3][5]);
			 System.out.println(taxTable_sss[4][0]+" "+taxTable_sss[4][1]+" "+taxTable_sss[4][5]);
			 System.out.println(taxTable_sss[5][0]+" "+taxTable_sss[5][1]+" "+taxTable_sss[5][5]);
			 System.out.println(taxTable_sss[6][0]+" "+taxTable_sss[6][1]+" "+taxTable_sss[6][5]);
			 System.out.println(taxTable_sss[7][0]+" "+taxTable_sss[7][1]+" "+taxTable_sss[7][5]);
			 System.out.println(taxTable_sss[8][0]+" "+taxTable_sss[8][1]+" "+taxTable_sss[8][5]);
			 System.out.println(taxTable_sss[9][0]+" "+taxTable_sss[9][1]+" "+taxTable_sss[9][5]);
			 System.out.println(taxTable_sss[10][0]+" "+taxTable_sss[10][1]+" "+taxTable_sss[10][5]);
			 System.out.println(taxTable_sss[11][0]+" "+taxTable_sss[11][1]+" "+taxTable_sss[11][5]);
			 System.out.println(taxTable_sss[12][0]+" "+taxTable_sss[12][1]+" "+taxTable_sss[12][5]);
			 System.out.println(taxTable_sss[13][0]+" "+taxTable_sss[13][1]+" "+taxTable_sss[13][5]);
			 System.out.println(taxTable_sss[14][0]+" "+taxTable_sss[14][1]+" "+taxTable_sss[14][5]);
			 System.out.println(taxTable_sss[15][0]+" "+taxTable_sss[15][1]+" "+taxTable_sss[15][5]);
			 System.out.println(taxTable_sss[16][0]+" "+taxTable_sss[16][1]+" "+taxTable_sss[16][5]);
			 System.out.println(taxTable_sss[17][0]+" "+taxTable_sss[17][1]+" "+taxTable_sss[17][5]);
			 System.out.println(taxTable_sss[18][0]+" "+taxTable_sss[18][1]+" "+taxTable_sss[18][5]);
			 System.out.println(taxTable_sss[19][0]+" "+taxTable_sss[19][1]+" "+taxTable_sss[19][5]);
			 System.out.println(taxTable_sss[20][0]+" "+taxTable_sss[20][1]+" "+taxTable_sss[20][5]);
			 System.out.println(taxTable_sss[21][0]+" "+taxTable_sss[21][1]+" "+taxTable_sss[21][5]);
			 System.out.println(taxTable_sss[22][0]+" "+taxTable_sss[22][1]+" "+taxTable_sss[22][5]);
			 System.out.println(taxTable_sss[23][0]+" "+taxTable_sss[23][1]+" "+taxTable_sss[23][5]);
			 System.out.println(taxTable_sss[24][0]+" "+taxTable_sss[24][1]+" "+taxTable_sss[24][5]);
			 System.out.println(taxTable_sss[25][0]+" "+taxTable_sss[25][1]+" "+taxTable_sss[25][5]);
			 System.out.println(taxTable_sss[26][0]+" "+taxTable_sss[26][1]+" "+taxTable_sss[26][5]);
			 System.out.println(taxTable_sss[27][0]+" "+taxTable_sss[27][1]+" "+taxTable_sss[27][5]);
			 System.out.println(taxTable_sss[28][0]+" "+taxTable_sss[28][1]+" "+taxTable_sss[28][5]);
			 System.out.println(taxTable_sss[29][0]+" "+taxTable_sss[29][1]+" "+taxTable_sss[29][5]);
			 System.out.println(taxTable_sss[30][0]+" "+taxTable_sss[30][1]+" "+taxTable_sss[30][5]);
			 System.out.println(taxTable_sss[31][0]+" "+taxTable_sss[31][1]+" "+taxTable_sss[31][5]);
			 System.out.println(taxTable_sss[32][0]+" "+taxTable_sss[32][1]+" "+taxTable_sss[32][5]);
			 System.out.println(taxTable_sss[33][0]+" "+taxTable_sss[33][1]+" "+taxTable_sss[33][5]);
			 System.out.println(taxTable_sss[34][0]+" "+taxTable_sss[34][1]+" "+taxTable_sss[34][5]);
			 System.out.println(taxTable_sss[35][0]+" "+taxTable_sss[35][1]+" "+taxTable_sss[35][5]);
			 System.out.println(taxTable_sss[36][0]+" "+taxTable_sss[36][1]+" "+taxTable_sss[36][5]);
			 
			 if (grossincome_sss <= Integer.parseInt(taxTable_sss[0][1])) {
		            taxrate_sss = Integer.parseInt(taxTable_sss[0][5]);
		     }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[1][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[1][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[1][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[2][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[2][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[2][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[3][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[3][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[3][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[4][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[4][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[4][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[5][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[5][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[5][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[6][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[6][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[6][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[7][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[7][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[7][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[8][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[8][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[8][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[9][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[9][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[9][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[10][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[10][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[10][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[11][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[11][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[11][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[12][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[12][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[12][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[13][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[13][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[13][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[14][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[14][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[14][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[15][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[15][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[15][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[16][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[16][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[16][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[17][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[17][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[17][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[18][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[18][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[18][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[19][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[19][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[19][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[20][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[20][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[20][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[21][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[21][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[21][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[22][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[22][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[22][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[23][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[23][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[23][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[24][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[24][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[24][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[25][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[25][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[25][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[26][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[26][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[26][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[27][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[27][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[27][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[28][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[28][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[28][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[24][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[24][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[24][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[25][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[25][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[25][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[26][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[26][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[26][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[27][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[27][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[27][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[28][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[28][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[28][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[29][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[29][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[29][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[30][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[30][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[30][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[31][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[31][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[31][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[32][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[32][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[32][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[33][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[33][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[33][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[34][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[34][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[34][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[35][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[35][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[35][5]);
			 }
			 else if((grossincome_sss >= Integer.parseInt(taxTable_sss[36][0])) && (grossincome_sss <= Double.parseDouble(taxTable_sss[36][1]))){
				 taxrate_sss = Integer.parseInt(taxTable_sss[36][5]);
			 }
			 System.out.println("");
			 System.out.println(taxrate_sss);

			  
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
