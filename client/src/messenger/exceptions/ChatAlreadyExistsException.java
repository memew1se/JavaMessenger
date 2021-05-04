package messenger.exceptions;

/**
 * Exception for existing chat
 */
public class ChatAlreadyExistsException extends Exception{

    /**
     * ChatAlreadyExistsException constructor
     *
     * @param errorMessage the message of exception
     */
    public ChatAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
