package messenger.exceptions;

public class ChatAlreadyExistsException extends Exception{

    public ChatAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
