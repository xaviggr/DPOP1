package Persistence.exception;

/**
 * La clase WriteJsonException extiende PersistenceJsonException y representa una excepción específica
 * para errores al intentar escribir en un archivo JSON. Proporciona información adicional sobre la
 * ubicación del archivo (ruta) y el motivo del error.
 */
@SuppressWarnings("SpellCheckingInspection")
public class WriteJsonException extends PersistenceJsonException {
    /**
     * Constructor de la excepción WriteJsonException.
     *
     * @param path  Ruta del archivo donde se produjo la excepción al intentar escribir.
     * @param cause Causa original de la excepción.
     */
    public WriteJsonException(String path, Throwable cause) {
        super("Error, trying to write on Json file: " + path, cause, path);
    }
}
