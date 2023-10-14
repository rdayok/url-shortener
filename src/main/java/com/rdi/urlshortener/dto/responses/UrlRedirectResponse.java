package com.rdi.urlshortener.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class UrlRedirectResponse {
    private String originalUrl;
}
