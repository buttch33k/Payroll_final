package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
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

public class UpdateEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField textField_firstname;
	private JTextField textField_lastname;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_age;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 527);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 90, 927, 8);
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
		
		JLabel lblCountry = new JLabel("Country: ");
		lblCountry.setBounds(20, 400, 118, 20);
		contentPane.add(lblCountry);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"US", "PH", "CHINA"}));
		comboBox.setBounds(148, 405, 146, 26);
		contentPane.add(comboBox);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(372, 105, 12, 350);
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
		
		textField = new JTextField();
		textField.setBounds(530, 201, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(413, 240, 103, 20);
		contentPane.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(530, 237, 146, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile: ");
		lblMobile.setBounds(413, 276, 69, 20);
		contentPane.add(lblMobile);
		
		textField_2 = new JTextField();
		textField_2.setBounds(530, 273, 146, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEmployeeType = new JLabel("Employee Type: ");
		lblEmployeeType.setBounds(399, 331, 135, 20);
		contentPane.add(lblEmployeeType);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(393, 364, 129, 29);
		contentPane.add(rdbtnAdmin);
		
		JRadioButton rdbtnEmployee = new JRadioButton("Employee");
		rdbtnEmployee.setBounds(393, 396, 123, 29);
		contentPane.add(rdbtnEmployee);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(540, 309, 12, 132);
		contentPane.add(separator_2);
		
		JLabel lblStatusId = new JLabel("Status ID:");
		lblStatusId.setBounds(567, 331, 109, 20);
		contentPane.add(lblStatusId);
		
		JRadioButton rdbtnActive = new JRadioButton("Active");
		rdbtnActive.setBounds(563, 364, 88, 29);
		contentPane.add(rdbtnActive);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Inactive");
		rdbtnNewRadioButton.setBounds(563, 396, 88, 29);
		contentPane.add(rdbtnNewRadioButton);
		
		
	}
}
