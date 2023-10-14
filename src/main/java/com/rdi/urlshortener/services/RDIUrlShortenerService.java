package com.rdi.urlshortener.services;

import com.google.common.hash.Hashing;
import com.rdi.urlshortener.data.models.Url;
import com.rdi.urlshortener.data.repositories.UrlShortenerRepository;
import com.rdi.urlshortener.dto.requests.GenerateShortUrlRequest;
import com.rdi.urlshortener.dto.responses.DeleteUrlResponse;
import com.rdi.urlshortener.dto.responses.GenerateShortUrlResponse;
import com.rdi.urlshortener.exception.UrlExpiredException;
import com.rdi.urlshortener.exception.UrlNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static com.rdi.urlshortener.utils.Mapper.map;


@Service
@RequiredArgsConstructor
public class RDIUrlShortenerService implements UrlShortenerService{

    private final UrlShortenerRepository urlShortenerRepository;

    @Override
    public GenerateShortUrlResponse generateShortUrl(GenerateShortUrlRequest generateShortUrlRequest) {
        String originalUrl = generateShortUrlRequest.getOriginalUrl();
        String shortUrl = hashOriginalUrl(originalUrl);
        Url url = map(shortUrl, originalUrl);
        Url savedUrl = urlShortenerRepository.save(url);
        return map(savedUrl);
    }

    private static String hashOriginalUrl(String originalUrl) {
        return Hashing.murmur3_32_fixed()
                .hashString(originalUrl.concat(String.valueOf(LocalDateTime.now())), StandardCharsets.UTF_8)
                .toString();
    }

    @Override
    public DeleteUrlResponse deleteUrl(String shortUrl) throws UrlNotFoundException {
        Url foundUrl = getUrl(shortUrl);
        urlShortenerRepository.delete(foundUrl);
        return new DeleteUrlResponse("URL DELETED SUCCESSFULLY");
    }

    @Override
    public HttpHeaders shortUrlRedirect(String shortUrl) throws UrlExpiredException, UrlNotFoundException, URISyntaxException {
        Url foundUrl = getUrl(shortUrl);
        checkIfUrlIsExpired(foundUrl);
        HttpHeaders headers = new HttpHeaders();
        URI uri = new URI(foundUrl.getOriginalUrl());
        headers.setLocation(uri);
        return headers;
    }

    private void checkIfUrlIsExpired(Url foundUrl) throws UrlExpiredException {
        if(foundUrl.getExpirationDateAndTime().isBefore(LocalDateTime.now())){
            urlShortenerRepository.delete(foundUrl);
            throw new UrlExpiredException("THIS SHORT URL HAS EXPIRED.");
        }
    }

    @Override
    public void updateUrlExpiryDateJustForTesting(String shortUrl) throws UrlNotFoundException {
        Url found = getUrl(shortUrl);
        if (found.getOriginalUrl().equals("http://www.rdi.io")) {
            found.setExpirationDateAndTime(LocalDateTime.now().minusDays(2));
            urlShortenerRepository.save(found);
        }
    }

    private Url getUrl(String shortUrl) throws UrlNotFoundException {
        return urlShortenerRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("URL NOT FOUND"));
    }


}
