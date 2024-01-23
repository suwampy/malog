package com.malog.member.infra.jwt;

import com.malog.member.domain.Tokens;
import com.malog.member.domain.UserRepository;
import com.malog.member.infra.exception.RefreshTokenNotFoundException;
import com.malog.member.infra.jwt.vo.Scopes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public final class TokenReIssuer {
    private final TokenGenerator tokenGenerator;
    private final TokenExtractor tokenExtractor;
    private final TokenParser tokenParser;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public Tokens reIssuance(String payload) {
        var token = tokenParser.parse(payload);

        token.verify(Scopes.REFRESH_TOKEN.authority());

        if (!refreshTokenRepository.existsByRefreshTokenJti(token.getJti())) {
            throw new RefreshTokenNotFoundException();
        }

        var account = userRepository.findByEmail(token.getSubject());
        var refreshTokenJti = refreshTokenRepository.findByRefreshTokenJti(token.getJti());
        refreshTokenJti.expire();
        return tokenGenerator.generate(account.getEmail(), account.getRoles());
    }
}
