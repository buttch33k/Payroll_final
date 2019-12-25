package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Employee.EmployeeMenuGui;
import Main.ConnectDB;

import admin.adminMenuGui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class LoginGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField_username;
	private JTextField textField_password;
	private JPasswordField passwordField;
	private JCheckBox chckbxShowPassword;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
					frame.setVisible(true);
					frame.setTitle("Login");

					WindowAdapter exitListener = new WindowAdapter() {

						@Override
						public void windowClosing(WindowEvent e) {
							int confirm = JOptionPane.showOptionDialog(frame,
									"Are You Sure to Close this Application?",
									"Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
							if(confirm == JOptionPane.YES_OPTION){
								frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);//yes

							} else if (confirm == JOptionPane.CANCEL_OPTION) {
								frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);//cancel
							} else {
								frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);//no
							}
						}
					};
					frame. addWindowListener(exitListener);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public LoginGui() {
		setTitle("Login ");
		setResizable(false);
		setAlwaysOnTop(true);
		conn = ConnectDB.doConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(52, 94, 95, 20);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(52, 127, 95, 20);
		contentPane.add(lblPassword);

		textField_username = new JTextField();
		textField_username.setBounds(179, 91, 146, 26);
		contentPane.add(textField_username);
		textField_username.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String query = ("call checklogz()");
				String username = textField_username.getText();
				String passwords = passwordField.getText();


				try {

					//					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_final?autoReconnect=true&useSSL=false","root", "root");
					PreparedStatement pst = conn.prepareStatement(query);
					stmt = conn.prepareCall(query);
					ResultSet rs = stmt.executeQuery(query);



					boolean flag = false;
					String roleAdmin = "1";
					String roleEmployee = "2";
					while (rs.next()) {

						if (username.equals(rs.getString(1)) && passwords.equals(rs.getString(2)) && roleAdmin.equals(rs.getString(3))) {
							//  block of code to be executed if condition1 is true
							dispose();
							String empidz = rs.getString("emp_id");
							EmployeeName.empid =empidz;

							String loginas = rs.getString("last_name");
							EmployeeName.emp_lstname =loginas;

							String stat_id = rs.getString("status_id");
							EmployeeName.Statz_ID =stat_id;

							String stat_Active = "1";
							String stat_Inactive = "2";
							String stat_Deact = "3";
						
//change
							if (stat_id.equals(stat_Active)) {
								adminMenuGui admin = new adminMenuGui();
								admin.setVisible(true);
							}else if (stat_id.equals(stat_Inactive)) {
								JOptionPane.showMessageDialog(null, "Your account is innactive");
								LoginGui frame = new LoginGui();
								frame.setVisible(true);
								frame.setTitle("Login");
							}else if (stat_id.equals(stat_Deact)) {
								JOptionPane.showMessageDialog(null, "Your account is Deactivated");
								LoginGui frame = new LoginGui();
								frame.setVisible(true);
								frame.setTitle("Login");
							}


							flag = true;
							break;
						} else if (username.equals(rs.getString(1)) && passwords.equals(rs.getString(2)) && roleEmployee.equals(rs.getString(3))) {
							//  block of code to be executed if the condition1 is false and condition2 is true
							dispose();
							String empid = rs.getString("emp_id");
							EmployeeName.empid =empid;

							String loginas = rs.getString("last_name");
							EmployeeName.emp_lstname =loginas;

							String stat_id = rs.getString("status_id");
							EmployeeName.Statz_ID =stat_id;

							String stat_Active = "1";
							String stat_Inactive = "2";
							String stat_Deact = "3";

							if (stat_id.equals(stat_Active)) {
								EmployeeMenuGui empMenu = new EmployeeMenuGui();
								empMenu.setVisible(true);
								empMenu.setTitle("Employee Menu");
							}else if (stat_id.equals(stat_Inactive)) {
								JOptionPane.showMessageDialog(null, "Your account is innactive");
								LoginGui frame = new LoginGui();
								frame.setVisible(true);
								frame.setTitle("Login");
							}else if (stat_id.equals(stat_Deact)) {
								JOptionPane.showMessageDialog(null, "Your account is Deactivated");
								LoginGui frame = new LoginGui();
								frame.setVisible(true);
								frame.setTitle("Login");
							}



							flag = true;
							break;
						}
					}
					if(!flag) {
						int input = JOptionPane.showConfirmDialog(null, " Account doesn't exist do you want to register");

						if(input == 0) {
							System.out.println("Pressed YES");
							RegisterGui regisframe = new RegisterGui();
							regisframe.setVisible(true);
							regisframe.setTitle("Register");
						}
						else if (input == 1){
							System.out.println("Pressed NO");
							dispose();

						} else {
							System.out.println("Pressed CANCEL");
							LoginGui Lframe = new LoginGui();
							Lframe.setTitle("Login");
						}
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(54, 197, 115, 29);
		contentPane.add(btnLogin);

		passwordField = new JPasswordField();
		passwordField.setBounds(179, 124, 146, 26);
		contentPane.add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});


		chckbxShowPassword = new JCheckBox("Show Password");

		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxShowPassword.isSelected()) {
					passwordField.setEchoChar((char)0);
				}else
					passwordField.setEchoChar('*');
			}
		});
		chckbxShowPassword.setBounds(179, 159, 140, 20);
		contentPane.add(chckbxShowPassword);

		JLabel lblPayrollManagementSystem = new JLabel("Payroll Management System");
		lblPayrollManagementSystem.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
		lblPayrollManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayrollManagementSystem.setBounds(12, 31, 355, 46);
		contentPane.add(lblPayrollManagementSystem);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGui regisframe = new RegisterGui();
//Deleted		
				dispose();
				regisframe.setVisible(true);
			}
		});
		btnRegister.setBounds(193, 198, 115, 27);
		contentPane.add(btnRegister);

	}
}
