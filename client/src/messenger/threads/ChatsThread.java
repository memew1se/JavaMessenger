package messenger.threads;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import messenger.App;
import messenger.controllers.MessengerController;
import messenger.entities.Chat;
import messenger.requests.ClientRequests;

public class ChatsThread extends Thread{

    private ClientRequests clientRequests;
    private App application;
    private MessengerController messengerController;

    public ChatsThread(MessengerController messengerController) {
        this.messengerController = messengerController;
        this.application = messengerController.getApplication();
        this.clientRequests = messengerController.getApplication().getClientRequests();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                messengerController.showChats();
                Thread.sleep(2500);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
