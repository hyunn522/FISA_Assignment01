package View;

import java.util.List;

public class FinishView {
	
	public static void printResult(String name, List<String> jobs) {
		
		StringBuilder sb = new StringBuilder();
		int count = jobs.size();
		
		for(int i = 0; i < count; i++) {
			if(i < count - 1) {
				sb.append(jobs.get(i)).append(", ");
			} else {
				sb.append(jobs.get(i)).append("입니다!");
			}
				
		}
		
		System.out.println("-------------------------");
		System.out.println(name + "님에게 어울리는 N잡은 " + sb);
		System.out.println("-------------------------");
	}
	
}