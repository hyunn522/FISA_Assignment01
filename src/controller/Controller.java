package controller;

import java.util.ArrayList;
import java.util.Arrays;

import View.FinishView;
import View.QuizView;
import View.StartView;
import Model.Model;

public class Controller {
	private static String name; 
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
//		String[] list = dataList.toArray(new String[dataList.size()]);
//		int size=0;
//		for(String data : dataList){
//			list[size++] = data;
//		}
//		
		model.saveScore(dataList);
		saveAnswerList = model.getResult();
		
		if (saveAnswerList.size() == 0) {
			System.out.println("적절한 N잡을 찾지 못했어요 ㅜㅜ");
			System.exit(0);;
		}
		
		System.out.println("결과는~~~~~~???!");
		
		if(saveAnswerList.size() == 0) {
			//TODO: throw 추가 예
		}else {
			// 결과값 출력
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < saveAnswerList.size(); i++) {
				if (i < saveAnswerList.size() - 1) sb.append(saveAnswerList.get(i) + ", ");
				else sb.append(saveAnswerList.get(i) + "입니다!");
			}
//			FinishView.print(name+" 님의 결과는~~?? 따따딸ㄴ~~~ " + saveAnswerList);
			FinishView.print(name+"님에게 어울리는 N잡은 " + sb);
			
		}
	}
	
	
	//이름 세팅
	public static void setName(String n) throws Exception {
		name = n;
//		System.out.println("controller !!");
//		System.out.println(name);
		
		if(n.length() == 0) {
			System.out.println("아 해줘.. 이름 내놔 뭐야?");
			StartView.setName();
		}
		else {
			System.out.println("퀴즈 쓰따뚜");
			model.setPerson(name);
			QuizView.startQuiz(model.getQuestions());
		}
	}
}
