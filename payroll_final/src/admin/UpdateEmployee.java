package admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Main.ConnectDB;
import org.jdesktop.xswingx.JXSearchField;
import org.jdesktop.xswingx.PromptSupport;
import org.jdesktop.xswingx.PromptSupport.FocusBehavior;
import org.jdesktop.xswingx.JXSearchField.LayoutStyle;
import javax.swing.JButton;

public class UpdateEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField textField_firstname;
	private JTextField textField_lastname;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_age;
	private JTextField textField_city;
	private JTextField textField_email;
	private JTextField textField_Mobile;
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmployee frame = new UpdateEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateEmployee() {
		conn = ConnectDB.doConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 527);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 50, 927, 8);
		contentPane.add(separator);
		
		JLabel lblFirstname = new JLabel("Firstname: ");
		lblFirstname.setBounds(20, 189, 118, 20);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname: ");
		lblLastname.setBounds(20, 225, 118, 20);
		contentPane.add(lblLastname);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(20, 117, 118, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(20, 153, 118, 20);
		contentPane.add(lblPassword);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(20, 259, 118, 20);
		contentPane.add(lblDateOfBirth);
		
		textField_firstname = new JTextField();
		textField_firstname.setBounds(148, 186, 146, 26);
		contentPane.add(textField_firstname);
		textField_firstname.setColumns(10);
		
		textField_lastname = new JTextField();
		textField_lastname.setBounds(148, 222, 146, 26);
		contentPane.add(textField_lastname);
		textField_lastname.setColumns(10);
		
		textField_username = new JTextField();
		textField_username.setBounds(148, 114, 146, 26);
		contentPane.add(textField_username);
		textField_username.setColumns(10);
		
		textField_password = new JTextField();
		textField_password.setBounds(148, 150, 146, 26);
		contentPane.add(textField_password);
		textField_password.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(148, 253, 146, 26);
		contentPane.add(dateChooser);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setBounds(20, 295, 69, 20);
		contentPane.add(lblAge);
		
		textField_age = new JTextField();
		textField_age.setBounds(148, 295, 146, 26);
		contentPane.add(textField_age);
		textField_age.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender: ");
		lblGender.setBounds(20, 331, 118, 20);
		contentPane.add(lblGender);
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(148, 327, 155, 29);
		contentPane.add(rdbtnMale);
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(148, 364, 155, 29);
		contentPane.add(rdbtnFemale);
		ButtonGroup bG = new ButtonGroup();
	     bG.add(rdbtnMale);
	     bG.add(rdbtnFemale);
		
		JLabel lblCountry = new JLabel("Country: ");
		lblCountry.setBounds(20, 400, 118, 20);
		contentPane.add(lblCountry);
		
		String[] c= new String[]{"US", "PH", "CHINA"};
		JComboBox comboBox = new JComboBox(c);
		comboBox.setBounds(148, 400, 146, 26);
		contentPane.add(comboBox);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(372, 105, 12, 338);
		contentPane.add(separator_1);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(413, 117, 103, 20);
		contentPane.add(lblAddress);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(530, 114, 235, 77);
		contentPane.add(textArea);
		
		JLabel lblCity = new JLabel("City: ");
		lblCity.setBounds(413, 204, 103, 20);
		contentPane.add(lblCity);
		
		textField_city = new JTextField();
		textField_city.setBounds(530, 201, 146, 26);
		contentPane.add(textField_city);
		textField_city.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(413, 240, 103, 20);
		contentPane.add(lblEmail);
		
		textField_email = new JTextField();
		textField_email.setBounds(530, 237, 146, 26);
		contentPane.add(textField_email);
		textField_email.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile: ");
		lblMobile.setBounds(413, 276, 69, 20);
		contentPane.add(lblMobile);
		
		textField_Mobile = new JTextField();
		textField_Mobile.setBounds(530, 273, 146, 26);
		contentPane.add(textField_Mobile);
		textField_Mobile.setColumns(10);
		
//		JLabel lblEmployeeType = new JLabel("Employee Type: ");
//		lblEmployeeType.setBounds(399, 331, 83, 20);
//		contentPane.add(lblEmployeeType);
//		ButtonGroup type = new ButtonGroup();
		
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(540, 309, 12, 132);
		contentPane.add(separator_2);
		
//		JLabel lblStatusId = new JLabel("Status ID:");
//		lblStatusId.setBounds(567, 331, 109, 20);
//		contentPane.add(lblStatusId);
//		JRadioButton rdbtnActive = new JRadioButton("2");
//		rdbtnActive.setBounds(563, 364, 146, 29);
//		contentPane.add(rdbtnActive);
//		JRadioButton rdbtnInactive = new JRadioButton("1");
//		rdbtnInactive.setBounds(563, 396, 135, 29);
//		contentPane.add(rdbtnInactive);
//		ButtonGroup Stat = new ButtonGroup();
//		Stat.add(rdbtnActive);
//		Stat.add(rdbtnInactive);
		
		JLabel lblupdate = new JLabel("");
		lblupdate.setBounds(252, 65, 253, 14);
		contentPane.add(lblupdate);
		
		JLabel labelnotFOund = new JLabel("");
		labelnotFOund.setBounds(148, 59, 109, 20);
		contentPane.add(labelnotFOund);
		
		
		
		
		
		
		JXSearchField srchfldEnterempId = new JXSearchField();
		srchfldEnterempId.setToolTipText("Search");
		srchfldEnterempId.setPromptFontStyle(2);
		srchfldEnterempId.setPrompt("Enter Employee ID: ");
		srchfldEnterempId.setLayoutStyle(LayoutStyle.VISTA);
		srchfldEnterempId.setUseSeperatePopupButton(true);
		srchfldEnterempId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "Select * from employee_Table  where emp_id = '"+srchfldEnterempId.getText().toString()+"' "; 
//					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
//					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
//					ResultSet rs = stmt.executeQuery(sql);


					
						rs = stmt.executeQuery(sql);
						System.out.println(sql);
						if (!rs.isBeforeFirst()) {
						    System.out.println("no data found");
						    labelnotFOund.setText("No Record Found");
						    java.util.Date d = new java.util.Date();
						    d.toString();
						    textField_username.setText("");
						    textField_password.setText("");
						    textField_firstname.setText("");
						    textField_lastname.setText("");
						    textField_city.setText("");
						    textField_email.setText("");
							textField_Mobile.setText("");
							textField_age.setText("");
							dateChooser.setDate(d);
							comboBox.setSelectedItem("Select Country");
							
							textArea.setText("");
							rdbtnMale.setSelected(false);
							rdbtnFemale.setSelected(false);
							
//							rdbtnAdmin.setSelected(false);
//							rdbtnInactive.setSelected(false);
//							
//							rdbtnActive.setSelected(false);
//							rdbtnInactive.setSelected(false);
							lblupdate.setText("");
							
						    
						    
						} 
						else {
						
						while(rs.next()){
							labelnotFOund.setText("");
							
							textField_username.setText(rs.getString("username"));
							textField_password.setText(rs.getString("passwords"));
							textField_firstname.setText(rs.getString("first_name"));
							textField_lastname.setText(rs.getString("last_name"));
							dateChooser.setDate(rs.getDate("dob"));
							textField_age.setText(rs.getString("age"));
							comboBox.setSelectedItem(rs.getString("country"));
							System.out.println(rs.getString("country"));
						
							textField_city.setText(rs.getString("city"));
							textArea.setText(rs.getString("address"));
							textField_email.setText(rs.getString("email"));
							textField_Mobile.setText(rs.getString("mobile_no"));
						
						
							
						String gen= rs.getString("Gender");
							System.out.println(rs.getString("Gender"));
						if(gen.equals("male")){
							rdbtnMale.setSelected(true);
							rdbtnFemale.setSelected(false);
						}
						else{
							rdbtnFemale.setSelected(true);
							rdbtnMale.setSelected(false);
						}
//						int type= rs.getInt("emp_type");	
//						if(type == 1){
//							rdbtnAdmin.setSelected(true);
//							rdbtnEmployee.setSelected(false);
//						}
//						else{
//							rdbtnAdmin.setSelected(false);
//							rdbtnEmployee.setSelected(true);
//						}
//						int status= rs.getInt("status_id");	
//						System.out.println(status);
//						if(type == 1){
//							rdbtnActive.setSelected(true);
//							rdbtnInactive.setSelected(false);
//						}
//						else{
//							rdbtnActive.setSelected(false);
//							rdbtnInactive.setSelected(true);
//						}								
						
						
						}
					}
						
						
						
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		srchfldEnterempId.setPrompt("Enter Employee ID\r\n");
		srchfldEnterempId.setBounds(20, 59, 118, 20);
		contentPane.add(srchfldEnterempId);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fname = textField_firstname.getText();
				String lname = textField_lastname.getText();
				String Usern = textField_username.getText().toString();
				String Passw = textField_password.getText().toString();
				String City = textField_city.getText();
				String emil =  textField_email.getText().toString();
				String num = textField_Mobile.getText().toString();
				String Age = textField_age.getText().toString();
				java.sql.Date dat = new java.sql.Date(dateChooser.getDate().getTime());
				String country = comboBox.getSelectedItem().toString();
				String add = textArea.getText();
				String gen  =bG.getSelection().getActionCommand();
//			
				try {
					String sql = "update employee_Table set username = '"+Usern+"',passwords = '"+Passw+"',last_name = '"+lname+"',first_name ='"+fname+"',dob = '"+dat+"',gender = '"+gen+"',country ='"+country+ "', city = '"+City+"', address ='"+add+"',email='"+ emil+"', mobile_no = '"+num+"' where emp_id = '"+srchfldEnterempId.getText().toString()+"' ";
					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
					stmt.executeUpdate(sql);
					System.out.println(sql);
					lblupdate.setText("Your Records are Successfully Updated");
								
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						//JOptionPane.showMessageDialog(null, e1);
						
					}
				}
		});
		btnNewButton.setBounds(264, 454, 89, 23);
		contentPane.add(btnNewButton);
		btnCancel.setBounds(382, 454, 89, 23);
		contentPane.add(btnCancel);
		
		
		
		
		
		JXSearchField searchField = new JXSearchField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "Select * from employee_Table where emp_id = '"+srchfldEnterempId.getText().toString()+"' "; 
