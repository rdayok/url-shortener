package com.rdi.urlshortener.exception;

public class RDIUrlException extends Throwable {
    public RDIUrlException(String message) {
        super(message);
    }

    public RDIUrlException(Throwable throwable) {
        super(throwable);
    }
}
