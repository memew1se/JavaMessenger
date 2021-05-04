package messenger.entities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * User model
 */
public class User {

    private LongProperty id;
    private StringProperty nickname;

    /**
     * User constructor
     *
     * @param id the user id
     * @param nickname the user nickname
     */
    public User(long id, String nickname) {
        this.id = new SimpleLongProperty(id);
        this.nickname = new SimpleStringProperty(nickname);
    }

    /**
     * Gets user id
     *
     * @return the user id
     */
    public long getId() {
        return id.get();
    }

    /**
     * Gets user id property
     *
     * @return the user id property
     */
    public LongProperty getIdProperty() {
        return id;
    }

    /**
     * Gets user nickname
     *
     * @return the user nickname
     */
    public String getNickname() {
        return nickname.get();
    }

    /**
     * Gets user nickname property
     *
     * @return the user nickname property
     */
    public StringProperty getNicknameProperty() {
        return nickname;
    }
}
