package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import main.Authority;
import main.AuthorityImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import services.SurveyService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Census;
import domain.CheckToken;
import domain.Survey;

@RestController
@RequestMapping("/vote")
public class SurveyController {

	// Services
	@Autowired
	private SurveyService surveyService;



	@RequestMapping(value = "/getcookies", method = RequestMethod.GET)
	public @ResponseBody String cookie(@CookieValue("user") String user,
			@CookieValue("token") String token) {
		return "{\"user\":\"" + user + "\", \"token\":\"" + token + "\"}";
	}

	// Método que devuelve la lista de votaciones creadas para editarlas.
	// Relación con CREACION/ADMINISTRACION DE CENSO.
	@RequestMapping(value = "/mine", method = RequestMethod.GET)
	public Collection<Survey> findAllSurveyByCreator(
			@CookieValue("user") String user, @CookieValue("token") String token) 
			throws JsonParseException, JsonMappingException, IOException{
		String creator = user;
		CheckToken isValid = new CheckToken();
		ObjectMapper checkToken = new ObjectMapper();
		isValid = checkToken.readValue(new URL(
				"http://localhost/auth/api/checkToken?token=" + token),
				domain.CheckToken.class);
		Assert.isTrue(isValid.getValid());
		Collection<Survey> res = surveyService.getAllCreatedSurveys(creator);
		return res;
	}

	// Método que devuelve la lista de votaciones finalizadas. Relación con
	// VISUALIZACION.
	@RequestMapping(value = "/finishedSurveys", method = RequestMethod.GET)
	public Collection<Survey> findAllfinishedSurveys() {
		Collection<Survey> res = surveyService.getAllFinishedSurveys();
		return res;
	}
	
	// Método que devuelve la lista completa de finalizadas. Relación con
	// VISUALIZACION.
	@RequestMapping(value = "/allSurveys", method = RequestMethod.GET)
	public Collection<Survey> findAllSurveys() {
		Collection<Survey> res = surveyService.findAll();
		return res;
	}

	// Método que borra una votación tras comprobar que no tiene censo
	// relacionado.
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void delete(@RequestParam int id) {
		surveyService.delete(id);
	}

	// Método devuelve una survey para realizar una votación. Relación con
	// CABINA DE VOTACION
	@RequestMapping(value = "/survey", method = RequestMethod.GET)
	public Survey getSurvey(@RequestParam int id) {
		Survey s = surveyService.findOne(id);
		return s;
	}
	
	

	// Método para guardar la votación creada.
	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Content-Type=application/json")
	public @ResponseBody Survey save(@RequestBody String surveyJson,
			@CookieValue("user") String user, @CookieValue("token") String token)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Survey s = mapper.readValue(surveyJson, Survey.class);

		CheckToken isValid = new CheckToken();
		ObjectMapper checkToken = new ObjectMapper();
		isValid = checkToken.readValue(new URL(
				"http://localhost/auth/api/checkToken?token=" + token),
				domain.CheckToken.class);
		Assert.isTrue(isValid.getValid());
		int i = surveyService.save2(s);
		Survey res = surveyService.findOne(i);
		Authority a = new AuthorityImpl();
		a.postKey(String.valueOf(res.getId()));
		return res;
	}

	// Método para guardar la votación con el censo. Relación con
	// CREACION/ADMINISTRACION DE CENSO.
	@RequestMapping(value = "/saveCensus", method = RequestMethod.GET)
	public @ResponseBody void saveCensus(@RequestParam Integer surveyId,
			@RequestParam Integer census) throws JsonParseException,
			JsonMappingException, IOException {
		
		surveyService.addCensus(surveyId, census);
	}

}
