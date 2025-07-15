package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public int setPerson(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// 1. 시퀀스 값 먼저 조회
			PreparedStatement pstmtSeq = conn.prepareStatement("SELECT person_seq.NEXTVAL FROM DUAL");
			rs = pstmtSeq.executeQuery();
			int id = 0;
			if (rs.next()) {
			    id = rs.getInt(1);
			}
			rs.close();
			pstmtSeq.close();

			// 2. 조회한 ID를 사용해서 INSERT 실행
			pstmt = conn.prepareStatement("INSERT INTO person (id, name) VALUES (?, ?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			int result = pstmt.executeUpdate();

			
	       if(result == 1) {
	    	   return id;
	       }
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return -1;
	}
}
