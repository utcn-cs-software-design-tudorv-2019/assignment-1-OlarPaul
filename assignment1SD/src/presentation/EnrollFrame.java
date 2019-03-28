package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import bll.CourseBLL;
import model.Course;

import javax.swing.JList;

public class EnrollFrame extends JFrame {
	private TableModel jModel;
	JFrame f;
	JTable j;
	CourseBLL course = new CourseBLL();

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnrollFrame window = new EnrollFrame();
					window.f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	EnrollFrame() {
		f = new JFrame();
		f.setTitle("Courses");
		j = new JTable();

		List<Course> myCourse = CourseBLL.getAllCourses();
		createCoursesModel(myCourse);
		j.setModel(
				new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3" }));
		j.setModel(jModel);
		j.setAutoCreateRowSorter(true);
		ChangeName(j, 0, "ID");
		ChangeName(j, 1, "Name");
		ChangeName(j, 2, "Teacher");
		j.setAutoCreateRowSorter(true);
		j.setBounds(30, 40, 200, 300);
		JScrollPane sp = new JScrollPane(j);
		f.add(sp);
		f.setSize(700, 400);
		f.setVisible(true);
	}

	public void createCoursesModel(final List<Course> courses) {

		jModel = new AbstractTableModel() {
			@Override
			public int getColumnCount() {
				return 3;
			}

			@Override
			public int getRowCount() {
				return courses.size();
			}

			@Override
			public Object getValueAt(int row, int col) {
				if (col == 0) {
					return courses.get(row).getId();
				} else if (col == 1) {
					return courses.get(row).getName();
				} else if (col == 2) {
					return courses.get(row).getTeacher();
				} else {
					return 0;
				}
			}
		};
	}

	public void ChangeName(JTable table, int col_index, String col_name) {
		table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
	}
}
