package messenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import kong.unirest.UnirestException;

import kong.unirest.json.JSONObject;
import messenger.exceptions.NonUniqueNicknameException;
import messenger.requests.ClientRequests;
import messenger.utils.IdConverter;
import messenger.utils.PasswordHasher;


public class LoginController extends BaseController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField nickTextField;

    @FXML
    private PasswordField passTextField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    public void signInButtonHandler() throws UnirestException {
        String nick = nickTextField.getText();
        String password = passTextField.getText();

        if (nick.isEmpty() | password.isEmpty()) {
            errorLabel.setText("Input nickname and password!");
            return;
        }

        password = PasswordHasher.hashPassword(password);

        try {
            ClientRequests loginRequests = new ClientRequests(nick, password);
            JSONObject jsonUser = loginRequests.signIn();

            if (jsonUser.isEmpty()) {
                errorLabel.setText("Couldn't find your account");
                return;
            }

            long user_id = IdConverter.convertHrefIdToLong(jsonUser);
            loginRequests.setId(user_id);
            this.application.setClientRequests(loginRequests);

            this.application.Messenger();

        } catch (UnirestException ue) {
            errorLabel.setText("Server is not responding! Please try later or check your internet connection");
            return;
        }
    }

    @FXML
    public void signUpButtonHandler() throws UnirestException, NonUniqueNicknameException {
        String nick = nickTextField.getText();
        String password = passTextField.getText();

        if (nick.isEmpty() | password.isEmpty()) {
            errorLabel.setText("Input nickname and password!");
            return;
        }

        password = PasswordHasher.hashPassword(password);

        try {
            ClientRequests loginRequests = new ClientRequests(nick, password);
            JSONObject jsonUser = loginRequests.signUp();

            long user_id = IdConverter.convertHrefIdToLong(jsonUser);
            loginRequests.setId(user_id);
            this.application.setClientRequests(loginRequests);

            this.application.Messenger();

        } catch (UnirestException ue) {
            errorLabel.setText("Server is not responding! Please try later or check your internet connection");
            return;
        } catch (NonUniqueNicknameException nn) {
            errorLabel.setText(nn.getMessage());
        }
    }

}
