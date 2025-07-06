package controller;

import java.util.ArrayList;

import View.FinishView;
import View.QuizView;
import View.StartView;
import Model.Model;

public class Controller {
	private static String name;  //사람 이름
	private static ArrayList<String> saveAnswerList;
	
	private static final Model model = Model.getModel();
	
//	퀴즈 리셋
	public static void resetQuiz() throws Exception {
		System.out.println("restart!");
		System.out.println("YO 반갑다, 이름이 뭐야?");
		StartView.setName();
	}
	
	// 입력값 저장
	public static void saveAnswerList(ArrayList<String> dataList) {
	
		model.saveScore(dataList);
		saveAnswerList = model.getResult();

		if (saveAnswerList == null || saveAnswerList.isEmpty()) {
			System.out.println("적절한 N잡을 찾지 못했어요 ㅜㅜ");
			System.exit(0);;
		}
		int count = saveAnswerList.size();
		
		System.out.println("-------------------------");
		System.out.println("결과는~~~~~~???!");
		

		// 결과값 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			if (i < count - 1) {
				sb.append(saveAnswerList.get(i) + ", ");
			}else {
				sb.append(saveAnswerList.get(i) + "입니다!");
			}
		}
		FinishView.print(name+"님에게 어울리는 N잡은 " + sb);
			
		System.out.println("-------------------------");
	}
	
	
	//이름 세팅  "" : length() 
	public static void setName(String n) throws Exception {
		
		name = n;
		
		if(n == null || n.length() == 0) {
			System.out.println("아 해줘.. 이름 내놔 뭐야?");
			StartView.setName();
		}else {
			System.out.println("퀴즈 쓰따뚜");
			model.setPerson(name);
			QuizView.startQuiz(model.getQuestions());
		}
		
	}
	
}
