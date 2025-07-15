package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.domain.Question;
import util.DBUtil;

public class QuestionDAO {

	private static QuestionDAO questionDao = new QuestionDAO();
	
	private QuestionDAO() {}
	
	public static QuestionDAO getQuestionDao() {
		return questionDao;
	}

	// 모든 질문 반환
	public ArrayList<Question> getQuestions() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Question> questions = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from question");
			
			questions = new ArrayList<>();
			while (rs.next()) {
				questions.add(new Question(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
		
		return questions;
	}
}
