package mark.cursa4.server.repositories;

import mark.cursa4.server.models.ChatEntity;
import mark.cursa4.server.models.MessageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * JPA repository interface for work with messages
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    /**
     * Find message by id
     *
     * @param id the message id
     * @return the list of one message with given id if it exists
     */
    @RestResource(path = "id", rel = "id")
    List<MessageEntity> findByIdIs(long id);

    /**
     * Find messages by chat
     *
     * @param id the chat id
     * @return the list of messages with given chat id if it exists
     */
    @RestResource(path = "chatid", rel = "chatid")
    List<MessageEntity> findByChat(ChatEntity id);
}
