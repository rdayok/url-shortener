package com.rdi.urlshortener.utils;

import com.rdi.urlshortener.data.models.Url;
import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.dto.responses.UrlRedirectResponse;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class Mapper {
    private static ModelMapper mapper = new ModelMapper();

    public static Url map(String shortUrl, String originalUrl) {
        Url url = new Url();
        url.setShortUrl(shortUrl);
        url.setOriginalUrl(originalUrl);
        url.setDateAndTimeCreated(LocalDateTime.now());
        url.setExpirationDateAndTime(url.getDateAndTimeCreated().plusDays(365));
        return url;
    }

    public static GenerateShortUrlResponse map(Url savedUrl) {
        return mapper.map(savedUrl, GenerateShortUrlResponse.class);
    }

}
