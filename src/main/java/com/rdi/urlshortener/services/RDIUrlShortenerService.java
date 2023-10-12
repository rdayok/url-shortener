package com.rdi.urlshortener.services;

import com.google.common.hash.Hashing;
import com.rdi.urlshortener.data.models.Url;
import com.rdi.urlshortener.data.repositories.UrlShortenerRepository;
import com.rdi.urlshortener.dto.responses.DeleteUrlResponse;
import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.exception.UrlNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static com.rdi.urlshortener.utils.Mapper.map;

@Service
@RequiredArgsConstructor
public class RDIUrlShortenerService implements UrlShortenerService{

    private final UrlShortenerRepository urlShortenerRepository;

    @Override
    public GenerateShortUrlResponse generateShortUrl(String originalUrl) {
        String shortUrl = hashOriginalUrl(originalUrl );
        Url url = map(shortUrl, originalUrl);
        Url savedUrl = urlShortenerRepository.save(url);
        return map(savedUrl);
    }

    @Override
    public DeleteUrlResponse deleteUrl(Long urlId) throws UrlNotFoundException {
        Url foundUrl = urlShortenerRepository.findById(urlId)
                .orElseThrow(() -> new UrlNotFoundException("URL NOT FOUND"));
        urlShortenerRepository.delete(foundUrl);

        return new DeleteUrlResponse("URL DELETED SUCCESSFULLY");
    }

    private static String hashOriginalUrl(String originalUrl) {
        return Hashing.murmur3_32_fixed()
                .hashString(originalUrl.concat(String.valueOf(LocalDateTime.now())), StandardCharsets.UTF_8)
                .toString();
    }
}
