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

    public Answer(int questionId, String text) {
        this.questionId = questionId;
        this.text = text;
    }
}
