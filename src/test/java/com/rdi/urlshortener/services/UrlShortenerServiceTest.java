package com.rdi.urlshortener.services;

import com.rdi.urlshortener.dto.responses.DeleteUrlResponse;
import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.exception.UrlNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class UrlShortenerServiceTest {

    @Autowired
    private UrlShortenerService urlShortenerService;
    private GenerateShortUrlResponse generateUrlResponse;

    @BeforeEach
    public void setUp() {
        String url = "http://www.rdi.africa";
        generateUrlResponse = urlShortenerService.generateShortUrl(url);
    }

    @Test
    public void testGenerationShortUrl() {
        String url = "http://www.rdi.africa";
        GenerateShortUrlResponse generateUrlResponse = urlShortenerService.generateShortUrl(url);
        assertNotNull(generateUrlResponse);
        assertThat(generateUrlResponse.getId()).isNotNull();
    }

    @Test
    public void testDeleteUrl() throws UrlNotFoundException {
        DeleteUrlResponse deleteUrlResponse = urlShortenerService.deleteUrl(generateUrlResponse.getId());
        assertNotNull(deleteUrlResponse);
        assertSame("URL DELETED SUCCESSFULLY", deleteUrlResponse.getMessage());
    }

    @Test
    public void testShortUrlRedirect() {
        String originalUrl = urlShortenerService.shortUrlRedirect(generateUrlResponse.getShortUrl());
    }
}
