package com.malog.notification.domain;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Inheritance
@Table(name = "notification")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Receiver receiver;

    private String message;

    private boolean isRead;

    @Enumerated(EnumType.STRING)
    private NotificationGroupType notificationGroupType;

    @CreationTimestamp
    private String createdAt;

    public Notification(Receiver receiver, String message, NotificationGroupType notificationGroupType) {
        this.receiver = receiver;
        this.message = message;
        this.notificationGroupType = notificationGroupType;
        this.isRead = false;
    }
}
