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


public class LoginController {

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
    public void signInButtonHandler() {
        String nick = nickField.getText();
        String password = passField.getText();

        if (nick.isEmpty() | password.isEmpty()) {
            errorLabel.setText("Input nickname and password!");
            return;
        }

        try {
            HttpResponse<JsonNode> response = Unirest.get("http://127.0.0.1:8080/userEntities/search/nickpass")
                    .queryString("nickname", nick)
                    .queryString("password", password)
                    .asJson();

            JSONArray user = response.getBody().getObject().getJSONObject("_embedded").getJSONArray("userEntities");

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
    public void signUpButtonHandler() {
        String nick = nickField.getText();
        String password = passField.getText();

        if (nick.isEmpty() | password.isEmpty()) {
            errorLabel.setText("Input nickname and password!");
            return;
        }

        try {
            JSONObject body = new JSONObject();

            body.put("nickname", nick);
            body.put("password", password);

            HttpResponse<JsonNode> response = Unirest.post("http://127.0.0.1:8080/userEntities")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(body)
                    .asJson();

        } catch (UnirestException ue) {
            errorLabel.setText("Server is not responding! Please try later or check your internet connection");
            return;
        }
    }

}
