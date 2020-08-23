
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forum.Forum;
import acme.entities.forumUser.ForumUser;
import acme.entities.message.Message;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("select a.forum from ForumUser a where a.authenticated.id = ?1")
	Collection<Forum> findManyInvolved(int id);

	@Query("select a from Forum a where a.id = ?1")
	Forum findOneById(int id);

	@Query("select a from Message a where a.forum.id = ?1")
	Collection<Message> findManyMessageByForumId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select a.authenticated from ForumUser a where a.forum.id = ?1")
	Collection<Authenticated> findUsersByForumId(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int authenticatedById);

	@Query("select a.authenticated.id from ForumUser a where a.forum.id = ?1 and a.creator = true")
	int findIdCreatorUser(int id);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findMessagesByForum(int id);

	@Query("select a from ForumUser a where a.forum.id = ?1")
	Collection<ForumUser> findForumUserByForum(int id);

}
