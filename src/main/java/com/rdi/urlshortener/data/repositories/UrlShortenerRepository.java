package com.rdi.urlshortener.data.repositories;

import com.rdi.urlshortener.data.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerRepository extends JpaRepository<Url, Long> {
}
