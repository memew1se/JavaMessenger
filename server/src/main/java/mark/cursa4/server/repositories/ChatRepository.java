package mark.cursa4.server.repositories;

import mark.cursa4.server.models.ChatEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    @RestResource(path = "id", rel = "id")
    List<ChatEntity> findByIdIs(long id);

    @RestResource(path = "idanduser", rel = "idanduser")
    List<ChatEntity> findByIdAndUsers(long id, long user);

    @RestResource(path = "userin", rel = "userin")
    List<ChatEntity> findByUsers(long id);
}
