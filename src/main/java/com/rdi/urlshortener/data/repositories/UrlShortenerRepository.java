package com.rdi.urlshortener.data.repositories;

import com.rdi.urlshortener.data.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlShortenerRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);
}
