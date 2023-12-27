package com.malog.member.infra.jwt;

import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public final class TokenExtractor {
    private static final String HEADER_PREFIX = "Bearer ";

    public Optional<String> extract(String header) {
        if (header == null || header.isBlank()) {
            return Optional.empty();
        } else {
            if (header.length() < HEADER_PREFIX.length()) {
                return Optional.empty();
            } else {
                return Optional.of(header.substring(HEADER_PREFIX.length()));
            }
        }
    }
}
