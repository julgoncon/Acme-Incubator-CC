
package acme.entities.investmentRound;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvestmentRound extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	private String				ticker;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creation;

	@NotBlank
	@Pattern(regexp = "^(SEED)?(ANGEL)?(SERIES-A)?(SERIES-B)?(SERIES-C)?(BRIDGE)?$", message = "{investmentRound.error.kindRound}")
	private String				kindRound;

	@NotBlank
	private String				title;

	@NotNull
	private Boolean				finalMode;

	@NotBlank
	private String				description;

	@Valid
	@NotNull
	private Money				amountMoney;

	@URL
	private String				link;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur		entrepreneur;

}
