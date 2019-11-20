package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Main.ConnectDB;
import Main.EmployeeName;
import Main.LoginGui;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.jdesktop.xswingx.JXSearchField;
import org.jdesktop.xswingx.JXSearchField.LayoutStyle;

public class adminMenuGui extends JFrame {

	private JPanel contentPane;
	
	
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_firstname;
	private JTextField textField_lastname;
	private JTextField textField_age;
	private JTextField textField_city;
	private JTextField textField_email;
	private JTextField textField_Mobile;
	private JTextField textField_emptype;
	private JTextField textField_stat;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String MOBILE_PATTERN = "\\d{10}";
	private JTextField textField_Username;
	private JTextField textField_Password;
	private JTextField textField_Firstname;
	private JTextField textField_Lastname;
	private JTextField textField_Age;
	private JTextField textField_City;
	private JTextField textField_Email;
	private JTextField textField_mobil;
	private JTextField textField_Empt;
	private JTextField textField_Stat;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminMenuGui frame = new adminMenuGui();
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
	public adminMenuGui() {
		conn = ConnectDB.doConnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 738, 445);
		contentPane.add(tabbedPane);
		
		JPanel panel_home = new JPanel();
		panel_home.setToolTipText("");
		tabbedPane.addTab("Home", null, panel_home, null);
		panel_home.setLayout(null);
		
		JLabel lblhiz = new JLabel("");
		lblhiz.setText(String.valueOf(EmployeeName.emp_lstname).toString());
		lblhiz.setBounds(114, 11, 46, 14);
		panel_home.add(lblhiz);
		
		JLabel label_39 = new JLabel("Logged in As:");
		label_39.setBounds(10, 11, 80, 14);
		panel_home.add(label_39);
		
		JLabel label_40 = new JLabel("Employee #:");
		label_40.setBounds(10, 36, 71, 14);
		panel_home.add(label_40);
		
