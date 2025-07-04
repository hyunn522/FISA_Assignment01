package Model;

import java.util.ArrayList;

import Model.domain.Answer;
import Model.domain.Job;
import Model.domain.Question;

public class Model {
	
	private Database db = new Database();
	
	private static Model model = new Model();
	
	private Model() {}
	public static Model getModel() { return model; }
	
	public void setPerson(String name) throws Exception {
		if (name.isEmpty()) throw new Exception("이름은 공백일 수 없습니다.");
		db.setPerson(name);
	}
	
	public Question[] getQuestions() throws Exception {
		Question[] questions = db.getQuestions();
		
		if (questions.length == 0) throw new Exception("퀴즈 정보가 없습니다.");
		return questions;
	}
	
	public Answer[] getAnswers() throws Exception {
		Answer[] answers = db.getAnswers();
		
		if (answers.length == 0) throw new Exception("정답 정보가 없습니다.");
		return answers;
	}
	
	public void saveScore(ArrayList<String> results) {
		int[] score = new int[4]; // 카테고리별 점수
		
		int cur = 0;
		for (int i = 0; i < 8; i++) {
			String result = results.get(i);
			if (i % 2 == 0) { // 홀수 번째 문항
				if (result.equals("네")) cur = 1; 
			} else { // 짝수 번째 문항
				if (result.equals("아니오")) cur++;
				score[i / 2] = cur; // 카테고리에 해당하는 점수 입력
				cur = 0; // 다음 카테고리를 위해 초기화
			}
		}
		
		db.saveScore(score);
	}
	
	public ArrayList<String> getResult() {
		int[] personScores = db.getPerson().getScore(); // 입력한 점수 정보
		Job[] jobs = db.getJobs(); // 기준 정보
		
		for (int i = 0; i < 16; i++) {
			boolean isPossible = true;
			int[] curScores = jobs[i].getScore();
			for (int j = 0; j < 4; j++) {
				if (personScores[j] != curScores[j]) {
					isPossible = false;
					break;
				}
			}
			
			if (isPossible) {
				return (ArrayList<String>) jobs[i].getName();
			}
		}
		
		return new ArrayList<String>();
	}
}
