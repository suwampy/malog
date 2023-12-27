package com.malog.member.infra.jpa;

import com.malog.member.infra.jwt.RefreshToken;
import com.malog.member.infra.jwt.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public final class RefreshTokenAdapter implements RefreshTokenRepository {

    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return jpaRefreshTokenRepository.save(refreshToken);
    }

    @Override
    public boolean existsByRefreshTokenJti(String jti) {
        return jpaRefreshTokenRepository.existsByRefreshTokenJti(jti);
    }

    @Override
    public RefreshToken findByRefreshTokenJti(String jti) {
        return jpaRefreshTokenRepository.findByRefreshTokenJti(jti);
    }
}
