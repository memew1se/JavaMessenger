package mark.cursa4.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "Users")
public class UserEntity extends BaseEntity{

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "secret_key")
    private String secret_key;

}
