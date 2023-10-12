package com.rdi.urlshortener.exception;

public class UrlNotFoundException extends RDIUrlException {
    public UrlNotFoundException(String message) {
        super(message);
    }
}
