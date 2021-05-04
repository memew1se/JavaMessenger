package messenger.exceptions;

/**
 * Exception for already existing nickname
 */
public class NonUniqueNicknameException extends Exception{

    /**
     * NonUniqueNicknameException constructor
     *
     * @param errorMessage the message of exception
     */
    public NonUniqueNicknameException(String errorMessage) {
        super(errorMessage);
    }

}
