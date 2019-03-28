package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Course;
import model.Student;
import model.Teacher;

public class StudentDAO {

	protected static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

	private static final String insertStatementString = "INSERT INTO student (name,address,cnp,mail,group_nr,password) VALUES (?,?,?,?,?,?)";
	private static final String findStatementString = "SELECT * FROM student where id = ?";
	private static final String updateStatementString = "UPDATE student SET name = ?, address = ?, cnp = ?, mail = ?, group_nr = ?, password = ? WHERE id = ?";
	private static final String deleteStatementString = "DELETE FROM student where id = ?";
	private static final String getAllStatementString = "SELECT * FROM student";
	private static final String loginStatementString = "SELECT * FROM student where  mail = ? AND password = ?";
	public static Student findById(int studentId) {
		Student toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, studentId);
			rs = findStatement.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			String name = rs.getString("name");
			String adress = rs.getString("address");
			int cnp = rs.getInt("cnp");
			String email = rs.getString("email");
			int group = rs.getInt("group");
			String password = rs.getString("password");
			
			toReturn = new Student(name, adress, cnp, email, group,password);
			toReturn.setId(id);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static int insert(Student student) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, student.getName());
			insertStatement.setString(2, student.getAdress());
			insertStatement.setInt(3, student.getCnp());
			insertStatement.setString(4, student.getMail());
			insertStatement.setInt(5, student.getGroup());
			insertStatement.setString(6, student.getPassword());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static boolean update(Student student, int idToUpdate) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;

		boolean updateResult = false;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, student.getName());
			updateStatement.setString(2, student.getAdress());
			updateStatement.setInt(3, student.getCnp());
			updateStatement.setString(4, student.getMail());
			updateStatement.setInt(5, student.getGroup());
			updateStatement.setString(6, student.getPassword());
			updateStatement.setInt(7, idToUpdate);
			updateResult = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAOzam:update " + e.getMessage());
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
			LOGGER.log(Level.WARNING, "StudentDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updateResult;
	}

	public static List<Student> getAllStudents() {
		List<Student> studentList = new ArrayList<>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(getAllStatementString);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int studentId = rs.getInt("id");
				String name = rs.getString("name");
				String adress = rs.getString("adress");
				int cnp = rs.getInt("cnp");
				String email = rs.getString("email");
				int group = rs.getInt("group_nr");
				String password = rs.getString("password");
				
				Student toAdd = new Student(name, adress, cnp, email, group, password);
				toAdd.setId(studentId);
				studentList.add(toAdd);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:getAllClients " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return studentList;
	}

	public static Student tryToLoginStudent(String mail, String password) {
		Student toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(loginStatementString);
			findStatement.setString(1, mail);
			findStatement.setString(2, password);
			rs = findStatement.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			String name = rs.getString("name");
			String adress = rs.getString("address");
			int cnp = rs.getInt("cnp");
			String email = rs.getString("mail");
			int group = rs.getInt("group_nr");
			String pass = rs.getString("password");
			
			toReturn = new Student(name, adress, cnp, email, group, pass);
			toReturn.setPassword(pass);
			toReturn.setId(id);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:login " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

}
