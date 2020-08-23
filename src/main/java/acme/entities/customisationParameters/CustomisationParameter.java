
package acme.entities.customisationParameters;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomisationParameter extends DomainEntity {

	//Serialisation identifier-----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes--------------------------------------------------------------

	@NotBlank
	private String				spamWords;

	@NotBlank
	private String				activitySector;

	@NotNull
	@Range(min = 0, max = 100)
	private Double				spamThreshold;

}
