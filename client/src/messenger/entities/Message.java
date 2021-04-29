package messenger.entities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    private LongProperty id;
    private LongProperty fromUserId;
    private StringProperty fromNickname;
    private LongProperty chatId;
    private StringProperty content;

    public Message(long id, long fromUserId, String fromNickname, long chatId, String content) {
        this.id = new SimpleLongProperty(id);
        this.fromUserId = new SimpleLongProperty(fromUserId);
        this.fromNickname = new SimpleStringProperty(fromNickname);
        this.chatId = new SimpleLongProperty(chatId);
        this.content = new SimpleStringProperty(content);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty getIdProperty() {
        return id;
    }

    public long getFromUserId() {
        return fromUserId.get();
    }

    public LongProperty getFromUserIdProperty() {
        return fromUserId;
    }

    public String getFromNickname() {
        return fromNickname.get();
    }

    public StringProperty getFromNicknameProperty() {
        return fromNickname;
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userId=" + fromUserId +
                ", from=" + fromNickname +
                ", chatId=" + chatId +
                ", content=" + content +
                '}';
    }
}
