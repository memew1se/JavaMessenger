package mark.cursa4.server.repositories;

import mark.cursa4.server.models.ChatEntity;
import mark.cursa4.server.models.MessageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @RestResource(path = "id", rel = "id")
    List<MessageEntity> findByIdIs(long id);

    @RestResource(path = "chatid", rel = "chatid")
    List<MessageEntity> findByChat(ChatEntity id);
}
