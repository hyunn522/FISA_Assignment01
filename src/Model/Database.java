package Model;

import Model.domain.Answer;
import Model.domain.Question;

public class Database {
	
	private static Package person;
	
	private static Question[] questions;
	
	private static Answer[] answers;
	
	static {
		questions = new Question[] {
				new Question("성향", "혼자서 조용히 보내는 시간이 에너지를 충전하는 데 도움이 되나요?"),
				new Question("성향", "새로운 사람들과 만나는 자리가 많을수록 더 즐겁나요?"),
				new Question("활동", "머리를 써서 문제를 해결하거나 아이디어를 내는 것이 더 편한가요?"),
				new Question("활동", "몸을 움직이며 활동하는 일을 할 때 더 성취감을 느끼나요?"),
				new Question("근무", "정해진 시간과 방식에 따라 일하는 것이 더 효율적이라고 생각하나요?"),
				new Question("근무", "스스로 시간을 정하고 유동적으로 일하는 걸 선호하시나요?"),
				new Question("목표", "일할 때 자기 성장이나 새로운 지식 습득이 가장 큰 동기인가요?"),
				new Question("목표", "가장 중요하게 생각하는 건 수익이나 보상인가요?")
		};
		
		answers = new Answer[] { // 각 문제에 대해 점수를 얻는 옵션
				new Answer("성향", "네"),
				new Answer("성향", "아니오"),
				new Answer("활동", "네"),
				new Answer("활동", "아니오"),
				new Answer("근무", "네"),
				new Answer("근무", "아니오"),
				new Answer("목표", "네"),
				new Answer("목표", "아니오")
				
		};
	}
}
