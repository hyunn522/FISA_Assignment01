package Model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonCategoryScore {
	private int id;
	private int personId;
	private String category;
	private int score;
	
	public PersonCategoryScore(int personId, String category, int score) {
		this.personId = personId;
		this.category = category;
		this.score = score;
	}
}
