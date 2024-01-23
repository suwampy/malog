package com.malog.member.infra.jwt;

import com.malog.common.error.InvalidTokenException;
import com.malog.member.infra.exception.AlreadyTokenExpiredException;
import com.malog.member.infra.jwt.vo.RawToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class TokenParser {
    @Value("${jwt.base64TokenKey}")
    private String base64TokenKey;

    public RawToken parse(String token) {
        try {
            Jws<Claims> jwt = parseToken(token);
            return createRawToken(jwt.getBody());
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException ex) {
            log.error("Invalid JWT Token", ex);
            throw new InvalidTokenException();
        } catch (ExpiredJwtException expiredEx) {
            log.info("JWT Token is expired", expiredEx);
            throw new AlreadyTokenExpiredException();
        }
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getKey(base64TokenKey))
            .build()
            .parseClaimsJws(token);
    }

    private RawToken createRawToken(Claims claims) {
        String email = claims.get("email", String.class);
        String jti = claims.get("jti", String.class);
        List<String> scopes = claims.get("scopes", List.class);
        return (jti == null) ? new RawToken(email, scopes) : new RawToken(email, jti, scopes);
    }

    private Key getKey(String signKey) {
        return Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));
    }
}
