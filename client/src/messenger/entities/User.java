package messenger.entities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private LongProperty id;
    private StringProperty nickname;

    public User(long id, String nickname) {
        this.id = new SimpleLongProperty(id);
        this.nickname = new SimpleStringProperty(nickname);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty getIdProperty() {
        return id;
    }

    public String getNickname() {
        return nickname.get();
    }

    public StringProperty getNicknameProperty() {
        return nickname;
    }
}
