package mark.cursa4.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "Participants")
public class ParticipantEntity {

    @Id @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int chat_id;

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Override
    public String toString() {
        return "ParticipantEntity{" +
                "chat_id=" + chat_id +
                ", user_id=" + user_id +
                '}';
    }
}
