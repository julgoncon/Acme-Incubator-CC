
package acme.entities.technologyRecord;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TechnologyRecord extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	private String				activitySector;

	@NotBlank
	private String				inventorName;

	@NotBlank
	private String				description;

	@URL
	@NotBlank
	private String				website;

	@NotBlank
	@Email
	private String				email;

	@NotNull
	private Boolean				openSource;

	@Min(-5)
	@Max(5)
	private Double				stars;

}
