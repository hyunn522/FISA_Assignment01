package View;

import java.util.ArrayList;

import Model.domain.Question;
import controller.Controller;

public class QuizView {
	public static void startQuiz(Question[] questions) throws Exception {
		ArrayList<String> answerList = new ArrayList<String>();
		
		for (Question quiz : questions) {
			System.out.println(quiz.getText()+"???");
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