/*
 * SponsorCreditCardRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.creditCard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronCreditCardRepository extends AbstractRepository {

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findOneById(int id);

	@Query("select b from Patron b where b.id = ?1")
	Patron findOnePatronById(int id);

	@Query("select p from Patron p where p.userAccount.id = ?1")
	Patron findOnePatronByUserAccountId(int id);

}
