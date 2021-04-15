package mark.cursa4.server.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

import java.sql.Timestamp;

@Entity
@Table(name = "Messages")
public class MessageEntity {

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "chat_id")
    private int chat_id;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", chat_id=" + chat_id +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
