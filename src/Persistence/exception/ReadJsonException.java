package Persistence.exception;

/**
 * La clase ReadJsonException extiende PersistenceJsonException y representa una excepción específica
 * para errores al intentar leer desde un archivo JSON. Proporciona información adicional sobre la
 * ubicación del archivo (ruta) y el motivo del error.
 */
@SuppressWarnings("SpellCheckingInspection")
public class ReadJsonException extends PersistenceJsonException {

    /**
     * Constructor de la excepción ReadJsonException.
     *
     * @param path  Ruta del archivo donde se produjo la excepción al intentar leer.
     * @param cause Causa original de la excepción.
     */
    public ReadJsonException(String path, Throwable cause) {
        super("Error, trying to read from Json file: " + path, cause, path);
    }
}