		JLabel label_41 = new JLabel("null");
		label_41.setText(String.valueOf(EmployeeName.empid).toString());
		label_41.setBounds(114, 36, 101, 14);
		panel_home.add(label_41);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginGui frame = new LoginGui();
			}
		});
		btnLogOut.setBounds(31, 61, 89, 23);
		panel_home.add(btnLogOut);
		
		
		
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Employee Settings", null, panel, null);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 11, 713, 395);
		panel.add(tabbedPane_1);
		
		JPanel panel_addEmp = new JPanel();
		tabbedPane_1.addTab("Add Employee", null, panel_addEmp, null);
		panel_addEmp.setLayout(null);
		
		JLabel label = new JLabel("Username: ");
		label.setBounds(10, 56, 118, 20);
		panel_addEmp.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 37, 688, 2);
		panel_addEmp.add(separator);
		
		JLabel label_1 = new JLabel("Password: ");
		label_1.setBounds(10, 87, 118, 20);
		panel_addEmp.add(label_1);
		
		JLabel label_2 = new JLabel("Firstname: ");
		label_2.setBounds(10, 118, 118, 20);
		panel_addEmp.add(label_2);
		
		JLabel label_3 = new JLabel("Lastname: ");
		label_3.setBounds(10, 149, 118, 20);
		panel_addEmp.add(label_3);
		
		JLabel label_4 = new JLabel("Date of Birth:");
		label_4.setBounds(10, 180, 118, 20);
		panel_addEmp.add(label_4);
		
		JLabel label_5 = new JLabel("Age: ");
		label_5.setBounds(10, 208, 69, 20);
		panel_addEmp.add(label_5);
		
		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setBounds(138, 53, 146, 26);
		panel_addEmp.add(textField_username);
		
		textField_password = new JTextField();
		textField_password.setColumns(10);
		textField_password.setBounds(138, 81, 146, 26);
		panel_addEmp.add(textField_password);
		
		textField_firstname = new JTextField();
		textField_firstname.setColumns(10);
		textField_firstname.setBounds(138, 112, 146, 26);
		panel_addEmp.add(textField_firstname);
		
		textField_lastname = new JTextField();
		textField_lastname.setColumns(10);
		textField_lastname.setBounds(138, 143, 146, 26);
		panel_addEmp.add(textField_lastname);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(138, 174, 146, 26);
		panel_addEmp.add(dateChooser);
		
		textField_age = new JTextField();
		textField_age.setColumns(10);
		textField_age.setBounds(138, 205, 146, 26);
		panel_addEmp.add(textField_age);
		
		JLabel label_6 = new JLabel("Gender: ");
		label_6.setBounds(10, 241, 118, 20);
		panel_addEmp.add(label_6);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(138, 238, 146, 20);
		panel_addEmp.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(138, 261, 155, 20);
		panel_addEmp.add(rdbtnFemale);
		
		ButtonGroup bG = new ButtonGroup();
	     bG.add(rdbtnMale);
	     bG.add(rdbtnFemale);
		
		JLabel label_7 = new JLabel("Country: ");
		label_7.setBounds(10, 284, 118, 20);
		panel_addEmp.add(label_7);
		
		String[] c= new String[]{"US", "PH", "CHINA"};
		JComboBox comboBox = new JComboBox(c);
		comboBox.setBounds(138, 284, 146, 26);
		panel_addEmp.add(comboBox);
		
				
		JLabel label_8 = new JLabel("Address: ");
		label_8.setBounds(383, 56, 103, 20);
		panel_addEmp.add(label_8);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(443, 54, 235, 56);
		panel_addEmp.add(textArea);
		
		textField_city = new JTextField();
		textField_city.setColumns(10);
		textField_city.setBounds(443, 118, 146, 26);
		panel_addEmp.add(textField_city);
		
		JLabel label_9 = new JLabel("City: ");
		label_9.setBounds(383, 121, 50, 20);
		panel_addEmp.add(label_9);
		
		JLabel label_10 = new JLabel("Email: ");
		label_10.setBounds(383, 152, 50, 20);
		panel_addEmp.add(label_10);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(443, 149, 146, 26);
		panel_addEmp.add(textField_email);
		
		JLabel label_11 = new JLabel("Mobile: ");
		label_11.setBounds(383, 183, 50, 20);
		panel_addEmp.add(label_11);
		
		textField_Mobile = new JTextField();
		textField_Mobile.setColumns(10);
		textField_Mobile.setBounds(443, 180, 146, 26);
		panel_addEmp.add(textField_Mobile);
		
		JLabel label_12 = new JLabel("Employee type:");
		label_12.setBounds(363, 217, 100, 14);
		panel_addEmp.add(label_12);
		
		textField_emptype = new JTextField();
		textField_emptype.setColumns(10);
		textField_emptype.setBounds(473, 214, 35, 20);
		panel_addEmp.add(textField_emptype);
		
		JLabel label_13 = new JLabel("[1] Admin");
		label_13.setBounds(383, 241, 103, 14);
		panel_addEmp.add(label_13);
		
		JLabel label_14 = new JLabel("[2] Employee");
		label_14.setBounds(383, 264, 103, 14);
		panel_addEmp.add(label_14);
		
		JLabel label_15 = new JLabel("Employee Status:");
		label_15.setBounds(518, 217, 104, 14);
		panel_addEmp.add(label_15);
		
		textField_stat = new JTextField();
		textField_stat.setColumns(10);
		textField_stat.setBounds(632, 214, 35, 20);
		panel_addEmp.add(textField_stat);
		
		JLabel label_16 = new JLabel("[1] Active");
		label_16.setBounds(533, 244, 89, 14);
		panel_addEmp.add(label_16);
		
		JLabel label_17 = new JLabel("[2] Inactive");
		label_17.setBounds(533, 264, 89, 14);
		panel_addEmp.add(label_17);
		
		JLabel label_18 = new JLabel("[3] Deactivate");
		label_18.setBounds(533, 287, 89, 14);
		panel_addEmp.add(label_18);
		
		JLabel lblrecord_suc = new JLabel("");
		lblrecord_suc.setBounds(282, 12, 169, 14);
		panel_addEmp.add(lblrecord_suc);
		
		JButton button = new JButton("Register Employeee");
		button.addActionListener(new ActionListener() {
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
				String addres = textArea.getText();
				String gen  =bG.getSelection().getActionCommand();
				String empT = textField_emptype.getText().toString();
				String empStat = textField_stat.getText().toString();;
				
				boolean testEmail, testMob,testpin;
				Pattern patternEmail, patternMob,patternpin;
				Matcher matcherEmail, matcherMob,matcherpin;
				

				patternEmail = Pattern.compile(EMAIL_PATTERN);
				matcherEmail = patternEmail.matcher(emil);
				testEmail =  matcherEmail.matches();
				
				patternMob = Pattern.compile(MOBILE_PATTERN);
				matcherMob = patternMob.matcher(num);
				testMob = matcherMob.matches();
				
				if((!testEmail) || (!testMob)){
					JOptionPane.showMessageDialog(null, "Enter Valid email or mobile");
				}
				
				else{
				
				try {
					
				String sqladd = "insert into employee_Table(username, passwords, emp_type, status_id,last_name,first_name,dob,age,gender,country,city,address,email,mobile_no)"
				+"values('"+Usern+"','"+Passw+"','"+empT+"','"+empStat+"','"+lname+"','"+fname+"','"+dat+"','"+Age+"','"+gen+"','"+country+"','"+City+"','"+addres+"','"+emil+"','"+num+"')";
				
						if(testEmail && testMob){
						JOptionPane.showMessageDialog(null, "Are You Sure to add this Record?");
						}
							System.out.println(sqladd);
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
							stmt = conn.prepareCall(sqladd);
							stmt.executeUpdate(sqladd);	
							lblrecord_suc.setText("Record Added Successfully");
							JOptionPane.showMessageDialog(null, "Record Added Successfully");
							LoginGui frame = new LoginGui();
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1);
					
				}
			}
		}
	});
		button.setBounds(264, 322, 140, 23);
		panel_addEmp.add(button);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(341, 56, 12, 248);
		panel_addEmp.add(separator_3);
		
		
		
		
		JPanel panel_updtEmp = new JPanel();
		tabbedPane_1.addTab("Update Employee", null, panel_updtEmp, null);
		panel_updtEmp.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 41, 688, 2);
		panel_updtEmp.add(separator_1);
		
		JLabel notfound = new JLabel("");
		notfound.setBounds(144, 13, 140, 14);
		panel_updtEmp.add(notfound);
		
		
		
		
		JLabel label_19 = new JLabel("Username: ");
		label_19.setBounds(10, 54, 118, 20);
		panel_updtEmp.add(label_19);
		
		JLabel label_20 = new JLabel("Password: ");
		label_20.setBounds(10, 85, 118, 20);
		panel_updtEmp.add(label_20);
		
		JLabel label_21 = new JLabel("Firstname: ");
		label_21.setBounds(10, 116, 118, 20);
		panel_updtEmp.add(label_21);
		
		JLabel label_22 = new JLabel("Lastname: ");
		label_22.setBounds(10, 147, 118, 20);
		panel_updtEmp.add(label_22);
		
		JLabel label_23 = new JLabel("Date of Birth:");
		label_23.setBounds(10, 178, 118, 20);
		panel_updtEmp.add(label_23);
		
		JLabel label_24 = new JLabel("Age: ");
		label_24.setBounds(10, 209, 69, 20);
		panel_updtEmp.add(label_24);
		
		JLabel label_25 = new JLabel("Gender: ");
		label_25.setBounds(10, 240, 118, 20);
		panel_updtEmp.add(label_25);
		
		JLabel label_26 = new JLabel("Country: ");
		label_26.setBounds(10, 271, 118, 20);
		panel_updtEmp.add(label_26);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(335, 54, 12, 264);
		panel_updtEmp.add(separator_2);
		
		textField_Username = new JTextField();
		textField_Username.setColumns(10);
		textField_Username.setBounds(138, 48, 146, 26);
		panel_updtEmp.add(textField_Username);
		
		textField_Password = new JTextField();
		textField_Password.setColumns(10);
		textField_Password.setBounds(138, 79, 146, 26);
		panel_updtEmp.add(textField_Password);
		
		textField_Firstname = new JTextField();
		textField_Firstname.setColumns(10);
		textField_Firstname.setBounds(138, 110, 146, 26);
		panel_updtEmp.add(textField_Firstname);
		
		textField_Lastname = new JTextField();
		textField_Lastname.setColumns(10);
		textField_Lastname.setBounds(138, 141, 146, 26);
		panel_updtEmp.add(textField_Lastname);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(138, 172, 146, 26);
		panel_updtEmp.add(dateChooser_1);
		
		textField_Age = new JTextField();
		textField_Age.setColumns(10);
		textField_Age.setBounds(138, 203, 146, 26);
		panel_updtEmp.add(textField_Age);
		
		JRadioButton radioButton = new JRadioButton("Male");
		radioButton.setBounds(134, 231, 56, 29);
		panel_updtEmp.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Female");
		radioButton_1.setBounds(192, 231, 92, 29);
		panel_updtEmp.add(radioButton_1);
		ButtonGroup bG1 = new ButtonGroup();
	     bG1.add(radioButton);
	     bG1.add(radioButton_1);
		
	     String[] cb= new String[]{"US", "PH", "CHINA"};
		JComboBox comboBox_1 = new JComboBox(cb);
		comboBox_1.setBounds(138, 271, 146, 26);
		panel_updtEmp.add(comboBox_1);
		
		JLabel label_27 = new JLabel("Address: ");
		label_27.setBounds(380, 54, 103, 20);
		panel_updtEmp.add(label_27);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(463, 54, 235, 77);
		panel_updtEmp.add(textArea_1);
		
		JLabel label_28 = new JLabel("City: ");
		label_28.setBounds(380, 147, 78, 20);
		panel_updtEmp.add(label_28);
		
		textField_City = new JTextField();
		textField_City.setColumns(10);
		textField_City.setBounds(463, 141, 146, 26);
		panel_updtEmp.add(textField_City);
		
		JLabel label_29 = new JLabel("Email: ");
		label_29.setBounds(380, 178, 78, 20);
		panel_updtEmp.add(label_29);
		
		textField_Email = new JTextField();
		textField_Email.setColumns(10);
		textField_Email.setBounds(463, 172, 146, 26);
		panel_updtEmp.add(textField_Email);
		
		JLabel label_30 = new JLabel("Mobile: ");
		label_30.setBounds(377, 209, 69, 20);
		panel_updtEmp.add(label_30);
		
		textField_mobil = new JTextField();
		textField_mobil.setColumns(10);
		textField_mobil.setBounds(463, 203, 146, 26);
		panel_updtEmp.add(textField_mobil);
		
		JLabel label_31 = new JLabel("Employee type:");
		label_31.setBounds(357, 243, 101, 14);
		panel_updtEmp.add(label_31);
		
		JLabel label_32 = new JLabel("[1] Admin");
		label_32.setBounds(377, 262, 103, 14);
		panel_updtEmp.add(label_32);
		
		JLabel label_33 = new JLabel("[2] Employee");
		label_33.setBounds(377, 277, 103, 14);
		panel_updtEmp.add(label_33);
		
		textField_Empt = new JTextField();
		textField_Empt.setBounds(463, 240, 34, 20);
		panel_updtEmp.add(textField_Empt);
		textField_Empt.setColumns(10);
		
		JLabel label_34 = new JLabel("Employee Status:");
		label_34.setBounds(507, 243, 102, 14);
		panel_updtEmp.add(label_34);
		
		JLabel label_35 = new JLabel("[1] Active");
		label_35.setBounds(507, 262, 89, 14);
		panel_updtEmp.add(label_35);
		
		JLabel label_36 = new JLabel("[2] Inactive");
		label_36.setBounds(507, 277, 89, 14);
		panel_updtEmp.add(label_36);
		
		JLabel label_37 = new JLabel("[3] Deactivate");
		label_37.setBounds(507, 293, 89, 14);
		panel_updtEmp.add(label_37);
		
		textField_Stat = new JTextField();
		textField_Stat.setBounds(609, 240, 34, 20);
		panel_updtEmp.add(textField_Stat);
		textField_Stat.setColumns(10);
		
		JLabel label_38 = new JLabel("");
		label_38.setBounds(260, 13, 164, 14);
		panel_updtEmp.add(label_38);
		
		JXSearchField srchfldEnterempId = new JXSearchField();
		srchfldEnterempId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "Select * from employee_Table where emp_id = '"+srchfldEnterempId.getText().toString()+"' "; 
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
					ResultSet rs = stmt.executeQuery(sql);


						rs = stmt.executeQuery(sql);
						System.out.println(sql);
						if (!rs.isBeforeFirst()) {
						    System.out.println("no data found");
						    notfound.setText("No Record Found");
						    java.util.Date d = new java.util.Date();
						    d.toString();
						    textField_Firstname.setText("");
						    textField_Lastname.setText("");
						    textField_Username.setText("");
						    textField_Password.setText("");
						    textField_City.setText("");
						    textField_Email.setText("");
							textField_mobil.setText("");
							textField_Age.setText("");
							textField_Empt.setText("");
							textField_Stat.setText("");
							dateChooser_1.setDate(d);
							comboBox_1.setSelectedItem("Select Country");
							
							textArea_1.setText("");
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
							
							textField_Firstname.setText(rs.getString("first_name"));
							textField_Lastname.setText(rs.getString("last_name"));
							textField_Username.setText(rs.getString("username"));
							textField_Password.setText(rs.getString("passwords"));
							dateChooser_1.setDate(rs.getDate("dob"));
							textField_Age.setText(rs.getString("age"));
						
							String gen= rs.getString("gender");
							
						if(gen.equals("Female")){
							radioButton.setSelected(true);
							radioButton_1.setSelected(false);
						}
						else{
							radioButton.setSelected(true);
							radioButton_1.setSelected(false);
						}
							String type = rs.getString("emp_type");
						
						comboBox_1.setSelectedItem(rs.getString("country"));
						textField_City.setText(rs.getString("city"));
						textArea_1.setText(rs.getString("address"));
						textField_Email.setText(rs.getString("email"));
						textField_mobil.setText(rs.getString("mobile_no"));
						textField_Empt.setText(rs.getString("emp_type"));
						textField_Stat.setText(rs.getString("status_id"));
						
						
						}
						
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		srchfldEnterempId.setUseSeperatePopupButton(true);
		srchfldEnterempId.setToolTipText("Search");
		srchfldEnterempId.setPromptFontStyle(2);
		srchfldEnterempId.setPrompt("Enter Employee ID: ");
		srchfldEnterempId.setLayoutStyle(LayoutStyle.VISTA);
		srchfldEnterempId.setBounds(10, 10, 118, 20);
		panel_updtEmp.add(srchfldEnterempId);
		
		
		JButton button_1 = new JButton("UPDATE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String fname1 = textField_Firstname.getText();
				String lname1 = textField_Lastname.getText();
				String Usern1 = textField_Username.getText().toString();
				String Passw1 = textField_Password.getText().toString();
				String City1 = textField_City.getText();
				String emil1 =  textField_Email.getText().toString();
				String num1 = textField_mobil.getText().toString();
				String Age1 = textField_Age.getText().toString();
				java.sql.Date dat1 = new java.sql.Date(dateChooser.getDate().getTime());
				String country1 = comboBox_1.getSelectedItem().toString();
				String add1 = textArea.getText();
				String gen1  =bG1.getSelection().getActionCommand();
//			
				try {
					String sql = "update employee_Table set username = '"+Usern1+"',passwords = '"+Passw1+"',last_name = '"+lname1+"',first_name ='"+fname1+"',dob = '"+dat1+"',gender = '"+gen1+"',country ='"+country1+ "', city = '"+City1+"', address ='"+add1+"',email='"+ emil1+"', mobile_no = '"+num1+"' where emp_id = '"+srchfldEnterempId.getText().toString()+"' ";
					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
					stmt.executeUpdate(sql);
					System.out.println(sql);
					label_38.setText("Your Records are Successfully Updated");
								
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						//JOptionPane.showMessageDialog(null, e1);
						
					}
				}
		});
		
		button_1.setBounds(296, 329, 89, 23);
		panel_updtEmp.add(button_1);
		
		
		
		
		
		
	}
}
