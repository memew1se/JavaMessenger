package mark.cursa4.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity of chat
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "Chats")
public class ChatEntity extends BaseEntity implements Serializable {

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection
    @Column(name = "users", nullable = false)
    private List<Long> users;

}
