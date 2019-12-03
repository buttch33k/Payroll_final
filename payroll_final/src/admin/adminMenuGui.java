package admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.toedter.calendar.JDateChooser;

import Main.ConnectDB;
import Main.EmployeeName;
import Main.LoginGui;
import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.jdesktop.xswingx.JXSearchField;
import org.jdesktop.xswingx.JXSearchField.LayoutStyle;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Scanner;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class adminMenuGui extends JFrame {

	private JPanel contentPane;
	
	static float hours;
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static LocalDateTime now = LocalDateTime.now();
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
	private JTextField textField_Empt1;
	private JTextField textField_Stat1;
	
	private JTable table;
	private JTextField textField_jobId;
	private JTextField textField_jobtitle;
	private JTextField textField_salaray;
	private JTable table1;
	private JTextField textField_EMpid_ps;
	private JTextField textField_name_ps;
	private JTextField textField_1_jobtitle_ps;
	private JTextField textField_rateperhour_ps;
	private JTextField textField_TAX_PS;
	private JTextField textField_sss_ps;
	private JTextField textField_pagibig_ps;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_jobIdz;
	private JTextField textField_TIn;
	private JTextField textField_Tout;
	private JButton btnTimeIn;

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
		label_39.setBounds(10, 11, 101, 14);
		panel_home.add(label_39);
		
		JLabel label_40 = new JLabel("Employee #:");
		label_40.setBounds(10, 36, 101, 14);
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
		btnLogOut.setBounds(10, 383, 89, 23);
		panel_home.add(btnLogOut);
		
		
		
		
		
		btnTimeIn = new JButton("Time in");
		btnTimeIn.setEnabled(false);
		btnTimeIn.setBounds(114, 60, 89, 23);
		panel_home.add(btnTimeIn);
		btnTimeIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Time in")&& (!textField_TIn.getText().isEmpty())) {
					textField_TIn.setEditable(false);
					btnTimeIn.setEnabled(false);
					textField_Tout.setEditable(true);
				}else 
					JOptionPane.showMessageDialog(null, "Please Enter Time in...");
					
			    	//btnTimeOut.setEnabled(false);
					textField_Tout.setEditable(false);
				
			}
		});
			
		
		
		JButton btnTimeOut = new JButton("Time out");
		btnTimeOut.setEnabled(false);
		btnTimeOut.setBounds(114, 91, 89, 23);
		panel_home.add(btnTimeOut);
		btnTimeOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getActionCommand().equals("Time out") && (!textField_Tout.getText().isEmpty())) {
					textField_Tout.setEditable(false);
					btnTimeOut.setEnabled(false);
					
					try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
					
					 hours =0;
					 String timein = textField_TIn.getText();
					 String timeout = textField_Tout.getText();
					 
					 DateFormat sdf = new SimpleDateFormat("hh:mm aa");
					 Date d1 = sdf.parse(timein);
					 Date d2 = sdf.parse(timeout);
					 
					 
					 if(d1.after(d2)){
					     float diffMs = d1.getTime() - d2.getTime();
					     float diffSec = diffMs / 1000;
					    
					     float min = diffSec / 60;
					      hours = (min /60)-1;
					   
					     //System.out.println("Difference in Hours "+hours);
					     
					    }
					    if(d1.before(d2)){
					     float diffMs = d2.getTime() - d1.getTime();
					     float diffSec = diffMs / 1000;
					    
					    
					     float min = diffSec / 60;
					      hours = (min /60)-1;
					     
					     //System.out.println("Difference in Hours "+hours);
					    
					    }

					    if(d1.equals(d2)){
					     System.out.println("The difference is 0 minutes and 0 seconds.");
					    }
					    
//					    if (hours > 8.0) {
//					    	double extraHours = hours - 8.0;
//					        double pay = ( 8* rate ) + ( extraHours * rate );
//					        System.out.println(pay);
//					    	} else {
//					    		double pay = hours * rate;
//					    		System.out.println(pay);
//					    	}
					    
					    String hr = Float.toString(hours);
					    
					     CallableStatement statement = conn.prepareCall("CALL insertAttend(?,?,?,?,?)");
					     statement.setString(1, String.valueOf(EmployeeName.empid).toString());
						 statement.setString(2, dtf.format(now));
						 statement.setString(3, timein);
						 statement.setString(4,timeout);
						 statement.setFloat(5, hours);
						 ResultSet rs = statement.executeQuery();
					 
						 JOptionPane.showMessageDialog(null, "Successfully added");	
				//	}

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}else
					JOptionPane.showMessageDialog(null, "Please Enter Time out...");
					
			}	
		});
				
		textField_TIn = new JTextField();
		
		textField_TIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					textField_TIn.doClick();
					if(!textField_TIn.getText().isEmpty()) {
						textField_Tout.setEditable(true);
					}
				}			
			}
		});
		textField_TIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				btnTimeIn.setEnabled(false);

			}
			public void mouseReleased(MouseEvent e) {
				
				btnTimeIn.setEnabled(false);

			}
			
		});
		textField_TIn.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

				if(textField_TIn.getText().isEmpty()) 
					textField_TIn.setEditable(true);
					btnTimeIn.setEnabled(true);
