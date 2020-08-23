
package acme.features.authenticated.forumUser;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forum.Forum;
import acme.entities.forumUser.ForumUser;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumUserRepository extends AbstractRepository {

	//	@Query("select t.users from Thread t where t.id = ?1")
	//	Collection<UserAccount> findUsersByThread(int threadId);
	/*
	 * @Query("select tu from ThreadUser tu where tu.id = ?1")
	 * ThreadUser findOneUserById(int id);
	 *
	 * @Query("select tu from ThreadUser tu where tu.thread.id = ?1")
	 * Collection<ThreadUser> findUsersByThread(int threadId);
	 *
	 * @Query("select t from Thread t where t.id = ?1")
	 * Thread findThreadById(int threadById);
	 *
	 * @Query("select a from Authenticated a where a.userAccount.username = ?1")
	 * Authenticated findUserByUsername(String username);
	 *
	 * @Query("select a from Authenticated a where a.id = ?1")
	 * Authenticated findAuthenticatedById(int authenticatedById);
	 */

	@Query("select a.authenticated.id from ForumUser a where a.forum.id = ?1 and a.creator = true")
	int findIdCreatorUser(int id);

	@Query("select a from Authenticated a where a.userAccount.username = ?1")
	Authenticated findUserByUsername(String username);

	@Query("select f from Forum f where f.id = ?1")
	Forum findForumById(int id);

	@Query("select f from ForumUser f where f.id = ?1")
	ForumUser findForumUserById(int id);

	@Query("select fu from ForumUser fu where fu.forum.id = ?1")
	Collection<ForumUser> findUsersByForum(int id);

	@Query("select u from Authenticated u")
	Collection<Authenticated> findAllUsers();

	@Query("select fu.forum.id from ForumUser fu where fu.id = ?1")
	int findIdForumByForumUser(int id);
}
