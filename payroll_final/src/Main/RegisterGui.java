package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class RegisterGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField_firstname;
	private JTextField textField_lastname;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_age;
	private JTextField textField_city;
	private JTextField textField_email;
	private JTextField textField_mobile;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String MOBILE_PATTERN = "\\d{10}";
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	private JTextField textField_emptype;
	private JTextField textField_status;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGui frame = new RegisterGui();
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
	public RegisterGui() {
		conn = ConnectDB.doConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 64, 797, 8);
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
		textField_firstname.setBounds(148, 111, 146, 26);
		contentPane.add(textField_firstname);
		textField_firstname.setColumns(10);
		
		textField_lastname = new JTextField();
		textField_lastname.setBounds(148, 147, 146, 26);
		contentPane.add(textField_lastname);
		textField_lastname.setColumns(10);
		
		textField_username = new JTextField();
		textField_username.setBounds(148, 186, 146, 26);
		contentPane.add(textField_username);
		textField_username.setColumns(10);
		
		textField_password = new JTextField();
		textField_password.setBounds(148, 219, 146, 26);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"US", "PH", "CHINA"}));
		comboBox.setBounds(148, 405, 146, 26);
		contentPane.add(comboBox);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(372, 105, 12, 336);
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
		
		textField_mobile = new JTextField();
		textField_mobile.setBounds(530, 273, 146, 26);
		contentPane.add(textField_mobile);
		textField_mobile.setColumns(10);
		
		JLabel lblrecord_suc = new JLabel("");
		lblrecord_suc.setBounds(348, 80, 46, 14);
		contentPane.add(lblrecord_suc);
		
//		JLabel lblEmployeeType = new JLabel("Employee type:");
//		lblEmployeeType.setBounds(413, 334, 80, 14);
//		contentPane.add(lblEmployeeType);
//		
//		textField_emptype = new JTextField();
//		textField_emptype.setBounds(488, 331, 35, 20);
//		contentPane.add(textField_emptype);
//		textField_emptype.setColumns(10);
//		
//		JLabel lblAdmin = new JLabel("[1] Admin");
//		lblAdmin.setBounds(413, 359, 103, 14);
//		contentPane.add(lblAdmin);
//		
//		JLabel lblEmployee = new JLabel("[2] Employee");
//		lblEmployee.setBounds(413, 379, 103, 14);
//		contentPane.add(lblEmployee);
//		
//		JLabel lblEmployeeStatus = new JLabel("Employee Status:");
//		lblEmployeeStatus.setBounds(547, 334, 89, 14);
//		contentPane.add(lblEmployeeStatus);
//		
//		textField_status = new JTextField();
//		textField_status.setBounds(649, 331, 35, 20);
//		contentPane.add(textField_status);
//		textField_status.setColumns(10);
//		
//		JLabel lblActive = new JLabel("[1] Active");
//		lblActive.setBounds(547, 359, 89, 14);
//		contentPane.add(lblActive);
//		
//		JLabel lblInactive = new JLabel("[2] Inactive");
//		lblInactive.setBounds(547, 379, 89, 14);
//		contentPane.add(lblInactive);
//		
//		JLabel lblInactive_1 = new JLabel("[3] Deactivate");
//		lblInactive_1.setBounds(547, 400, 89, 14);
//		contentPane.add(lblInactive_1);
		
		
		JButton btn_register = new JButton("Register Employeee");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fname = textField_firstname.getText();
				String lname = textField_lastname.getText();
				String Usern = textField_username.getText().toString();
				String Passw = textField_password.getText().toString();
				String City = textField_city.getText();
				String emil =  textField_email.getText().toString();
				String num = textField_mobile.getText().toString();
				String Age = textField_age.getText().toString();
				java.sql.Date dat = new java.sql.Date(dateChooser.getDate().getTime());
				String country = comboBox.getSelectedItem().toString();
				String addres = textArea.getText();
				String gen  =bG.getSelection().getActionCommand();
//				String empT = textField_emptype.getText().toString();
//				String empStat = textField_status.getText().toString();;
				
				int empT = 2;
				int empStat = 2;
				
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
							Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
							stmt = conn.prepareCall(sqladd);
							stmt.executeUpdate(sqladd);	
							lblrecord_suc.setText("Successfully Registered");
							JOptionPane.showMessageDialog(null, "Successfully Registered");
							dispose();
							
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1);
					
				}
			}
		}
	});
	btn_register.setBounds(332, 458, 89, 23);
	contentPane.add(btn_register);
	
	
	
	
	
	}
}
