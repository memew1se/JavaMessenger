package messenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import messenger.exceptions.NonUniqueNickname;
import messenger.requests.BaseController;
import messenger.requests.ClientRequests;


public class LoginController extends BaseController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField nickField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    public void signInButtonHandler() throws UnirestException {
        String nick = nickField.getText();
        String password = passField.getText();

        if (nick.isEmpty() | password.isEmpty()) {
            errorLabel.setText("Input nickname and password!");
            return;
        }

        try {
            ClientRequests cr = new ClientRequests(nick, password);
            JSONArray user = cr.signIn();

            if (user.isEmpty()) {
                errorLabel.setText("Couldn't find your account");
                return;
            }

        } catch (UnirestException ue) {
            errorLabel.setText("Server is not responding! Please try later or check your internet connection");
            return;
        }
    }

    @FXML
    public void signUpButtonHandler() throws UnirestException, NonUniqueNickname {
        String nick = nickField.getText();
        String password = passField.getText();

        if (nick.isEmpty() | password.isEmpty()) {
            errorLabel.setText("Input nickname and password!");
            return;
        }

        try {
            ClientRequests cr = new ClientRequests(nick, password);
            cr.signUp();

        } catch (UnirestException ue) {
            errorLabel.setText("Server is not responding! Please try later or check your internet connection");
            return;
        } catch (NonUniqueNickname nn) {
            errorLabel.setText(nn.getMessage());
        }
    }

}
