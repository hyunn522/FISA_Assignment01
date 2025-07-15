package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.JobDAO;
import Model.domain.Job;
import Model.domain.Question;
import View.FailView;
import View.QuizView;

public class Controller {
	private static  JobDAO jobDAO = JobDAO.getJobDAO();
	
//	public static void resetQuiz() throws Exception {
//		System.out.println("Restart!");
//		System.out.println("안녕하세요? 부업추천 프로그램입니다. 성함을 입력해주세요.");
//		StartView.setName();
//	}
	public static void setName(String name) {
		if(name !=null && name.length() != 0) {
			// TODO: 사람 생성 코드 (서현언닝)
			System.out.println("성겅");
			QuizView.start();
		} else {
			FailView.print("저장을 실패했어요. 다시 시작해주세요.");
		}
		
	}
	
	public static ArrayList<Question> getQuizs(){
		// 퀴즈 데이터 불러오기
		ArrayList<Question> datas = null;
		
		
		return datas;
	}
	
	public static void save(ArrayList<String> answerList) {
		int index = 0;
		ArrayList<Integer> scoreList = new ArrayList<>();
		
		for (String answer : answerList) {
			int score = 0;
			if(index%2 != 0) {
				// 카테고리 종류에 따른 구분
				for(int type = 0; type < 2; type++) {
					if(answerList.get(index - type).equals("네")) {
						System.out.println(answerList.get(index - type));
						score++;
					}
				scoreList.add(score == 0? 1 : score);
				}
			}
			index++;
		}
	}

	
	public static ArrayList<Job> getJobs(){
		try {
			ArrayList<Job> jobs = jobDAO.getJobs();
			System.out.println(jobs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
}
