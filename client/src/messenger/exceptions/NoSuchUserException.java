package messenger.exceptions;

/**
 * Exception for nonexistent user
 */
public class NoSuchUserException extends Exception {

    /**
     * NoSuchUserException constructor
     *
     * @param errorMessage the message of the exception
     */
    public NoSuchUserException(String errorMessage) {super(errorMessage);}
}
