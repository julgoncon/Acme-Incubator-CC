
package acme.entities.banner;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Banner extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Atributos

	@NotBlank
	@URL
	private String				picture;

	@NotBlank
	private String				slogan;

	@NotBlank
	@URL
	private String				targetURL;

	@OneToOne(optional = true)
	@Valid
	private CreditCard			creditCard;

	@Valid
	@ManyToOne(optional = false)
	private Patron				patron;

}
