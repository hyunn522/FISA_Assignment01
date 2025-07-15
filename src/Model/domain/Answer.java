package Model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Answer {
	private int id;
	private int questionId;
	private String text;
//	private String category;

//	@Override
//	public boolean equals(Object o) {
//		if (o instanceof Answer) {
//			Answer a = (Answer) o;
//			return this.category.equals(a.category) && this.text.equals(a.text);
//		}
//		return false;
//	}
//	
//	@Override
//	public int hashCode() {
//	    return category.hashCode() + text.hashCode();
//	}

}
