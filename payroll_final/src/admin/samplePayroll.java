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
		String taxTable[][] = new String[5][3];
		int i = 0;
		int j = 0;
		
		
		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");

			/* select table */
			
			 cs = conn.prepareCall("{call callTax()}");
			 
			 cs.execute();
			 
			 rs = cs.executeQuery();
			 
			 
			 while(rs.next()) { 

				 for( j = 0; j <= 2;j++) {
					 
					 if(j == 0) {
						 taxTable[i][j] = rs.getString(1);

					 }else if(j == 1) {
						 taxTable[i][j] = rs.getString(2);

					 }else if(j == 2) {
						 taxTable[i][j] = rs.getString(3);

					 }
					 
				 }
				 i = i+ 1;

			 }	
			 System.out.println(taxTable[0][0]+" 250000 "+taxTable[0][1]+" .20 "+taxTable[0][2]+" ");
			 System.out.println(taxTable[1][0]+" 400000 "+taxTable[1][1]+" .25 "+taxTable[1][2]+" 30000");
			 System.out.println(taxTable[2][0]+" 800000 "+taxTable[2][1]+" .30 "+taxTable[2][2]+" 130000");
			 System.out.println(taxTable[3][0]+" 2000000 "+taxTable[3][1]+" 0.32 "+taxTable[3][2]+" 490000 ");
			 System.out.println(taxTable[4][0]+" 8000000 "+taxTable[4][1]+" 0.35 "+taxTable[4][2]+" 2410000 ");
			 
			 System.out.println("");
			 
			 
		 
			 if (grossincome <= Integer.parseInt(taxTable[0][0])) {
	            taxrate =(grossincome);
	        }
	        else if((grossincome >= Integer.parseInt(taxTable[0][0])) && (grossincome <= Integer.parseInt(taxTable[1][0]))){
	            taxrate = (grossincome - Integer.parseInt(taxTable[0][0]))*  Double.parseDouble(taxTable[0][1]);
	        }
	        else if((grossincome >= Integer.parseInt(taxTable[1][0])) && (grossincome <= Integer.parseInt(taxTable[2][0]))){
	            taxrate = (((Integer.parseInt(taxTable[1][0]) -grossincome  ) * Double.parseDouble(taxTable[1][1]))+Integer.parseInt(taxTable[1][2]));
	        }
	        else if((grossincome >= Integer.parseInt(taxTable[2][0])) && (grossincome <= Integer.parseInt(taxTable[3][0]))){
	            taxrate = (((grossincome - Integer.parseInt(taxTable[2][0])) * Double.parseDouble(taxTable[2][1]))+Integer.parseInt(taxTable[2][2]));
	        }
			
	        else if((grossincome >= Integer.parseInt(taxTable[3][0])) && (grossincome <= Integer.parseInt(taxTable[4][0]))){
	            taxrate = (((grossincome - Integer.parseInt(taxTable[3][0])) * Double.parseDouble(taxTable[3][1]))+Integer.parseInt(taxTable[3][2]));
	        }
	        else if(grossincome > Integer.parseInt(taxTable[4][0])) {
	        	 taxrate = (((grossincome - Integer.parseInt(taxTable[4][0])) * Double.parseDouble(taxTable[4][1]))+Integer.parseInt(taxTable[4][2]));
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
