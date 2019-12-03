package Employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import Main.EmployeeName;
import Main.LoginGui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import org.jdesktop.xswingx.JXSearchField;
import org.jdesktop.xswingx.JXSearchField.LayoutStyle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmployeeMenuGui extends JFrame {

	LoginGui emp;
	private JPanel contentPane;
	private JTextField txtField_TI;
	private JTextField txtField_TO;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_firstname;
	private JTextField textField_lastname;
	private JTextField textField_age;
	private JTextField textField_city;
	private JTextField textField_email;
	private JTextField textField_Mobile;

	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	
	static float hours;
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static LocalDateTime now = LocalDateTime.now();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMenuGui frame = new EmployeeMenuGui();
					frame.setVisible(true);
					frame.setTitle("Employee Menu");
					WindowAdapter exitListener = new WindowAdapter() {

						@Override
						public void windowClosing(WindowEvent e) {
							int confirm = JOptionPane.showOptionDialog(frame, "Are You Sure to Close this Application?",
									"Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
									null, null, null);
							if (confirm == JOptionPane.YES_OPTION) {
								frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);// yes

							} else if (confirm == JOptionPane.CANCEL_OPTION) {
								frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);// cancel
							} else {
								frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);// no
							}
						}
					};
					frame.addWindowListener(exitListener);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeMenuGui() {
		
		//textField_TI.setEditable(true);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 648, 452);
		contentPane.add(tabbedPane);

		JPanel panel_time = new JPanel();

		tabbedPane.addTab("Time in/out", null, panel_time, null);
		panel_time.setLayout(null);
		
		JLabel lblpleaseEnter = new JLabel("");
		lblpleaseEnter.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblpleaseEnter.setForeground(new Color(128, 0, 0));
		lblpleaseEnter.setBounds(103, 168, 156, 14);
		panel_time.add(lblpleaseEnter);
		
		JButton btnTimeI = new JButton("Time in");
		btnTimeI.setEnabled(false);
		btnTimeI.setBounds(202, 79, 89, 23);
		panel_time.add(btnTimeI);
		btnTimeI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(e.getActionCommand().equals("Time in")&& (!txtField_TI.getText().isEmpty())) {
						txtField_TI.setEditable(false);
						btnTimeI.setEnabled(false);
						txtField_TO.setEditable(true);
					}else 
						lblpleaseEnter.setText("Please Enter Time in...");
				    	//btnTimeOut.setEnabled(false);
						txtField_TO.setEditable(false);
					
			}
		});

		JButton btnTimeOut = new JButton("Time out");
		btnTimeOut.setEnabled(false);
		btnTimeOut.setBounds(202, 110, 89, 23);
		panel_time.add(btnTimeOut);
		btnTimeOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getActionCommand().equals("Time out") && (!txtField_TO.getText().isEmpty())) {
					txtField_TO.setEditable(false);
					btnTimeOut.setEnabled(false);
					
					try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "Qwerty120995!");
					
					 hours =0;
					 String timein = txtField_TI.getText();
					 String timeout = txtField_TO.getText();
					 
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
					lblpleaseEnter.setText("Please Enter Time out...");
			}	
		});
		
		txtField_TI = new JTextField();
		txtField_TI.setBounds(20, 80, 120, 20);
		panel_time.add(txtField_TI);
		txtField_TI.setColumns(10);
		txtField_TI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnTimeI.doClick();
					if(!txtField_TI.getText().isEmpty()) {
						txtField_TO.setEditable(true);
					}
				}			
			}
		});
		txtField_TI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				btnTimeI.setEnabled(false);

			}
			public void mouseReleased(MouseEvent e) {
				
				btnTimeI.setEnabled(false);

			}
			
		});
		txtField_TI.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

				if(txtField_TI.getText().isEmpty()) 
					txtField_TI.setEditable(true);
					btnTimeI.setEnabled(true);
				if(!lblpleaseEnter.getText().isEmpty()) 
					lblpleaseEnter.setText(null);
							
			}
		});

		
		txtField_TO = new JTextField();
		txtField_TO.setBounds(20, 111, 120, 20);
		panel_time.add(txtField_TO);
		txtField_TO.setColumns(10);
		txtField_TO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
					btnTimeOut.doClick();
					txtField_TO.setEditable(false);
					
		            }
			}
		});
		txtField_TO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
					btnTimeOut.setEnabled(false);
					
				
			}	
			public void mouseReleased(MouseEvent e) {

				btnTimeOut.setEnabled(false);
				
				
			}	
		});
		txtField_TO.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				if(txtField_TO.getText().isEmpty()) 
					txtField_TO.setEditable(true);
					btnTimeOut.setEnabled(true);
				if(!lblpleaseEnter.getText().isEmpty()) 
					lblpleaseEnter.setText(null);
			}
		});
		
		JLabel lblHi = new JLabel("");
		lblHi.setText(String.valueOf(EmployeeName.empid).toString());
		lblHi.setBounds(101, 42, 101, 14);
		panel_time.add(lblHi);

		JLabel lblEmployee = new JLabel("Employee #:");
		lblEmployee.setBounds(9, 42, 71, 14);
		panel_time.add(lblEmployee);

		JLabel label_12 = new JLabel("Logged in As:");
		label_12.setBounds(10, 17, 80, 14);
		panel_time.add(label_12);

		JLabel lblLastname = new JLabel("");
		lblLastname.setText(String.valueOf(EmployeeName.emp_lstname).toString());
		lblLastname.setBounds(101, 17, 101, 14);
		panel_time.add(lblLastname);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					LoginGui log = new LoginGui();	
					
					dispose(); //to exit
					
					log.main(null);
					//System.exit(0);
			}
		});
		btnLogOut.setBounds(489, 374, 89, 23);
		panel_time.add(btnLogOut);
		
		JLabel lblDate = new JLabel(dtf.format(now));
		lblDate.setBounds(310, 17, 101, 14);
		panel_time.add(lblDate);

		JPanel panel_updatedetails = new JPanel();
		tabbedPane.addTab("Update Details", null, panel_updatedetails, null);
		panel_updatedetails.setLayout(null);

		JLabel label = new JLabel("Username: ");
		label.setBounds(10, 38, 98, 20);
		panel_updatedetails.add(label);

		textField_username = new JTextField();
		textField_username.setBounds(138, 38, 86, 20);
		panel_updatedetails.add(textField_username);
		textField_username.setColumns(10);

		JLabel label_1 = new JLabel("Password: ");
		label_1.setBounds(10, 69, 118, 20);
		panel_updatedetails.add(label_1);

		textField_password = new JTextField();
		textField_password.setBounds(138, 69, 86, 20);
		panel_updatedetails.add(textField_password);
		textField_password.setColumns(10);

		JLabel label_2 = new JLabel("Firstname: ");
		label_2.setBounds(10, 100, 118, 20);
		panel_updatedetails.add(label_2);

		textField_firstname = new JTextField();
		textField_firstname.setBounds(138, 100, 86, 20);
		panel_updatedetails.add(textField_firstname);
		textField_firstname.setColumns(10);

		JLabel label_3 = new JLabel("Lastname: ");
		label_3.setBounds(10, 131, 118, 20);
		panel_updatedetails.add(label_3);

		textField_lastname = new JTextField();
		textField_lastname.setBounds(138, 131, 86, 20);
		panel_updatedetails.add(textField_lastname);
		textField_lastname.setColumns(10);

		JLabel label_4 = new JLabel("Date of Birth:");
		label_4.setBounds(10, 162, 98, 20);
		panel_updatedetails.add(label_4);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(138, 162, 91, 20);
		panel_updatedetails.add(dateChooser);

		JLabel label_5 = new JLabel("Age: ");
		label_5.setBounds(10, 193, 69, 20);
		panel_updatedetails.add(label_5);

		textField_age = new JTextField();
		textField_age.setBounds(138, 193, 86, 20);
		panel_updatedetails.add(textField_age);
		textField_age.setColumns(10);

		JLabel label_6 = new JLabel("Gender: ");
		label_6.setBounds(10, 224, 118, 20);
		panel_updatedetails.add(label_6);

		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(138, 220, 86, 24);
		panel_updatedetails.add(rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(138, 247, 86, 24);
		panel_updatedetails.add(rdbtnFemale);
		ButtonGroup bG = new ButtonGroup();
		bG.add(rdbtnMale);
		bG.add(rdbtnFemale);

		JLabel label_7 = new JLabel("Country: ");
		label_7.setBounds(10, 274, 118, 20);
		panel_updatedetails.add(label_7);

		JComboBox comboBox = new JComboBox(new Object[] {});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "US", "PH", "CHINA" }));
		comboBox.setBounds(138, 274, 86, 26);
		panel_updatedetails.add(comboBox);

		JLabel label_8 = new JLabel("Address: ");
		label_8.setBounds(330, 41, 103, 20);
		panel_updatedetails.add(label_8);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(408, 36, 197, 77);
		panel_updatedetails.add(textArea);

		JLabel label_9 = new JLabel("City: ");
		label_9.setBounds(330, 131, 103, 20);
		panel_updatedetails.add(label_9);

		textField_city = new JTextField();
		textField_city.setBounds(408, 131, 86, 20);
		panel_updatedetails.add(textField_city);
		textField_city.setColumns(10);

		JLabel label_10 = new JLabel("Email: ");
		label_10.setBounds(330, 165, 78, 20);
		panel_updatedetails.add(label_10);

		textField_email = new JTextField();
		textField_email.setBounds(408, 162, 86, 20);
		panel_updatedetails.add(textField_email);
		textField_email.setColumns(10);

		JLabel label_11 = new JLabel("Mobile: ");
		label_11.setBounds(330, 196, 69, 20);
		panel_updatedetails.add(label_11);

		textField_Mobile = new JTextField();
		textField_Mobile.setBounds(408, 193, 86, 20);
		panel_updatedetails.add(textField_Mobile);
		textField_Mobile.setColumns(10);

		JLabel lblupdate = new JLabel("");
		lblupdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblupdate.setBounds(250, 11, 128, 14);
		panel_updatedetails.add(lblupdate);

		JXSearchField srchfldEnterempId = new JXSearchField();
		srchfldEnterempId.setText(String.valueOf(EmployeeName.empid).toString());
		srchfldEnterempId.setToolTipText("Search");
		srchfldEnterempId.setPromptFontStyle(2);
		srchfldEnterempId.setPrompt("Enter Employee ID: ");
		srchfldEnterempId.setLayoutStyle(LayoutStyle.VISTA);
		srchfldEnterempId.setUseSeperatePopupButton(true);
		srchfldEnterempId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "Select * from employee_Table  where emp_id = '"
							+ srchfldEnterempId.getText().toString() + "' ";
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false", "root","Qwerty120995!");
					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
					ResultSet rs = stmt.executeQuery(sql);

					rs = stmt.executeQuery(sql);
					System.out.println(sql);
					if (!rs.isBeforeFirst()) {
						System.out.println("no data found");

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

						lblupdate.setText("");

					} else {
						while (rs.next()) {
							textField_firstname.setText(rs.getString("first_name"));
							textField_lastname.setText(rs.getString("last_name"));
							textField_username.setText(rs.getString("username"));
							textField_password.setText(rs.getString("passwords"));
							dateChooser.setDate(rs.getDate("dob"));
							textField_age.setText(rs.getString("age"));
							comboBox.setSelectedItem(rs.getString("country"));
							System.out.println(rs.getString("country"));

							textField_city.setText(rs.getString("city"));
							textArea.setText(rs.getString("address"));
							textField_email.setText(rs.getString("email"));
							textField_Mobile.setText(rs.getString("mobile_no"));

							String gen = rs.getString("Gender");
							System.out.println(rs.getString("Gender"));
							if (gen.equals("male")) {
								rdbtnMale.setSelected(true);
								rdbtnFemale.setSelected(false);
							} else {
								rdbtnFemale.setSelected(true);
								rdbtnMale.setSelected(false);
							}							

						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		srchfldEnterempId.setBounds(10, 7, 86, 20);
		panel_updatedetails.add(srchfldEnterempId);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = textField_firstname.getText();
				String lname = textField_lastname.getText();
				String Usern = textField_username.getText().toString();
				String Passw = textField_password.getText().toString();
				String City = textField_city.getText();
				String emil = textField_email.getText().toString();
				String num = textField_Mobile.getText().toString();
				String Age = textField_age.getText().toString();
				java.sql.Date dat = new java.sql.Date(dateChooser.getDate().getTime());
				String country = comboBox.getSelectedItem().toString();
				String add = textArea.getText();
				String gen = bG.getSelection().getActionCommand();
//			
				try {
					String sql = "update employee_Table set username = '" + Usern + "',passwords = '" + Passw
							+ "',last_name = '" + lname + "',first_name ='" + fname + "',dob = '" + dat + "',gender = '"
							+ gen + "',country ='" + country + "', city = '" + City + "', address ='" + add
							+ "',email='" + emil + "', mobile_no = '" + num + "' where emp_id = '"
							+ srchfldEnterempId.getText().toString() + "' ";

					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false", "root","Qwerty120995!");
					PreparedStatement pst = conn.prepareStatement(sql);
					stmt = conn.prepareCall(sql);
					stmt.executeUpdate(sql);
					System.out.println(sql);
					lblupdate.setText("Your Records are Successfully Updated");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					// JOptionPane.showMessageDialog(null, e1);

				}
			}
		});

		btnUpdate.setBounds(207, 350, 89, 23);
		panel_updatedetails.add(btnUpdate);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(306, 350, 89, 23);
		panel_updatedetails.add(btnCancel);

		JPanel panel_payslipgen = new JPanel();
		tabbedPane.addTab("New tab", null, panel_payslipgen, null);

	}
}

//@Override
//public void focusLost(FocusEvent e) {
//	
//	if(!textField_TI.getText().equals(""))
//	textField_TI.setText("Please enter time-in");
//	else
//		textField_TI.setText("");
//}

//textField_TI.addActionListener(new ActionListener() {
//public void actionPerformed(ActionEvent e) {
//	
//	if(textField_TI.getText().equals("")) {
//		textField_TI.setEditable(true);
//		btnTimeI.setEnabled(true);
//	}else
//		textField_TI.setEditable(false);
//	btnTimeI.setEnabled(false);
//}
//});
