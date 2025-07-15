package View;

import Model.domain.Answer;
import Model.domain.PersonAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Model.domain.Question;
//import controller.Controller;
import controller.Controller;

public class QuizView {
//	public static ArrayList<String> answerArrayList =  null;

	// 시작
	public static void start(int personId) {
		ArrayList<PersonAnswer> answerArrayList = new ArrayList<>();
		final Scanner sc = new Scanner(System.in);
		
		ArrayList<Question> resultList =  Controller.getQuizs();
	
		for (Question question : resultList) {
			System.out.println(question.getText()+"?");
			String answer =  sc.nextLine().trim();
			
			if(answer.isEmpty()) {
				System.out.println("답변을 입력해주세요~");
				answer = sc.nextLine().trim();
			}
			
			answerArrayList.add(new PersonAnswer(question.getCategory(), answer));	
		}
		
		// 값 저장
		Controller.save(personId, answerArrayList);
	}

	
}