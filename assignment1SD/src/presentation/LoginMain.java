package presentation;

import bll.StudentBLL;
import bll.TeacherBLL;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMain {

	private JFrame frame;
	private JTextField emailTxtField;
	private JTextField passwordTxtField;

	StudentBLL studentBLL = new StudentBLL();
	TeacherBLL teacherBLL = new TeacherBLL();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMain window = new LoginMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LoginMain() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton1 = new JButton("Register");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				RegisterFrame registerFrame = new RegisterFrame();
				registerFrame.setVisible(true);
			}
		});

		btnNewButton1.setBounds(149, 195, 97, 25);
		frame.getContentPane().add(btnNewButton1);

		emailTxtField = new JTextField();
		emailTxtField.setBounds(131, 56, 134, 22);
		frame.getContentPane().add(emailTxtField);
		emailTxtField.setColumns(10);

		passwordTxtField = new JPasswordField();
		passwordTxtField.setBounds(131, 122, 134, 22);
		frame.getContentPane().add(passwordTxtField);
		passwordTxtField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(149, 86, 97, 25);
		frame.getContentPane().add(lblPassword);

		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(149, 30, 97, 25);
		frame.getContentPane().add(lblNewLabel);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (studentBLL.loginStudent(emailTxtField.getText(), passwordTxtField.getText()) != null) {
					JOptionPane.showMessageDialog(null, "Username and password are correct for student!");
					Student student = studentBLL.loginStudent(emailTxtField.getText(),passwordTxtField.getText());
					frame.dispose();
					StudentProfile studentFrame = new StudentProfile(student);
					studentFrame.setVisible(true);
				}
				else if (teacherBLL.loginTeacher(emailTxtField.getText(), passwordTxtField.getText()) != null) {
					
					JOptionPane.showMessageDialog(null, "Username and password are correct for teacher!");
					
				} 
				else {
					JOptionPane.showMessageDialog(null, "Username and password are not correct!");
				}
			}
		});
		btnLogin.setBounds(149, 157, 97, 25);
		frame.getContentPane().add(btnLogin);
	}

}
