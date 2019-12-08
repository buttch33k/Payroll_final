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
	
	int grossincome;

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

		textField_salary = new JTextField();
		textField_salary.setBounds(99, 126, 86, 20);
		panel_PAYSLIP.add(textField_salary);
		textField_salary.setColumns(10);


		JXSearchField searchFieldIDpayslip = new JXSearchField();
		searchFieldIDpayslip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "Select * from employee_Table e INNER join JobTitle_Table j on e.emp_id =j.job_id where emp_id = '"+searchFieldIDpayslip.getText().toString()+"'";
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println(sql);
					
//					static double grossincome = textField_salary(rs.getString("job_salary"));
					
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
						String sql2 = ("Select tax_Min,tax_Max,tax_TaxOnLowerLimit,tax_TaxOnExcessOverLimit from employee_Table");
						Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
						stmt1 = conn.prepareCall(sql2);
						ResultSet rs2 = stmt.executeQuery(sql2);
						rs2 = stmt1.executeQuery(sql2);
						System.out.println(sql2);
						String taxTable[][] = new String[6][4];
						int grossincome = Integer.parseInt(String.valueOf(EmployeeName.gi).toString());
						int i = 0;
						int j = 0;
						
						

						while(rs2.next()){
							
							
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
//						 0 250000 0 0
						 System.out.println(taxTable[0][0]+" "+taxTable[0][1]+" "+taxTable[0][2]+" "+taxTable[0][3]);
//						 250000 400000 0 0.2
						 System.out.println(taxTable[1][0]+" "+taxTable[1][1]+" "+taxTable[1][2]+" "+taxTable[1][3]);
//						 400000 800000 30000 0.25
						 System.out.println(taxTable[2][0]+" "+taxTable[2][1]+" "+taxTable[2][2]+" "+taxTable[2][3]);
//						 800000 2000000 130000 0.3
						 System.out.println(taxTable[3][0]+" "+taxTable[3][1]+" "+taxTable[3][2]+" "+taxTable[3][3]);
//						 2000000 8000000 490000 0.32
						 System.out.println(taxTable[4][0]+" "+taxTable[4][1]+" "+taxTable[4][2]+" "+taxTable[4][3]);
//						 8000000 8000001 2410000 0.35
						 System.out.println(taxTable[5][0]+" "+taxTable[5][1]+" "+taxTable[5][2]+" "+taxTable[5][3]);
						 System.out.println("");
//						 1
						if (grossincome <= Integer.parseInt(taxTable[0][1])) {
				            taxrate =(grossincome);
				        }
//						2
				        else if((grossincome >= Integer.parseInt(taxTable[1][0])) && (grossincome <= Integer.parseInt(taxTable[1][1]))){
				            taxrate = (grossincome - Integer.parseInt(taxTable[1][0]))*  Double.parseDouble(taxTable[1][3]);
				        }
//						3
				        else if((grossincome >= Integer.parseInt(taxTable[2][0])) && (grossincome <= Integer.parseInt(taxTable[2][1]))){
				            taxrate = (((Integer.parseInt(taxTable[2][0]) -grossincome  ) * Double.parseDouble(taxTable[2][3]))+Integer.parseInt(taxTable[2][2]));
				        }
//						415000
				        else if((grossincome >= Integer.parseInt(taxTable[3][0])) && (grossincome <= Integer.parseInt(taxTable[3][1]))){
				            taxrate = (((grossincome - Integer.parseInt(taxTable[3][0])) * Double.parseDouble(taxTable[3][3]))+Integer.parseInt(taxTable[3][2]));
				        }
//						5
						
				        else if((grossincome >= Integer.parseInt(taxTable[4][0])) && (grossincome <= Integer.parseInt(taxTable[4][1]))){
				            taxrate = (((grossincome - Integer.parseInt(taxTable[4][0])) * Double.parseDouble(taxTable[4][3]))+Integer.parseInt(taxTable[4][2]));
				        }
//						6
				        else if(grossincome > Integer.parseInt(taxTable[5][0])) {
				        	 taxrate = (((grossincome - Integer.parseInt(taxTable[5][0])) * Double.parseDouble(taxTable[5][3]))+Integer.parseInt(taxTable[5][2]));
				        }else {
							
						}
						
						
						
				  
						 System.out.println(taxrate);
						 double montlyTaxrate = taxrate/12;
//						 textField_tax.double.parseDouble(montlyTaxrate);
						 System.out.println(montlyTaxrate);
						 
					
			}




				}catch (Exception e2) {
				}
				// TODO: handle exception
			}



		});
		searchFieldIDpayslip.setPrompt("Enter Employee Id");
		searchFieldIDpayslip.setBounds(10, 11, 128, 20);
		panel_PAYSLIP.add(searchFieldIDpayslip);



		JLabel lblJob = new JLabel("Job");
		lblJob.setBounds(10, 104, 46, 14);
		panel_PAYSLIP.add(lblJob);

		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(10, 129, 46, 14);
		panel_PAYSLIP.add(lblSalary);


	}
}
