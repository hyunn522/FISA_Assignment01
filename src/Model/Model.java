package Model;

import java.util.ArrayList;

import Model.domain.Answer;
import Model.domain.Job;
import Model.domain.Person;
import Model.domain.Question;

public class Model {
	
	private static final Model model = new Model();
	private final Database db = Database.getInstance();
	
	private Model() {}
	
	public static Model getModel() {
		return model;
	}
	
	public Person getPerson() {
	    return db.getPerson();
	}
	
	public void setPerson(String name) throws Exception {
		if(name == null || name.trim().isEmpty()){
			throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
		}
		db.setPersonName(name);
	}
	
	public Question[] getQuestions() throws Exception {
		Question[] questions = db.getQuestions();
		if(questions == null || questions.length == 0) {
			throw new Exception("퀴즈 정보가 없습니다.");
		}
		return questions;
	}
	
	public Answer[] getAnswers() throws Exception {
		Answer[] answers = db.getAnswers();
		if(answers == null || answers.length == 0) {
			throw new Exception("정답 정보가 없습니다.");
		}
		return answers;
	}
	
	public void saveScore(Answer[] userAnswers) {
		
	    int[] score = new int[4];
	    Answer[] correctAnswers = db.getAnswers();

	    for (int i = 0; i < userAnswers.length; i++) {
	        Answer userAnswer = userAnswers[i];
	        String category = userAnswer.getCategory();

	        int categoryIndex = switch (category) {
	            case "성향" -> 0;
	            case "활동" -> 1;
	            case "근무" -> 2;
	            case "목표" -> 3;
	            default -> -1;
	        };
	        
	        for (Answer correct : correctAnswers) {
	            if (userAnswer.equals(correct)) {
	                score[categoryIndex]++;
	                break;
	            }
	        }
	    }

	    db.saveScore(score);
	}

	
	public ArrayList<String> getResult() {
	    int[] personScores = db.getPerson().getScore();
	    Job[] jobs = db.getJobs();

	    for (Job job : jobs) {
	        int[] jobScore = job.getScore();
	        boolean match = true;

	        for (int i = 0; i < 4; i++) {
	            if (personScores[i] != jobScore[i]) {
	                match = false;
	                break;
	            }
	        }

	        if (match) {
	            return new ArrayList<>(job.getNames());
	        }
	    }

	    return new ArrayList<>();
	}

}
