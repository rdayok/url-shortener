package com.rdi.urlshortener.controllers;

import com.rdi.urlshortener.dto.requests.GenerateShortUrlRequest;
import com.rdi.urlshortener.dto.responses.DeleteUrlResponse;
import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.exception.UrlExpiredException;
import com.rdi.urlshortener.exception.UrlNotFoundException;
import com.rdi.urlshortener.services.UrlShortenerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("rdi/urlshortner/")
@AllArgsConstructor
public class RDIController {

    private UrlShortenerService rdiUrlShortenerService;

    @PostMapping
    public ResponseEntity<GenerateShortUrlResponse> generateShortUrl(@RequestBody GenerateShortUrlRequest originalUrl) {
        GenerateShortUrlResponse generateShortUrlResponse = rdiUrlShortenerService.generateShortUrl(originalUrl);
        return new ResponseEntity<>(generateShortUrlResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("{shortUrl}")
    public ResponseEntity<DeleteUrlResponse> deleteUrl(@PathVariable String shortUrl) throws UrlNotFoundException {
        DeleteUrlResponse deleteUrlResponse = rdiUrlShortenerService.deleteUrl(shortUrl);
        return new ResponseEntity<>(deleteUrlResponse, HttpStatus.OK);
    }

    @GetMapping("{shortUrl}")
    public ResponseEntity<?> redirectShortUrl(@PathVariable String shortUrl) throws UrlNotFoundException, UrlExpiredException, URISyntaxException {
        HttpHeaders headers = rdiUrlShortenerService.shortUrlRedirect(shortUrl);
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}
