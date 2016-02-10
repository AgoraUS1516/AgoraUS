package services;

import java.util.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Census;
import domain.Survey;
import repositories.SurveyRepository;
@Service
public class SurveyService {

	//Repository
	@Autowired
	private SurveyRepository surveyRepository;
	
	//Services
	@Autowired
	private QuestionService questionService;
	
	
	public Integer save2(Survey s){
		Assert.notNull(s);
		Survey s1 = surveyRepository.save(s);
		return s1.getId();
	}
	
	
	public void save(Survey s){
		Assert.notNull(s);
		surveyRepository.save(s);
	
	}
	
	public Survey findOne(int id){
		Assert.notNull(id);
		return surveyRepository.findOne(id);
	}
	
	public Collection<Survey> findAll(){
		Collection<Survey> res = new LinkedList<>();
		
		res = surveyRepository.findAll();
		Assert.notNull(res);
		
		return res;
	}
		
	public void delete(int id){
		Assert.notNull(id);
		surveyRepository.delete(id);
	}
	//Método de interacción con el subsistema de Creacion de Censos
	public void addCensus(Integer s, Integer c){
		Survey survey = findOne(s);
		Assert.notNull(c);
		Assert.notNull(survey);
		
		Assert.isTrue(survey.getCensus() == null);
		survey.setCensus(c);
		surveyRepository.save(survey);
		
	}
	
	//Método de interacción con el subsistema de Visualización
	public List<Survey>getAllFinishedSurveys(){
		
		Date now = new Date();
		List<Survey>res = surveyRepository.getAllFinishedSurveys(now);
		return res;
	}
	
	public List<Survey>getAllCreatedSurveys(String usernameCreator){
		List<Survey>res = surveyRepository.getAllCreatedSurveys(usernameCreator);
		return res;
	}

	
	public Boolean posible (int id) {
		Assert.notNull(id);
		Survey s = findOne(id);
		
		if(s.getCensus() == null){
			return true;
		}else{
			return false;
		}
	}

}
