package messenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import messenger.exceptions.ChatAlreadyExistsException;
import messenger.exceptions.NoSuchUserException;
import messenger.requests.ClientRequests;

/**
 * Controller for creating new chat
 */
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

    /**
     * Handler for createChatButton
     */
    public void createChatButtonHandler() {

        // Getting info for new chat
        String name = nicknameTextField.getText();
        String chatName = chatNameTextField.getText();

        // Getting client session info to make request
        ClientRequests newChatRequests = application.getClientRequests();

        try {

            // Making POST request to server. Shutting down window on success
            newChatRequests.createChat(name, chatName);
            stage.close();

        } catch (NoSuchUserException ue) {

            // If there is no user client want create chat with alert
            errorLabel.setText(ue.getMessage());

        } catch (ChatAlreadyExistsException ce) {

            // If chat already exists alert
            errorLabel.setText(ce.getMessage());
        }
    }

    /**
     * Sets stage
     *
     * @param stage the stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
