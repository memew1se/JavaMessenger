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
public class ParticipantEntity extends BaseEntity implements Serializable{


    @Id
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatEntity chat;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
