package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import Main.ConnectDB;
import Main.EmployeeName;
import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import com.toedter.calendar.JMonthChooser;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.CaretEvent;
import org.jdesktop.xswingx.JXTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

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
	private JTable PHILTable;
	private JTable taxTable;
	private JTable SSSTable;
	private JTable PAGIBIGTable;
	private JTextField textField_basesalary;
	private JTextField textField_taxMin;
	private JTextField textField_tax_Max;
	private JTextField textField_TaxOnLowerLimit;
	private JTextField textField_TaxOnExcessOverLimit;
	private JTextField textField_id;
	private JTextField textField_sssID;
	private JTextField textField_sssMIN;
	private JTextField textField_SSSMAX;
	private JTextField textField_SSSEC;
	private JTextField textField_SSSER;
	private JTextField textField_SSSEE;
	private JTextField textField_SSSTOTAL;
	private JTextField textField_PAGIBIG;
	boolean isMonthly=false;
	boolean isSemi_Monthly=false;
	boolean isWeekly=false;

	boolean isJan=false;
	boolean isFeb=false;
	boolean isMar=false;
	boolean isApr=false;
	boolean isMay=false;
	boolean isJune=false;
	boolean isJuly=false;
	boolean isAug=false;
	boolean isSept=false;
	boolean isOct=false;
	boolean isNov=false;
	boolean isDec=false;
	private JTextField textField_genTax;
	private JTextField textField_genSSS;
	private JTextField textField_genPHILHEALTH;
	private JTextField textField_genPagibig;
	private JTextField textField_genNet;
	private JTextField textField_updtphilID;
	private JTextField textField_updtMIN;
	private JTextField textField_updtMAX;
	private JTextField textField_UPDTRATE;
	private JTextField textField_idpagibig;
	private JTextField textField_min;
	private JTextField textField_pagibigmax;
	private JTextField textField_employeeSharepagibig;
	private JTextField textField_employersharepagibig;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPayrollGui Payframe = new AdminPayrollGui();
					Payframe.setVisible(true);
					WindowAdapter exitListener = new WindowAdapter() {

						@Override
						public void windowClosing(WindowEvent e) {
							int confirm = JOptionPane.showOptionDialog(Payframe,
									"Are You Sure to Close this Application?",
									"Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
							if(confirm == JOptionPane.YES_OPTION){
								Payframe.setDefaultCloseOperation(Payframe.DISPOSE_ON_CLOSE);//yes
								adminMenuGui adminGUiframe = new adminMenuGui();
								adminGUiframe.setVisible(true);
								adminGUiframe.setTitle("Admin Menu");

							} else if (confirm == JOptionPane.CANCEL_OPTION) {
								Payframe.setDefaultCloseOperation(Payframe.DO_NOTHING_ON_CLOSE);//cancel
							} else {
								Payframe.setDefaultCloseOperation(Payframe.DO_NOTHING_ON_CLOSE);//no
							}
						}
					};
					Payframe.addWindowListener(exitListener);
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
		setTitle("Payroll Menu");
		setResizable(false);
		conn = ConnectDB.doConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 51, 863, 665);
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
		lblGrossIncome.setBounds(10, 256, 215, 26);
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

		JLabel lblNoDataFound = new JLabel("");
		lblNoDataFound.setBounds(191, 13, 134, 16);
		panel_PAYSLIP.add(lblNoDataFound);


		JXSearchField searchFieldIDpayslip = new JXSearchField();		
		searchFieldIDpayslip.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try {
					String sql = "Select * from employee_Table e INNER join JobTitle_Table j on e.job_id = j.job_id where emp_id =  '"+searchFieldIDpayslip.getText().toString()+"'";
					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println(sql);




					if (!rs.isBeforeFirst()) {
						System.out.println("no data found");
						lblNoDataFound.setText("No Record Found");
						textField_fn.setText("");
						textField_job.setText("");
						textField_salary.setText("");

						textField_tax.setText("");
						textField_SSS.setText("");
						textField_PHILHEALTH.setText("");
						textField_PAGIBIG.setText("");
					}
					else {

						while(rs.next()){
							lblNoDataFound.setText("");

							//							label_1.setText("");
							textField_fn.setText(rs.getString("first_name"));
							textField_job.setText(rs.getString("job_title"));
							textField_salary.setText(rs.getString("job_salary"));

							int gi = rs.getInt("job_salary");
							EmployeeName.gi =gi;


						}
						String chekzTax =("call allTax()");

						try {
							float taxrate = 0;
							String taxTable[][] = new String[6][4];
							int i = 0;
							int j = 0;
							float grossincome = EmployeeName.gi*12;
							System.out.println("ANNUALINCOME"+grossincome);
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
							System.out.println(taxTable[0][0]+" "+taxTable[0][1]+" "+taxTable[0][2]+" "+taxTable[0][3]);
							System.out.println(taxTable[1][0]+" "+taxTable[1][1]+" "+taxTable[1][2]+" "+taxTable[1][3]);
							System.out.println(taxTable[2][0]+" "+taxTable[2][1]+" "+taxTable[2][2]+" "+taxTable[2][3]);
							System.out.println(taxTable[3][0]+" "+taxTable[3][1]+" "+taxTable[3][2]+" "+taxTable[3][3]);
							System.out.println(taxTable[4][0]+" "+taxTable[4][1]+" "+taxTable[4][2]+" "+taxTable[4][3]);
							System.out.println(taxTable[5][0]+" "+taxTable[5][1]+" "+taxTable[5][2]+" "+taxTable[5][3]);
							System.out.println("");
							if (grossincome <= Integer.parseInt(taxTable[0][1])) {
								taxrate =grossincome*0;
							}
							else if((grossincome >= Integer.parseInt(taxTable[1][0])) && (grossincome <= Integer.parseInt(taxTable[1][1]))){
								taxrate = (grossincome - Integer.parseInt(taxTable[1][0])) *  Float.parseFloat(taxTable[1][3]);
							}
							else if((grossincome >= Integer.parseInt(taxTable[2][0])) && (grossincome <= Integer.parseInt(taxTable[2][1]))){
								taxrate = (((Integer.parseInt(taxTable[2][0]) -grossincome  ) * Float.parseFloat(taxTable[2][3]))+Integer.parseInt(taxTable[2][2]));
							}
							else if((grossincome >= Integer.parseInt(taxTable[3][0])) && (grossincome <= Integer.parseInt(taxTable[3][1]))){
								taxrate = (((grossincome - Integer.parseInt(taxTable[3][0])) * Float.parseFloat(taxTable[3][3]))+Integer.parseInt(taxTable[3][2]));
							}
							else if((grossincome >= Integer.parseInt(taxTable[4][0])) && (grossincome <= Integer.parseInt(taxTable[4][1]))){
								taxrate = (((grossincome - Integer.parseInt(taxTable[4][0])) * Float.parseFloat(taxTable[4][3]))+Integer.parseInt(taxTable[4][2]));
							}
							else if(grossincome > Integer.parseInt(taxTable[5][0])) {
								taxrate = (((grossincome - Integer.parseInt(taxTable[5][0])) * Float.parseFloat(taxTable[5][3]))+Integer.parseInt(taxTable[5][2]));
							}else {

							}

							System.out.println(taxrate);
							System.out.println("taxrate "+taxrate);
							float montlyTaxrate = taxrate/12;

							System.out.println("monthly rate " +montlyTaxrate);
							String month = Float.toString(montlyTaxrate);

							textField_tax.setText(month);
							System.out.println("month tax "+ month);

							String checkSSS = ("{call allTax_sss()}");

							try {
								

								String taxTable_sss[][] = new String[37][6];
								int cSSS = 0;
								int rSSS = 0;
								System.out.println("SSS Table");
								double grossincome_sss = EmployeeName.gi;
								System.out.println(grossincome_sss);

								double taxrate_sss = 0;

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
								else if(grossincome_sss >= Integer.parseInt(taxTable_sss[36][1])){
									taxrate_sss = Integer.parseInt(taxTable_sss[36][5]);
								}

								System.out.println(taxrate_sss);

								String SSSOut = Double.toString(taxrate_sss);

								textField_SSS.setText(SSSOut);
								System.out.println("");



								String checkPHIL = ("{call allTax_PHILV1()}");
								try {

									float grossincome_PHIL = EmployeeName.gi;
									System.out.println("philhealth: "+grossincome_PHIL);

									float taxrate_PHIL = 0;
									String taxTable_PHIL[][] = new String[3][3];
									int cPHIL = 0;
									int rPHIL = 0;
									Class.forName("com.mysql.jdbc.Driver");
									stmt = conn.prepareCall(checkPHIL);
									ResultSet rs4 = stmt.executeQuery(checkPHIL);		 		 
									while(rs4.next()) { 

										for( rPHIL = 0; rPHIL <= 2;rPHIL++) {

											if(rPHIL == 0) {
												taxTable_PHIL[cPHIL][rPHIL] = rs4.getString(1);
											}else if(rPHIL == 1) {
												taxTable_PHIL[cPHIL][rPHIL] = rs4.getString(2);
											}else if(rPHIL == 2) {
												taxTable_PHIL[cPHIL][rPHIL] = rs4.getString(3);

											}
										}
										cPHIL = cPHIL+ 1;

									}	

									System.out.println(taxTable_PHIL[0][0]+" "+taxTable_PHIL[0][1]+" "+taxTable_PHIL[0][2]);
									System.out.println(taxTable_PHIL[1][0]+" "+taxTable_PHIL[1][1]+" "+taxTable_PHIL[1][2]);
									System.out.println(taxTable_PHIL[2][0]+" "+taxTable_PHIL[2][1]+" "+taxTable_PHIL[2][2]);



									if (grossincome_PHIL <= Float.parseFloat(taxTable_PHIL[0][1])) {
										taxrate_PHIL =(grossincome_PHIL* Float.parseFloat(taxTable_PHIL[0][2]));
									}
									else if((grossincome_PHIL >= Float.parseFloat(taxTable_PHIL[1][0])) && (grossincome_PHIL <= Float.parseFloat(taxTable_PHIL[1][1]))){
										taxrate_PHIL = (grossincome_PHIL*Float.parseFloat(taxTable_PHIL[1][2]));
									}
									else if(grossincome_PHIL >= Float.parseFloat(taxTable_PHIL[2][1])){
										taxrate_PHIL = (grossincome_PHIL*Float.parseFloat(taxTable_PHIL[2][2]));

									}

									System.out.println("taxxrate phil"+taxrate_PHIL);
									String PHILOut = Double.toString(taxrate_PHIL);
									textField_PHILHEALTH.setText(PHILOut);
									System.out.println("");
									String checkpagbigz = ("{call allTax_PAGibig()}");
									try {


										double grossincome_pagIbig = EmployeeName.gi;

										double taxrate_pagIbig = 0;
										String taxTable_pagIbig[][] = new String[2][4];
										int cpagIbig = 0;
										int rpagIbig = 0;
										stmt = conn.prepareCall(checkpagbigz);
										ResultSet rs5 = stmt.executeQuery(checkpagbigz);	

										while(rs5.next()) { 

											for( rpagIbig = 0; rpagIbig <= 3;rpagIbig++) {

												if(rpagIbig == 0) {
													taxTable_pagIbig[cpagIbig][rpagIbig] = rs5.getString(1);
												}else if(rpagIbig == 1) {
													taxTable_pagIbig[cpagIbig][rpagIbig] = rs5.getString(2);
												}else if(rpagIbig == 2) {
													taxTable_pagIbig[cpagIbig][rpagIbig] = rs5.getString(3);
												}else if(rpagIbig == 3) {
													taxTable_pagIbig[cpagIbig][rpagIbig] = rs5.getString(4);
												}
											}
											cpagIbig = cpagIbig+ 1;

										}	

										System.out.println(taxTable_pagIbig[0][0]+" "+taxTable_pagIbig[0][1]+" "+taxTable_pagIbig[0][2]+" "+taxTable_pagIbig[0][3]);
										System.out.println(taxTable_pagIbig[1][0]+" "+taxTable_pagIbig[1][1]+" "+taxTable_pagIbig[1][2]+" "+taxTable_pagIbig[1][3]);


										if (grossincome_pagIbig <= Double.parseDouble(taxTable_pagIbig[0][1])) {
											taxrate_pagIbig = (grossincome_pagIbig*Double.parseDouble(taxTable_pagIbig[0][2])+(grossincome_pagIbig*Double.parseDouble(taxTable_pagIbig[0][3])));
										}
										else if(grossincome_pagIbig >= Double.parseDouble(taxTable_pagIbig[1][0])){
											taxrate_pagIbig = (grossincome_pagIbig*Double.parseDouble(taxTable_pagIbig[1][2])+(grossincome_pagIbig*Double.parseDouble(taxTable_pagIbig[1][3])));

										}
										System.out.println("");
										System.out.println(taxrate_pagIbig);
										String phigOut = Double.toString(taxrate_pagIbig);

										textField_PAGIBIG.setText(phigOut);

									} catch (Exception q) {
										q.printStackTrace();

									}


								} catch (Exception e) {
									e.printStackTrace();

								}


							} catch (Exception v) {
								v.printStackTrace();

							}


						} catch (Exception w) {
							w.printStackTrace();

						}
					}
				}catch (Exception e2) {
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

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(isJan && isMonthly) {

						System.out.println("montly jan");	
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"1\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isFeb && isMonthly) {
						System.out.println("feb monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"2\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isMar && isMonthly) {
						System.out.println("Mar monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"3\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isApr && isMonthly) {
						System.out.println("Apr monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"4\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isMay && isMonthly) {
						System.out.println("May monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"5\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isJune && isMonthly) {
						System.out.println("June monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"6\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isJuly && isMonthly) {
						System.out.println("July monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"7\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isAug && isMonthly) {
						System.out.println("Aug monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"8\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isSept && isMonthly) {
						System.out.println("sept monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"9\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isOct && isMonthly) {
						System.out.println("oct monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"10\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isNov && isMonthly) {
						System.out.println("nov monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"11\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isDec && isMonthly) {
						System.out.println("dec monhtlu");
						//					tax
						float taxdivideby2 = Float.parseFloat(textField_tax.getText());
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = Float.parseFloat(textField_SSS.getText());
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = Float.parseFloat(textField_PHILHEALTH.getText());
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = Float.parseFloat(textField_PAGIBIG.getText());
						String IBIGdivibyTwo = Float.toString(IBIGdivideby2);
						textField_genPagibig.setText(IBIGdivibyTwo);
						String motmot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"12\"");
						try {
							stmt = conn.prepareCall(motmot);
							ResultSet rs = stmt.executeQuery(motmot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								String convert = Float.toString(totalSalaryperMonth);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=totalSalaryperMonth-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//semi monthly
					else if(isJan && isSemi_Monthly) {
						System.out.println("Semi_Monthly jaN");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"1\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isFeb && isSemi_Monthly) {
						System.out.println("Semi_Monthly feb");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"2\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isMar && isSemi_Monthly) {
						System.out.println("Semi_Monthly mar");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"3\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isApr && isSemi_Monthly) {
						System.out.println("Semi_Monthly Apr");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"4\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isMay && isSemi_Monthly) {
						System.out.println("Semi_Monthly to");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"5\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isJune && isSemi_Monthly) {
						System.out.println("Semi_Monthly June");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"6\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isJuly && isSemi_Monthly) {
						System.out.println("Semi_Monthly July");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"7\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isAug && isSemi_Monthly) {
						System.out.println("Semi_Monthly to");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"8\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isSept && isSemi_Monthly) {
						System.out.println("Semi_Monthly Sept");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"9\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isOct && isSemi_Monthly) {
						System.out.println("Semi_Monthly Oct");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"10\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isNov && isSemi_Monthly) {
						System.out.println("Semi_Monthly to Nov");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"11\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isDec && isSemi_Monthly) {
						System.out.println("Semi_Monthly to Dec");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/2);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/2);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/2);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/2);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String semimot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"12\"");
						try {
							stmt = conn.prepareCall(semimot);
							ResultSet rs = stmt.executeQuery(semimot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/2;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//				Weekly
					else if(isJan && isWeekly) {
						System.out.println("weekly jan");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"1\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;			

							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isFeb && isWeekly) {
						System.out.println("weekly feb");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"2\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isMar && isWeekly) {
						System.out.println("weekly mar");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"3\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isApr && isWeekly) {
						System.out.println("weekly apr");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"4\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isMay && isWeekly) {
						System.out.println("weekly may");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"5\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isJune && isWeekly) {
						System.out.println("weekly june");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"6\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isJuly && isWeekly) {
						System.out.println("weekly july");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"7\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isAug && isWeekly) {
						System.out.println("weekly aug");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"8\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isSept && isWeekly) {
						System.out.println("weekly sept");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"9\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isOct && isWeekly) {
						System.out.println("weekly oct");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"10\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);

								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);
								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isNov && isWeekly) {
						System.out.println("weekly nov");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"11\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(isDec && isWeekly) {
						System.out.println("weekly dec");
						//					tax
						float taxdivideby2 = (Float.parseFloat(textField_tax.getText())/4);
						String taxdivibyTwo = Float.toString(taxdivideby2);
						textField_genTax.setText(taxdivibyTwo);
						//					sss
						float SSSdivideby2 = (Float.parseFloat(textField_SSS.getText())/4);
						String SSSdivibyTwo = Float.toString(SSSdivideby2);
						textField_genSSS.setText(SSSdivibyTwo);
						//					Philhealth
						float Phildivideby2 = (Float.parseFloat(textField_PHILHEALTH.getText())/4);
						String PhildivibyTwo = Float.toString(Phildivideby2);
						textField_genPHILHEALTH.setText(PhildivibyTwo);
						//					pagibig					
						float IBIGdivideby2 = (Float.parseFloat(textField_PAGIBIG.getText())/4);
						String IBIGdivibyTwo = Float.toString(Phildivideby2);
						textField_genPagibig.setText(PhildivibyTwo);
						String weeklymot =("select sum(totalhr) from attendance where employee_id ='"+searchFieldIDpayslip.getText()+"' and month_ = \"12\"");
						try {
							stmt = conn.prepareCall(weeklymot);
							ResultSet rs = stmt.executeQuery(weeklymot);

							while(rs.next()) {
								System.out.println("");
								float totalh = rs.getFloat(1);
								System.out.println(totalh);
								float bSalary = (Float.parseFloat(textField_salary.getText())/20);
								float hourdivide = bSalary/8;
								float totalSalaryperMonth = hourdivide*totalh;

								System.out.println( "cmpute HOur salary: " +totalSalaryperMonth);
								float semiMotmot = totalSalaryperMonth/4;
								String convert = Float.toString(semiMotmot);
								textField_basesalary.setText(convert);
								float taxTotal= taxdivideby2+SSSdivideby2+Phildivideby2+IBIGdivideby2;
								float taxfinal=semiMotmot-taxTotal;
								String convertnet =Float.toString(taxfinal);
								textField_genNet.setText(convertnet);

								break;						
							}			
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		btnGenerate.setBounds(141, 170, 97, 25);
		panel_PAYSLIP.add(btnGenerate);

		textField_basesalary = new JTextField();
		textField_basesalary.setBounds(109, 295, 116, 22);
		panel_PAYSLIP.add(textField_basesalary);
		textField_basesalary.setColumns(10);

		String[] dit= new  String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY", "AUG", "SEPT", "OCT", "NOV", "DEC"};
		JComboBox comboBox_date = new JComboBox(dit);
		comboBox_date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_date.getSelectedItem().equals("JAN")) {
					isJan=true;
					isFeb=false;
					isMar=false;
					isApr=false;
					isMay=false;
					isJune=false;
					isJuly=false;
					isAug=false;
					isSept= false;
					isOct=false;
					isNov=false;
					isDec=false;

				}else if(comboBox_date.getSelectedItem().equals("FEB")){
					isJan=false;
					isFeb=true;
					isMar=false;
					isApr=false;
					isMay=false;
					isJune=false;
					isJuly=false;
					isAug=false;
					isSept= false;
					isOct=false;
					isNov=false;
					isDec=false;
				}else if(comboBox_date.getSelectedItem().equals("MAR")){
					isJan=false;
					isFeb=false;
					isMar=true;
					isApr=false;
					isMay=false;
					isJune=false;
					isJuly=false;
					isAug=false;
					isSept= false;
					isOct=false;
					isNov=false;
					isDec=false;

				}else if(comboBox_date.getSelectedItem().equals("APR")){
					isJan=false;
					isFeb=false;
					isMar=false;
					isApr=true;
					isMay=false;
					isJune=false;
					isJuly=false;
					isAug=false;
					isSept= false;
					isOct=false;
					isNov=false;
					isDec=false;
				}else if(comboBox_date.getSelectedItem().equals("MAY")){
					isJan=false;
					isFeb=false;
					isMar=false;
					isApr=false;
					isMay=true;
					isJune=false;
					isJuly=false;
					isAug=false;
					isSept=false;
					isOct=false;
					isNov=false;
					isDec=false;
				}else if(comboBox_date.getSelectedItem().equals("JUNE")){
					isJan=false;
					isFeb=false;
					isMar=false;
					isApr=false;
					isMay=false;
					isJune=true;
					isJuly=false;
					isAug=false;
					isSept= false;
					isOct=false;
					isNov=false;
					isDec=false;
				}else if(comboBox_date.getSelectedItem().equals("JULY")){
					isJan=false;
					isFeb=false;
					isMar=false;
					isApr=false;
					isMay=false;
					isJune=false;
					isJuly=true;
					isAug=false;
					isSept= false;
					isOct=false;
					isNov=false;
					isDec=false;
				}else if(comboBox_date.getSelectedItem().equals("AUG")){
					isJan=false;
					isFeb=false;
					isMar=false;
					isApr=false;
					isMay=false;
					isJune=false;
					isJuly=false;
					isAug=true;
					isSept= false;
					isOct=false;
					isNov=false;
					isDec=false;
				}else if(comboBox_date.getSelectedItem().equals("SEPT")){
					isJan=false;
					isFeb=false;
					isMar=false;
					isApr=false;
					isMay=false;
					isJune=false;
					isJuly=false;
					isAug=false;
					isSept=true;
					isOct=false;
					isNov=false;
					isDec=false;
				}else if(comboBox_date.getSelectedItem().equals("OCT")){
					isJan=false;
					isFeb=false;
					isMar=false;
					isApr=false;
					isMay=false;
					isJune=false;
					isJuly=false;
					isAug=false;
					isSept= false;
					isOct=true;
					isNov=false;
					isDec=false;
				}else if(comboBox_date.getSelectedItem().equals("NOV")){
					isJan=false;
					isFeb=false;
					isMar=false;
					isApr=false;
					isMay=false;
					isJune=false;
					isJuly=false;
					isAug=false;
					isSept= false;
					isOct=false;
					isNov=true;
					isDec=false;
				}else if(comboBox_date.getSelectedItem().equals("DEC")){
					isJan=false;
					isFeb=false;
					isMar=false;
					isApr=false;
					isMay=false;
					isJune=false;
					isJuly=false;
					isAug=false;
					isSept= false;
					isOct=false;
					isNov=false;
					isDec=true;
				}


			}
		});
		comboBox_date.setBounds(10, 172, 111, 21);
		panel_PAYSLIP.add(comboBox_date);

		String[] bayad= new String[]{"Monthly", "Semi_Monthly","Weekly"};
		JComboBox comboBox_pay = new JComboBox(bayad);
		comboBox_pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_pay.getSelectedItem().equals("Monthly")) {
					isMonthly=true;
					isSemi_Monthly=false;
					isWeekly=false;
				}else if(comboBox_pay.getSelectedItem().equals("Semi_Monthly")){
					isMonthly=false;
					isSemi_Monthly=true;
					isWeekly=false;
				}else if(comboBox_pay.getSelectedItem().equals("Weekly")){
					isMonthly=false;
					isSemi_Monthly=false;
					isWeekly=true;
				}


			}
		});
		comboBox_pay.setBounds(10, 212, 111, 20);
		panel_PAYSLIP.add(comboBox_pay);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 156, 836, 2);
		panel_PAYSLIP.add(separator);

		JLabel label = new JLabel("Deduction");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(10, 330, 215, 26);
		panel_PAYSLIP.add(label);

		JLabel lblTax_1 = new JLabel("TAX");
		lblTax_1.setBounds(10, 369, 56, 16);
		panel_PAYSLIP.add(lblTax_1);

		JLabel lblSss_1 = new JLabel("SSS");
		lblSss_1.setBounds(10, 398, 56, 16);
		panel_PAYSLIP.add(lblSss_1);

		JLabel lblPhilhealth = new JLabel("PHILHEALTH");
		lblPhilhealth.setBounds(10, 427, 97, 16);
		panel_PAYSLIP.add(lblPhilhealth);

		JLabel lblPagibig = new JLabel("PAGIBIG");
		lblPagibig.setBounds(10, 456, 97, 16);
		panel_PAYSLIP.add(lblPagibig);

		JLabel label_1 = new JLabel("PAGIBIG");
		label_1.setBounds(546, 78, 97, 16);
		panel_PAYSLIP.add(label_1);

		textField_PAGIBIG = new JTextField();
		textField_PAGIBIG.setBounds(618, 75, 116, 22);
		panel_PAYSLIP.add(textField_PAGIBIG);
		textField_PAGIBIG.setColumns(10);

		textField_genTax = new JTextField();
		textField_genTax.setBounds(109, 366, 116, 22);
		panel_PAYSLIP.add(textField_genTax);
		textField_genTax.setColumns(10);

		textField_genSSS = new JTextField();
		textField_genSSS.setBounds(109, 395, 116, 22);
		panel_PAYSLIP.add(textField_genSSS);
		textField_genSSS.setColumns(10);

		textField_genPHILHEALTH = new JTextField();
		textField_genPHILHEALTH.setBounds(109, 424, 116, 22);
		panel_PAYSLIP.add(textField_genPHILHEALTH);
		textField_genPHILHEALTH.setColumns(10);

		textField_genPagibig = new JTextField();
		textField_genPagibig.setBounds(109, 453, 116, 22);
		panel_PAYSLIP.add(textField_genPagibig);
		textField_genPagibig.setColumns(10);

		JLabel lblBasicSalary = new JLabel("Base salary");
		lblBasicSalary.setBounds(10, 301, 87, 16);
		panel_PAYSLIP.add(lblBasicSalary);

		JLabel lblNetIncome = new JLabel("Net Income");
		lblNetIncome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNetIncome.setBounds(4, 485, 134, 26);
		panel_PAYSLIP.add(lblNetIncome);

		textField_genNet = new JTextField();
		textField_genNet.setBounds(109, 524, 116, 22);
		panel_PAYSLIP.add(textField_genNet);
		textField_genNet.setColumns(10);

		JLabel lblNet = new JLabel("Net");
		lblNet.setBounds(10, 527, 56, 16);
		panel_PAYSLIP.add(lblNet);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(377, 171, 206, 339);
		panel_PAYSLIP.add(textArea);

		JButton btnGeneratePayslip = new JButton("Generate payslip");
		btnGeneratePayslip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("***************************************\n");
				textArea.setText(textArea.getText()+"         *PAYROLL MANAGEMENT*          \n");
				textArea.setText(textArea.getText()+"***************************************\n");
				Date obj = new Date();
				String Date = obj.toString();
				textArea.setText(textArea.getText()+"\n"+Date+"\n");
				textArea.setText(textArea.getText()+"\n");
				textArea.setText(textArea.getText()+"Gross Income\n");
				textArea.setText(textArea.getText()+"Base Salary: "+textField_basesalary.getText()+" \n");
				textArea.setText(textArea.getText()+"\n");
				textArea.setText(textArea.getText()+"Deduction\n");
				textArea.setText(textArea.getText()+"Tax: "+textField_genTax.getText()+" \n");
				textArea.setText(textArea.getText()+"SSS: "+textField_genSSS.getText()+" \n");
				textArea.setText(textArea.getText()+"Philhealth: "+textField_genPHILHEALTH.getText()+" \n");
				textArea.setText(textArea.getText()+"Pagibig: "+textField_genPagibig.getText()+" \n");
				textArea.setText(textArea.getText()+"\n");
				textArea.setText(textArea.getText()+"NET INCOME\n");
				textArea.setText(textArea.getText()+"NET: "+textField_genNet.getText()+" \n");
				textArea.setText(textArea.getText()+"\n");
				textArea.setText(textArea.getText()+"\n");
				textArea.setText(textArea.getText()+"                              Signiture\n");
				textArea.setText(textArea.getText()+"***************************************\n");

			}
		});
		btnGeneratePayslip.setBounds(377, 524, 153, 23);
		panel_PAYSLIP.add(btnGeneratePayslip);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.print();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnPrint.setBounds(377, 560, 97, 25);
		panel_PAYSLIP.add(btnPrint);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(328, 178, 11, 432);
		panel_PAYSLIP.add(separator_1);


		JPanel panel_TAXSETTINGS = new JPanel();
		tabbedPane.addTab("TAX SETTINGS", null, panel_TAXSETTINGS, null);
		panel_TAXSETTINGS.setLayout(null);

		JTabbedPane tabbedPane_settings = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_settings.setBounds(12, 13, 834, 609);
		panel_TAXSETTINGS.add(tabbedPane_settings);

		JPanel panel_TAX = new JPanel();
		tabbedPane_settings.addTab("TAX", null, panel_TAX, null);
		panel_TAX.setLayout(null);

		JLabel lblTaxTable = new JLabel("TRAIN TAX TABLE");
		lblTaxTable.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTaxTable.setBounds(10, 13, 467, 39);
		panel_TAX.add(lblTaxTable);

		textField_taxMin = new JTextField();
		textField_taxMin.setBounds(158, 524, 116, 22);
		panel_TAX.add(textField_taxMin);
		textField_taxMin.setColumns(10);

		textField_tax_Max = new JTextField();
		textField_tax_Max.setBounds(158, 550, 116, 22);
		panel_TAX.add(textField_tax_Max);
		textField_tax_Max.setColumns(10);

		textField_TaxOnLowerLimit = new JTextField();
		textField_TaxOnLowerLimit.setBounds(436, 524, 116, 22);
		panel_TAX.add(textField_TaxOnLowerLimit);
		textField_TaxOnLowerLimit.setColumns(10);

		textField_TaxOnExcessOverLimit = new JTextField();
		textField_TaxOnExcessOverLimit.setBounds(436, 550, 116, 22);
		panel_TAX.add(textField_TaxOnExcessOverLimit);
		textField_TaxOnExcessOverLimit.setColumns(10);

		JScrollPane scrollPane_TAX = new JScrollPane();

		scrollPane_TAX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRowIndex=taxTable.getSelectedRow();
				DefaultTableModel model =  (DefaultTableModel) taxTable.getModel();
				textField_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
				textField_taxMin.setText(model.getValueAt(selectedRowIndex, 1).toString());
				textField_tax_Max.setText(model.getValueAt(selectedRowIndex, 2).toString());
				textField_TaxOnLowerLimit.setText(model.getValueAt(selectedRowIndex, 3).toString());
				textField_TaxOnExcessOverLimit.setText(model.getValueAt(selectedRowIndex, 4).toString());

			}


		});
		scrollPane_TAX.setBounds(10, 59, 809, 452);
		panel_TAX.add(scrollPane_TAX);

		taxTable = new JTable();
		scrollPane_TAX.setViewportView(taxTable);
		try {
			stmt = conn.createStatement();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		JButton btnLoadTable = new JButton("LOAD TABLE");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String showTax = "call allTaxT()";

					rs=stmt.executeQuery(showTax);
					taxTable.setModel(DbUtils.resultSetToTableModel(rs));


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}
		});
		btnLoadTable.setBounds(564, 523, 116, 25);
		panel_TAX.add(btnLoadTable);

		JButton btnNewButton_updateTax = new JButton("UPDATE");
		btnNewButton_updateTax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int value1 = Integer.parseInt( textField_id.getText());
					int value2 = Integer.parseInt( textField_taxMin.getText());
					double value3 = Double.parseDouble( textField_tax_Max.getText());
					double value4 = Double.parseDouble( textField_TaxOnLowerLimit.getText());
					double value5 = Double.parseDouble( textField_TaxOnExcessOverLimit.getText());

					String updateTax = "UPDATE Taxtable_Table set tax_Min = '"+value2+"',tax_Max='"+value3+"',"
							+ "tax_TaxOnLowerLimit='"+value4+"',tax_TaxOnExcessOverLimit='"+value5+"'where tax_id ='"+value1+"'";
					PreparedStatement pst = conn.prepareStatement(updateTax);
					stmt = conn.prepareCall(updateTax);
					stmt.execute(updateTax);
					System.out.println(updateTax);
