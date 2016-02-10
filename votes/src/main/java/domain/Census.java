package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Census extends DomainEntity {

	public Census() {
		super();
	}

	private String name;

	public String toString() {
		return name;
	}
}
