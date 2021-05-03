package mark.cursa4.server.repositories;

import mark.cursa4.server.models.ChatEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * JPA repository interface for work with chats
 */
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    /**
     * Find chat by id
     *
     * @param id the chat id
     * @return the list of one chat with given id if it exists
     */
    @RestResource(path = "id", rel = "id")
    List<ChatEntity> findByIdIs(long id);

    /**
     * Find chat by id and user in it
     *
     * @param id the chat id
     * @param user the user id
     * @return the list of one chat with given chat and user id if it exists
     */
    @RestResource(path = "idanduser", rel = "idanduser")
    List<ChatEntity> findByIdAndUsers(long id, long user);

    /**
     * Find chats by user in it
     *
     * @param id the user id
     * @return the list of chats with given user id if it exists
     */
    @RestResource(path = "userin", rel = "userin")
    List<ChatEntity> findByUsers(long id);
}
