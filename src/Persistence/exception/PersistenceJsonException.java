package Persistence.exception;

import java.io.IOException;

public abstract class PersistenceJsonException extends IOException {

    private final String path;
    public PersistenceJsonException(String message, Throwable cause, String path) {
        super(message, cause);
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
