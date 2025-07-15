package View;

import Model.domain.Answer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Model.domain.Question;
//import controller.Controller;
import controller.Controller;

public class QuizView {
//	public static ArrayList<String> answerArrayList =  null;

	// 시작
	public static void start() {
		ArrayList<String> answerArrayList = new ArrayList<>();
		final Scanner sc = new Scanner(System.in);
		
		//TODO: 서형언니 코드랑 연동하고 나서 주석 풀기
//		ArrayList<Question> resultList =  Controller.getQuizs();
		
		ArrayList<Question> resultList = new ArrayList<>(
				Arrays.asList(new Question(1, "dd", "ddd"),new Question(1, "dd", "ddd"),new Question(1, "dd", "ddd"),new Question(1, "dd", "ddd"),new Question(1, "dd", "ddd"),new Question(1, "dd", "ddd"),new Question(1, "dd", "ddd"),new Question(1, "dd", "ddd"))
				);
		
		for (Question question : resultList) {
			System.out.println(question.getText()+"?");
			String answer =  sc.nextLine().trim();
			
			if(answer.isEmpty()) {
				System.out.println("답변을 입력해주세요~");
				answer = sc.nextLine().trim();
			}
			
			answerArrayList.add(answer);	
		}
		
		// 값 저장
		Controller.save(answerArrayList);
	}
	
}