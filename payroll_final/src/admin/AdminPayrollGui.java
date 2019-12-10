package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jdesktop.xswingx.JXSearchField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import Main.ConnectDB;
import Main.EmployeeName;

import javax.swing.JTabbedPane;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import com.toedter.calendar.JMonthChooser;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import org.jdesktop.xswingx.JXTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class AdminPayrollGui extends JFrame {

	private JPanel contentPane;

	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	ResultSet rs,rs1,rs2,rs3,rs4,rs5;
	private JTextField textField_tax;
	private JTextField textField_SSS;
	private JTextField textField_PHILHEALTH;
	private JTextField textField_fn;
	private JTextField textField_job;
	private JTextField textField_salary;
	
	
	static PreparedStatement pst = null;
	static PreparedStatement pst1 = null;
	static CallableStatement cs = null;
	
	
	int grossincome;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPayrollGui Payframe = new AdminPayrollGui();

					Payframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPayrollGui() {
		conn = ConnectDB.doConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 863, 691);
		contentPane.add(tabbedPane);

		JPanel panel_PAYSLIP = new JPanel();
		tabbedPane.addTab("PAYSLIP", null, panel_PAYSLIP, null);
		panel_PAYSLIP.setLayout(null);

		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setBounds(10, 79, 79, 14);
		panel_PAYSLIP.add(lblFullName);



		JLabel lblNewLabel = new JLabel("Employee Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 42, 215, 26);
		panel_PAYSLIP.add(lblNewLabel);

		JLabel lblGrossIncome = new JLabel("Gross Income");
		lblGrossIncome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGrossIncome.setBounds(10, 250, 215, 26);
		panel_PAYSLIP.add(lblGrossIncome);

		JLabel lblDeduction = new JLabel("Deduction");
		lblDeduction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDeduction.setBounds(340, 42, 215, 26);
		panel_PAYSLIP.add(lblDeduction);

		JLabel lblTax = new JLabel("TAX");
		lblTax.setBounds(340, 79, 76, 14);
		panel_PAYSLIP.add(lblTax);

		textField_tax = new JTextField();
		textField_tax.setBounds(426, 76, 86, 20);
		panel_PAYSLIP.add(textField_tax);
		textField_tax.setColumns(10);

		JLabel lblSss = new JLabel("SSS");
		lblSss.setBounds(340, 104, 76, 14);
		panel_PAYSLIP.add(lblSss);

		textField_SSS = new JTextField();
		textField_SSS.setBounds(426, 101, 86, 20);
		panel_PAYSLIP.add(textField_SSS);
		textField_SSS.setColumns(10);

		JLabel lblNewLabel_PHILHEALTH = new JLabel("PHILHEALTH");
		lblNewLabel_PHILHEALTH.setBounds(340, 129, 79, 14);
		panel_PAYSLIP.add(lblNewLabel_PHILHEALTH);

		textField_PHILHEALTH = new JTextField();
		textField_PHILHEALTH.setBounds(426, 126, 86, 20);
		panel_PAYSLIP.add(textField_PHILHEALTH);
		textField_PHILHEALTH.setColumns(10);

		textField_fn = new JTextField();
		textField_fn.setBounds(99, 76, 86, 20);
		panel_PAYSLIP.add(textField_fn);
		textField_fn.setColumns(10);

		textField_job = new JTextField();
		textField_job.setBounds(99, 101, 86, 20);
		panel_PAYSLIP.add(textField_job);
		textField_job.setColumns(10);

		


		JXSearchField searchFieldIDpayslip = new JXSearchField();		
		
		searchFieldIDpayslip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "Select * from employee_Table e INNER join JobTitle_Table j on e.emp_id =j.job_id where emp_id = '"+searchFieldIDpayslip.getText().toString()+"'";
					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println(sql);
					

					
					double taxrate = 0;
					if (!rs.isBeforeFirst()) {
						JOptionPane.showMessageDialog(null, "No data found");
						textField_fn.setText("");
						textField_job.setText("");
						textField_salary.setText("");

					}
					else {

						while(rs.next()){
							//							label_1.setText("");
							textField_fn.setText(rs.getString("first_name"));
							textField_job.setText(rs.getString("job_title"));
							textField_salary.setText(rs.getString("job_salary"));
							
							int gi = rs.getInt("job_salary");
							EmployeeName.gi =gi;


						}
					}
				}catch (Exception e2) {
				}

				String chekzTax =("call allTax()");
				
				try {
					double taxrate = 0;
					String taxTable[][] = new String[6][4];
					int i = 0;
					int j = 0;
					double grossincome = EmployeeName.gi*12;
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
					stmt = conn.prepareCall(chekzTax);
					ResultSet rs1 = stmt.executeQuery(chekzTax);
					
						 		 
					 while(rs1.next()) { 

						 for( j = 0; j <= 3;j++) {
							 
							 if(j == 0) {
								 taxTable[i][j] = rs1.getString(1);
							 }else if(j == 1) {
								 taxTable[i][j] = rs1.getString(2);
							 }else if(j == 2) {
								 taxTable[i][j] = rs1.getString(3);
							 }else if(j == 3) {
								 taxTable[i][j] = rs1.getString(4);
							 }		 
						 }
						 i = i+ 1;

					 }	
//					 0 250000 0 0
					 System.out.println(taxTable[0][0]+" "+taxTable[0][1]+" "+taxTable[0][2]+" "+taxTable[0][3]);
//					 250000 400000 0 0.2
					 System.out.println(taxTable[1][0]+" "+taxTable[1][1]+" "+taxTable[1][2]+" "+taxTable[1][3]);
//					 400000 800000 30000 0.25
					 System.out.println(taxTable[2][0]+" "+taxTable[2][1]+" "+taxTable[2][2]+" "+taxTable[2][3]);
//					 800000 2000000 130000 0.3
					 System.out.println(taxTable[3][0]+" "+taxTable[3][1]+" "+taxTable[3][2]+" "+taxTable[3][3]);
//					 2000000 8000000 490000 0.32
					 System.out.println(taxTable[4][0]+" "+taxTable[4][1]+" "+taxTable[4][2]+" "+taxTable[4][3]);
//					 8000000 8000001 2410000 0.35
					 System.out.println(taxTable[5][0]+" "+taxTable[5][1]+" "+taxTable[5][2]+" "+taxTable[5][3]);
					 System.out.println("");
//					 1
					if (grossincome <= Integer.parseInt(taxTable[0][1])) {
			            taxrate =(grossincome);
			        }
//					2
			        else if((grossincome >= Integer.parseInt(taxTable[1][0])) && (grossincome <= Integer.parseInt(taxTable[1][1]))){
			            taxrate = (grossincome - Integer.parseInt(taxTable[1][0]))*  Double.parseDouble(taxTable[1][3]);
			        }
//					3
			        else if((grossincome >= Integer.parseInt(taxTable[2][0])) && (grossincome <= Integer.parseInt(taxTable[2][1]))){
			            taxrate = (((Integer.parseInt(taxTable[2][0]) -grossincome  ) * Double.parseDouble(taxTable[2][3]))+Integer.parseInt(taxTable[2][2]));
			        }
//					415000
			        else if((grossincome >= Integer.parseInt(taxTable[3][0])) && (grossincome <= Integer.parseInt(taxTable[3][1]))){
			            taxrate = (((grossincome - Integer.parseInt(taxTable[3][0])) * Double.parseDouble(taxTable[3][3]))+Integer.parseInt(taxTable[3][2]));
			        }
//					5
					
			        else if((grossincome >= Integer.parseInt(taxTable[4][0])) && (grossincome <= Integer.parseInt(taxTable[4][1]))){
			            taxrate = (((grossincome - Integer.parseInt(taxTable[4][0])) * Double.parseDouble(taxTable[4][3]))+Integer.parseInt(taxTable[4][2]));
			        }
//					6
			        else if(grossincome > Integer.parseInt(taxTable[5][0])) {
			        	 taxrate = (((grossincome - Integer.parseInt(taxTable[5][0])) * Double.parseDouble(taxTable[5][3]))+Integer.parseInt(taxTable[5][2]));
			        }else {
						
					}
			  
					 System.out.println(taxrate);
					 double montlyTaxrate = taxrate/12;
					 
					 System.out.println(montlyTaxrate);
					 String month = Double.toString(montlyTaxrate);
					 
					 textField_tax.setText(month);
					

					  
				} catch (Exception w) {
					w.printStackTrace();

				}
				
				String checkSSS = ("{call allTax_sss()}");
				
				try {
					String taxTable_sss[][] = new String[37][6];
					int cSSS = 0;
					int rSSS = 0;
					System.out.println("enter rate");
					double grossincome_sss = EmployeeName.gi;
				
					double taxrate_sss = 0;
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
					stmt = conn.prepareCall(checkSSS);
					ResultSet rs3 = stmt.executeQuery(checkSSS);			 		 
					 while(rs3.next()) { 

						 for( rSSS = 0; rSSS <= 5;rSSS++) {
							 
							 if(rSSS == 0) {
								 taxTable_sss[cSSS][rSSS] = rs3.getString(1);
							 }else if(rSSS == 1) {
								 taxTable_sss[cSSS][rSSS] = rs3.getString(2);
							 }else if(rSSS == 2) {
								 taxTable_sss[cSSS][rSSS] = rs3.getString(3);
							 }else if(rSSS == 3) {
								 taxTable_sss[cSSS][rSSS] = rs3.getString(4);
							 }else if(rSSS == 4) {
								 taxTable_sss[cSSS][rSSS] = rs3.getString(5);
							 }else if(rSSS == 5) {
								 taxTable_sss[cSSS][rSSS] = rs3.getString(6);
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
					 
					 String SSSOut = Double.toString(taxrate_sss);
					 
					 textField_SSS.setText(SSSOut);

					  
				} catch (Exception q) {
					q.printStackTrace();

				}
			}



		});
		
		searchFieldIDpayslip.setPrompt("Enter Employee Id");
		searchFieldIDpayslip.setBounds(10, 11, 128, 20);
		panel_PAYSLIP.add(searchFieldIDpayslip);
		
		textField_salary = new JTextField();
		textField_salary.setBounds(99, 126, 86, 20);
		panel_PAYSLIP.add(textField_salary);
		textField_salary.setColumns(10);



		JLabel lblJob = new JLabel("Job");
		lblJob.setBounds(10, 104, 46, 14);
		panel_PAYSLIP.add(lblJob);

		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(10, 129, 46, 14);
		panel_PAYSLIP.add(lblSalary);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(12, 194, 111, 22);
		panel_PAYSLIP.add(monthChooser);
		
		JButton btnGenerate = new JButton("generate");
		btnGenerate.setBounds(145, 191, 97, 25);
		panel_PAYSLIP.add(btnGenerate);
		
		textField = new JTextField();
		textField.setBounds(99, 281, 116, 22);
		panel_PAYSLIP.add(textField);
		textField.setColumns(10);
		
		String[] bayad= new String[]{"Monthly", "Semi-Monthly","Weekly"};
		JComboBox comboBox = new JComboBox(bayad);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		comboBox.setBounds(10, 229, 111, 20);
		panel_PAYSLIP.add(comboBox);
		
		JPanel panel_TAXSETTINGS = new JPanel();
		tabbedPane.addTab("TAX SETTINGS", null, panel_TAXSETTINGS, null);
		panel_TAXSETTINGS.setLayout(null);
			
		JTabbedPane tabbedPane_settings = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_settings.setBounds(12, 13, 834, 635);
		panel_TAXSETTINGS.add(tabbedPane_settings);
						
		JPanel panel_TAX = new JPanel();
		tabbedPane_settings.addTab("TAX", null, panel_TAX, null);
		
		JPanel panel_SSS = new JPanel();
		tabbedPane_settings.addTab("SSS", null, panel_SSS, null);
		
		JPanel panel_PHILHEALTH = new JPanel();
		tabbedPane_settings.addTab("PHILHEALTH", null, panel_PHILHEALTH, null);


	}
}
