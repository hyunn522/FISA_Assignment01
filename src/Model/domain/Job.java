package Model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Job {
	
	private int id;
	private int personality;
	private int activity;
	private int work;
	private int goal;
	private String recommends;
	
}
