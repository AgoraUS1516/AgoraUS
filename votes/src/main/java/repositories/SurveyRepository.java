package repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Survey;
@Repository
public interface SurveyRepository extends JpaRepository<Survey,Integer>{
	
	@Query("select s from Survey s where ?1 = s.endDate")
	public List<Survey> getAllFinishedSurveys(Date now);
	
	@Query("select s from Survey s where ?1 = s.usernameCreator")
	public List<Survey> getAllCreatedSurveys(String username);
}
