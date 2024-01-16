package com.malog.member.application;

import com.malog.member.application.command.RegisterConfirm;
import com.malog.member.application.command.UserLogin;
import com.malog.member.application.command.UserRegister;
import com.malog.member.domain.service.UserLoginProcessor;
import com.malog.member.domain.service.UserRegisterProcessor;
import com.malog.member.infra.jwt.TokenGenerator;
import com.malog.member.infra.jwt.TokenReIssuer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserRegisterProcessor registerProcessor;
    private final UserLoginProcessor userLoginProcessor;
    private final TokenGenerator tokenGenerator;
    private final TokenReIssuer tokenReIssuer;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public void register(UserRegister cmd) {
        var account = registerProcessor.register(cmd.email(), cmd.password(), cmd.username());
        assert account != null;
        account.pollAllEvents().forEach(publisher::publishEvent);
    }

    @Transactional
    public void confirm(RegisterConfirm cmd) throws Exception {
        var account = registerProcessor.registerConfirm(cmd.token(), cmd.email());
        assert account != null;
        account.pollAllEvents().forEach(publisher::publishEvent);
    }

    @Transactional
    public Token login(UserLogin cmd) {
        var user = userLoginProcessor.login(cmd.email(), cmd.password());
        var tokens = tokenGenerator.generate(user.getEmail(), user.getRoles());
        return new Token(tokens.accessToken(), tokens.refreshToken());
    }

    @Transactional
    public Token reissue(String token) {
        var tokens = tokenReIssuer.reIssuance(token);
        return new Token(tokens.accessToken(), tokens.refreshToken());
    }

    public record Token(String accessToken, String refreshToken) {

    }
}
