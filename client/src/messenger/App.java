package messenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import messenger.controllers.LoginController;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.show();
        UserLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void UserLogin() throws IOException {
        primaryStage.setTitle("JavaMessenger");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("fxml/login.fxml"));
        Parent loginForm = loader.load();
        primaryStage.setScene(new Scene(loginForm));

        LoginController lc = loader.getController();
        lc.setApplication(this);
    }
}
