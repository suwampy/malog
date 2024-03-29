package com.malog.notification.domain;

public interface NotificationSender<T extends Notification> {
    Class<T> appliesTo();

    void send(T notification);
}
