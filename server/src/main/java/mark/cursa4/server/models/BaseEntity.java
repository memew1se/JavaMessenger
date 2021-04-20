package mark.cursa4.server.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.util.Calendar;

@MappedSuperclass
@Data
@NoArgsConstructor
@ToString
public class BaseEntity {

    @CreationTimestamp
    private Calendar creationTime;

    @UpdateTimestamp
    private Calendar updateTime;
}
