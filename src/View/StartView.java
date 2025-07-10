package View;

import java.util.Scanner;

import controller.Controller;

// 기본 페이지
public class StartView {
	
	static final Scanner sc= new Scanner(System.in);
	
	public static void setName() throws Exception {
		String name;
		
		while(true) {
			name = sc.nextLine().trim();
			
			if(isValidName(name)) {
				break;
			}
			
			System.out.println("공백없이 이름을 입력해주세요!");
		}
		
		Controller.setName(name);
	}
	
	private static boolean isValidName(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("성함이 어떻게 되시나요?");
        setName();
    }
}
