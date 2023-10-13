package com.rdi.urlshortener.services;

import com.rdi.urlshortener.dto.responses.DeleteUrlResponse;
import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.exception.UrlExpiredException;
import com.rdi.urlshortener.exception.UrlNotFoundException;

public interface UrlShortenerService {
    GenerateShortUrlResponse generateShortUrl(String url);

    DeleteUrlResponse deleteUrl(String shortUrl) throws UrlNotFoundException;

    String shortUrlRedirect(String shortUrl) throws UrlNotFoundException, UrlExpiredException;

    void updateUrlExpiryDateJustForTesting(String url) throws UrlNotFoundException;
}
