package com.malog.member.application;

import com.malog.member.application.command.RegisterConfirm;
import com.malog.member.application.command.UserRegister;
import com.malog.member.domain.User;
import com.malog.member.domain.UserRegisterProcessor;
import com.malog.member.domain.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@AllArgsConstructor
public class UserFacade {

    private final UserRegisterProcessor registerProcessor;
    private final ApplicationEventPublisher publisher;

    public void register(UserRegister cmd) {
        var account = registerProcessor.register(cmd.email(), cmd.password(), cmd.username());
        assert account != null;
        account.pollAllEvents().forEach(publisher::publishEvent);
    }

}
