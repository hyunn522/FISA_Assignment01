package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Model.domain.Answer;
import Model.domain.Question;
import controller.Controller;

public class QuizView {

	public static void start(int personId) throws IOException {
    	
        ArrayList<Answer> userAnswer = new ArrayList<>();
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ArrayList<Question> correctAnswer = Controller.getQuizs();

            for (Question question : correctAnswer) {
                String answer = "";
                while (answer.isBlank()) {
                    System.out.println(question.getText());
                    answer = br.readLine().trim();
                    if (answer.isBlank()) {
                        System.out.println("답변을 입력해주세요~");
                    }
                }
                userAnswer.add(new Answer(question.getId(), answer));
            }

            Controller.save(personId, userAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("입력 중 문제가 발생했습니다.");
        }
    }
}
