package messenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import messenger.requests.ClientRequests;

public class NewChatController extends BaseController {

    @FXML
    private Label nicknameLabel;

    @FXML
    private TextField nicknameTextField;

    @FXML
    private Button createChatButton;

    @FXML
    private Label errorLabel;

    private Stage stage;

    public void createChatButtonHandler() {
        String name = nicknameTextField.getText();
        ClientRequests newChatRequests = application.getClientRequests();


    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
