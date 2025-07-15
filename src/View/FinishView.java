package View;

import controller.Controller;

public class FinishView {
	
	public static void printResult(String name, String job) {

		System.out.println("--------------------------------------------------------------------");
		System.out.println(name + "님에게 어울리는 N잡은 : " + job);
		System.out.println("--------------------------------------------------------------------");
	}

	public static void getResult(int personId) {
		Controller.getJobs(personId);
	}
	
}