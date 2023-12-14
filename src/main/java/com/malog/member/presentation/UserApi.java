package com.malog.member.presentation;

import com.malog.member.application.UserFacade;
import com.malog.member.application.command.RegisterConfirm;
import com.malog.member.application.command.UserRegister;
import com.malog.member.presentation.request.RegisterConfirmReq;
import com.malog.member.presentation.request.UserRegisterReq;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public final class UserApi {
    private final UserFacade accountFacade;
    @PostMapping(value = "/api/v1/auth/register",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody @Valid UserRegisterReq request) {
        accountFacade.register(new UserRegister(request.email(),
            request.password(),
            request.username()));
    }

    @PostMapping(value = "/api/v1/auth/confirm",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public void confirm(@RequestBody @Valid RegisterConfirmReq request) throws Exception {
        accountFacade.confirm(new RegisterConfirm(request.token(), request.email()));
    }
}
