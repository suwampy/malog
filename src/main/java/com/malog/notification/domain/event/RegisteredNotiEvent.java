package com.malog.notification.domain.event;

import com.malog.common.domain.DomainEvent;
import java.time.LocalDateTime;

public record RegisteredNotiEvent(String accountId, String userName, String email, String emailCheckToken, LocalDateTime occurredOn) implements
    DomainEvent {

}
