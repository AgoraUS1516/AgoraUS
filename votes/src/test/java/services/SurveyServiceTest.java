package services;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import domain.Question;
import domain.Survey;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class SurveyServiceTest extends AbstractTest {

	@Autowired
	private SurveyService surveyService;

	@Test
	public void saveCreateSurveyTest() {

		Survey s = new Survey();

		// Census c = new Census();
		Collection<Question> questions = new LinkedList<>();
		Question q = new Question("Pregunta cualquiera");
		questions.add(q);

		s.setCensus(2);
		s.setDescription("Prueba");
		s.setTitle("Titulo prueba");
		s.setCategory("Deportes");
		s.setStartDate(new Date());
		s.setEndDate(new Date());
		s.setQuestions(questions);

		surveyService.save(s);

		System.out.println("====== Mostrando los surveys despues de guardar ======");
		for (Survey e : surveyService.findAll()) {
			System.out.println(e);
		}

	}

	@Test
	public void getAllFinishedSurveysTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stringFecha = "2020-10-10";
		Date fecha;
		try {
			fecha = (Date)sdf.parse(stringFecha);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					 "Formato de fecha inválido o fecha no válida");
		}
		Survey s = new Survey();
		Collection<Question> questions = new LinkedList<>();
		Question q = new Question("Pregunta cualquiera");
		questions.add(q);
		s.setCensus(2);
		s.setDescription("Prueba");
		s.setTitle("Titulo prueba");
		s.setCategory("Deportes");
		s.setStartDate(new Date());
		s.setEndDate(new Date());
		s.setQuestions(questions);

		Survey s2 = new Survey();
		Collection<Question> questions2 = new LinkedList<>();
		Question q2 = new Question("Pregunta cualquiera2");
		questions2.add(q2);

		s2.setCensus(5);
		s2.setDescription("Prueba2");
		s2.setTitle("Titulo prueba2");
		s2.setCategory("Deportes");
		s2.setStartDate(new Date());
		s2.setEndDate(fecha);
		s2.setQuestions(questions2);

		surveyService.save(s);
		surveyService.save(s2);

		System.out.println("====== Mostrando los surveys despues de guardar ======");
		for (Survey a : surveyService.findAll()) {
			System.out.println(a);
		}

		List<Survey> res = surveyService.getAllFinishedSurveys();
		System.out.println("====== Mostrando solo la prueba terminada ======");
		System.out.println(res);

	}

	@Test
 public void getAllCreatedSurveysTest(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stringFecha = "2020-10-10";
		Date fecha;
		try {
			fecha = (Date)sdf.parse(stringFecha);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					 "Formato de fecha inválido o fecha no válida");
		}
 Survey s = new Survey();
 Collection<Question> questions = new LinkedList<>();
 Question q = new Question("Pregunta cualquiera");
 questions.add(q);
 String usuario1 = "Usuario1";

 s.setCensus(2);
 s.setDescription("Prueba");
 s.setTitle("Titulo prueba");
 s.setCategory("Deportes");
 s.setStartDate(new Date());
 s.setEndDate(new Date());
 s.setQuestions(questions);
 s.setUsernameCreator(usuario1);

 Survey s2 = new Survey();
 Collection<Question> questions2 = new LinkedList<>();
 Question q2 = new Question("Pregunta cualquiera2");
 questions2.add(q2);
 String usuario2 = "Usuario2";

 s2.setCensus(5);
 s2.setDescription("Prueba2");
 s2.setTitle("Titulo prueba2");
 s2.setCategory("Deportes");
 s2.setStartDate(new Date());
 s2.setEndDate(fecha);
 s2.setQuestions(questions2);
 s.setUsernameCreator(usuario2);

 surveyService.save(s);
 surveyService.save(s2);

 System.out.println("====== Mostrando los surveys despues de guardar ======");
 for(Survey a : surveyService.findAll()){
 System.out.println(a);
 }

 List<Survey> res = surveyService.getAllCreatedSurveys(usuario2);
 System.out.println("====== Mostrando Surveys filtradas por usernameCreator======");
 System.out.println(res);

 }

	@Test
 public void deleteTest(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stringFecha = "2020-10-10";
		Date fecha;
		try {
			fecha = (Date)sdf.parse(stringFecha);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					 "Formato de fecha inválido o fecha no válida");
		}
		Survey s = new Survey();
		Collection<Question> questions = new LinkedList<>();
		Question q = new Question("Pregunta cualquiera");
		questions.add(q);
		String usuario1 = "Usuario1";

		s.setCensus(2);
		s.setDescription("Prueba");
		s.setTitle("Titulo prueba");
		s.setCategory("Deportes");
		s.setStartDate(new Date());
		s.setEndDate(new Date());
		s.setQuestions(questions);
		s.setUsernameCreator(usuario1);

		Survey s2 = new Survey();
		Collection<Question> questions2 = new LinkedList<>();
		Question q2 = new Question("Pregunta cualquiera2");
		questions2.add(q2);
		String usuario2 = "Usuario2";

		s2.setCensus(5);
		s2.setDescription("Prueba2");
		s2.setTitle("Titulo prueba2");
		s2.setCategory("Deportes");
		s2.setStartDate(new Date());
		s2.setEndDate(fecha);
		s2.setQuestions(questions2);
		s.setUsernameCreator(usuario2);

		Integer res1 = this.surveyService.save2(s);
		Integer res2 = this.surveyService.save2(s2);

		System.out.println("====== Mostrando los surveysIDs despues de guardar======");
		for (Survey a : surveyService.findAll()) {
			System.out.println(a.getId());
		}

		surveyService.delete(res1);

		System.out.println("====== Mostrando los surveysIDs despues de borrar======");
		for (Survey a : surveyService.findAll()) {
			System.out.println(a.getId());
		}

	}

	@Test
	public void posibleTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stringFecha = "2020-10-10";
		Date fecha;
		try {
			fecha = (Date)sdf.parse(stringFecha);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					 "Formato de fecha inválido o fecha no válida");
		}
		Survey s = new Survey();
		Collection<Question> questions = new LinkedList<>();
		Question q = new Question("Pregunta cualquiera");
		questions.add(q);
		String usuario1 = "Usuario1";

		s.setCensus(2);
		s.setDescription("Prueba");
		s.setTitle("Titulo prueba");
		s.setCategory("Deportes");
		s.setStartDate(new Date());
		s.setEndDate(new Date());
		s.setQuestions(questions);
		s.setUsernameCreator(usuario1);

		Survey s2 = new Survey();
		Collection<Question> questions2 = new LinkedList<>();
		Question q2 = new Question("Pregunta cualquiera2");
		questions2.add(q2);
		String usuario2 = "Usuario2";

		s2.setCensus(null);
		s2.setDescription("Prueba2");
		s2.setTitle("Titulo prueba2");
		s2.setCategory("Deportes");
		s2.setStartDate(new Date());
		s2.setEndDate(fecha);
		s2.setQuestions(questions2);
		s.setUsernameCreator(usuario2);

		Integer res1 = this.surveyService.save2(s);
		Integer res2 = this.surveyService.save2(s2);

		System.out.println("====== Mostrando los surveys despues de guardar ======");
		for (Survey a : surveyService.findAll()) {
			System.out.println(a.getId());
		}
		System.out.println("====== Mostrando si es posible añadir censo ======");
		System.out.println(surveyService.posible(res1));
		System.out.println(surveyService.posible(res2));
	}

}