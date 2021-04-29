package messenger.entities;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Chat {

    private LongProperty id;
    private StringProperty name;
    private ListProperty<Long> usersId;

    private ListProperty<Message> messages;

    public Chat(long id, String name, List<Long> users) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);

        ObservableList<Long> userList = FXCollections.observableArrayList(users);
        this.usersId = new SimpleListProperty<Long>(userList);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty getIdProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public List<Long> getUsersId() {
        return usersId.get();
    }

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
