package bll;

import dao.TeacherDAO;
import model.Teacher;

public class TeacherBLL {
	public Teacher loginTeacher(String mail, String password) {
		return TeacherDAO.tryToLoginTeacher(mail, password);
	}

}
