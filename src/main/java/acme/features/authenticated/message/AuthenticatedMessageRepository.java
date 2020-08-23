
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.forum.Forum;
import acme.entities.message.Message;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select a from Message a where a.forum.id=?1 ")
	Collection<Message> findManyByForumId(int id);

	@Query("select a from Message a where a.id = ?1")
	Message findOneById(int id);

	@Query("select a from Forum a where a.id = ?1")
	Forum findForumById(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select a.authenticated from ForumUser a where a.forum.id = ?1")
	Collection<Authenticated> findUsersByForumId(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int authenticatedById);

	@Query("select cp from CustomisationParameter cp")
	CustomisationParameter findCustomisationParameters();

}
