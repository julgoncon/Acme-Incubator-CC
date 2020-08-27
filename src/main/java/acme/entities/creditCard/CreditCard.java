
package acme.entities.creditCard;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				holderName;

	@NotBlank
	@Pattern(regexp = "^VISA|MASTERCARD|DINNERS|AMEX$", message = "{creditCard.error.brandName}")
	private String				brandName;

	@NotBlank
	@CreditCardNumber
	private String				number;

	@NotNull
	@Range(min = 1, max = 12)
	private Integer				expirationMonth;

	@NotNull
	@Range(min = 00, max = 99)
	private Integer				expirationYear;

	@NotNull
	@Range(min = 000, max = 999)
	private Integer				cvv;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
