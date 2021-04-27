package messenger.entities;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Chat {

    private LongProperty id;
    private StringProperty name;
    private ListProperty<User> users;

    private ListProperty<Message> messages;

    public Chat(long id, String name, List<User> users) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);

        ObservableList<User> userList = FXCollections.observableArrayList(users);
        this.users = new SimpleListProperty<User>(userList);
    }

}
