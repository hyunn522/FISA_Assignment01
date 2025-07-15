package Model;

import java.util.ArrayList;

import Model.domain.Answer;

public class AnswerDAO {
	private AnswerDAO answerDAO = new AnswerDAO();
	
	private AnswerDAO() {}
	
	public AnswerDAO getAnswerDAO() {
		return answerDAO;
	}
	
	public ArrayList<Answer> getAnswers(){
		return null;
	}
}
