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
import model.Student;
import model.Teacher;

public class CourseDAO {
	protected static final Logger LOGGER = Logger.getLogger(CourseDAO.class.getName());

	private static final String insertStatementString = "INSERT INTO course (name,teacher_id) VALUES (?,?)";
	private static final String findStatementString = "SELECT * FROM teacher INNER JOIN course ON teacher.id = course.teacher_id WHERE course.id = ?";
	private static final String updateStatementString = "UPDATE course SET name = ?, teacher_id = ? WHERE id = ?";
	private static final String deleteStatementString = "DELETE FROM course where id = ?";
	private static final String getAllStatementString = "SELECT * FROM teacher INNER JOIN course ON teacher.id = course.teacher_id";

	public static Course findById(int courseId) {
		Course toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, courseId);
			rs = findStatement.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			String name = rs.getString("name");
			Teacher teacher = new Teacher(rs.getString("teacher.name"), rs.getString("address"),
					rs.getInt("teacher.cnp"), rs.getString("teacher.mail"), rs.getDouble("salary"),rs.getString("teacher.password"));
			teacher.setId(rs.getInt("teacher.id"));
			toReturn = new Course(teacher,name);
			toReturn.setId(id);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CourseDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static int insert(Course course) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, course.getName());
			insertStatement.setInt(2, course.getTeacher().getId());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CourseDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static boolean update(Course course, int idToUpdate) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;

		boolean updateResult = false;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, course.getName());
			updateStatement.setInt(2, course.getTeacher().getId());
			updateStatement.setInt(3, idToUpdate);
			updateResult = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UpdateDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updateResult;
	}

	public static boolean delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;

		boolean updateResult = false;
		try {
			updateStatement = dbConnection.prepareStatement(deleteStatementString);
			updateStatement.setInt(1, id);
			updateResult = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CourseDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updateResult;
	}

	public static List<Course> getAllCourses() {
		List<Course> courseList = new ArrayList<>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(getAllStatementString);
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
			LOGGER.log(Level.WARNING, "CourseDAO:getAllCourses " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return courseList;
	}
}
