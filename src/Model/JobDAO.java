package Model;

import java.sql.Connection;
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

	public ArrayList<Job> getJobs() throws SQLException {
		ArrayList<Job> jobs = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from Job");

			jobs = new ArrayList<>();
			while (rs.next()) {
				jobs.add(new Job(rs.getInt("id"), rs.getInt("c_personality"), rs.getInt("c_activity"), rs.getInt("c_work"),
						rs.getInt("c_goal"), rs.getString("content")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
		return jobs;

	}
}
