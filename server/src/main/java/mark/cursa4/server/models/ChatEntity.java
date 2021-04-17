package mark.cursa4.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "Chats")
public class ChatEntity extends BaseEntity{

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
