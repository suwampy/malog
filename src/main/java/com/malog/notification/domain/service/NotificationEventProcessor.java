package com.malog.notification.domain.service;

import com.malog.common.domain.DomainEvent;
import com.malog.notification.domain.Notification;

public interface NotificationEventProcessor<T extends DomainEvent> {

    Class<T> appliesTo();

    Notification process(T event);
}

