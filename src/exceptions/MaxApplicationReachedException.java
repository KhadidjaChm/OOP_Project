package exceptions;
//Thrown when a student exceeds the maximum allowed applications (5).
public class MaxApplicationReachedException extends RuntimeException {
    public MaxApplicationReachedException(String message) {

        super(message);
    }
}
