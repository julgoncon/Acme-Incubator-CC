/*
 * AdministratorCustomisationParameterRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.customisationParameter;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomisationParameterRepository extends AbstractRepository {

	@Query("select c from CustomisationParameter c")
	Collection<CustomisationParameter> findManyAll();

	@Query("select c from CustomisationParameter c where c.id= ?1")
	CustomisationParameter findById(int id);

	@Query("select c.activitySector from CustomisationParameter c")
	String findActivitySector();

}
