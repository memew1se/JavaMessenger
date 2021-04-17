package mark.cursa4.server.repositories;

import mark.cursa4.server.models.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
