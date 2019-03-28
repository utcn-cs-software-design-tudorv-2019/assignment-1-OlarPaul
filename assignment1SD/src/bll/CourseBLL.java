package bll;

import java.util.List;

import dao.CourseDAO;
import dao.EnrolmentDAO;
import model.Course;

public class CourseBLL {
	public static List<Course> getAllCourses() {
		return CourseDAO.getAllCourses();
	}

	public static List<Course> getCoursesForStudent(int courseIdSet) {
		return EnrolmentDAO.getCoursesForStudent(courseIdSet);
	}
}
