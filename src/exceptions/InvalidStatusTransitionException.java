package exceptions;
// Thrown when an invalid status change is attempted.
// Example: Company tries to accept before admin pre-accepts.


public class InvalidStatusTransitionException extends RuntimeException {
    public InvalidStatusTransitionException(String message) {
        super(message);
    }
}