//					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
//					PreparedStatement pst = conn.prepareStatement(sql);
//					stmt = conn.prepareCall(sql);
//					ResultSet rs = stmt.executeQuery(sql);


					
						rs = stmt.executeQuery(sql);
						System.out.println(sql);
						if (!rs.isBeforeFirst()) {
						    System.out.println("no data found");
						    labelnotFOund.setText("No Record Found");
						    java.util.Date d = new java.util.Date();
						    d.toString();
						    textField_firstname.setText("");
						    textField_lastname.setText("");
						    textField_username.setText("");
						    textField_password.setText("");
						    textField_city.setText("");
						    textField_email.setText("");
							textField_Mobile.setText("");
							textField_age.setText("");
							dateChooser.setDate(d);
							comboBox.setSelectedItem("Select Country");
							
							textArea.setText("");
							rdbtnMale.setSelected(false);
							rdbtnFemale.setSelected(false);
							
//							rdbtnAdmin.setSelected(false);
//							rdbtnInactive.setSelected(false);
//							
//							rdbtnActive.setSelected(false);
//							rdbtnInactive.setSelected(false);
							
						    
						    
						} 
						else {
						
						while(rs.next()){
							
							textField_firstname.setText(rs.getString("first_name"));
							textField_lastname.setText(rs.getString("last_name"));
							textField_username.setText(rs.getString("username"));
							textField_password.setText(rs.getString("passwords"));
							dateChooser.setDate(rs.getDate("dob"));
							textField_age.setText(rs.getString("age"));
						
							String gen= rs.getString("gender");
							
						if(gen.equals("Female")){
							rdbtnMale.setSelected(true);
							rdbtnFemale.setSelected(false);
						}
						else{
							rdbtnFemale.setSelected(true);
							rdbtnMale.setSelected(false);
						}
							String type = rs.getString("emp_type");
//						if(type.equals("1")){
//							rdbtnAdmin.setSelected(true);
//							rdbtnEmployee.setSelected(false);
//						}else if(type.equals("2")) {
//							rdbtnAdmin.setSelected(true);
//							rdbtnEmployee.setSelected(false);
//						}
//						else{
//							rdbtnAdmin.setSelected(true);
//							rdbtnEmployee.setSelected(false);
//						}
//						String Status = rs.getString("status_id");
//						if(Status.equals("1")){
//							rdbtnActive.setSelected(true);
//							rdbtnInactive.setSelected(false);
//						}
//						else{
//							rdbtnActive.setSelected(true);
//							rdbtnInactive.setSelected(false);
//						}
//						
						
						
						comboBox.setSelectedItem(rs.getString("country"));
						textField_city.setText(rs.getString("city"));
						textArea.setText(rs.getString("address"));
						textField_email.setText(rs.getString("email"));
						textField_Mobile.setText(rs.getString("mobile_no"));
						
						
						}
						
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		
	}
}
