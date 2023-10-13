package com.rdi.urlshortener.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@Table(name = "URLS")
public class Url {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime dateAndTimeCreated;
    private LocalDateTime expirationDateAndTime;
}
