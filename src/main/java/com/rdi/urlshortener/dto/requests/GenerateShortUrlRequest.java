package com.rdi.urlshortener.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GenerateShortUrlRequest {
    private String originalUrl;
}
