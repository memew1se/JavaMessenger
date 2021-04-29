package messenger.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import messenger.App;
import messenger.entities.Chat;
import messenger.entities.Message;
import messenger.requests.ClientRequests;

import java.util.ArrayList;
import java.util.List;

public class MessengerController {

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

    private App application;
    private ClientRequests messengerRequests;
    private ObservableList<Chat> allChats;

    public void settings(App application) {
        this.application = application;
        messengerRequests = application.getClientRequests();

        allChats = FXCollections.observableArrayList();
        allChats = messengerRequests.getChats();

        chatTableView.setItems(allChats);

        chatTableColumn.setCellValueFactory(cell -> cell.getValue().getNameProperty());

    }

}
