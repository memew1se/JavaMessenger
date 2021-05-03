package messenger.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import messenger.App;
import messenger.entities.Chat;
import messenger.entities.Message;
import messenger.requests.ClientRequests;
import messenger.threads.ChatsThread;
import messenger.threads.MessagesThread;


public class MessengerController extends BaseController {

    @FXML
    private TableView<Chat> chatTableView;

    @FXML
    private TableColumn<Chat, String> chatTableColumn;

    @FXML
    private TableView<Message> messageTableView;

    @FXML
    private TableColumn<Message, String> userTableColumn;

    @FXML
    private TableColumn<Message, String> messageTableColumn;

    @FXML
    private Button newChatButton;

    @FXML
    private TextField contentTextField;

    @FXML
    private Button sendMessageButton;

    private ClientRequests messengerRequests;
    private ObservableList<Chat> allChats;
    private ObservableList<Message> allMessages;
    private long currentChat;
    private Thread chatsThread;
    private Thread messagesThread;

    public void configure(App application) {
        setApplication(application);
        messengerRequests = application.getClientRequests();

        chatTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Chat>() {
            @Override
            public void changed(ObservableValue<? extends Chat> observable, Chat oldValue, Chat newValue) {
                if(chatTableView.getSelectionModel().getSelectedItem() != null) {
                    setCurrentChat(newValue.getId());
                    startMessagesThread();
                }
            }
        });

        allMessages = FXCollections.observableArrayList();

        allChats = FXCollections.observableArrayList();

        messageTableView.setItems(allMessages);

        chatTableColumn.setCellValueFactory(cell -> cell.getValue().getNameProperty());

        userTableColumn.setCellValueFactory(cell -> cell.getValue().getFromNicknameProperty());
        messageTableColumn.setCellValueFactory(cell -> cell.getValue().getContentProperty());

        chatsThread = new ChatsThread(this);
        chatsThread.start();

    }

    public void showChats() {
        ObservableList<Chat> chats = messengerRequests.getChats();
        allChats.clear();
        allChats = chats;
        chatTableView.setItems(allChats);
    }

    public void showMessages() {
        ObservableList<Message> messages = messengerRequests.getMessages(currentChat);
        allMessages.clear();
        allMessages = messages;
        messageTableView.setItems(allMessages);
    }

    public void startMessagesThread() {
        try {
            messagesThread.interrupt();
            messagesThread = new MessagesThread(this);
            messagesThread.start();
        } catch (NullPointerException pointerException) {
            messagesThread = new MessagesThread(this);
            messagesThread.start();
        }
    }

    @FXML
    public void sendMessageButtonHandler() {
        String message = contentTextField.getText();

        if (message.isEmpty() | getCurrentChat() == 0) {
            contentTextField.setText("");
            return;
        }

        application.getClientRequests().sendMessage(getCurrentChat(), message);
        contentTextField.setText("");
    }

    @FXML
    public void createChatButtonHandler() {
        application.newChat();
    }

    public void setCurrentChat(long currentChat) {
        this.currentChat = currentChat;
    }

    public long getCurrentChat() {
        return currentChat;
    }

}
