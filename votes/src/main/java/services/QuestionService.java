package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.QuestionRepository;
import domain.DomainEntity;
import domain.Question;

@Service
public class QuestionService extends DomainEntity{

	//Repository
	@Autowired
	private QuestionRepository questionRepository;

	//Methods
	public Question create(){
		Question o = new Question();
		return o;
	}
}
