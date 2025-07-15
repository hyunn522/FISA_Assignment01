package controller;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.AnswerDAO;
import Model.JobDAO;
import Model.PersonCategoryScoreDAO;
import Model.PersonDAO;
import Model.QuestionDAO;
import Model.domain.Answer;
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
			FailView.print("이름 불러오기를 실패했어요.");
		}
		return null;
	}
	
	public static void setName(String name) throws IOException {
		if (name != null && name.length() != 0) {
			try {
				int personId = personDAO.setPerson(name);
				QuizView.start(personId);
			} catch (SQLException e) {
				FailView.print("저장을 실패했어요. 다시 시작해주세요.");
				e.printStackTrace();
			}
		} else {
			FailView.print("저장을 실패했어요. 다시 시작해주세요.");
		}

	}

	public static ArrayList<Question> getQuizs() {
		ArrayList<Question> datas = null;
		try {
			datas = questionDao.getQuestions();
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.print("문제를 받아오지 못했어요. 다시 시도해주세요");
		}
		return datas;
	}

	private static ArrayList<Answer> getAnswers() {
		ArrayList<Answer> answers = null;
		try {
			answers = answerDAO.getAnswers();
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.print("N잡을 구할 수 없어요.");
		}
		return answers;

	}

	public static void save(int personId, ArrayList<Answer> answerList) {
	    try {
	        // 1. 정답 리스트 (questionId → 정답 text)
	        Map<Integer, String> correctMap = new HashMap<>();
	        for (Answer a : getAnswers()) {
	            correctMap.put(a.getQuestionId(), a.getText());
	        }

	        // 2. 문제 리스트 (questionId → category)
	        Map<Integer, String> questionCategoryMap = new HashMap<>();
	        for (Question q : getQuizs()) {
	            questionCategoryMap.put(q.getId(), q.getCategory());
	        }

	        // 3. 카테고리별 점수 계산 (일치한 질문 수만 집계)
	        Map<String, Integer> categoryScores = new HashMap<>();
	        for (Answer userAnswer : answerList) {
	            int qid = userAnswer.getQuestionId();
	            String userText = userAnswer.getText();
	            String correctText = correctMap.get(qid);
	            String category = questionCategoryMap.get(qid);

	            if (category == null) continue;

	            if (userText.equals(correctText)) {
	                categoryScores.put(category, categoryScores.getOrDefault(category, 0) + 1);
	            }
	        }

	        // 4. 점수 보정 및 객체 생성 (0~1점 → 1점, 2점 → 2점)
	        ArrayList<PersonCategoryScore> scoreList = new ArrayList<>();
	        for (Map.Entry<String, Integer> entry : categoryScores.entrySet()) {
	            String category = entry.getKey();
	            int rawScore = entry.getValue();
	            int finalScore = rawScore >= 2 ? 2 : 1;
	            scoreList.add(new PersonCategoryScore(0, personId, category, finalScore));
	        }

	        // 보정이 안 된 카테고리(0점인 경우)는 여기 없으니 1점으로 따로 추가
	        List<String> allCategories = List.of("성향", "활동", "근무", "목표");
	        for (String category : allCategories) {
	            boolean alreadyIn = scoreList.stream().anyMatch(s -> s.getCategory().equals(category));
	            if (!alreadyIn) {
	                scoreList.add(new PersonCategoryScore(0, personId, category, 1));
	            }
	        }

	        // 5. DB 저장
	        personCategoryScoreDAO.saveAnswer(personId, scoreList);
	        FinishView.getResult(personId);

	    } catch (SQLException e) {
	        e.printStackTrace();
	        FailView.print("결과 저장 중 문제가 발생했습니다. 다시 시도해주세요!");
	    }
	}

	public static String getJobs(int personId) {
	    try {
	        String jobs = jobDAO.getJob(personId);
	        FinishView.printResult(getName(personId), jobs);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
