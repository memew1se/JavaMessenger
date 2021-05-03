package mark.cursa4.server.repositories;

import mark.cursa4.server.models.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @RestResource(path = "id", rel = "id")
    List<UserEntity> findByIdIs(long id);

    @RestResource(path = "nickpass", rel = "nickpass")
    List<UserEntity> findByNicknameAndPassword(String nickname, String password);

    @RestResource(path = "nickname", rel = "nickname")
    List<UserEntity> findByNickname(String nickname);
}
