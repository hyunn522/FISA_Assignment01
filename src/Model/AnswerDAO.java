package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.domain.Answer;
import util.DBUtil;

public class AnswerDAO {
	
	private static AnswerDAO answerDAO = new AnswerDAO();
	
	private AnswerDAO() {}
	
	public static AnswerDAO getAnswerDao() {
		return answerDAO;
	}
	
	// 질문에 대한 모든 정답 반환
	public ArrayList<Answer> getAnswers() throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Answer> answers = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from answer");
			
			answers = new ArrayList<>();
			while (rs.next()) {
				answers.add(new Answer(rs.getInt(1), rs.getInt(2), rs.getString(3)));
			}
		} finally {
			DBUtil.close(conn, stmt);
		}
		
		return answers;
	}
}
