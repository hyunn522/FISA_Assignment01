package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.domain.Answer;
import Model.domain.Question;
import controller.Controller;

public class QuizView {

    public static void start(int personId) {
        ArrayList<Answer> answerArrayList = new ArrayList<>();
        try (Scanner sc = new Scanner(System.in)) {
            ArrayList<Question> resultList = Controller.getQuizs();

            for (Question question : resultList) {
                String answer = "";
                while (answer.isBlank()) {
                    System.out.println(question.getText() + "?");
                    answer = sc.nextLine().trim();
                    if (answer.isBlank()) {
                        System.out.println("답변을 입력해주세요~");
                    }
                }
                answerArrayList.add(new Answer(question.getId(), answer));
            }

            Controller.save(personId, answerArrayList);
        }
    }
}
