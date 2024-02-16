package Bussines.exception;

public class PersistenceIntegrationException extends BusinessException {
    public PersistenceIntegrationException(String message, Throwable cause) {
        super("Error integrating with persistence layer: " + message, cause);
    }
}
