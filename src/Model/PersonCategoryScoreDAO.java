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
	
	// 각 카테고리별 사람 정보 저장
	public boolean saveAnswer(int personId, ArrayList<PersonCategoryScore> scores) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DBUtil.getConnection();
	        pstmt = conn.prepareStatement("INSERT INTO personcategoryscore (id, personid, category, score) VALUES (?, ?, ?, ?)");

	        int resultCnt = 0;
	        for (PersonCategoryScore score : scores) {
	            // 시퀀스에서 ID 수동으로 할당
	            Statement stmt = conn.createStatement();
	            rs = stmt.executeQuery("SELECT score_id_seq.NEXTVAL FROM dual");
	            int newId = rs.next() ? rs.getInt(1) : -1;
	            DBUtil.close(null, stmt, rs);

	            pstmt.setInt(1, newId);
	            pstmt.setInt(2, personId);
	            pstmt.setString(3, score.getCategory());
	            pstmt.setInt(4, score.getScore());

	            resultCnt += pstmt.executeUpdate();
	        }

	        return resultCnt == scores.size();
	    } finally {
	        DBUtil.close(conn, pstmt, rs);
	    }
	}

}