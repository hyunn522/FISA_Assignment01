package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.domain.Answer;
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
	public boolean setPerson(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
	        pstmt = conn.prepareStatement("INSERT INTO person (name) VALUES (?)");
	        pstmt.setString(1, name);

	        int result = pstmt.executeUpdate();
	        
	       if(result == 1) {
	    	   return true;
	       }
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}
}
