package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.domain.Person;
import util.DBUtil;

public class PersonDAO {
	
	private static PersonDAO personDAO = new PersonDAO();
	
	private PersonDAO() {}
	
	public static PersonDAO getPersonDAO() {
		return personDAO;
	}
	
	public Person getPerson(int personId) throws SQLException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Person person = null;
		try {
			conn = DBUtil.getConnection();
			psmt = conn.prepareStatement("select * from person where id=?");
			psmt.setInt(1, personId);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				person = new Person(rs.getInt(1), rs.getString(2));	
			}
		} finally {
			DBUtil.close(conn, psmt, rs);
		}
		
		return person;
	}

	public int setPerson(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();

			int newId = -1;
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT person_id_seq.NEXTVAL FROM dual");
			if (rs.next()) {
				newId = rs.getInt(1);
			}
			DBUtil.close(null, stmt, rs);

			pstmt = conn.prepareStatement("INSERT INTO person (id, name) VALUES (?, ?)");
			pstmt.setInt(1, newId);
			pstmt.setString(2, name);
			int result = pstmt.executeUpdate();

			if (result == 1) {
				return newId;
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		return -1;
	}

}
