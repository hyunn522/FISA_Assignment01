package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

public class PersonCategoryScoreDAO {

	private static PersonCategoryScoreDAO personCategoryScoreDAO = new PersonCategoryScoreDAO();

	private PersonCategoryScoreDAO() {
	}

	public static PersonCategoryScoreDAO getPersonCategoryScoreDAO() {
		return personCategoryScoreDAO;
	}

	// 각 카테고리별 사람 정보 저장
	public boolean saveAnswer(int personId, String category, int score) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO personcategoryscore (personid, category, score) VALUES (?, ?, ?)");
			pstmt.setInt(1, personId);
			pstmt.setString(2, category);
			pstmt.setInt(3, score);
			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		return false;
	}
}