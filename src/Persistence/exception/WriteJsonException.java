package Persistence.exception;

public class WriteJsonException extends PersistenceJsonException {

    public WriteJsonException(String path, Throwable cause) {
        super("Error, trying to write on Json file: " + path, cause, path);
    }
}
