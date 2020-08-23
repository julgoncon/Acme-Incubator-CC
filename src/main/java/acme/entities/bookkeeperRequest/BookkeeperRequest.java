
package acme.entities.bookkeeperRequest;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookkeeperRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				firmName;

	@NotBlank
	private String				responsibilityStatement;

	private Boolean				status;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private UserAccount			userAccount;
}
