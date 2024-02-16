package Bussines.exception;

import java.io.IOException;

public abstract class BusinessException extends Exception {
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
