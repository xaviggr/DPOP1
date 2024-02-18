package Bussines.exception;

/**
 * Excepción que representa un error de validación.
 */
@SuppressWarnings("SpellCheckingInspection")
public class ValidationException extends BusinessException {
    /**
     * Constructor que acepta un mensaje y una causa.
     *
     * @param message Mensaje de la excepción.
     * @param cause   Causa de la excepción.
     */
    public ValidationException(String message, Throwable cause) {
        super("Validation error: " + message, cause);
    }
}
