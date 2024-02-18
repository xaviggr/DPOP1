package Persistence.exception;

import java.io.IOException;

/**
 * La clase abstracta PersistenceJsonException extiende IOException y representa una excepción específica
 * para problemas de persistencia relacionados con operaciones JSON. Contiene información adicional sobre la
 * ubicación del archivo (ruta) donde se produjo la excepción.
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class PersistenceJsonException extends IOException {

    private final String path;
    /**
     * Constructor de la excepción PersistenceJsonException.
     *
     * @param message Mensaje descriptivo de la excepción.
     * @param cause   Causa original de la excepción.
     * @param path    Ruta del archivo donde se produjo la excepción.
     */
    public PersistenceJsonException(String message, Throwable cause, String path) {
        super(message, cause);
        this.path = path;
    }
    /**
     * Obtiene la ruta del archivo donde se produjo la excepción.
     *
     * @return La ruta del archivo.
     */
    public String getPath() {
        return this.path;
    }
}
