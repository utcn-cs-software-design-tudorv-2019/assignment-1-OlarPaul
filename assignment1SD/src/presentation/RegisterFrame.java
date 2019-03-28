package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.StudentBLL;
import model.Student;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField address;
	private JTextField cnp;
	private JTextField mail;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblMail;
	private JTextField group;
	private JLabel lblGroup;
	private JButton register;
	private JTextField password;
	
	StudentBLL st = new StudentBLL();
	public RegisterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		name = new JTextField();
		name.setBounds(111, 48, 219, 22);
		contentPane.add(name);
		name.setColumns(10);

		address = new JTextField();
		address.setBounds(111, 111, 219, 22);
		contentPane.add(address);
		address.setColumns(10);

		cnp = new JTextField();
		cnp.setBounds(111, 176, 219, 22);
		contentPane.add(cnp);
		cnp.setColumns(10);

		mail = new JTextField();
		mail.setBounds(111, 238, 219, 22);
		contentPane.add(mail);
		mail.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(12, 48, 87, 22);
		contentPane.add(lblName);

		lblNewLabel = new JLabel("CNP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 179, 87, 19);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(33, 113, 56, 16);
		contentPane.add(lblNewLabel_1);

		lblMail = new JLabel("Mail");
		lblMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblMail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMail.setBounds(12, 238, 87, 25);
		contentPane.add(lblMail);

		group = new JTextField();
		group.setBounds(111, 299, 219, 22);
		contentPane.add(group);
		group.setColumns(10);

		lblGroup = new JLabel("Group");
		lblGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroup.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGroup.setBounds(22, 299, 77, 22);
		contentPane.add(lblGroup);
		
		password = new JTextField();
		password.setBounds(111, 354, 219, 22);
		contentPane.add(password);
		password.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPassword.setBounds(12, 354, 97, 22);
		contentPane.add(lblPassword);

		register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student(name.getText(), address.getText(), Integer.parseInt(cnp.getText()),
						mail.getText(), Integer.parseInt(group.getText()),password.getText());
				int result = st.insertStudent(student);
				if (result != -1) {
					JOptionPane.showMessageDialog(null, "Inregistrarea a avut success");
					
				} else {
					JOptionPane.showMessageDialog(null, "Eroare la inregistrare!");
				}

			}
		});
		register.setBounds(111, 400, 97, 25);
		contentPane.add(register);

	}
}
