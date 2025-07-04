package controller;

import java.util.ArrayList;
import java.util.Arrays;

import View.FinishView;
import View.QuizView;
import View.StartView;

public class Controller {
	private static String name; 
	private static String[] saveAnswerList; // TODO: 추후 타입 변경 예정
	
//	퀴즈 리셋
	public static void resetQuiz() {
		System.out.println("게임 다시 시작");
		System.out.println("YO 반갑다, 이름이 뭐야?");
		StartView.setName();
	}
	
	// 입력값 저장
	public static void saveAnswerList(ArrayList<String> dataList) {
		String[] list = dataList.toArray(new String[dataList.size()]);
		int size=0;
		for(String data : dataList){
			list[size++] = data;
		}
		
		saveAnswerList = list;

		System.out.println("결과는~~~~~~???!");
		System.out.println(Arrays.toString(list));
		
		if(saveAnswerList.length == 0) {
			//TODO: throw 추가 예
		}else {
			// 결과값 출력
			FinishView.print(name+" 님의 결과는~~?? 따따딸ㄴ~~~");
			
		}
	}
	
	
	//이름 세팅
	public static void setName(String n) {
		name = n;
		System.out.println("controller !!");
		System.out.println(name);
		
		if(n.length() == 0) {
			System.out.println("아 해줘.. 이름 내놔 뭐야?");
			StartView.setName();
		}
		else {
			System.out.println("퀴즈 쓰따뚜");
			QuizView.startQuiz();
		}
	}
}
