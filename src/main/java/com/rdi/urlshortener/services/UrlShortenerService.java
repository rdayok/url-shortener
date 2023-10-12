package com.rdi.urlshortener.services;

import com.rdi.urlshortener.dto.responses.DeleteUrlResponse;
import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.exception.UrlNotFoundException;

public interface UrlShortenerService {
    GenerateShortUrlResponse generateShortUrl(String url);

    DeleteUrlResponse deleteUrl(Long urlId) throws UrlNotFoundException;
}
