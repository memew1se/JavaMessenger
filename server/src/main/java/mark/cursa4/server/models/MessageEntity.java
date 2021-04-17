package mark.cursa4.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "Messages")
public class MessageEntity extends BaseEntity{

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatEntity chat;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Timestamp timestamp;

}
