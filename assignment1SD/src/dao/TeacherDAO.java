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
import model.Student;
import model.Teacher;

public class TeacherDAO {
	protected static final Logger LOGGER = Logger.getLogger(TeacherDAO.class.getName());

	private static final String insertStatementString = "INSERT INTO teacher (name,address,cnp,mail,salary,password) VALUES (?,?,?,?,?,?)";
	private static final String findStatementString = "SELECT * FROM teacher where id = ?";
	private static final String updateStatementString = "UPDATE teacher SET name = ?, address = ?, mail = ?, cnp = ?, salary = ? WHERE id = ?";
	private static final String deleteStatementString = "DELETE FROM teacher where id = ?";
	private static final String getAllStatementString = "SELECT * FROM teacher";
	private static final String loginStatementString = "SELECT * FROM teacher where mail=? AND password=?";
	
	public static Teacher findById(int teacherId) {
		Teacher toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, teacherId);
			rs = findStatement.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			String name = rs.getString("name");
			String adress = rs.getString("address");
			int cnp = rs.getInt("cnp");
			String email = rs.getString("mail");
			double salary = rs.getDouble("salary");
			String password = rs.getString("password");

			toReturn = new Teacher(name, adress, cnp, email, salary, password);
			toReturn.setId(id);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeacherDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static int insert(Teacher teacher) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(2, teacher.getName());
			insertStatement.setString(3, teacher.getAdress());
			insertStatement.setInt(4, teacher.getCnp());
			insertStatement.setString(5, teacher.getMail());
			insertStatement.setDouble(6, teacher.getSalary());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeacherDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static boolean update(Teacher teacher, int idToUpdate) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;

		boolean updateResult = false;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, teacher.getName());
			updateStatement.setString(2, teacher.getAdress());
			updateStatement.setInt(3, teacher.getCnp());
			updateStatement.setString(4, teacher.getMail());
			updateStatement.setDouble(5, teacher.getSalary());
			updateStatement.setInt(6, idToUpdate);
			updateResult = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeacherDAOzam:update " + e.getMessage());
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
			LOGGER.log(Level.WARNING, "TeacherDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return updateResult;
	}

	public static Teacher tryToLoginTeacher(String mail, String password) {
		Teacher toReturn = null;

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
			double salary = rs.getDouble("salary");
			String pass = rs.getString("password");

			toReturn = new Teacher(name, adress, cnp, email, salary,pass);
			toReturn.setId(id);
			toReturn.setPassword(pass);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeacherDAO:Login " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static List<Teacher> getAllTeachers() {
		List<Teacher> teacherList = new ArrayList<>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(getAllStatementString);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int teacherId = rs.getInt("id");
				String name = rs.getString("name");
				String adress = rs.getString("adress");
				int cnp = rs.getInt("cnp");
				String email = rs.getString("email");
				double salary = rs.getDouble("salary");
				String password = rs.getString("password");
				
				Teacher toAdd = new Teacher(name, adress, cnp, email, salary,password);
				toAdd.setId(teacherId);
				teacherList.add(toAdd);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeacherDAO:getAllClients " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return teacherList;
	}
}
