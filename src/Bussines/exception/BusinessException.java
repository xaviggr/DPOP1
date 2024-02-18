package Bussines.exception;

/**
 * Clase abstracta que representa una excepción de la capa bussiness.
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class BusinessException extends Exception {
    /**
     * Constructor que acepta un mensaje y una causa.
     *
     * @param message Mensaje de la excepción.
     * @param cause   Causa de la excepción.
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
