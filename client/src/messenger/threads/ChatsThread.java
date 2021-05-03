package messenger.threads;

import messenger.App;
import messenger.controllers.MessengerController;
import messenger.requests.ClientRequests;

public class ChatsThread extends Thread{

    private MessengerController messengerController;
    private App application;
    private ClientRequests clientRequests;

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
