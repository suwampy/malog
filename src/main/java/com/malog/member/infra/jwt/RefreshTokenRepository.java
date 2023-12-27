package com.malog.member.infra.jwt;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);
    boolean existsByRefreshTokenJti(String jti);
    RefreshToken findByRefreshTokenJti(String jti);
}
