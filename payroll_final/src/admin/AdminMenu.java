package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.EmployeeName;

import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
					frame.setVisible(true);
					frame.setTitle("Admin");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 730, 31);
		contentPane.add(menuBar);
		
		JMenu mnEmployee = new JMenu("EMPLOYEE");
		menuBar.add(mnEmployee);
		
		JMenuItem mntmAddEmployee = new JMenuItem("ADD EMPLOYEE");
		mntmAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmployee addemp = new addEmployee();
				addemp.setVisible(true);
				addemp.setTitle("Add Employee");
			}
		});
		
		mntmAddEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		mnEmployee.add(mntmAddEmployee);
		
		JMenuItem mntmUpdateEmployee = new JMenuItem("UPDATE EMPLOYEE");
		mntmUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateEmployee uptemp = new UpdateEmployee();
				uptemp.setVisible(true);
				uptemp.setTitle("Update Employee");
				
			}
		});
		mntmUpdateEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		mnEmployee.add(mntmUpdateEmployee);
		
		JMenuItem mntmPendingNewAccount = new JMenuItem("PENDING NEW ACCOUNT");
		mnEmployee.add(mntmPendingNewAccount);
		
		JMenu mnTimeIn = new JMenu("TIME IN / OUT");
		menuBar.add(mnTimeIn);
		
		JMenu mnCrudTaz = new JMenu("SETTINGS");
		menuBar.add(mnCrudTaz);
		
		JMenu mnPayslip = new JMenu("PAYSLIP");
		menuBar.add(mnPayslip);
		
		JLabel lblNewLabel = new JLabel("Logged in As:");
		lblNewLabel.setBounds(10, 42, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lbl_emp = new JLabel("emp");
		lbl_emp.setText(String.valueOf(EmployeeName.empid).toString());
		lbl_emp.setBounds(117, 67, 46, 14);
		contentPane.add(lbl_emp);
		
		JLabel lblEmployee = new JLabel("Employee #:");
		lblEmployee.setBounds(10, 67, 80, 14);
		contentPane.add(lblEmployee);
		
		JLabel lblNewLabel_logas = new JLabel("");
		lblNewLabel_logas.setText(String.valueOf(EmployeeName.emp_lstname).toString());
		lblNewLabel_logas.setBounds(117, 42, 46, 14);
		contentPane.add(lblNewLabel_logas);
		
		
	}
}
