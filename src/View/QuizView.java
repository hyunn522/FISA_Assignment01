package View;

import java.util.ArrayList;

import controller.Controller;

public class QuizView {
	public static void startQuiz() {
		String[] quizList = {"dddd","aswdfsadf"};
		ArrayList<String> answerList = new ArrayList<String>();
		
		for (String quiz : quizList) {
			System.out.println(quiz+"???");
			String result = StartView.sc.nextLine();
			
			if(result.length() == 0) {
				Controller.resetQuiz();
			}else {
				answerList.add(result);
			}
		}
		
		// 질문 끗
		Controller.saveAnswerList(answerList);
	}
}