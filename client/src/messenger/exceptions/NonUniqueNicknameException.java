package messenger.exceptions;

public class NonUniqueNicknameException extends Exception{

    public NonUniqueNicknameException(String errorMessage) {
        super(errorMessage);
    }

}
