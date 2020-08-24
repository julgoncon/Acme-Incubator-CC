
package acme.entities.application;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "status")
})
public class Application extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	private String				ticker;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creation;

	@NotBlank
	@Pattern(regexp = "^(pending)?(accepted)?(rejected)?$")
	private String				status;

	@NotBlank
	@Column(length = 1024)
	private String				statement;

	@Valid
	@NotNull
	private Money				investmentOffer;

	private String				justification;

	private String				offer;

	@URL
	private String				moreInfo;

	@Pattern(regexp = "^((?=[^ ]{10,})(?=(.*\\p{L}.*){1,})(?=(.*\\p{N}.*){1,})((.*\\p{P}.*){1,}))?$", message = "{application.error.password}")
	private String				password;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				changeStatus;
	// Relationships-----------------------------------------------------
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Investor			investor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private InvestmentRound		investmentRound;

}
