package View;

import Model.domain.Answer;
import java.util.ArrayList;

import Model.domain.Question;
import controller.Controller;

public class QuizView {
	public static void startQuiz(Question[] questions) throws Exception {
		Answer[] answers = new Answer[8];
		
		for (int idx = 0; idx < 8; idx++) {
			Question quiz = questions[idx];
			System.out.println(quiz.getText()+"???");
			System.out.println("(네 또는 아니오로 대답해주세요!)");
			String result = StartView.sc.nextLine();
			
			if(result.length() == 0  || !(result.equals("네") || result.equals("아니오"))) {
				Controller.resetQuiz();
			}else {
				answers[idx] = new Answer(quiz.getCategory(), result);
			}
		}
		
		// 질문 끗
		Controller.saveAnswerList(answers);
	}
}