
package acme.entities.challenge;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline")
})
public class Challenge extends DomainEntity {

	//Serialisation identifier-----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes--------------------------------------------------------------

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				deadline;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	@NotBlank
	private String				rookieLevelGoal;

	@Valid
	@NotNull
	private Money				rookieLevelReward;

	@NotBlank
	private String				averageLevelGoal;

	@Valid
	@NotNull
	private Money				averageLevelReward;

	@NotBlank
	private String				expertLevelGoal;

	@Valid
	@NotNull
	private Money				expertLevelReward;

}
