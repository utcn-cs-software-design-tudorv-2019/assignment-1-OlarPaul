package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import bll.StudentBLL;
import model.Student;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class EditFrame extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField address;
	private JTextField cnp;
	private JTextField mail;
	private JTextField group;
	private JTextField password;

	private StudentBLL studentBLL = new StudentBLL();
	private int id;

	public EditFrame(Student student) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		name = new JTextField();
		name.setBounds(181, 34, 204, 22);
		contentPane.add(name);
		name.setColumns(10);
		name.setText(student.getName());

		address = new JTextField();
		address.setBounds(181, 92, 204, 22);
		contentPane.add(address);
		address.setColumns(10);
		address.setText(student.getAdress());

		cnp = new JTextField();
		cnp.setBounds(181, 160, 204, 22);
		contentPane.add(cnp);
		cnp.setColumns(10);
		cnp.setText(String.valueOf(student.getCnp()));

		mail = new JTextField();
		mail.setBounds(181, 221, 204, 22);
		contentPane.add(mail);
		mail.setColumns(10);
		mail.setText(student.getMail());

		group = new JTextField();
		group.setBounds(181, 286, 204, 22);
		contentPane.add(group);
		group.setColumns(10);
		group.setText(String.valueOf(student.getGroup()));

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblName.setBounds(71, 36, 78, 16);
		contentPane.add(lblName);

		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(71, 91, 78, 22);
		contentPane.add(lblNewLabel);

		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCnp.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCnp.setBounds(71, 162, 78, 16);
		contentPane.add(lblCnp);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblMail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMail.setBounds(71, 222, 78, 19);
		contentPane.add(lblMail);

		JLabel lblGroup = new JLabel("Group");
		lblGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroup.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGroup.setBounds(71, 289, 78, 19);
		contentPane.add(lblGroup);

		password = new JPasswordField();
		password.setBounds(181, 343, 204, 22);
		contentPane.add(password);
		password.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(86, 349, 63, 16);
		contentPane.add(lblPassword);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = student.getId();
				boolean result = studentBLL.deleteStudent(id);
				if (result) {
					JOptionPane.showMessageDialog(null, "Student data was deleted succesfully");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Error !");
				}

			}
		});

		btnDelete.setBounds(253, 394, 97, 25);
		contentPane.add(btnDelete);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = student.getId();
				Student studentupdate = new Student(name.getText(), address.getText(), Integer.parseInt(cnp.getText()),
						mail.getText(), Integer.parseInt(group.getText()), password.getText());
				boolean result = studentBLL.updateStudent(studentupdate, id);
				if (result) {
					JOptionPane.showMessageDialog(null, "Student data was updated succesfully");
				} else {
					JOptionPane.showMessageDialog(null, "Error !");
				}

			}
		});
		editButton.setBounds(86, 394, 97, 25);
		contentPane.add(editButton);

	}

}
