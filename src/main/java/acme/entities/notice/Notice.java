
package acme.entities.notice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notice extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@URL
	@NotBlank
	private String				picture;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creation;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				deadline;

	@NotBlank
	@Column(length = 1024)
	private String				body;

	private String				links;

}
