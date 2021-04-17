package mark.cursa4.server.repositories;

import mark.cursa4.server.models.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
}
