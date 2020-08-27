
package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.entities.forum.Forum;
import acme.entities.forumUser.ForumUser;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.investmentRound.entrepreneur.id = ?1")
	Collection<Application> findManyApplicationByEntrepreneurId(int id);

	@Query("select a from Application a where a.id = ?1")
	Application findOneById(int id);

	@Query("select a from Forum a where a.investmentRound.id = ?1")
	Forum findOneForumByInvestmentRoundId(int id);

	@Query("select a from Forum a where a.id = ?1")
	Forum findOneForumById(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthById(int id);

	@Query("select a from ForumUser a where a.forum.id = ?1")
	Collection<ForumUser> findOneForumUserByForumId(int id);

}
