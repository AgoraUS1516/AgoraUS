package domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Survey extends DomainEntity implements Serializable {

	private static final long serialVersionUID = 749544364605664829L;

	// Attributes
	private String title;
	private String description;
	private String category;
	private Date startDate;
	private Date endDate;
	private Integer census;

	// Constructors
	public Survey() {
		super();

		questions = new LinkedList<Question>();
	}

	public Survey(String title, String description, String category, Date startDate, Date endDate, Integer census) {
		super();

		checkFecha(startDate, endDate);

		this.title = title;
		this.description = description;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.census = census;

		questions = new LinkedList<Question>();
	}

	// Methods
	@NotBlank
	@Length(min = 5, max = 15, message = "The field must be between 5 and 15 characters")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@Length(min = 5, max = 150, message = "The field must be between 5 and 150 characters")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	@Length(min = 3, max = 15, message = "The field must be between 3 and 15 characters")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		if (startDate != null) {
			checkFecha2(startDate);
		}
		this.startDate = startDate;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		if (endDate != null) {
		}
		this.endDate = endDate;
	}

	public Integer getCensus() {
		return census;
	}

	public void setCensus(Integer census) {
		this.census = census;
	}

	// Relationships
	private Collection<Question> questions;
	private String usernameCreator;

	public String getUsernameCreator() {
		return usernameCreator;
	}

	public void setUsernameCreator(String usernameCreator) {
		this.usernameCreator = usernameCreator;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotEmpty
	public Collection<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}

	public void addQuestion(Question q) {
		questions.add(q);
	}

	public void removeQuestion(Question q) {
		questions.remove(q);
	}

	@Override
	public String toString() {
		return "Survey [title=" + title + ", description=" + description + "category=" + category + ", startDate="
				+ startDate + ", endDate=" + endDate + ", census=" + census + ", questions=" + questions + "]";
	}

	private void checkFecha(Date startDate, Date endDate) {

		if (startDate.before(new Date())) {
			throw new IllegalArgumentException("La fecha de inicio no puede ser anterior a la actual");
		}

		if (endDate.before(startDate)) {
			throw new IllegalArgumentException("La fecha de fin no puede ir antes que la de inicio");
		}

	}
	
	private void checkFecha2(Date startDate) {

		Date now = Date.from(Instant.now().minusSeconds(10));
		
		if (startDate.before(now)) {
			throw new IllegalArgumentException("La fecha de inicio no puede ser anterior a la actual");
		}

	}

}
