package Model;

import java.util.List;

import Model.domain.Answer;
import Model.domain.Job;
import Model.domain.Person;
import Model.domain.Question;

public class Database {
	
	private static final Database instance = new Database();
	
	private Person person;
	private Question[] questions;
	private Answer[] answers;
	private Job[] jobs;
	
	private Database() {
		
		this.person = new Person();
		
		this.questions = new Question[] {
			new Question("성향", "혼자서 조용히 보내는 시간이 에너지를 충전하는 데 도움이 되나요?"),
			new Question("성향", "새로운 사람들과 만나는 자리가 많을수록 더 즐겁나요?"),
			new Question("활동", "머리를 써서 문제를 해결하거나 아이디어를 내는 것이 더 편한가요?"),
			new Question("활동", "몸을 움직이며 활동하는 일을 할 때 더 성취감을 느끼나요?"),
			new Question("근무", "정해진 시간과 방식에 따라 일하는 것이 더 효율적이라고 생각하나요?"),
			new Question("근무", "스스로 시간을 정하고 유동적으로 일하는 걸 선호하시나요?"),
			new Question("목표", "일할 때 자기 성장이나 새로운 지식 습득이 가장 큰 동기인가요?"),
			new Question("목표", "가장 중요하게 생각하는 건 수익이나 보상인가요?")
		};
		
		this.answers = new Answer[] {
			new Answer("성향", "네"),
			new Answer("성향", "아니오"),
			new Answer("활동", "네"),
			new Answer("활동", "아니오"),
			new Answer("근무", "네"),
			new Answer("근무", "아니오"),
			new Answer("목표", "네"),
			new Answer("목표", "아니오")
		};
		
		this.jobs = new Job[] {
			new Job(new int[] { 2, 2, 2, 2 }, List.of("온라인 강의 제작", "블로그 운영", "전자책 집필", "데이터 라벨링", "온라인 튜터링")),
			new Job(new int[] { 2, 2, 2, 1 }, List.of("유튜브 콘텐츠 제작", "앱 개발", "번역/통역", "온라인 리서치", "블로그·SNS 콘텐츠 제작")),
			new Job(new int[] { 2, 1, 2, 2 }, List.of("수공예(악세서리, 캔들 등) 제작 및 판매", "정기적인 오프라인 클래스 운영")),
			new Job(new int[] { 2, 1, 2, 1 }, List.of("그림·일러스트 프리랜서", "사진 촬영 및 편집", "원데이 클래스 강사")),
			new Job(new int[] { 2, 2, 1, 2 }, List.of("오프라인 그룹 강의", "학원 강사", "코워킹 스페이스 운영")),
			new Job(new int[] { 2, 2, 1, 1 }, List.of("네트워킹 기획 컨설팅", "프리랜서 강연", "팟캐스트 진행")),
			new Job(new int[] { 2, 1, 1, 2 }, List.of("오프라인 매장 알바", "행사 스태프", "플리마켓 운영")),
			new Job(new int[] { 2, 1, 1, 1 }, List.of("여행 가이드", "행사 진행", "체험 프로그램 운영")),
			new Job(new int[] { 1, 2, 2, 2 }, List.of("데이터 입력", "온라인 설문조사", "원격 사무보조", "온라인 고객센터")),
			new Job(new int[] { 1, 2, 2, 1 }, List.of("리워드 앱", "크라우드소싱 플랫폼 작업", "온라인 상품 리뷰", "프리랜서 번역")),
			new Job(new int[] { 1, 1, 2, 2 }, List.of("택배 분류", "단기 알바(포장, 검수 등)", "오프라인 배달")),
			new Job(new int[] { 1, 1, 2, 1 }, List.of("중고거래", "심부름 앱", "오프라인 배달(자유 스케줄)")),
			new Job(new int[] { 1, 2, 1, 2 }, List.of("텔레마케팅", "콜센터 상담", "팀 프로젝트 프리랜서")),
			new Job(new int[] { 1, 2, 1, 1 }, List.of("온라인투자 영업", "SNS 마케팅 대행")),
			new Job(new int[] { 1, 1, 1, 2 }, List.of("매장 판매직", "창업 운영 지원", "프로모션 스태프")),
			new Job(new int[] { 1, 1, 1, 1 }, List.of("배달 라이더", "식사 도우미", "거리 홍보", "플리마켓 부스 운영")) 
		};
	}
	
	public static Database getInstance() {
		return instance;
	}
	
	public void setPersonName(String name) {
		this.person.setName(name);
	}
	
	public void saveScore(int[] score) {
		this.person.setScore(score);
	}
	
	public Person getPerson() {
		return person;
	}
	
	public Question[] getQuestions() {
		return questions;
	}
	
	public Answer[] getAnswers() {
		return answers;
	}
	
	public Job[] getJobs() {
		return jobs;
	}
}
