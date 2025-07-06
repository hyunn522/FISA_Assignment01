package View;

import java.util.ArrayList;

import Model.domain.Question;
import controller.Controller;

public class QuizView {
	public static void startQuiz(Question[] questions) throws Exception {
		ArrayList<String> answerList = new ArrayList<String>();
		
		for (Question quiz : questions) {
			System.out.println(quiz.getText()+"???");
			System.out.println("(네 또는 아니오로 대답해주세요!)");
			String result = StartView.sc.nextLine();
			
			if(result.length() == 0  || !(result.equals("네") || result.equals("아니오"))) {
				Controller.resetQuiz();
			}else {
				answerList.add(result);
			}
		}
		
		// 질문 끗
		Controller.saveAnswerList(answerList);
	}
}