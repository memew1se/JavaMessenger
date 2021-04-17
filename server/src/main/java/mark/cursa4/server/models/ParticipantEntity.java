package mark.cursa4.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "Participants")
public class ParticipantEntity  {

    @EmbeddedId
    private ParticipantEntityId id;

    @MapsId("chat_id")
    @ManyToOne(targetEntity = ChatEntity.class)
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    private ChatEntity chat;

    @MapsId("user_id")
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

}
