package com.malog.member.infra.jwt;

import com.malog.member.domain.Tokens;
import com.malog.member.domain.UserRole;
import com.malog.member.infra.jpa.RefreshTokenAdapter;
import com.malog.member.infra.jwt.vo.Scopes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public final class TokenGenerator {
    private final RefreshTokenAdapter refreshTokenRepository;
    @Value("${jwt.expiration}")
    private String tokenExpiration;

    @Value("${jwt.refreshTokenExpiration}")
    private String refreshTokenExpiration;

    @Value("${jwt.tokenIssuer}")
    private String tokenIssuer;

    @Value("${jwt.base64TokenKey}")
    private String base64TokenKey;

    public Tokens generate(String email, Set<UserRole> roles) {
        String accessToken = createToken(email, roles, false);
        String refreshToken = createToken(email, roles, true);
        return new Tokens(accessToken, refreshToken);
    }

    private String createToken(String email, Set<UserRole> roles, boolean isAccessToken) {
        var claims = new HashMap<String, Object>();
        claims.put("email", email);
        claims.put("scopes", getScopes(roles, isAccessToken));
        var currentTime = LocalDateTime.now();
        var key = getSigningKey();

        return Jwts.builder()
            .setClaims(claims)
            .setIssuer(tokenIssuer)
            .setId(isAccessToken ? null : generateJti())
            .setIssuedAt(convertToDate(currentTime))
            .setExpiration(convertToDate(currentTime, isAccessToken))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(base64TokenKey.getBytes(
            StandardCharsets.UTF_8));
    }

    private List<String> getScopes(Set<UserRole> roles, boolean isAccessToken) {
        return isAccessToken ? roles.stream()
            .map(r -> "ROLE_" + r.name()).collect(Collectors.toList())
            : List.of(Scopes.REFRESH_TOKEN.authority());
    }

    private String generateJti() {
        var jti = UUID.randomUUID().toString();
        refreshTokenRepository.save(new RefreshToken(jti));
        return jti;
    }

    private Date convertToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date convertToDate(LocalDateTime dateTime, boolean isAccessToken) {
        var expirationSec = isAccessToken ? tokenExpiration
            : refreshTokenExpiration;
        return convertToDate(dateTime.plusMinutes(Long.parseLong(expirationSec)));
    }

}
