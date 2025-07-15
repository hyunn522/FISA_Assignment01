package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.domain.Answer;
import Model.domain.Job;
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
			pstmt = conn.prepareStatement(
					"select * from Job where c_personality=(select score from PersonCategoryScore where category = '성향' and person_id = ? ) and "
							+ "c_activity=(select score from PersonCategoryScore where category = '활동' and person_id = ? )"
							+ "c_work=(select score from PersonCategoryScore where category = '근무' and person_id = ? )"
							+ "c_goal=(select score from PersonCategoryScore where category = '목표' and person_id = ? )"	
					);
			
			
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
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return job;

	}
}
