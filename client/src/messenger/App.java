package messenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import messenger.controllers.LoginController;
import messenger.controllers.MessengerController;
import messenger.controllers.NewChatController;
import messenger.requests.ClientRequests;

import java.io.IOException;

/**
 *  JavaFX messenger application
 */
public class App extends Application {

    private Stage primaryStage;
    private ClientRequests clientRequests;

    /**
     * The entry point of program
     *
     * @param primaryStage the primary stage for the application
     * @throws Exception - if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.show();
        UserLogin();
    }

    /**
     * Start of the program
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Login page
     */
    public void UserLogin() {
        try {
            primaryStage.setTitle("Auth");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("fxml/login.fxml"));
            Parent loginForm = loader.load();
            primaryStage.setScene(new Scene(loginForm));

            LoginController lc = loader.getController();
            lc.setApplication(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Messenger page
     */
    public void Messenger() {
        try {
            primaryStage.setTitle("JavaMessenger");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("fxml/messenger.fxml"));
            Parent messengerForm = loader.load();
            primaryStage.setScene(new Scene(messengerForm));

            MessengerController mc = loader.getController();
            mc.configure(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * New chat page
     */
    public void newChat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("fxml/newChat.fxml"));
            Parent newChatForm = loader.load();

            Stage newChatStage = new Stage();
            newChatStage.setTitle("New chat");
            newChatStage.initModality(Modality.WINDOW_MODAL);
            newChatStage.initOwner(primaryStage);
            newChatStage.setScene(new Scene(newChatForm));

            NewChatController cc = loader.getController();
            cc.setApplication(this);
            cc.setStage(newChatStage);

            newChatStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets clientRequests
     *
     * @param clientRequests the clientRequests
     */
    public void setClientRequests(ClientRequests clientRequests) {
        this.clientRequests = clientRequests;
    }

    /**
     * Gets clientRequests
     *
     * @return the clientRequests
     */
    public ClientRequests getClientRequests() {
        return clientRequests;
    }
}
