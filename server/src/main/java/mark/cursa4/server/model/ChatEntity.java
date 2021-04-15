package mark.cursa4.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "Chats")
public class ChatEntity {

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Override
    public String toString() {
        return "ChatEntity{" +
                "id=" + id +
                ", user_id=" + user_id +
                '}';
    }
}
