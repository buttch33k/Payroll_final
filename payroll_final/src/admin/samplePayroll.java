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
		Scanner input = new Scanner( System.in);
		System.out.println("sample enter");
		System.out.println("enter gross");
		int grossincome = input.nextInt();
		double taxrate = 0;
		String taxTable[][] = new String[6][4];
		int i = 0;
		int j = 0;
		
		
		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
			/* select table */			
			 cs = conn.prepareCall("{call allTax()}");			 
			 cs.execute();			 
			 rs = cs.executeQuery();			 		 
			 while(rs.next()) { 

				 for( j = 0; j <= 3;j++) {
					 
					 if(j == 0) {
						 taxTable[i][j] = rs.getString(1);
					 }else if(j == 1) {
						 taxTable[i][j] = rs.getString(2);
					 }else if(j == 2) {
						 taxTable[i][j] = rs.getString(3);
					 }else if(j == 3) {
						 taxTable[i][j] = rs.getString(4);
					 }		 
				 }
				 i = i+ 1;

			 }	
//			 0 250000 0 0
			 System.out.println(taxTable[0][0]+" "+taxTable[0][1]+" "+taxTable[0][2]+" "+taxTable[0][3]);
//			 250000 400000 0 0.2
			 System.out.println(taxTable[1][0]+" "+taxTable[1][1]+" "+taxTable[1][2]+" "+taxTable[1][3]);
//			 400000 800000 30000 0.25
			 System.out.println(taxTable[2][0]+" "+taxTable[2][1]+" "+taxTable[2][2]+" "+taxTable[2][3]);
//			 800000 2000000 130000 0.3
			 System.out.println(taxTable[3][0]+" "+taxTable[3][1]+" "+taxTable[3][2]+" "+taxTable[3][3]);
//			 2000000 8000000 490000 0.32
			 System.out.println(taxTable[4][0]+" "+taxTable[4][1]+" "+taxTable[4][2]+" "+taxTable[4][3]);
//			 8000000 8000001 2410000 0.35
			 System.out.println(taxTable[5][0]+" "+taxTable[5][1]+" "+taxTable[5][2]+" "+taxTable[5][3]);
			 System.out.println("");
//			 1
			if (grossincome <= Integer.parseInt(taxTable[0][1])) {
	            taxrate =(grossincome);
	        }
//			2
	        else if((grossincome >= Integer.parseInt(taxTable[1][0])) && (grossincome <= Integer.parseInt(taxTable[1][1]))){
	            taxrate = (grossincome - Integer.parseInt(taxTable[1][0]))*  Double.parseDouble(taxTable[1][3]);
	        }
//			3
	        else if((grossincome >= Integer.parseInt(taxTable[2][0])) && (grossincome <= Integer.parseInt(taxTable[2][1]))){
	            taxrate = (((Integer.parseInt(taxTable[2][0]) -grossincome  ) * Double.parseDouble(taxTable[2][3]))+Integer.parseInt(taxTable[2][2]));
	        }
//			415000
	        else if((grossincome >= Integer.parseInt(taxTable[3][0])) && (grossincome <= Integer.parseInt(taxTable[3][1]))){
	            taxrate = (((grossincome - Integer.parseInt(taxTable[3][0])) * Double.parseDouble(taxTable[3][3]))+Integer.parseInt(taxTable[3][2]));
	        }
//			5
			
	        else if((grossincome >= Integer.parseInt(taxTable[4][0])) && (grossincome <= Integer.parseInt(taxTable[4][1]))){
	            taxrate = (((grossincome - Integer.parseInt(taxTable[4][0])) * Double.parseDouble(taxTable[4][3]))+Integer.parseInt(taxTable[4][2]));
	        }
//			6
	        else if(grossincome > Integer.parseInt(taxTable[5][0])) {
	        	 taxrate = (((grossincome - Integer.parseInt(taxTable[5][0])) * Double.parseDouble(taxTable[5][3]))+Integer.parseInt(taxTable[5][2]));
	        }else {
				
			}
	  
			 System.out.println(taxrate);
			 double montlyTaxrate = taxrate/12;
			 
			 System.out.println(montlyTaxrate);
			 
			

			  
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
