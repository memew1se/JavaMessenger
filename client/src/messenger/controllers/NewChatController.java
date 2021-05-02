package messenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import messenger.exceptions.ChatAlreadyExistsException;
import messenger.exceptions.NoSuchUserException;
import messenger.requests.ClientRequests;

public class NewChatController extends BaseController {

    @FXML
    private TextField nicknameTextField;

    @FXML
    private TextField chatNameTextField;

    @FXML
    private Button createChatButton;

    @FXML
    private Label errorLabel;

    private Stage stage;

    public void createChatButtonHandler() {
        String name = nicknameTextField.getText();
        String chatName = chatNameTextField.getText();
        ClientRequests newChatRequests = application.getClientRequests();

        try {
            newChatRequests.createChat(name, chatName);
            stage.close();
        } catch (NoSuchUserException ue) {
            errorLabel.setText(ue.getMessage());
        } catch (ChatAlreadyExistsException ce) {
            errorLabel.setText(ce.getMessage());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
