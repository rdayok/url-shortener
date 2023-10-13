package com.rdi.urlshortener.services;

import com.rdi.urlshortener.dto.responses.DeleteUrlResponse;
import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.exception.UrlExpiredException;
import com.rdi.urlshortener.exception.UrlNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UrlShortenerServiceTest {

    @Autowired
    private UrlShortenerService urlShortenerService;
    private GenerateShortUrlResponse generateUrlResponse;
    private final String url = "http://www.rdi.io";

    @BeforeEach
    public void setUp() {
        generateUrlResponse = urlShortenerService.generateShortUrl(url);
    }

    @Test
    public void testGenerationShortUrl() {
        assertNotNull(generateUrlResponse);
        assertThat(generateUrlResponse.getId()).isNotNull();
    }

    @Test
    public void testDeleteUrl() throws UrlNotFoundException {
        DeleteUrlResponse deleteUrlResponse =
                urlShortenerService.deleteUrl(generateUrlResponse.getShortUrl());
        assertNotNull(deleteUrlResponse);
        assertSame("URL DELETED SUCCESSFULLY", deleteUrlResponse.getMessage());
    }

    @Test
    public void testShortUrlRedirect() throws UrlNotFoundException, UrlExpiredException {
        String originalUrl = urlShortenerService.shortUrlRedirect(generateUrlResponse.getShortUrl());
        assertThat(originalUrl).isNotNull();
        assertEquals(originalUrl, url);
    }

    @Test
    public void testRedirectingExpiredUrl_afterWhichUrlIsThenDeleted() throws UrlNotFoundException {
        urlShortenerService.updateUrlExpiryDateJustForTesting(generateUrlResponse.getShortUrl());
        assertThrows(UrlExpiredException.class,
                () -> urlShortenerService.shortUrlRedirect(generateUrlResponse.getShortUrl()));
        assertThrows(UrlNotFoundException.class,
                () -> urlShortenerService.shortUrlRedirect(generateUrlResponse.getShortUrl()));
    }

    @Test
    public void testRedirectingNoneExistingShortUrl() throws UrlNotFoundException {
        String shortUrl = "ihhfoeo4";
        System.out.println(generateUrlResponse.getId());
        assertThrows(UrlNotFoundException.class,
                () -> urlShortenerService.shortUrlRedirect(shortUrl));
        try {
            urlShortenerService.shortUrlRedirect(shortUrl);
        } catch (UrlNotFoundException | UrlExpiredException exception) {
            assertSame(exception.getMessage(), "URL NOT FOUND");
        }
    }
}
