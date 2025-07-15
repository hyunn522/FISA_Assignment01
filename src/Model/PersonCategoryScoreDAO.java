package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.domain.PersonCategoryScore;
import util.DBUtil;

public class PersonCategoryScoreDAO {
	
	private static PersonCategoryScoreDAO personCategoryScoreDAO = new PersonCategoryScoreDAO();
	
	private PersonCategoryScoreDAO() {}
	
	public static PersonCategoryScoreDAO getPersonCategoryScoreDAO() {
		return personCategoryScoreDAO;
	}
	
	public boolean saveAnswer(int personId, ArrayList<PersonCategoryScore> scores) throws SQLException {
	    Connection conn = null;
	    PreparedStatement psmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DBUtil.getConnection();
	        psmt = conn.prepareStatement("INSERT INTO personcategoryscore (id, personid, category, score) VALUES (?, ?, ?, ?)");

	        int resultCnt = 0;
	        for (PersonCategoryScore score : scores) {
	            Statement stmt = conn.createStatement();
	            rs = stmt.executeQuery("SELECT score_id_seq.NEXTVAL FROM dual");
	            int newId = rs.next() ? rs.getInt(1) : -1;
	            DBUtil.close(null, stmt, rs);

	            psmt.setInt(1, newId);
	            psmt.setInt(2, personId);
	            psmt.setString(3, score.getCategory());
	            psmt.setInt(4, score.getScore());

	            resultCnt += psmt.executeUpdate();
	        }

	        return resultCnt == scores.size();
	    } finally {
	        DBUtil.close(conn, psmt, rs);
	    }
	}

}