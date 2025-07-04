package View;

import java.util.Scanner;

import controller.Controller;

// 기본 페이지
public class StartView {
	static final Scanner sc= new Scanner(System.in);
	
	public static void setName() throws Exception {
		String name = sc.nextLine();
		Controller.setName(name);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("YO 반갑다, 이름이 뭐야?");
		setName();
	}
}
