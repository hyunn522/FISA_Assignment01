package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class JobDAO {
	private static JobDAO jobDAO = new JobDAO();

	private JobDAO() {}

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
			pstmt = conn.prepareStatement("SELECT * FROM Job WHERE "
					+ "c_personality = (SELECT score FROM PersonCategoryScore WHERE category = '성향' AND PERSONID = ?) AND "
					+ "c_activity = (SELECT score FROM PersonCategoryScore WHERE category = '활동' AND PERSONID = ?) AND "
					+ "c_work = (SELECT score FROM PersonCategoryScore WHERE category = '근무' AND PERSONID = ?) AND "
					+ "c_goal = (SELECT score FROM PersonCategoryScore WHERE category = '목표' AND PERSONID = ?)");

			pstmt.setInt(1, personId);
			pstmt.setInt(2, personId);
			pstmt.setInt(3, personId);
			pstmt.setInt(4, personId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				job = rs.getString("content");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;			
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		
		return job;

	}
}
