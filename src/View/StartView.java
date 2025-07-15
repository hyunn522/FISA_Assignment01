package View;

import java.util.Scanner;

import controller.Controller;

//import controller.Controller;

// 기본 페이지
public class StartView {
	public static void main(String[] args) {
		String inputName = null;
		
		System.out.println("성함이 어떻게 되시나요?");
		final Scanner sc = new Scanner(System.in);

		inputName = sc.nextLine().trim();
		
		while(inputName.isEmpty()) {
			System.out.println("성함을 다시 입력해주세요");
			inputName = sc.nextLine().trim();
		}
		Controller.setName(inputName);
	}
}
