//package controller;
//
//import Model.domain.Answer;
//import java.util.ArrayList;
//
//import View.FinishView;
//import View.QuizView;
//import View.StartView;
//import Model.ModelDAO;
//
//public class Controller {
//	
//	private static final ModelDAO model = ModelDAO.getModel();
//	
//	public static void resetQuiz() throws Exception {
//		System.out.println("Restart!");
//		System.out.println("안녕하세요? 부업추천 프로그램입니다. 성함을 입력해주세요.");
//		StartView.setName();
//	}
//	
//	public static void setName(String name) throws Exception {
//		if(name == null || name.trim().isEmpty()) {
//			System.out.println("성함을 입력해주세요!!");
//			StartView.setName();
//			return;
//		}
//		
//		model.setPerson(name);
//		System.out.println("퀴즈를 시작하겠습니다.");
//		QuizView.startQuiz(model.getQuestions());
//	}
//	
//	public static void saveAnswerList(Answer[] answers) {
//		model.saveScore(answers);
//		ArrayList<String> result = model.getResult();
//		
//		if(result == null || result.isEmpty()) {
//			System.out.println("적절한 N잡을 찾지 못했습니다.");
//			System.exit(0);
//		}
//		
//		FinishView.printResult(model.getPerson().getName(), result);
//	}
//}
