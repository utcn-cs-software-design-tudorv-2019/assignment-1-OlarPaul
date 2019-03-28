package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.StudentBLL;
import model.Student;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentProfile extends JFrame {

	private JPanel contentPane;

	public StudentProfile(Student student) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EditFrame editFrame = new EditFrame(student);
				editFrame.setVisible(true);
				dispose();
			}
		});
		editButton.setBounds(77, 158, 117, 39);
		contentPane.add(editButton);

		JButton enrollButton = new JButton("Enroll");
		enrollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EnrollFrame enrollFrame = new EnrollFrame();
				enrollFrame.setVisible(true);
			}
		});

		enrollButton.setBounds(234, 158, 117, 39);
		contentPane.add(enrollButton);

		JLabel lblNewLabel = new JLabel("STUDENT PROFILE ACTIONS");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(90, 52, 252, 60);
		contentPane.add(lblNewLabel);
	}

}
