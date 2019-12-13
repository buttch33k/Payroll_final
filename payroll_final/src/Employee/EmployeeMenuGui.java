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

import Main.ConnectDB;
import Main.EmployeeName;
import Main.LoginGui;
import net.proteanit.sql.DbUtils;

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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Date;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.time.format.FormatStyle;
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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class EmployeeMenuGui extends JFrame {

	LoginGui emp;
	private JPanel contentPane;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_firstname;
	private JTextField textField_lastname;
	private JTextField textField_age;
	private JTextField textField_city;
	private JTextField textField_email;
	private JTextField textField_Mobile;
	
//	Connection conn;
//	Statement stmt;
//	ResultSet rs,rss;
	
	float hours;
	
	JLabel Time_O = new JLabel(dtf.format(lt));
	String timeout = Time_O.getText();
	JButton btnTimeOut = new JButton("Time out");
	JLabel Time_I = new JLabel(dtf.format(lt));
	String timein = Time_I.getText();
	
	DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDateTime now = LocalDateTime.now();
	
	DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM");
	LocalDateTime now2 = LocalDateTime.now();
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:m a");
	static LocalTime lt = LocalTime.now();
	
	 DateTimeFormatter dtfAP = DateTimeFormatter.ofPattern("a");
	 LocalTime ltAP = LocalTime.now();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		
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
	
	public EmployeeMenuGui(){
		
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
		
		Time_I.setBackground(Color.WHITE);
		Time_I.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Time_I.setForeground(Color.RED);
		Time_I.setBounds(50, 80, 120, 20);
		panel_time.add(Time_I);
		
		JButton btnTimeI = new JButton("Time in");
		btnTimeI.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
					if(!btnTimeI.isEnabled()) {
						btnTimeOut.setEnabled(true);
						Time_O.setEnabled(true);
					}
			}
		});
		btnTimeI.setBounds(202, 79, 89, 23);
		panel_time.add(btnTimeI);
		btnTimeI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
						btnTimeI.setEnabled(false);
						Time_I.setEnabled(false);
						
						try {
						
						Connection	conn = ConnectDB.doConnect();
						
					 	 CallableStatement statement = conn.prepareCall("CALL insertTI(?,?,?,?)");
					     statement.setString(1, String.valueOf(EmployeeName.empid).toString());
						 statement.setString(2, dtf1.format(now));
						 statement.setString(3, timein);
						 statement.setString(4, dtf2.format(now2));
						 
						 statement.executeQuery();
						 
						 btnTimeI.setEnabled(false);
						 
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
			}
		});
		
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
		
		JLabel lblHi = new JLabel("");
		lblHi.setText(String.valueOf(EmployeeName.empid).toString());
		lblHi.setBounds(101, 42, 101, 14);
		panel_time.add(lblHi);
		lblHi.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String empId = lblHi.getText();
				String datenow = dtf1.format(now);

				try {
					Connection conn = ConnectDB.doConnect();

					PreparedStatement pst = null;
					pst = conn.prepareStatement("select * from attendance where employee_id = ? and date = ?");
					pst.setString(1, empId);
					pst.setString(2, datenow);
					ResultSet rs = pst.executeQuery();

					while(rs.next()) {
						
						if(rs.getString(3) != null) {
							btnTimeI.setEnabled(false);
							Time_I.setEnabled(false);
							Time_I.setText(rs.getString(3));
							System.out.println("timeinverify");
						}
						
						if(rs.getString(4) != null) {
							System.out.println("disable line 248");
							btnTimeOut.setEnabled(false);
							Time_O.setEnabled(false);
							Time_O.setText(rs.getString(4));
							System.out.println("timoutverify");
						}
						

					}

					

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

						
			}
		});
		
		Time_O.setEnabled(false);			
		Time_O.setBackground(Color.WHITE);
		Time_O.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Time_O.setForeground(Color.RED);
		Time_O.setBounds(50, 111, 120, 20);
		panel_time.add(Time_O);
		Time_O.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(!btnTimeI.isEnabled()) {

					Thread clock = new Thread() {
						public void run() {

							try {
								for(;;) {
									Calendar calz = new GregorianCalendar();

									int minute = calz.get(Calendar.MINUTE);
									int hour = calz.get(Calendar.HOUR);
									String meridiem = calz.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.getDefault());


									Time_O.setText(hour + ":" + minute  + " "+ meridiem); //
									sleep(1000);
								}
							} catch(InterruptedException e){
								e.printStackTrace();
							}
						}
					};
					clock.start();
					
					Time_O.setEnabled(true);
					System.out.println("enable line 397");
					//btnTimeOut.setEnabled(true);
				}

			}
		});
		
		
		btnTimeOut.setEnabled(false);
		btnTimeOut.setBounds(202, 110, 89, 23);
		panel_time.add(btnTimeOut);
		btnTimeOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Time_O.getText();
				String eId = lblHi.getText();
				//String ngayon = dtf1.format(now);

				try {
					Connection conn = ConnectDB.doConnect();

					PreparedStatement pst = null;
					pst = conn.prepareStatement("select * from attendance where date = curdate() and employee_id = " + eId);

					ResultSet rs = pst.executeQuery();

					while(rs.next()) {
						String ti = rs.getString(3);
						String to = Time_O.getText();
						DateFormat sdf = new SimpleDateFormat("hh:mm aa");
						Date d1 = sdf.parse(ti);
						Date d2 = sdf.parse(to);

						if(d1.after(d2)){
							float diffMs = d1.getTime() - d2.getTime();
							float diffSec = diffMs / 1000;

							float min = diffSec / 60;
							hours = (min /60)-1;

							System.out.println("After: Difference in Hours "+hours);

						}
						if(d1.before(d2)){
							float diffMs = d2.getTime() - d1.getTime();
							float diffSec = diffMs / 1000;


							float min = diffSec / 60;
							hours = (min /60)-1;

							System.out.println("before: Difference in Hours "+hours);

						}

						if(d1.equals(d2)){
							System.out.println("The difference is 0 minutes and 0 seconds.");
						}

						System.out.println("computed");

						try {

							Connection conn1 = ConnectDB.doConnect();

							PreparedStatement pstt = null;
							pstt = conn1.prepareStatement("UPDATE attendance SET time_out=?, totalhr = ?  WHERE employee_id=? and date = curdate()");
							System.out.println("nagupdate na");
							pstt.setString(1, to);
							pstt.setFloat(2, hours);
							pstt.setString(3, eId);
							pstt.executeUpdate();
							//ResultSet rss = pstt.executeQuery();
							System.out.println("disable line 474");
							btnTimeOut.setEnabled(false);
							Time_O.setEnabled(false);

							JOptionPane.showMessageDialog(null, "Successfully added");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();	
						}

						break;
					}

				}catch(SQLException | ParseException c) {
					c.printStackTrace();
				}
				System.out.println("disable line 491");
				btnTimeOut.setEnabled(false);

			}	
		});

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
		
		JLabel lblDate = new JLabel(dtf1.format(now));
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
							+ String.valueOf(EmployeeName.empid).toString() + "' ";

					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false", "root",
							"root");
					PreparedStatement pst = conn.prepareStatement(sql);
					Statement stmt = conn.prepareCall(sql);
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
		
		JLabel lblupdateDetails = new JLabel("");
		lblupdateDetails.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					String sql = "Select * from employee_Table  where emp_id = '"
							+ String.valueOf(EmployeeName.empid).toString() + "' ";
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false", "root",
							"root");
					PreparedStatement pst = conn.prepareStatement(sql);
					Statement stmt = conn.prepareCall(sql);
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
		lblupdateDetails.setText(String.valueOf(EmployeeName.empid).toString());
		lblupdateDetails.setBounds(118, 13, 46, 14);
		panel_updatedetails.add(lblupdateDetails);
		
		JLabel lblEmployeeNumber = new JLabel("Employee number: ");
		lblEmployeeNumber.setBounds(10, 13, 98, 14);
		panel_updatedetails.add(lblEmployeeNumber);

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

