package messenger.threads;

import messenger.App;
import messenger.controllers.MessengerController;
import messenger.requests.ClientRequests;

public class MessagesThread extends Thread{

    private ClientRequests clientRequests;
    private App application;
    private MessengerController messengerController;

    public MessagesThread(MessengerController messengerController) {
        this.messengerController = messengerController;
        this.application = messengerController.getApplication();
        this.clientRequests = messengerController.getApplication().getClientRequests();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                messengerController.showMessages();
                Thread.sleep(250);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
