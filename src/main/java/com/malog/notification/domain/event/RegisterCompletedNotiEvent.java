package com.malog.notification.domain.event;

import com.malog.common.domain.DomainEvent;
import java.time.LocalDateTime;

public record RegisterCompletedNotiEvent(String accountId, LocalDateTime occurredOn) implements DomainEvent {

}
