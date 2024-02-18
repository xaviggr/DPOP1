package Bussines.exception;

/**
 * Excepción que representa un error de integración con la capa de persistencia.
 */
public class PersistenceIntegrationException extends BusinessException {
    /**
     * Constructor que acepta un mensaje y una causa.
     *
     * @param message Mensaje de la excepción.
     * @param cause   Causa de la excepción.
     */
    public PersistenceIntegrationException(String message, Throwable cause) {
        super("Error integrating with persistence layer: " + message, cause);
    }
}
