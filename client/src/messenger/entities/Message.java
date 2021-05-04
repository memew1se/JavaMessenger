package messenger.entities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model for message
 */
public class Message {

    private LongProperty id;
    private LongProperty fromUserId;
    private StringProperty fromNickname;
    private LongProperty chatId;
    private StringProperty content;

    /**
     * Message constructor
     *
     * @param id the message id
     * @param fromUserId the user id
     * @param fromNickname the user nickname
     * @param chatId the chat id
     * @param content the text of message
     */
    public Message(long id, long fromUserId, String fromNickname, long chatId, String content) {
        this.id = new SimpleLongProperty(id);
        this.fromUserId = new SimpleLongProperty(fromUserId);
        this.fromNickname = new SimpleStringProperty(fromNickname);
        this.chatId = new SimpleLongProperty(chatId);
        this.content = new SimpleStringProperty(content);
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
     * Gets user id
     *
     * @return the user id
     */
    public long getFromUserId() {
        return fromUserId.get();
    }

    /**
     * Gets user id property
     *
     * @return the user id property
     */
    public LongProperty getFromUserIdProperty() {
        return fromUserId;
    }

    /**
     * Gets user nickname
     *
     * @return the user nickname
     */
    public String getFromNickname() {
        return fromNickname.get();
    }

    /**
     * Gets user nickname property
     *
     * @return the user nickname property
     */
    public StringProperty getFromNicknameProperty() {
        return fromNickname;
    }

    /**
     * Gets chat id
     *
     * @return the chat id
     */
    public long getChatId() {
        return chatId.get();
    }

    /**
     * Gets the chat id property
     *
     * @return the chat id property
     */
    public LongProperty getChatIdProperty() {
        return chatId;
    }

    /**
     * Gets text of message
     *
     * @return the text of message
     */
    public String getContent() {
        return content.get();
    }

    /**
     * Gets text of message property
     *
     * @return the text of message property
     */
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