//				if(!lblpleaseEnter.getText().isEmpty()) 
//					lblpleaseEnter.setText(null);
							
			}
		});
		textField_TIn.setBounds(10, 61, 86, 20);
		panel_home.add(textField_TIn);
		textField_TIn.setColumns(10);
		

		textField_Tout = new JTextField();
		textField_Tout.setBounds(10, 92, 86, 20);
		panel_home.add(textField_Tout);
		textField_Tout.setColumns(10);
		textField_Tout.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
				textField_Tout.doClick();
				textField_Tout.setEditable(false);
				}
			}
		});
		textField_Tout.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		
			textField_Tout.setEnabled(false);
				
			
		}	
		public void mouseReleased(MouseEvent e) {

			textField_Tout.setEnabled(false);
			
			
			}	
		});
			textField_Tout.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				if(textField_Tout.getText().isEmpty()) 
					textField_Tout.setEditable(true);
				textField_Tout.setEnabled(true);
	//			if(!lblpleaseEnter.getText().isEmpty()) 
	//				lblpleaseEnter.setText(null);
			}
		});
		
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
		
		JRadioButton rdbtnMale = new JRadioButton("male");
		rdbtnMale.setBounds(138, 238, 146, 20);
		panel_addEmp.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("female");
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
		label_8.setBounds(383, 56, 55, 20);
		panel_addEmp.add(label_8);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(443, 54, 146, 56);
		panel_addEmp.add(scrollPane_2);
		
		JTextArea textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);
		
		textField_city = new JTextField();
		textField_city.setColumns(10);
		textField_city.setBounds(443, 118, 123, 26);
		panel_addEmp.add(textField_city);
		
		JLabel label_9 = new JLabel("City: ");
		label_9.setBounds(383, 121, 50, 20);
		panel_addEmp.add(label_9);
		
		JLabel label_10 = new JLabel("Email: ");
		label_10.setBounds(383, 152, 50, 20);
		panel_addEmp.add(label_10);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(443, 149, 123, 26);
		panel_addEmp.add(textField_email);
		
		JLabel label_11 = new JLabel("Mobile: ");
		label_11.setBounds(383, 183, 50, 20);
		panel_addEmp.add(label_11);
		
		textField_Mobile = new JTextField();
		textField_Mobile.setColumns(10);
		textField_Mobile.setBounds(443, 180, 123, 26);
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
				String gender  = bG.getSelection().getActionCommand();
				String empT = textField_emptype.getText().toString();
				String empStat = textField_stat.getText().toString();
				String jabD = textField_jobIdz.getText().toString();
			
				
				
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
					
				String sqladd = "insert into employee_Table(username, passwords, emp_type, status_id,job_id_emp,last_name,first_name,dob,age,gender,country,city,address,email,mobile_no)"
				+"values('"+Usern+"','"+Passw+"','"+empT+"','"+empStat+"','"+jabD+"','"+lname+"','"+fname+"','"+dat+"','"+Age+"','"+gender+"','"+country+"','"+City+"','"+addres+"','"+emil+"','"+num+"')";
				
						if(testEmail && testMob){
						JOptionPane.showMessageDialog(null, "Are You Sure to add this Record?");
						}
							System.out.println(sqladd);
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
							stmt = conn.prepareCall(sqladd);
							stmt.executeUpdate(sqladd);	
							
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
		button.setBounds(264, 322, 169, 23);
		panel_addEmp.add(button);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(341, 56, 12, 248);
		panel_addEmp.add(separator_3);
		
		JLabel lblJobId_1 = new JLabel("JOB ID:");
		lblJobId_1.setBounds(576, 124, 46, 14);
		panel_addEmp.add(lblJobId_1);
		
		textField_jobIdz = new JTextField();
		textField_jobIdz.setName("");
		
		
		textField_jobIdz.setBounds(635, 121, 50, 20);
		panel_addEmp.add(textField_jobIdz);
		textField_jobIdz.setColumns(10);
		
		
		
		
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
		
		JRadioButton radioButton = new JRadioButton("male");
		radioButton.setBounds(134, 231, 56, 29);
		panel_updtEmp.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("female");
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
		
		textField_Empt1 = new JTextField();
		textField_Empt1.setBounds(463, 240, 34, 20);
		panel_updtEmp.add(textField_Empt1);
		textField_Empt1.setColumns(10);
		
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
		
		textField_Stat1 = new JTextField();
		textField_Stat1.setBounds(609, 240, 34, 20);
		panel_updtEmp.add(textField_Stat1);
		textField_Stat1.setColumns(10);
		
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
							JOptionPane.showMessageDialog(null, "No Data Found");
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
							textField_Empt1.setText("");
							textField_Stat1.setText("");
							dateChooser_1.setDate(d);
							comboBox_1.setSelectedItem("Select Country");
							
							textArea_1.setText("");
							radioButton.setSelected(false);
							radioButton_1.setSelected(false);
							
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
						
							String gen1= rs.getString("gender");
							
						if(gen1.equals("male")){
							radioButton.setSelected(true);
							radioButton_1.setSelected(false);
						}
						else{
							radioButton.setSelected(false);
							radioButton_1.setSelected(true);
						}
							String type = rs.getString("emp_type");
						
						comboBox_1.setSelectedItem(rs.getString("country"));
						textField_City.setText(rs.getString("city"));
						textArea_1.setText(rs.getString("address"));
						textField_Email.setText(rs.getString("email"));
						textField_mobil.setText(rs.getString("mobile_no"));
						textField_Empt1.setText(rs.getString("emp_type"));
						textField_Stat1.setText(rs.getString("status_id"));
						
						
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
				java.sql.Date dat1 = new java.sql.Date(dateChooser_1.getDate().getTime());
				String country1 = comboBox_1.getSelectedItem().toString();
				String add1 = textArea_1.getText();
				String gen1  =bG1.getSelection().getActionCommand();
				int emptypers = Integer.parseInt(textField_Empt1.getText());
				int empStatus= Integer.parseInt(textField_Stat1.getText());
