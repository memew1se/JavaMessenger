package messenger.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import kong.unirest.UnirestException;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import messenger.exceptions.NonUniqueNicknameException;
import messenger.requests.ClientRequests;
import messenger.utils.IdConverter;
import messenger.utils.PasswordHasher;


/**
 * Controller for application login
 */
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
    private Button aboutAuthorButton;

    /**
     * Handler for signInButton
     *
     * @throws UnirestException if there are problems
     *                          with connection
     */
    @FXML
    public void signInButtonHandler() throws UnirestException {

        // Getting user input info for session
        String nick = nickTextField.getText();
        String password = passTextField.getText();

        // Empty user input alert
        if (nick.isEmpty() | password.isEmpty()) {
            errorLabel.setText("Input nickname and password!");
            return;
        }

        // Hashing password
        password = PasswordHasher.hashPassword(password);

        try {

            // Making GET request and getting session user if it exists
            ClientRequests loginRequests = new ClientRequests(nick, password);
            JSONObject jsonUser = loginRequests.signIn();

            // Setting user info for session
            long user_id = IdConverter.convertHrefIdToLong(jsonUser);
            loginRequests.setId(user_id);
            this.application.setClientRequests(loginRequests);

            // Starting the main app
            this.application.Messenger();

        } catch (UnirestException ue) {

            // Connection problems alert
            errorLabel.setText("Server is not responding! Please try later or check your internet connection");
            return;

        } catch (JSONException je) {

            // Wrong username or password alert
            errorLabel.setText("Couldn't find your account");
            return;
        }
    }

    /**
     * Handler for signUpButton
     *
     * @throws UnirestException
     * @throws NonUniqueNicknameException
     */
    @FXML
    public void signUpButtonHandler() throws UnirestException, NonUniqueNicknameException {

        // Getting user input info for session
        String nick = nickTextField.getText();
        String password = passTextField.getText();

        // Empty user input alert
        if (nick.isEmpty() | password.isEmpty()) {
            errorLabel.setText("Input nickname and password!");
            return;
        }

        // Hashing password
        password = PasswordHasher.hashPassword(password);

        try {

            // Making POST request to create new user
            ClientRequests loginRequests = new ClientRequests(nick, password);
            JSONObject jsonUser = loginRequests.signUp();

            // Saving user info for session
            long user_id = IdConverter.convertHrefIdToLong(jsonUser);
            loginRequests.setId(user_id);
            this.application.setClientRequests(loginRequests);

            // Starting main app
            this.application.Messenger();

        } catch (UnirestException ue) {

            // Connection problems alert
            errorLabel.setText("Server is not responding! Please try later or check your internet connection");
            return;

        } catch (NonUniqueNicknameException nn) {

            // Nickname is already taken alert
            errorLabel.setText(nn.getMessage());
            return;
        }
    }

    public void aboutAuthorButtonHandler() {
        return;
    }

}
