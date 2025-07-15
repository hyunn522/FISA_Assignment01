package Model;

import java.util.ArrayList;

import Model.domain.Answer;
import Model.domain.Job;
import Model.domain.Question;

public class Model {
	
	
	private static Model model = new Model();
	
	private Model() {}
	public static Model getModel() { return model; }
	
	public void setPerson(String name) throws Exception {
		
		if (name.isEmpty()) {
			throw new Exception("이름은 공백일 수 없습니다.");
		}   
		
		Database.setPerson(name);
		
	}
	
	public Question[] getQuestions() throws Exception {
		Question[] questions = Database.getQuestions();
		
		if (questions.length == 0) {
			throw new Exception("퀴즈 정보가 없습니다.");
		}
		return questions;
	}
	
	public Answer[] getAnswers() throws Exception {
		Answer[] answers = Database.getAnswers();
		
		if (answers.length == 0) {
			throw new Exception("정답 정보가 없습니다.");
		}
		return answers;
	}
	
	public void saveScore(Answer[] userAnswers) {
		int[] score = new int[4]; // 카테고리별 점수

		Answer[] answer = Database.getAnswers();
		for (int categoryIdx = 0; categoryIdx < 4; categoryIdx++) { // 카테고리 개수만큼
			for (int questionIdx = 0; questionIdx < 2; questionIdx++) {
				int scoreIdx = categoryIdx * 2 + questionIdx;
				if (userAnswers[scoreIdx].equals(answer[scoreIdx])) {
					score[categoryIdx]++;
				}
			}
		}
		
		Database.saveScore(score);
	}
	
	public ArrayList<String> getResult() {
		// 사용자 점수에 따른 N잡 진단
		int[] personScores = Database.getPerson().getScore(); // 입력한 점수 정보
		Job[] jobs = Database.getJobs(); // 기준 정보
		
		for (int i = 0; i < 16; i++) {
			boolean isPossible = true;
			int[] curScores = jobs[i].getScore();
			
			for (int index = 0; index < 4; index++) {
				int personScore = personScores[index];
				int curScore = curScores[index];

				if ((personScore > 0 && personScore != curScore) || (personScore == 0 && personScore + 1 != curScore)) {
					isPossible = false;
					break;
				}
			}
			
			if (isPossible) {
                return new ArrayList<String>(jobs[i].getName());
			}
		}
		
//		return new ArrayList<String>();
		return null;//????
	}
	
}
