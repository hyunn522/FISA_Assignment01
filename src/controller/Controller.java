package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.AnswerDAO;
import Model.JobDAO;
import Model.PersonCategoryScoreDAO;
import Model.PersonDAO;
import Model.QuestionDAO;
import Model.domain.Answer;
import Model.domain.PersonAnswer;
import Model.domain.PersonCategoryScore;
import Model.domain.Question;
import View.FailView;
import View.FinishView;
import View.QuizView;

public class Controller {
	
	private static JobDAO jobDAO = JobDAO.getJobDAO();
	private static PersonDAO personDAO = PersonDAO.getPersonDAO();
	private static QuestionDAO questionDao = QuestionDAO.getQuestionDao();
	private static PersonCategoryScoreDAO personCategoryScoreDAO = PersonCategoryScoreDAO.getPersonCategoryScoreDAO();
	private static AnswerDAO answerDAO = AnswerDAO.getAnswerDao();

	public static String getName(int personId) {
		
		try {
			return personDAO.getPerson(personId).getName();
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.print("이름 불러오기를 실패했어요 ㅜㅜ");
		}
		return null;
		
	}

	
	public static void setName(String name) {

		if (name != null && name.length() != 0) {
			try {
				QuizView.start(personDAO.setPerson(name));
			} catch (SQLException e) {
				FailView.print("저장을 실패했어요. 다시 시작해주세요.");
				e.printStackTrace();
			}
		} else {
			FailView.print("저장을 실패했어요. 다시 시작해주세요.");
		}

	}

	
	public static ArrayList<Question> getQuizs() {
		// 퀴즈 데이터 불러오기
		ArrayList<Question> datas = null;
		try {
			datas = questionDao.getQuestions();
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.print("문제를 받아오지 못했어요ㅜㅜ 다시 시도해주세요");
		}

		return datas;
	}

	private static ArrayList<Answer> getAnswers() {
		ArrayList<Answer> answers = null;
		try {
			answers = answerDAO.getAnswers();
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.print("N잡을 구할 수 없어요 ㅜ");
		}
		return answers;

	}

	public static void save(int personId, ArrayList<PersonAnswer> answerList) {
		int index = 0;
		ArrayList<PersonCategoryScore> scoreList = new ArrayList<>();
		ArrayList<Answer> rightAnswerList = getAnswers(); // 정답목록

		int score = 0;
		for (PersonAnswer answer : answerList) {
			if (index % 2 != 0) {
				// 카테고리 종류에 따른 구분
				for (int type = 0; type < 2; type++) {
					 // 정답
					String rightAnswer = rightAnswerList.get(index - type).getText();
					
					if (answerList.get(index - type).getAnswer().equals(rightAnswer)) {
						score++;
					}
				}
				scoreList.add(new PersonCategoryScore(personId, answer.getCategory(), score == 0 ? 1 : score));
				try {
					personCategoryScoreDAO.saveAnswer(personId, answer.getCategory(), score == 0 ? 1 : score);
					score = 0;
				} catch (SQLException e) {
					e.printStackTrace();
					FailView.print("결과값이 이상이 있어요. 다시 시도해주세요!");
				}
			}
			index++;
		}
		
		Controller.getJobs(personId);
	}

	public static void getJobs(int personId) {
		try {

			FinishView.printResult(getName(personId), jobDAO.getJob(personId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}






