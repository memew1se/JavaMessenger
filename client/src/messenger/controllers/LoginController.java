package messenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;

import messenger.exceptions.NonUniqueNickname;
import messenger.requests.ClientRequests;
import messenger.utils.IdConverter;
import messenger.utils.PasswordAuth;


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

        password = PasswordAuth.hashPassword(password);

        try {
            ClientRequests cr = new ClientRequests(nick, password);
            JSONArray jsonUser = cr.signIn();

            if (jsonUser.isEmpty()) {
                errorLabel.setText("Couldn't find your account");
                return;
            }

            long user_id = IdConverter.convert(jsonUser);
            cr.setId(user_id);
            this.application.setCr(cr);

            this.application.Chat();

        } catch (UnirestException ue) {
            errorLabel.setText("Server is not responding! Please try later or check your internet connection");
            return;
        }
    }

    @FXML
    public void signUpButtonHandler() throws UnirestException, NonUniqueNickname {
        String nick = nickTextField.getText();
        String password = passTextField.getText();

        if (nick.isEmpty() | password.isEmpty()) {
            errorLabel.setText("Input nickname and password!");
            return;
        }

        password = PasswordAuth.hashPassword(password);

        try {
            ClientRequests cr = new ClientRequests(nick, password);
            JSONArray jsonUser = cr.signUp();

            long user_id = IdConverter.convert(jsonUser);
            cr.setId(user_id);
            this.application.setCr(cr);

            this.application.Chat();

        } catch (UnirestException ue) {
            errorLabel.setText("Server is not responding! Please try later or check your internet connection");
            return;
        } catch (NonUniqueNickname nn) {
            errorLabel.setText(nn.getMessage());
        }
    }

}
