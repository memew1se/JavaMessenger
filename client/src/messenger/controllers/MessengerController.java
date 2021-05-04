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


/**
 * Controller for the main app
 */
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

    @FXML
    private Button deleteChatButtonHandler;

    private ClientRequests messengerRequests;
    private ObservableList<Chat> allChats;
    private ObservableList<Message> allMessages;
    private long currentChat;
    private Thread chatsThread;
    private Thread messagesThread;

    /**
     * Configuration of controller
     *
     * @param application the App
     */
    public void configure(App application) {

        // Setting application and user session
        setApplication(application);
        messengerRequests = application.getClientRequests();

        // Setting chat loading on chatTableView click
        chatTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Chat>() {
            @Override
            public void changed(ObservableValue<? extends Chat> observable, Chat oldValue, Chat newValue) {
                if(chatTableView.getSelectionModel().getSelectedItem() != null) {

                    // Getting messages
                    setCurrentChat(newValue.getId());
                    startMessagesThread();
                }
            }
        });

        // Creating ArrayLists for chats and messages
        allMessages = FXCollections.observableArrayList();
        allChats = FXCollections.observableArrayList();

        // Setting view for chatTableView
        chatTableColumn.setCellValueFactory(cell -> cell.getValue().getNameProperty());

        // Setting view for userTableView
        userTableColumn.setCellValueFactory(cell -> cell.getValue().getFromNicknameProperty());
        messageTableColumn.setCellValueFactory(cell -> cell.getValue().getContentProperty());

        // Getting chats
        chatsThread = new ChatsThread(this);
        chatsThread.start();

    }

    /**
     * Chats loader
     */
    public void showChats() {
        ObservableList<Chat> chats = messengerRequests.getChats();
        allChats.clear();
        allChats = chats;
        chatTableView.setItems(allChats);
    }

    /**
     * Messages loader
     */
    public void showMessages() {
        ObservableList<Message> messages = messengerRequests.getMessages(currentChat);
        allMessages.clear();
        allMessages = messages;
        messageTableView.setItems(allMessages);
    }

    /**
     * Starting new thread to load messages
     */
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

    /**
     * Handler fo sendMessageButton
     */
    @FXML
    public void sendMessageButtonHandler() {
        String message = contentTextField.getText();

        // Empty user input alert
        if (message.isEmpty() | getCurrentChat() == 0) {
            contentTextField.setText("");
            return;
        }

        application.getClientRequests().sendMessage(getCurrentChat(), message);
        contentTextField.setText("");
    }

    /**
     * Handler for createChatButton
     */
    @FXML
    public void createChatButtonHandler() {
        application.newChat();
    }

    @FXML
    public void deleteChatButtonHandler() {
        return;
    }


    /**
     * Sets current chat
     *
     * @param currentChat the chat id
     */
    public void setCurrentChat(long currentChat) {
        this.currentChat = currentChat;
    }

    /**
     * Gets current chat
     *
     * @return the chat id
     */
    public long getCurrentChat() {
        return currentChat;
    }

}