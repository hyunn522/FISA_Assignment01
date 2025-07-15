package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class JobDAO {

	private static JobDAO jobDAO = new JobDAO();

	private JobDAO() {
	}

	public static JobDAO getJobDAO() {
		return jobDAO;
	}

	public String getJob(int personId) throws SQLException {
	    String job = null;

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DBUtil.getConnection();

	        String sql = 
	            "SELECT * FROM Job " +
	            "WHERE c_personality = (SELECT score FROM PersonCategoryScore WHERE category = '성향' AND personid = ?) " +
	            "AND c_activity = (SELECT score FROM PersonCategoryScore WHERE category = '활동' AND personid = ?) " +
	            "AND c_work = (SELECT score FROM PersonCategoryScore WHERE category = '근무' AND personid = ?) " +
	            "AND c_goal = (SELECT score FROM PersonCategoryScore WHERE category = '목표' AND personid = ?)";

	        pstmt = conn.prepareStatement(sql);

	        // 동일한 personId를 네 번 바인딩
	        for (int i = 1; i <= 4; i++) {
	            pstmt.setInt(i, personId);
	        }

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            job = rs.getString("content");
	        }

	    } finally {
	        DBUtil.close(conn, pstmt, rs);
	    }

	    return job;
	}

}
