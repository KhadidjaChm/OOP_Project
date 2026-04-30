package exceptions;
// Thrown when a user tries to perform an action they are not allowed to do
public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String message) {

        super(message);
    }
}