//					JOptionPane.showConfirmDialog(null, "Updated");
					JOptionPane.showMessageDialog(null, "Updated");
//correct


				}catch(Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_updateTax.setBounds(687, 523, 130, 25);
		panel_TAX.add(btnNewButton_updateTax);

		textField_id = new JTextField();
		textField_id.setBounds(10, 524, 27, 22);
		panel_TAX.add(textField_id);
		textField_id.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tax Minimum");
		lblNewLabel_1.setBounds(60, 524, 86, 16);
		panel_TAX.add(lblNewLabel_1);

		JLabel lblTaxMaximum = new JLabel("Tax Maximum");
		lblTaxMaximum.setBounds(60, 553, 86, 16);
		panel_TAX.add(lblTaxMaximum);

		JLabel lblTaxOnLower = new JLabel("Tax on lower limit");
		lblTaxOnLower.setBounds(286, 527, 116, 16);
		panel_TAX.add(lblTaxOnLower);

		JLabel lblTaxOnExcess = new JLabel("Tax on Excess over limit");
		lblTaxOnExcess.setBounds(286, 553, 138, 16);
		panel_TAX.add(lblTaxOnExcess);
		try {
			stmt1 = conn.createStatement();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		JPanel panel_PHILHEALTH = new JPanel();
		tabbedPane_settings.addTab("PHILHEALTH", null, panel_PHILHEALTH, null);
		panel_PHILHEALTH.setLayout(null);

		JLabel lblPhilhealthTable = new JLabel("PHILHEALTH TABLE");
		lblPhilhealthTable.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblPhilhealthTable.setBounds(12, 13, 467, 39);
		panel_PHILHEALTH.add(lblPhilhealthTable);

		JScrollPane scrollPane_PHIL = new JScrollPane();
		scrollPane_PHIL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRowPhil=PHILTable.getSelectedRow();
				DefaultTableModel model1 =  (DefaultTableModel) PHILTable.getModel();
				textField_updtphilID.setText(model1.getValueAt(selectedRowPhil, 0).toString());
				textField_updtMIN.setText(model1.getValueAt(selectedRowPhil, 1).toString());
				textField_updtMAX.setText(model1.getValueAt(selectedRowPhil, 2).toString());
				textField_UPDTRATE.setText(model1.getValueAt(selectedRowPhil, 3).toString());

			}
		});
		scrollPane_PHIL.setBounds(12, 65, 809, 451);
		panel_PHILHEALTH.add(scrollPane_PHIL);

		PHILTable = new JTable();
		scrollPane_PHIL.setViewportView(PHILTable);
		try {
			stmt2 = conn.createStatement();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		JButton btnLoadPhilhealthTable = new JButton("LOAD PHILHEALTH TABLE");
		btnLoadPhilhealthTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String showphil = "call allTax_PHILV1Table()";

					rs2=stmt1.executeQuery(showphil);
					PHILTable.setModel(DbUtils.resultSetToTableModel(rs2));


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLoadPhilhealthTable.setBounds(635, 529, 182, 25);
		panel_PHILHEALTH.add(btnLoadPhilhealthTable);

		JButton btnNewButton_UPDATEPHIL = new JButton("UPDATE");
		btnNewButton_UPDATEPHIL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int PHIL1 = Integer.parseInt( textField_updtphilID.getText());
					float PHIL2 = Float.parseFloat( textField_updtMIN.getText());
					float PHIL3 = Float.parseFloat( textField_updtMAX.getText());
					float PHIL4 = Float.parseFloat( textField_UPDTRATE.getText());

					String updatePhil = "UPDATE Philhealth_tableV1 set  Philhealth_min='"+PHIL2+"', Philhealth_max='"+PHIL3+"',"
							+ " Philhealth_Premiumrate='"+PHIL4+"' where Philhealth_id ='"+PHIL1+"'";

					stmt1 = conn.prepareCall(updatePhil);
					stmt1.execute(updatePhil);
					System.out.println(updatePhil);
					JOptionPane.showMessageDialog(null, "Updated");



				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_UPDATEPHIL.setBounds(526, 529, 105, 25);
		panel_PHILHEALTH.add(btnNewButton_UPDATEPHIL);

		textField_updtphilID = new JTextField();
		textField_updtphilID.setBounds(12, 527, 27, 20);
		panel_PHILHEALTH.add(textField_updtphilID);
		textField_updtphilID.setColumns(10);

		textField_updtMIN = new JTextField();
		textField_updtMIN.setBounds(97, 529, 86, 20);
		panel_PHILHEALTH.add(textField_updtMIN);
		textField_updtMIN.setColumns(10);

		textField_updtMAX = new JTextField();
		textField_updtMAX.setBounds(239, 531, 86, 20);
		panel_PHILHEALTH.add(textField_updtMAX);
		textField_updtMAX.setColumns(10);

		textField_UPDTRATE = new JTextField();
		textField_UPDTRATE.setBounds(428, 529, 86, 20);
		panel_PHILHEALTH.add(textField_UPDTRATE);
		textField_UPDTRATE.setColumns(10);

		JLabel lblMin_1 = new JLabel("Min");
		lblMin_1.setBounds(64, 529, 56, 16);
		panel_PHILHEALTH.add(lblMin_1);

		JLabel lblMax_1 = new JLabel("Max");
		lblMax_1.setBounds(195, 529, 56, 16);
		panel_PHILHEALTH.add(lblMax_1);

		JLabel lblPremiumRate = new JLabel("Premium Rate");
		lblPremiumRate.setBounds(337, 529, 105, 16);
		panel_PHILHEALTH.add(lblPremiumRate);

		JPanel panel_pagibig = new JPanel();
		tabbedPane_settings.addTab("Pagibig", null, panel_pagibig, null);
		panel_pagibig.setLayout(null);

		JLabel lblPagibigTable = new JLabel("PAGIBIG TABLE");
		lblPagibigTable.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblPagibigTable.setBounds(12, 13, 467, 39);
		panel_pagibig.add(lblPagibigTable);

		JScrollPane scrollPane_PAGIBIG = new JScrollPane();
		scrollPane_PAGIBIG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRowPhil=PAGIBIGTable.getSelectedRow();
				DefaultTableModel model1 =  (DefaultTableModel) PAGIBIGTable.getModel();
				textField_idpagibig.setText(model1.getValueAt(selectedRowPhil, 0).toString());
				textField_min.setText(model1.getValueAt(selectedRowPhil, 1).toString());
				textField_pagibigmax.setText(model1.getValueAt(selectedRowPhil, 2).toString());
				textField_employeeSharepagibig.setText(model1.getValueAt(selectedRowPhil, 3).toString());
				textField_employersharepagibig.setText(model1.getValueAt(selectedRowPhil, 4).toString());

			}
		});
		scrollPane_PAGIBIG.setBounds(12, 65, 809, 437);
		panel_pagibig.add(scrollPane_PAGIBIG);


		PAGIBIGTable = new JTable();
		scrollPane_PAGIBIG.setViewportView(PAGIBIGTable);
		try {
			stmt3 = conn.createStatement();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		JButton btnNewButton_LOADpAGIBIG = new JButton("LOAD PAGIBIG TABLE");
		btnNewButton_LOADpAGIBIG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String showPGIBIG = "call allTax_PAGibigT()";

					rs3=stmt3.executeQuery(showPGIBIG);
					PAGIBIGTable.setModel(DbUtils.resultSetToTableModel(rs3));


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_LOADpAGIBIG.setBounds(662, 513, 159, 25);
		panel_pagibig.add(btnNewButton_LOADpAGIBIG);

		JButton btnUpdate_PAGIBIG = new JButton("UPDATE");
		btnUpdate_PAGIBIG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int PAGIBIG1 = Integer.parseInt( textField_idpagibig.getText());
					float PAGIBIG2 = Float.parseFloat( textField_min.getText());
					float PAGIBIG3 = Float.parseFloat( textField_pagibigmax.getText());
					float PAGIBIG4 = Float.parseFloat( textField_employeeSharepagibig.getText());
					float PAGIBIG5 = Float.parseFloat( textField_employersharepagibig.getText());

					String updatepagibig = "UPDATE Pagibig_table set  Pagibig_min='"+PAGIBIG2+"', Pagibig_max='"+PAGIBIG3+"',"
							+ " Pagibig_employeeShare='"+PAGIBIG4+"',  Pagibig_employerShare='"+PAGIBIG5+"' where Pagibig_id ='"+PAGIBIG1+"'";

					stmt1 = conn.prepareCall(updatepagibig);
					stmt1.execute(updatepagibig);
					System.out.println(updatepagibig);
					JOptionPane.showMessageDialog(null, "Updated");



				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate_PAGIBIG.setBounds(662, 543, 159, 25);
		panel_pagibig.add(btnUpdate_PAGIBIG);

		textField_idpagibig = new JTextField();
		textField_idpagibig.setBounds(12, 515, 39, 20);
		panel_pagibig.add(textField_idpagibig);
		textField_idpagibig.setColumns(10);

		textField_min = new JTextField();
		textField_min.setBounds(165, 515, 86, 20);
		panel_pagibig.add(textField_min);
		textField_min.setColumns(10);

		textField_pagibigmax = new JTextField();
		textField_pagibigmax.setBounds(165, 548, 86, 20);
		panel_pagibig.add(textField_pagibigmax);
		textField_pagibigmax.setColumns(10);

		textField_employeeSharepagibig = new JTextField();
		textField_employeeSharepagibig.setBounds(433, 513, 86, 20);
		panel_pagibig.add(textField_employeeSharepagibig);
		textField_employeeSharepagibig.setColumns(10);

		textField_employersharepagibig = new JTextField();
		textField_employersharepagibig.setBounds(433, 548, 86, 20);
		panel_pagibig.add(textField_employersharepagibig);
		textField_employersharepagibig.setColumns(10);

		JLabel lblPagmin = new JLabel("Pagibig min");
		lblPagmin.setBounds(81, 517, 82, 16);
		panel_pagibig.add(lblPagmin);

		JLabel lblPagibigMax = new JLabel("Pagibig Max");
		lblPagibigMax.setBounds(81, 550, 86, 16);
		panel_pagibig.add(lblPagibigMax);

		JLabel lblPagIbig = new JLabel("Pag ibig employee Share");
		lblPagIbig.setBounds(263, 517, 158, 16);
		panel_pagibig.add(lblPagIbig);

		JLabel lblPagIbigEmployer = new JLabel("Pag ibig employer Share");
		lblPagIbigEmployer.setBounds(263, 550, 158, 16);
		panel_pagibig.add(lblPagIbigEmployer);



		JPanel panel_SSS = new JPanel();
		tabbedPane_settings.addTab("SSS", null, panel_SSS, null);
		panel_SSS.setLayout(null);

		JLabel lblSssTable = new JLabel("SSS TABLE");
		lblSssTable.setBounds(12, 13, 467, 39);
		lblSssTable.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_SSS.add(lblSssTable);

		JScrollPane scrollPane_SSS = new JScrollPane();

		scrollPane_SSS.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				int selectedRowSSS=SSSTable.getSelectedRow();
				DefaultTableModel model1 =  (DefaultTableModel) SSSTable.getModel();
				textField_sssID.setText(model1.getValueAt(selectedRowSSS, 0).toString());
				textField_sssMIN.setText(model1.getValueAt(selectedRowSSS, 1).toString());
				textField_SSSMAX.setText(model1.getValueAt(selectedRowSSS, 2).toString());
				textField_SSSEC.setText(model1.getValueAt(selectedRowSSS, 3).toString());
				textField_SSSER.setText(model1.getValueAt(selectedRowSSS, 4).toString());
				textField_SSSEE.setText(model1.getValueAt(selectedRowSSS, 5).toString());
				textField_SSSTOTAL.setText(model1.getValueAt(selectedRowSSS, 6).toString());
			}
		});
		scrollPane_SSS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRowSSS=SSSTable.getSelectedRow();
				DefaultTableModel model1 =  (DefaultTableModel) SSSTable.getModel();
				textField_sssID.setText(model1.getValueAt(selectedRowSSS, 0).toString());
				textField_sssMIN.setText(model1.getValueAt(selectedRowSSS, 1).toString());
				textField_SSSMAX.setText(model1.getValueAt(selectedRowSSS, 2).toString());
				textField_SSSEC.setText(model1.getValueAt(selectedRowSSS, 3).toString());
				textField_SSSER.setText(model1.getValueAt(selectedRowSSS, 4).toString());
				textField_SSSEE.setText(model1.getValueAt(selectedRowSSS, 5).toString());
				textField_SSSTOTAL.setText(model1.getValueAt(selectedRowSSS, 6).toString());

			}
		});				
		scrollPane_SSS.setBounds(12, 58, 807, 446);
		panel_SSS.add(scrollPane_SSS);

		SSSTable = new JTable();
		scrollPane_SSS.setViewportView(SSSTable);

		JButton btnNewButton_LoadSSS = new JButton("Load SSS Table");
		btnNewButton_LoadSSS.setBounds(683, 517, 136, 25);
		btnNewButton_LoadSSS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				try {
					String showSSS = "call allTax_sssT()";

					rs1=stmt1.executeQuery(showSSS);
					SSSTable.setModel(DbUtils.resultSetToTableModel(rs1));


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_SSS.add(btnNewButton_LoadSSS);

		JButton btnNewButton_Update = new JButton("Update");
		btnNewButton_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int SSS1 = Integer.parseInt( textField_sssID.getText());
					double SSS2 = Integer.parseInt( textField_sssMIN.getText());
					double SSS3 = Double.parseDouble( textField_SSSMAX.getText());
					double SSS4 = Double.parseDouble( textField_SSSEC.getText());
					double SSS5 = Double.parseDouble( textField_SSSER.getText());
					double SSS6 = Double.parseDouble( textField_SSSEE.getText());
					double SSS7 = Double.parseDouble( textField_SSSTOTAL.getText());
					String updatesss = "UPDATE sss_table set sss_min='"+SSS2+"', sss_max='"+SSS3+"', sss_ec='"+SSS4+"',"
							+ " sss_er='"+SSS5+"', sss_ee='"+SSS6+"', sss_total='"+SSS7+"' where sss_id ='"+SSS1+"'";

					stmt1 = conn.prepareCall(updatesss);
					stmt1.execute(updatesss);
					System.out.println(updatesss);
					JOptionPane.showMessageDialog(null, "Updated");



				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_Update.setBounds(683, 542, 136, 25);
		panel_SSS.add(btnNewButton_Update);

		textField_sssID = new JTextField();
		textField_sssID.setBounds(12, 517, 28, 22);
		panel_SSS.add(textField_sssID);
		textField_sssID.setColumns(10);

		textField_sssMIN = new JTextField();
		textField_sssMIN.setBounds(106, 517, 116, 22);
		panel_SSS.add(textField_sssMIN);
		textField_sssMIN.setColumns(10);

		textField_SSSMAX = new JTextField();
		textField_SSSMAX.setBounds(106, 543, 116, 22);
		panel_SSS.add(textField_SSSMAX);
		textField_SSSMAX.setColumns(10);

		textField_SSSEC = new JTextField();
		textField_SSSEC.setBounds(269, 517, 116, 22);
		panel_SSS.add(textField_SSSEC);
		textField_SSSEC.setColumns(10);

		textField_SSSER = new JTextField();
		textField_SSSER.setBounds(269, 543, 116, 22);
		panel_SSS.add(textField_SSSER);
		textField_SSSER.setColumns(10);

		textField_SSSEE = new JTextField();
		textField_SSSEE.setBounds(487, 517, 116, 22);
		panel_SSS.add(textField_SSSEE);
		textField_SSSEE.setColumns(10);

		textField_SSSTOTAL = new JTextField();
		textField_SSSTOTAL.setBounds(487, 543, 116, 22);
		panel_SSS.add(textField_SSSTOTAL);
		textField_SSSTOTAL.setColumns(10);

		JLabel lblMin = new JLabel("MIN");
		lblMin.setBounds(52, 517, 56, 16);
		panel_SSS.add(lblMin);

		JLabel lblMax = new JLabel("MAX");
		lblMax.setBounds(52, 546, 56, 16);
		panel_SSS.add(lblMax);

		JLabel lblEc = new JLabel("EC");
		lblEc.setBounds(234, 517, 56, 16);
		panel_SSS.add(lblEc);

		JLabel lblEr = new JLabel("ER");
		lblEr.setBounds(234, 539, 56, 16);
		panel_SSS.add(lblEr);

		JLabel lblEe = new JLabel("EE");
		lblEe.setBounds(419, 517, 56, 16);
		panel_SSS.add(lblEe);

		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(419, 546, 56, 16);
		panel_SSS.add(lblTotal);







		JButton btnHome = new JButton("Home");
		btnHome.setBounds(12, 13, 97, 25);
		contentPane.add(btnHome);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminMenuGui adminGui = new adminMenuGui();
				dispose();
				adminGui.setVisible(true);
				adminGui.setTitle("Admin Menu");

			}
		});
	}
}
