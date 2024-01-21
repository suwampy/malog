package com.malog.blog.domain.event;

import com.malog.common.domain.DomainEvent;
import java.time.LocalDateTime;

public record BlogInitializedEvent(String uKey, LocalDateTime occurredOn) implements DomainEvent {
}
