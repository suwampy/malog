package com.malog.member.domain;

import com.malog.common.domain.DomainEvent;
import java.time.LocalDateTime;

public final class RegisterCompletedEvent implements DomainEvent {

    private final String uKey;
    private final LocalDateTime occurredOn;

    public RegisterCompletedEvent(User user) {
        this.uKey = user.getUKey();
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    public String getuKey() {
        return uKey;
    }
}
