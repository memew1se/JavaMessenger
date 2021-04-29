package messenger.entities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    private LongProperty id;
    private LongProperty userId;
    private StringProperty from;
    private LongProperty chatId;
    private StringProperty content;

    public Message(long id, long userId, String from, long chatId, String content) {
        this.id = new SimpleLongProperty(id);
        this.userId = new SimpleLongProperty(userId);
        this.from = new SimpleStringProperty(from);
        this.chatId = new SimpleLongProperty(chatId);
        this.content = new SimpleStringProperty(content);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty getIdProperty() {
        return id;
    }

    public long getUserId() {
        return userId.get();
    }

    public String getFrom() {
        return from.get();
    }

    public StringProperty getFromProperty() {
        return from;
    }

    public long getChatId() {
        return chatId.get();
    }

    public LongProperty getChatIdProperty() {
        return chatId;
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty getContentProperty() {
        return content;
    }
}
