package mark.cursa4.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Entity of message
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "Messages")
public class MessageEntity extends BaseEntity{

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity fromId;

    @JoinColumn(name = "from")
    private String fromName;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatEntity chat;

    @Column(name = "content", nullable = false)
    private String content;

}
