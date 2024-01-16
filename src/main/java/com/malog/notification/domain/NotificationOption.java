package com.malog.notification.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationOption {
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private boolean disabled;

    public NotificationOption(NotificationType type) {
        this.type = type;
    }

    public boolean isEqualTo(NotificationType type) {
        return this.type.equals(type);
    }

    // 알림 끄기
    public void disable() {
        this.disabled = true;
    }


    // 알림 켜기
    public void enable() {
        this.disabled = false;
    }
}
