package Persistence.exception;

public class ReadJsonException extends PersistenceJsonException {

    public ReadJsonException(String path, Throwable cause) {
        super("Error, trying to read from Json file: " + path, cause, path);
    }
}
