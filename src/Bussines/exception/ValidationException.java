package Bussines.exception;

public class ValidationException extends BusinessException {
    public ValidationException(String message, Throwable cause) {
        super("Validation error: " + message, cause);
    }
}
