package com.malog.member.domain;

import com.malog.common.domain.DomainEvent;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;

@Getter
public final class RegisteredAccountEvent implements DomainEvent {
    private final String uKey;
    private final String name;
    private final String emailCheckToken;
    private final String email;
    private final LocalDateTime occurredOn;

    public RegisteredAccountEvent(User user) {
        this.uKey = user.getUKey();
        this.name = user.getName();
        this.email = user.getEmail();
        this.emailCheckToken = user.getEmailCheckToken();
        this.occurredOn = LocalDateTime.now();
    }
    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
