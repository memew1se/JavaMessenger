package messenger.entities;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Model for chat
 */
public class Chat {

    private LongProperty id;
    private StringProperty name;
    private ListProperty<Long> usersId;

    private ListProperty<Message> messages;

    /**
     * Chat constructor
     *
     * @param id the chat id
     * @param name the chat name
     * @param users the list of users in chat
     */
    public Chat(long id, String name, List<Long> users) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);

        ObservableList<Long> userList = FXCollections.observableArrayList(users);
        this.usersId = new SimpleListProperty<Long>(userList);
    }

    /**
     * Gets chat id
     *
     * @return the chat id
     */
    public long getId() {
        return id.get();
    }

    /**
     * Gets chat id property
     *
     * @return the chat id property
     */
    public LongProperty getIdProperty() {
        return id;
    }

    /**
     * Gets chat name
     *
     * @return the chat name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Gets the chat name property
     *
     * @return the chat name property
     */
    public StringProperty getNameProperty() {
        return name;
    }

    /**
     * Gets list of users in chat
     *
     * @return the list of users in chat
     */
    public List<Long> getUsersId() {
        return usersId.get();
    }

    /**
     * Get list of users in chat (property)
     *
     * @return the list of users in chat (property)
     */
    public ListProperty<Long> getUsersIdProperty() {
        return usersId;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name=" + name +
                ", users_id=" + usersId +
                '}';
    }
}
