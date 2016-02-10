package domain;

import javax.persistence.Entity;
@Entity
public class Question extends DomainEntity{
	
	//Attributes
	private String text;
	
	public Question(){
		super();
		text = "";
	}
	
	public Question(String s){
		super();
		text = s;
	}
	//Methods
	public Question(Survey s){
		text = new String("");
	}
	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}

	@Override
	public String toString() {
		return "Question [text = " + text + "]";
	}
		
	
}
