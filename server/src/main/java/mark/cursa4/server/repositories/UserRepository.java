package mark.cursa4.server.repositories;

import mark.cursa4.server.models.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * JPA repository interface for work with users
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find user by id
     * 
     * @param id the user id
     * @return the list of one user with given id if it exists
     */
    @RestResource(path = "id", rel = "id")
    List<UserEntity> findByIdIs(long id);

    /**
     * Find user by nickname and password
     * 
     * @param nickname the nickname of user
     * @param password the password of user
     * @return the list of one user with given nickname and password if it exists
     */
    @RestResource(path = "nickpass", rel = "nickpass")
    List<UserEntity> findByNicknameAndPassword(String nickname, String password);

    /**
     * Find user by nickname
     * 
     * @param nickname the nickname of user
     * @return the list of one user with given nickname if it exists
     */
    @RestResource(path = "nickname", rel = "nickname")
    List<UserEntity> findByNickname(String nickname);
}
