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
	
	// 사람 불러오기
	public Person getPerson(int personId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Person person = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from person where id=?");
			pstmt.setInt(1, personId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				person = new Person(rs.getInt(1), rs.getString(2));	
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		
		return person;
	}

	// 사람 저장
	// 사람 저장 - Oracle 방식: 시퀀스에서 NEXTVAL 먼저 가져오기
	public int setPerson(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();

			// 1. 시퀀스에서 ID 수동 추출
			int newId = -1;
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT person_id_seq.NEXTVAL FROM dual");
			if (rs.next()) {
				newId = rs.getInt(1);
			}
			DBUtil.close(null, stmt, rs);  // close stmt, rs

			// 2. 그 ID로 insert
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
