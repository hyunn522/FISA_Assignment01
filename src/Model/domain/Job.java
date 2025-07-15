package Model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Job {
	private int id;
	private int cPersonality;
	private int cActivity;
	private int cWork;
	private int cGoal;
	private String content;

}
