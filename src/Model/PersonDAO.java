package Model;

import Model.domain.Person;

public class PersonDAO {
	private PersonDAO personDAO = new PersonDAO();
	
	private PersonDAO() {}
	
	public PersonDAO getPersonDAO() {
		return personDAO;
	}
	
	
	// 사람 불러오기
	public Person getPerson() {
		return null;
	}

	// 사람 저장
	public boolean setPerson(String name) {
		return false;
	}
}
