package Model;

import java.util.ArrayList;

import Model.domain.Question;

public class QuestionDAO {
	private QuestionDAO questionDAO = new QuestionDAO();
	
	private QuestionDAO() {}
	
	public QuestionDAO getQuestionDAO() {
		return questionDAO;
	}
	
	// 질문 받아오기
	public ArrayList<Question> getQuestions() {
		return null;
	}

}
