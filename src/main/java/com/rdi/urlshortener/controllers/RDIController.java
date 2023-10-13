package com.rdi.urlshortener.controllers;

import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.services.RDIUrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rdi/urlshortner/")
@RequiredArgsConstructor
public class RDIController {

    private RDIUrlShortenerService rdiUrlShortenerService;

    @PostMapping("{originalUrl}")
    public ResponseEntity<GenerateShortUrlResponse> generateShortUrl(@PathVariable String originalUrl) {

    }
}
