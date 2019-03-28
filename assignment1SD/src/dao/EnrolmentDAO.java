package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Course;
import model.Teacher;

public class EnrolmentDAO {
	protected static final Logger LOGGER = Logger.getLogger(EnrolmentDAO.class.getName());

	private static final String insertStatementString = "INSERT INTO enrolment (student_id,course_id) VALUES (?,?)";
	//private static final String findStatementString = "SELECT * FROM enrolment where id = ?";
	private static final String getCoursesForStudentStatement = "SELECT * FROM course INNER JOIN enrolment \r\n"
			+ "ON course.id = enrolment.course_id\r\n" + "INNER JOIN teacher ON teacher.id  = course.teacher_id\r\n"
			+ "WHERE student_id = ?";

	public static int enrollToCourse(int studentId, int courseId) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, studentId);
			insertStatement.setInt(2, courseId);
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "EnrolmentDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static List<Course> getCoursesForStudent(int courseIdSet) {
		List<Course> courseList = new ArrayList<>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(getCoursesForStudentStatement);
			findStatement.setLong(1, courseIdSet);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int courseId = rs.getInt("id");
				String name = rs.getString("name");
				Teacher teacher = new Teacher(rs.getString("teacher.name"), rs.getString("address"),
						rs.getInt("teacher.cnp"), rs.getString("teacher.mail"), rs.getDouble("salary"), rs.getString("teacher.password"));
				teacher.setId(rs.getInt("teacher.id"));
				Course toAdd = new Course(teacher, name);
				toAdd.setId(courseId);
				courseList.add(toAdd);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "EnrolmentDAO:getAllClients " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return courseList;

	}
}
