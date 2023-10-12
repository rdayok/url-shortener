package com.rdi.urlshortener.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GenerateShortUrlResponse {
    private Long id;
    private String shortUrl;
    private LocalDateTime expirationDateAndTime;
}
