package View;

import Model.domain.Answer;
import java.util.ArrayList;
import java.util.Scanner;

import Model.domain.Question;
import controller.Controller;

public class QuizView {
	
	public static void startQuiz(Question[] questions) throws Exception {
		
		int questionsCnt = questions.length;
		Answer[] answers = new Answer[questionsCnt];
		Scanner scanner = StartView.sc;
		
		for(int idx = 0; idx < questionsCnt; idx++) {
			Question quiz = questions[idx];
			String input;
			
			while(true) {
				System.out.println(quiz.getText() + "?");
				System.out.println("(네 or 아니오)");
				
				input = scanner.nextLine().trim();
				
				if(isValidAnswer(input)) {
					break;
				}
				
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
			
			answers[idx] = new Answer(quiz.getCategory(), input);
		}
		
		Controller.saveAnswerList(answers);
	}
	
	private static boolean isValidAnswer(String input) {
        return input.equals("네") || input.equals("아니오");
    }
}