//package Employee;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTabbedPane;
//import javax.swing.JTextField;
//import javax.swing.JSpinner;
//import javax.swing.JButton;
//import javax.swing.JTextArea;
//import com.toedter.calendar.JDateChooser;
//
//import Main.EmployeeName;
//import Main.LoginGui;
//import net.proteanit.sql.DbUtils;
//
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import javax.swing.JRadioButton;
//import javax.swing.JComboBox;
//import javax.swing.ButtonGroup;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.SwingConstants;
//import org.jdesktop.xswingx.JXSearchField;
//import org.jdesktop.xswingx.JXSearchField.LayoutStyle;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.Locale;
//import java.util.Date;
//import java.time.Clock;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//
//import java.time.format.FormatStyle;
//import java.awt.event.FocusAdapter;
//import java.awt.event.FocusEvent;
//import javax.swing.event.CaretListener;
//import javax.swing.event.CaretEvent;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.InputMethodListener;
//import java.awt.event.InputMethodEvent;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeEvent;
//
//public class EmployeeMenuGui extends JFrame {
//
//	LoginGui emp;
//	private JPanel contentPane;
//	private JTextField textField_username;
//	private JTextField textField_password;
//	private JTextField textField_firstname;
//	private JTextField textField_lastname;
//	private JTextField textField_age;
//	private JTextField textField_city;
//	private JTextField textField_email;
//	private JTextField textField_Mobile;
//	
//	Connection conn;
//	Statement stmt;
//	ResultSet rs,rs1,rs2,rs3;
//	
//	float hours;
//	
//	JLabel Time_O = new JLabel();
//	String timeout;
//	JButton btnTimeOut = new JButton("Time out");
//	JLabel Time_I = new JLabel(dtf.format(lt));
//	String timein;
//	
//	DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	LocalDateTime now = LocalDateTime.now();
//	
//	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:m a");
//	static LocalTime lt = LocalTime.now();
//	
//	 DateTimeFormatter dtfAP = DateTimeFormatter.ofPattern("a");
//	 LocalTime ltAP = LocalTime.now();
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args){
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EmployeeMenuGui frame = new EmployeeMenuGui();
//					frame.setVisible(true);
//					frame.setTitle("Employee Menu");
//					WindowAdapter exitListener = new WindowAdapter() {
//
//						@Override
//						public void windowClosing(WindowEvent e) {
//							int confirm = JOptionPane.showOptionDialog(frame, "Are You Sure to Close this Application?",
//									"Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
//									null, null, null);
//							if (confirm == JOptionPane.YES_OPTION) {
//								frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);// yes
//
//							} else if (confirm == JOptionPane.CANCEL_OPTION) {
//								frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);// cancel
//							} else {
//								frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);// no
//							}
//						}
//					};
//					frame.addWindowListener(exitListener);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	
//	/**
//	 * Create the frame.
//	 */
//	
//	public EmployeeMenuGui(){
//		
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 670, 482);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.setBounds(0, 0, 648, 452);
//		contentPane.add(tabbedPane);
//
//		JPanel panel_time = new JPanel();
//
//		tabbedPane.addTab("Time in/out", null, panel_time, null);
//		panel_time.setLayout(null);
//		
//		Time_I.setBackground(Color.WHITE);
//		Time_I.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		Time_I.setForeground(Color.RED);
//		Time_I.setBounds(32, 131, 120, 20);
//		panel_time.add(Time_I);
//		
//		JButton btnTimeI = new JButton("Time in");
//		btnTimeI.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//				
//					if(!btnTimeI.isEnabled()) {
//						btnTimeOut.setEnabled(true);
//						Time_O.setEnabled(true);
//					}
//			}
//		});
//		btnTimeI.setBounds(162, 131, 89, 23);
//		panel_time.add(btnTimeI);
//		btnTimeI.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//					
//						btnTimeI.setEnabled(false);
//						Time_I.setEnabled(false);
//						
//						try {
//							
//						timein = Time_I.getText();
//						Class.forName("com.mysql.jdbc.Driver");
//						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
//						
//					 	 CallableStatement statement = conn.prepareCall("CALL insertTI(?,?,?)");
//					     statement.setString(1, String.valueOf(EmployeeName.empid).toString());
//						 statement.setString(2, dtf1.format(now));
//						 statement.setString(3, timein);
//						 
//						 statement.executeQuery();
//						 Time_I.setText(timein);
//						 btnTimeI.setEnabled(false);
//						 
//						} catch (SQLException | ClassNotFoundException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						} 		
//			}
//		});
//		
//		JLabel lblHi = new JLabel("");
//		lblHi.setText(String.valueOf(EmployeeName.empid).toString());
//		lblHi.setBounds(101, 42, 101, 14);
//		panel_time.add(lblHi);
//		lblHi.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//
//				String empId = lblHi.getText();
//				String datenow = dtf1.format(now);
//				
//					try {
//						String sq = "select employee_id, time_in, date from Attendance_NowTI where date = CURDATE() && employee_id = '"+empId+"'";
//						System.out.println(sq);
//						Class.forName("com.mysql.jdbc.Driver");
//						Connection conn2 = DriverManager
//								.getConnection(
//								"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false",
//								"root", "root");
//						
//						PreparedStatement pst = null;
//						pst = conn2.prepareStatement(sq);
//						rs = pst.executeQuery(sq);
//						//stmt = conn.prepareCall(sq);
//						
//						while(rs.next()) {
//							
//							if(rs.getString(1).equals(empId) && rs.getString(3).equals(datenow)) {
//								btnTimeI.setEnabled(false);
//								Time_I.setEnabled(false);
//								Time_I.setText(rs.getString(2));
//								System.out.println(rs.getString(2)+ " eeeeeetimeI");
//								//check = true;
//								break;
////							}if(!check) {	
////								btnTimeI.setEnabled(true);
//							}
//								
//						}
//						
//					} catch (ClassNotFoundException | SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					try {
//						boolean t = false;
//						String sqq = "select e_id, cur, time_out,no_of_hours from timeout where cur = CURDATE() and e_id ='"+empId+"' ";
//						System.out.println(sqq);
//						Class.forName("com.mysql.jdbc.Driver");
//						Connection conn3 = DriverManager
//								.getConnection(
//								"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false",
//								"root", "root");
//						
//						PreparedStatement pst1 = null;
//						pst1 = conn3.prepareStatement(sqq);
//						rs1 = pst1.executeQuery(sqq);
//						//stmt = conn.prepareCall(sqq);
//						
//						while(rs1.next()) {
//							
//							if(rs.getString(1).equals(empId) && rs1.getString(2).equals(datenow)) {
//								btnTimeOut.setEnabled(false);
//								Time_O.setEnabled(false);
//								Time_O.setText(rs1.getString(3));
//								System.out.println(rs.getString(3) + "amen");
//								t = true;
//								break;
//							}if(t) {	
//								btnTimeI.setEnabled(true);
//							}
//						}
//						
//					} catch (ClassNotFoundException | SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}	
//			}
//		});
//		
//		Time_O.setEnabled(false);			
//		Time_O.setBackground(Color.WHITE);
//		Time_O.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		Time_O.setForeground(Color.RED);
//		Time_O.setBounds(310, 131, 120, 20);
//		panel_time.add(Time_O);
//		Time_O.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//				if(btnTimeOut.isEnabled()) {
//					Thread clock = new Thread() {
//						public void run() {
//
//							try {
//								for(;;) {
//									Calendar calz = new GregorianCalendar();
//									
//									int sec = calz.get(Calendar.SECOND);
//									int minute = calz.get(Calendar.MINUTE);
//									int hour = calz.get(Calendar.HOUR);
//									String meridiem = calz.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.getDefault());
//
//									Time_O.setText(hour + ":" + minute  + " "+ meridiem + sec); //
//									sleep(1000);
//									
////									if(!btnTimeOut.isEnabled()) {
////										try {
////											String datenow = dtf1.format(now);
////											String empId = lblHi.getText();
////											String s = "select e_id, cur, time_out,no_of_hours from timeout where cur = CURDATE() and e_id ='"+empId+"' ";
////											//boolean ch = false;
////											Class.forName("com.mysql.jdbc.Driver");
////											Connection conn4 = DriverManager
////													.getConnection(
////													"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false",
////													"root", "root");
////											
////											PreparedStatement pst2 = null;
////											pst2 = conn4.prepareStatement(s);
////											rs2 = pst2.executeQuery(s);
////											//stmt = conn.prepareCall(s);
////											
////											while(rs2.next()) {
////												
////												if(rs2.getString(1).equals(empId) && rs2.getString(2).equals(datenow)) {
////													btnTimeOut.setEnabled(false);
////													Time_O.setEnabled(false);
////													Time_O.setText(rs2.getString(3));
////													System.out.println(rs2.getString(3) + " alien");
////													//ch = true;
////													
////													break;
////												}
////												//if(ch)
////													
////													//System.out.println("aa");
////											}
////						
////										} catch (ClassNotFoundException | SQLException e) {
////											// TODO Auto-generated catch block
////											e.printStackTrace();
////										}
////									}
//
//								}
//							} catch(InterruptedException e){
//								e.printStackTrace();
//							}
//						}
//					};
//					clock.start();
//
//				}else if(!btnTimeOut.isEnabled()) {
//					try {
//						String datenow = dtf1.format(now);
//						String empId = lblHi.getText();
//						String s = "select e_id, cur, time_out,no_of_hours from timeout where cur = CURDATE() and e_id ='"+empId+"' ";
//						//boolean ch = false;
//						Class.forName("com.mysql.jdbc.Driver");
//						Connection conn4 = DriverManager
//								.getConnection(
//								"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false",
//								"root", "root");
//						
//						PreparedStatement pst2 = null;
//						pst2 = conn4.prepareStatement(s);
//						rs2 = pst2.executeQuery(s);
//						//stmt = conn.prepareCall(s);
//						
//						while(rs2.next()) {
//							
//							if(rs2.getString(1).equals(empId) && rs2.getString(2).equals(datenow)) {
//								btnTimeOut.setEnabled(false);
//								Time_O.setEnabled(false);
//								Time_O.setText(rs2.getString(3));
//								System.out.println(rs2.getString(3) + " alien");
//								//ch = true;
//								
//								break;
//							}
//							//if(ch)
//								
//								//System.out.println("aa");
//						}
//	
//					} catch (ClassNotFoundException | SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}	
//
//				}
//					
//			}
//		});
//	
//		
//		btnTimeOut.setEnabled(false);
//		btnTimeOut.setBounds(452, 131, 89, 23);
//		panel_time.add(btnTimeOut);
//		btnTimeOut.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//	
//					try {
//
//						Class.forName("com.mysql.jdbc.Driver");
//						Connection conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
//
//						timeout = Time_O.getText();
//
//						try {
//							Class.forName("com.mysql.jdbc.Driver");
//							Connection conn5 = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
//							PreparedStatement pstmt = null;
//							
//							pstmt = conn5.prepareStatement("select * from Attendance_NowTI");
//							rs3 = pstmt.executeQuery();
//
//							while(rs3.next()) {
//								String ti = rs3.getString(3);
//
//								DateFormat sdf = new SimpleDateFormat("h:m a");
//								Date d1 = sdf.parse(ti);
//								Date d2 = sdf.parse(timeout);
//
//								if(d1.after(d2)){
//							    float diffMs = d1.getTime() - d2.getTime();
//							    float diffSec = diffMs / 1000;
//							    
//							    float min = diffSec / 60;
//							    hours = (min /60)-1;
//							   
//							     System.out.println("afterDifference in Hours "+hours);
//							     break;
//							   
//							    }
//							    if(d1.before(d2)){
//							     float diffMs = d2.getTime() - d1.getTime();
//							     float diffSec = diffMs / 1000;
//							    
//							     float min = diffSec / 60;
//							      hours = (min /60)-1;
//							     
//							     System.out.println("beforeDifference in Hours "+hours);
//							     break;
//							    }
//
//							    if(d1.equals(d2)){
//							     System.out.println("etoThe difference is 0 minutes and 0 seconds.");
//							     break;
//							    }
//								
//							}
//
//						} catch (SQLException | ParseException sql) {
//							// TODO Auto-generated catch block
//							sql.printStackTrace();
//						}
//
//						CallableStatement statements = conn4.prepareCall("CALL inserTO(?,?,?)");
//						statements.setString(1, String.valueOf(EmployeeName.empid).toString());
//						statements.setString(2,timeout);
//						statements.setFloat(3, hours);
//
//						statements.executeUpdate();
//						
//						Time_O.setText(timeout);
//						btnTimeOut.setEnabled(false);
//						Time_O.setEnabled(false);
//						
//						JOptionPane.showMessageDialog(null, "Successfully added");	
//						
//					} catch (ClassNotFoundException |SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
//			}	
//		});
//
//		JLabel lblEmployee = new JLabel("Employee #:");
//		lblEmployee.setBounds(9, 42, 71, 14);
//		panel_time.add(lblEmployee);
//
//		JLabel label_12 = new JLabel("Logged in As:");
//		label_12.setBounds(10, 17, 80, 14);
//		panel_time.add(label_12);
//
//		JLabel lblLastname = new JLabel("");
//		lblLastname.setText(String.valueOf(EmployeeName.emp_lstname).toString());
//		lblLastname.setBounds(101, 17, 101, 14);
//		panel_time.add(lblLastname);
//		
//		JButton btnLogOut = new JButton("Log out");
//		btnLogOut.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//					LoginGui log = new LoginGui();	
//					
//					dispose(); //to exit
//					
//					log.main(null);
//					//System.exit(0);
//			}
//		});
//		btnLogOut.setBounds(489, 374, 89, 23);
//		panel_time.add(btnLogOut);
//		
//		JLabel lblDate = new JLabel(dtf1.format(now));
//		lblDate.setBounds(310, 17, 101, 14);
//		panel_time.add(lblDate);
//
//		JPanel panel_updatedetails = new JPanel();
//		tabbedPane.addTab("Update Details", null, panel_updatedetails, null);
//		panel_updatedetails.setLayout(null);
//
//		JLabel label = new JLabel("Username: ");
//		label.setBounds(10, 38, 98, 20);
//		panel_updatedetails.add(label);
//
//		textField_username = new JTextField();
//		textField_username.setBounds(138, 38, 86, 20);
//		panel_updatedetails.add(textField_username);
//		textField_username.setColumns(10);
//
//		JLabel label_1 = new JLabel("Password: ");
//		label_1.setBounds(10, 69, 118, 20);
//		panel_updatedetails.add(label_1);
//
//		textField_password = new JTextField();
//		textField_password.setBounds(138, 69, 86, 20);
//		panel_updatedetails.add(textField_password);
//		textField_password.setColumns(10);
//
//		JLabel label_2 = new JLabel("Firstname: ");
//		label_2.setBounds(10, 100, 118, 20);
//		panel_updatedetails.add(label_2);
//
//		textField_firstname = new JTextField();
//		textField_firstname.setBounds(138, 100, 86, 20);
//		panel_updatedetails.add(textField_firstname);
//		textField_firstname.setColumns(10);
//
//		JLabel label_3 = new JLabel("Lastname: ");
//		label_3.setBounds(10, 131, 118, 20);
//		panel_updatedetails.add(label_3);
//
//		textField_lastname = new JTextField();
//		textField_lastname.setBounds(138, 131, 86, 20);
//		panel_updatedetails.add(textField_lastname);
//		textField_lastname.setColumns(10);
//
//		JLabel label_4 = new JLabel("Date of Birth:");
//		label_4.setBounds(10, 162, 98, 20);
//		panel_updatedetails.add(label_4);
//
//		JDateChooser dateChooser = new JDateChooser();
//		dateChooser.setBounds(138, 162, 91, 20);
//		panel_updatedetails.add(dateChooser);
//
//		JLabel label_5 = new JLabel("Age: ");
//		label_5.setBounds(10, 193, 69, 20);
//		panel_updatedetails.add(label_5);
//
//		textField_age = new JTextField();
//		textField_age.setBounds(138, 193, 86, 20);
//		panel_updatedetails.add(textField_age);
//		textField_age.setColumns(10);
//
//		JLabel label_6 = new JLabel("Gender: ");
//		label_6.setBounds(10, 224, 118, 20);
//		panel_updatedetails.add(label_6);
//
//		JRadioButton rdbtnMale = new JRadioButton("Male");
//		rdbtnMale.setBounds(138, 220, 86, 24);
//		panel_updatedetails.add(rdbtnMale);
//
//		JRadioButton rdbtnFemale = new JRadioButton("Female");
//		rdbtnFemale.setBounds(138, 247, 86, 24);
//		panel_updatedetails.add(rdbtnFemale);
//		ButtonGroup bG = new ButtonGroup();
//		bG.add(rdbtnMale);
//		bG.add(rdbtnFemale);
//
//		JLabel label_7 = new JLabel("Country: ");
//		label_7.setBounds(10, 274, 118, 20);
//		panel_updatedetails.add(label_7);
//
//		JComboBox comboBox = new JComboBox(new Object[] {});
//		comboBox.setModel(new DefaultComboBoxModel(new String[] { "US", "PH", "CHINA" }));
//		comboBox.setBounds(138, 274, 86, 26);
//		panel_updatedetails.add(comboBox);
//
//		JLabel label_8 = new JLabel("Address: ");
//		label_8.setBounds(330, 41, 103, 20);
//		panel_updatedetails.add(label_8);
//
//		JTextArea textArea = new JTextArea();
//		textArea.setBounds(408, 36, 197, 77);
//		panel_updatedetails.add(textArea);
//
//		JLabel label_9 = new JLabel("City: ");
//		label_9.setBounds(330, 131, 103, 20);
//		panel_updatedetails.add(label_9);
//
//		textField_city = new JTextField();
//		textField_city.setBounds(408, 131, 86, 20);
//		panel_updatedetails.add(textField_city);
//		textField_city.setColumns(10);
//
//		JLabel label_10 = new JLabel("Email: ");
//		label_10.setBounds(330, 165, 78, 20);
//		panel_updatedetails.add(label_10);
//
//		textField_email = new JTextField();
//		textField_email.setBounds(408, 162, 86, 20);
//		panel_updatedetails.add(textField_email);
//		textField_email.setColumns(10);
//
//		JLabel label_11 = new JLabel("Mobile: ");
//		label_11.setBounds(330, 196, 69, 20);
//		panel_updatedetails.add(label_11);
//
//		textField_Mobile = new JTextField();
//		textField_Mobile.setBounds(408, 193, 86, 20);
//		panel_updatedetails.add(textField_Mobile);
//		textField_Mobile.setColumns(10);
//
//		JLabel lblupdate = new JLabel("");
//		lblupdate.setHorizontalAlignment(SwingConstants.CENTER);
//		lblupdate.setBounds(250, 11, 128, 14);
//		panel_updatedetails.add(lblupdate);
//
//		JButton btnUpdate = new JButton("UPDATE");
//		btnUpdate.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String fname = textField_firstname.getText();
//				String lname = textField_lastname.getText();
//				String Usern = textField_username.getText().toString();
//				String Passw = textField_password.getText().toString();
//				String City = textField_city.getText();
//				String emil = textField_email.getText().toString();
//				String num = textField_Mobile.getText().toString();
//				String Age = textField_age.getText().toString();
//				java.sql.Date dat = new java.sql.Date(dateChooser.getDate().getTime());
//				String country = comboBox.getSelectedItem().toString();
//				String add = textArea.getText();
//				String gen = bG.getSelection().getActionCommand();
////			
//				try {
//					String sql = "update employee_Table set username = '" + Usern + "',passwords = '" + Passw
//							+ "',last_name = '" + lname + "',first_name ='" + fname + "',dob = '" + dat + "',gender = '"
//							+ gen + "',country ='" + country + "', city = '" + City + "', address ='" + add
//							+ "',email='" + emil + "', mobile_no = '" + num + "' where emp_id = '"
//							+ String.valueOf(EmployeeName.empid).toString() + "' ";
//
//					Connection conn = DriverManager.getConnection(
//							"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false", "root",
//							"root");
//					PreparedStatement pst = conn.prepareStatement(sql);
//					stmt = conn.prepareCall(sql);
//					stmt.executeUpdate(sql);
//					System.out.println(sql);
//					lblupdate.setText("Your Records are Successfully Updated");
//
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					// JOptionPane.showMessageDialog(null, e1);
//
//				}
//			}
//		});
//
//		btnUpdate.setBounds(207, 350, 89, 23);
//		panel_updatedetails.add(btnUpdate);
//
//		JButton btnCancel = new JButton("CANCEL");
//		btnCancel.setBounds(306, 350, 89, 23);
//		panel_updatedetails.add(btnCancel);
//		
//		JLabel lblupdateDetails = new JLabel("");
//		lblupdateDetails.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//				try {
//					String sql = "Select * from employee_Table  where emp_id = '"
//							+ String.valueOf(EmployeeName.empid).toString() + "' ";
//					Connection conn = DriverManager.getConnection(
//							"jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false", "root",
//							"root");
//					PreparedStatement pst = conn.prepareStatement(sql);
//					stmt = conn.prepareCall(sql);
//					ResultSet rs = stmt.executeQuery(sql);
//
//					rs = stmt.executeQuery(sql);
//					System.out.println(sql);
//					if (!rs.isBeforeFirst()) {
//						System.out.println("no data found");
//
//						java.util.Date d = new java.util.Date();
//						d.toString();
//						textField_firstname.setText("");
//						textField_lastname.setText("");
//						textField_username.setText("");
//						textField_password.setText("");
//						textField_city.setText("");
//						textField_email.setText("");
//						textField_Mobile.setText("");
//						textField_age.setText("");
//						dateChooser.setDate(d);
//						comboBox.setSelectedItem("Select Country");
//
//						textArea.setText("");
//						rdbtnMale.setSelected(false);
//						rdbtnFemale.setSelected(false);
//
//						lblupdate.setText("");
//
//					} else {
//						while (rs.next()) {
//							textField_firstname.setText(rs.getString("first_name"));
//							textField_lastname.setText(rs.getString("last_name"));
//							textField_username.setText(rs.getString("username"));
//							textField_password.setText(rs.getString("passwords"));
//							dateChooser.setDate(rs.getDate("dob"));
//							textField_age.setText(rs.getString("age"));
//							comboBox.setSelectedItem(rs.getString("country"));
//							System.out.println(rs.getString("country"));
//
//							textField_city.setText(rs.getString("city"));
//							textArea.setText(rs.getString("address"));
//							textField_email.setText(rs.getString("email"));
//							textField_Mobile.setText(rs.getString("mobile_no"));
//
//							String gen = rs.getString("Gender");
//							System.out.println(rs.getString("Gender"));
//							if (gen.equals("male")) {
//								rdbtnMale.setSelected(true);
//								rdbtnFemale.setSelected(false);
//							} else {
//								rdbtnFemale.setSelected(true);
//								rdbtnMale.setSelected(false);
//							}							
//
//						}
//					}
//
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
//		lblupdateDetails.setText(String.valueOf(EmployeeName.empid).toString());
//		lblupdateDetails.setBounds(118, 13, 46, 14);
//		panel_updatedetails.add(lblupdateDetails);
//		
//		JLabel lblEmployeeNumber = new JLabel("Employee number: ");
//		lblEmployeeNumber.setBounds(10, 13, 98, 14);
//		panel_updatedetails.add(lblEmployeeNumber);
//
//		JPanel panel_payslipgen = new JPanel();
//		tabbedPane.addTab("New tab", null, panel_payslipgen, null);
//
//	}
//}
//
////@Override
////public void focusLost(FocusEvent e) {
////	
////	if(!textField_TI.getText().equals(""))
////	textField_TI.setText("Please enter time-in");
////	else
////		textField_TI.setText("");
////}
//
////textField_TI.addActionListener(new ActionListener() {
////public void actionPerformed(ActionEvent e) {
////	
////	if(textField_TI.getText().equals("")) {
////		textField_TI.setEditable(true);
////		btnTimeI.setEnabled(true);
////	}else
////		textField_TI.setEditable(false);
////	btnTimeI.setEnabled(false);
////}
////});
