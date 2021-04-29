package messenger.entities;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Chat {

    private LongProperty id;
    private StringProperty name;
    private ListProperty<Long> users_id;

    private ListProperty<Message> messages;

    public Chat(long id, String name, List<Long> users) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);

        ObservableList<Long> userList = FXCollections.observableArrayList(users);
        this.users_id = new SimpleListProperty<Long>(userList);
    }

    public long getId() {
        return id.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name=" + name +
                ", users_id=" + users_id +
                '}';
    }
}
