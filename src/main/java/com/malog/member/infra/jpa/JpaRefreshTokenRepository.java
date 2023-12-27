package com.malog.member.infra.jpa;

import com.malog.member.infra.jwt.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByRefreshTokenJti(String jti);
    RefreshToken findByRefreshTokenJti(String jti);

}