//			
				try {
					String sql = "update employee_Table set emp_type = '"+emptypers+"', status_id = '"+empStatus+"', username = '"+Usern1+"',passwords = '"+Passw1+"',last_name = '"+lname1+"',first_name ='"+fname1+"',dob = '"+dat1+"',gender = '"+gen1+"',country ='"+country1+ "', city = '"+City1+"', address ='"+add1+"',email='"+ emil1+"', mobile_no = '"+num1+"' where emp_id = '"+srchfldEnterempId.getText().toString()+"' ";
					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
					stmt.executeUpdate(sql);
					System.out.println(sql);
					
					JOptionPane.showMessageDialog(null, "Your Records are Successfully Updated");
								
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						//JOptionPane.showMessageDialog(null, e1);
						
					}
				}
		});
		
		button_1.setBounds(296, 329, 89, 23);
		panel_updtEmp.add(button_1);
		
		JPanel panel_PendingEmployee = new JPanel();
		panel_PendingEmployee.setToolTipText("");
		tabbedPane_1.addTab("Pending Employee", null, panel_PendingEmployee, null);
		panel_PendingEmployee.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 688, 167);
		panel_PendingEmployee.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try {
			stmt = conn.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JButton btnShowAllPending = new JButton("Show all pending Employee");
		btnShowAllPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "call viewAllInactiveUser()";
					
						rs=stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		
		btnShowAllPending.setBounds(10, 189, 177, 23);
		panel_PendingEmployee.add(btnShowAllPending);
		
		JPanel panel_addjobdetails = new JPanel();
		tabbedPane_1.addTab("Add Job Details", null, panel_addjobdetails, null);
		panel_addjobdetails.setLayout(null);
		
		JXSearchField searchFieldjobid = new JXSearchField();
		searchFieldjobid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sqljob = "Select * from JobTitle_Table where emp_id_job = '"+searchFieldjobid.getText().toString()+"' "; 
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
					PreparedStatement pst = conn.prepareStatement(sqljob);
					stmt = conn.prepareCall(sqljob);
					ResultSet rs = stmt.executeQuery(sqljob);


						rs = stmt.executeQuery(sqljob);
						System.out.println(sqljob);
						if (!rs.isBeforeFirst()) {
						    System.out.println("no data found");
						    JOptionPane.showMessageDialog(null, "NO Data Found");
						    textField_jobId.setText("");
						    
						    textField_jobtitle.setText("");
						    textField_salaray.setText("");
						   
						} 
						else {
						
						while(rs.next()){
						
						    textField_jobId.setText(rs.getString("job_id"));
						    textField_jobtitle.setText(rs.getString("job_title"));
						    textField_salaray.setText(rs.getString("job_salary"));
						    
						}
						
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "NO EMployee ID FOund");
						e1.printStackTrace();
					}
				
			}
		});
		
		searchFieldjobid.setBounds(113, 11, 104, 20);
		panel_addjobdetails.add(searchFieldjobid);
		
		JLabel lblEmployeeId = new JLabel("Employee id");
		lblEmployeeId.setBounds(10, 14, 93, 14);
		panel_addjobdetails.add(lblEmployeeId);
		
		JLabel lblJobId = new JLabel("Job Id");
		lblJobId.setBounds(10, 39, 93, 14);
		panel_addjobdetails.add(lblJobId);
		
		textField_jobId = new JTextField();
		textField_jobId.setBounds(113, 36, 104, 20);
		panel_addjobdetails.add(textField_jobId);
		textField_jobId.setColumns(10);
		
		JLabel lblJobTitle = new JLabel("Job Title");
		lblJobTitle.setBounds(10, 64, 93, 14);
		panel_addjobdetails.add(lblJobTitle);
		
		textField_jobtitle = new JTextField();
		textField_jobtitle.setBounds(113, 61, 104, 20);
		panel_addjobdetails.add(textField_jobtitle);
		textField_jobtitle.setColumns(10);
		
		JLabel lblJobSalary = new JLabel("Job Salary");
		lblJobSalary.setBounds(10, 89, 93, 14);
		panel_addjobdetails.add(lblJobSalary);
		
		textField_salaray = new JTextField();
		textField_salaray.setBounds(113, 86, 104, 20);
		panel_addjobdetails.add(textField_salaray);
		textField_salaray.setColumns(10);
		
		JButton btnSaveDetails = new JButton("Save Details");
		btnSaveDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String j_id = textField_jobId.getText().toString();
				String e_id = searchFieldjobid.getText().toString();
				String j_title = textField_jobtitle.getText();
				String j_salary = textField_salaray.getText();
				
				try {
					
					String sql = "insert into JobTitle_Table(job_id,job_title,job_salary,emp_id_job) values('"+j_id+"','"+j_title+"','"+j_salary+"','"+e_id+"')";
				
					stmt.executeUpdate(sql);
					System.out.print(sql);
					JOptionPane.showMessageDialog(null, "Record Added Successfully");
				
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Duplicate job ID");
					e1.printStackTrace();
				}
				
			}
		});
		JScrollPane scrollPane_showjobRecords = new JScrollPane();
		scrollPane_showjobRecords.setBounds(278, 11, 420, 311);
		panel_addjobdetails.add(scrollPane_showjobRecords);
				
		btnSaveDetails.setBounds(10, 124, 104, 23);
		panel_addjobdetails.add(btnSaveDetails);
		
		table1 = new JTable();
		scrollPane_showjobRecords.setViewportView(table1);
		try {
			stmt = conn.createStatement();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		JButton btnShowAllJob = new JButton("Show all job record");
		btnShowAllJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sqljob = "call viewAllJOb()";
					
						rs=stmt.executeQuery(sqljob);
						table1.setModel(DbUtils.resultSetToTableModel(rs));
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		btnShowAllJob.setBounds(426, 333, 149, 23);
		panel_addjobdetails.add(btnShowAllJob);
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setToolTipText("Enter Employee ID");
		tabbedPane.addTab("Payroll", null, panel_4, null);
		panel_4.setLayout(null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setBounds(10, 11, 713, 395);
		panel_4.add(tabbedPane_3);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_3.addTab("Payslip", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblEmployeeId_1 = new JLabel("Employee ID");
		lblEmployeeId_1.setBounds(10, 11, 83, 14);
		panel_3.add(lblEmployeeId_1);
		
		textField_EMpid_ps = new JTextField();
		textField_EMpid_ps.setEditable(false);
		textField_EMpid_ps.setBounds(103, 8, 86, 20);
		panel_3.add(textField_EMpid_ps);
		textField_EMpid_ps.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 36, 83, 14);
		panel_3.add(lblName);
		
		textField_name_ps = new JTextField();
		textField_name_ps.setBounds(103, 33, 116, 20);
		panel_3.add(textField_name_ps);
		textField_name_ps.setColumns(10);
		
		JLabel lblJobTitle_1 = new JLabel("Job Title");
		lblJobTitle_1.setBounds(10, 61, 83, 14);
		panel_3.add(lblJobTitle_1);
		
		textField_1_jobtitle_ps = new JTextField();
		textField_1_jobtitle_ps.setBounds(103, 58, 116, 20);
		panel_3.add(textField_1_jobtitle_ps);
		textField_1_jobtitle_ps.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 86, 46, 14);
		panel_3.add(lblDate);
		
		JDateChooser dateChooser_today_ps = new JDateChooser();
		dateChooser_today_ps.setBounds(103, 80, 116, 20);
		panel_3.add(dateChooser_today_ps);
		
		JLabel lblRatePerHour = new JLabel("Rate Per hour");
		lblRatePerHour.setBounds(10, 111, 83, 14);
		panel_3.add(lblRatePerHour);
		
		textField_rateperhour_ps = new JTextField();
		textField_rateperhour_ps.setEditable(false);
		textField_rateperhour_ps.setBounds(103, 108, 86, 20);
		panel_3.add(textField_rateperhour_ps);
		textField_rateperhour_ps.setColumns(10);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 137, 688, 2);
		panel_3.add(separator_4);
		
		JLabel lblDedution = new JLabel("Dedution:");
		lblDedution.setBounds(10, 145, 83, 14);
		panel_3.add(lblDedution);
		
		JLabel lblTax = new JLabel("Tax");
		lblTax.setBounds(10, 170, 46, 14);
		panel_3.add(lblTax);
		
		JLabel lblSss = new JLabel("SSS");
		lblSss.setBounds(10, 195, 46, 14);
		panel_3.add(lblSss);
		
		JLabel lblPagIbig = new JLabel("PAG IBIG");
		lblPagIbig.setBounds(10, 220, 46, 14);
		panel_3.add(lblPagIbig);
		
		textField_TAX_PS = new JTextField();
		textField_TAX_PS.setEditable(false);
		textField_TAX_PS.setBounds(103, 167, 86, 20);
		panel_3.add(textField_TAX_PS);
		textField_TAX_PS.setColumns(10);
		
		textField_sss_ps = new JTextField();
		textField_sss_ps.setEditable(false);
		textField_sss_ps.setBounds(103, 192, 86, 20);
		panel_3.add(textField_sss_ps);
		textField_sss_ps.setColumns(10);
		
		textField_pagibig_ps = new JTextField();
		textField_pagibig_ps.setEditable(false);
		textField_pagibig_ps.setBounds(103, 217, 86, 20);
		panel_3.add(textField_pagibig_ps);
		textField_pagibig_ps.setColumns(10);
		
		JLabel lblAdditional = new JLabel("Additional:");
		lblAdditional.setBounds(10, 245, 83, 14);
		panel_3.add(lblAdditional);
		
		textField = new JTextField();
		textField.setBounds(103, 242, 86, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblNetpay = new JLabel("Netpay");
		lblNetpay.setBounds(10, 270, 46, 14);
		panel_3.add(lblNetpay);
		
		textField_1 = new JTextField();
		textField_1.setBounds(103, 267, 86, 20);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JXSearchField searchField = new JXSearchField();
		searchField.setPrompt("Enter Employee ID");
		searchField.setBounds(408, 11, 122, 20);
		panel_3.add(searchField);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_3.addTab("New tab", null, panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Reports", null, panel_1, null);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(12, 13, 709, 389);
		panel_1.add(tabbedPane_2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_2.addTab("Generate all Employee", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 13, 680, 302);
		panel_2.add(scrollPane_1);
		
		JButton btnShowAllEmployee = new JButton("Show All Employee");
		btnShowAllEmployee.setBounds(284, 328, 141, 25);
		panel_2.add(btnShowAllEmployee);
		
		
		
		
		
		
	}
}
