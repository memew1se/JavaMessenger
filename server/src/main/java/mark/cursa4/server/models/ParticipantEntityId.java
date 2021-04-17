package mark.cursa4.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class ParticipantEntityId implements Serializable {

    private int chat_id;
    private int user_id;

    public ParticipantEntityId(int chat_id, int user_id) {
        this.chat_id = chat_id;
        this.user_id = user_id;
    }
}
