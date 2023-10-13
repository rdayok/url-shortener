package com.rdi.urlshortener.exception;

public class UrlExpiredException extends RDIUrlException{
    public UrlExpiredException(String message) {
        super(message);
    }
}
