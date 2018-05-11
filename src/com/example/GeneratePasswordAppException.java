package com.example;

public class GeneratePasswordAppException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public GeneratePasswordAppException() {
    }

    public GeneratePasswordAppException(String message) {
        super(message);
    }

    public GeneratePasswordAppException(Throwable cause) {
        super(cause);
    }

    public GeneratePasswordAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneratePasswordAppException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
