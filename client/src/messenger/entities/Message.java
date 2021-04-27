package messenger.entities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    private LongProperty id;
    private LongProperty user_id;
    private StringProperty from;
    private LongProperty chat_id;
    private StringProperty content;

    public Message(long id, long user_id, String from, long chat_id, String content) {
        this.id = new SimpleLongProperty(id);
        this.user_id = new SimpleLongProperty(user_id);
        this.from = new SimpleStringProperty(from);
        this.chat_id = new SimpleLongProperty(chat_id);
        this.content = new SimpleStringProperty(content);
    }

}
