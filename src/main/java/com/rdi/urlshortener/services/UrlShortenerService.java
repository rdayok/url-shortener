package com.rdi.urlshortener.services;

import com.rdi.urlshortener.dto.requests.GenerateShortUrlRequest;
import com.rdi.urlshortener.dto.responses.DeleteUrlResponse;
import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.exception.UrlExpiredException;
import com.rdi.urlshortener.exception.UrlNotFoundException;
import org.springframework.http.HttpHeaders;

import java.net.URISyntaxException;

public interface UrlShortenerService {
    GenerateShortUrlResponse generateShortUrl(GenerateShortUrlRequest url);

    DeleteUrlResponse deleteUrl(String shortUrl) throws UrlNotFoundException;

    HttpHeaders shortUrlRedirect(String shortUrl) throws UrlNotFoundException, UrlExpiredException, URISyntaxException;

    void updateUrlExpiryDateJustForTesting(String url) throws UrlNotFoundException;
}
