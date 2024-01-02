package com.malog.member.presentation;

import com.malog.member.application.UserFacade;
import com.malog.member.application.command.RegisterConfirm;
import com.malog.member.application.command.UserLogin;
import com.malog.member.application.command.UserRegister;
import com.malog.member.presentation.request.RegisterConfirmReq;
import com.malog.member.presentation.request.TokenReissueReq;
import com.malog.member.presentation.request.UserLoginReq;
import com.malog.member.presentation.request.UserRegisterReq;
import com.malog.member.presentation.response.TokenRes;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public final class UserApi {

    private final UserFacade accountFacade;

    @PostMapping(value = "/api/v1/auth/register")
    public void register(@RequestBody @Valid UserRegisterReq request) {
        accountFacade.register(
            new UserRegister(request.email(), request.password(), request.username()));
    }

    @PostMapping(value = "/api/v1/auth/confirm")
    public void confirm(@RequestBody @Valid RegisterConfirmReq request) throws Exception {
        accountFacade.confirm(new RegisterConfirm(request.token(), request.email()));
    }

    @PostMapping(value = "/api/v1/auth/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenRes> login(@RequestBody @Valid UserLoginReq request) {
        var tokens = accountFacade.login(new UserLogin(request.email(), request.password()));
        return ResponseEntity.ok(new TokenRes(tokens.accessToken(), tokens.refreshToken()));
    }

    @PostMapping(value = "/api/v1/auth/reissue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenRes> reissue(@RequestBody TokenReissueReq request) {
        var tokens = accountFacade.reissue(request.refreshToken());
        return ResponseEntity.ok(new TokenRes(tokens.accessToken(), tokens.refreshToken()));
    }

}
