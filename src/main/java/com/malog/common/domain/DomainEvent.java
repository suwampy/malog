package com.malog.common.domain;

import java.time.LocalDateTime;
import java.util.Date;

public interface DomainEvent {
    LocalDateTime occurredOn();
}
