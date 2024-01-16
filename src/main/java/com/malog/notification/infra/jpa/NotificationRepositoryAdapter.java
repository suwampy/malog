package com.malog.notification.infra.jpa;

import com.malog.notification.domain.Notification;
import com.malog.notification.domain.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepository {

    private final JpaNotificationRepository notificationRepository;
    @Override
    public Notification save(Notification entity) {
        return notificationRepository.save(entity);
    }
}
