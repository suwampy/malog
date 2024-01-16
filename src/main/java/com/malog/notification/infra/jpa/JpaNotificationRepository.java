package com.malog.notification.infra.jpa;

import com.malog.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationRepository extends JpaRepository<Notification, Long> {

}
