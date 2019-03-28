package bll;

import java.util.List;
import java.util.NoSuchElementException;

import dao.StudentDAO;
import model.Student;

public class StudentBLL {

	public Student findClientById(int id) {
		Student st = StudentDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return st;
	}
	
	public Student loginStudent(String email, String password){
		return StudentDAO.tryToLoginStudent(email, password);
	}

	public int insertStudent(Student student) {
		return StudentDAO.insert(student);
	}

	public boolean updateStudent(Student student, int id) {

		return StudentDAO.update(student, id);
	}

	public boolean deleteStudent(int id) {
		return StudentDAO.delete(id);
	}

	public List<Student> getAllStudents() {
		return StudentDAO.getAllStudents();
	}
}
