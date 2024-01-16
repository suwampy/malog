package com.malog.notification.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Arrays;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationGroupType type;

    private boolean disabled;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<NotificationOption> options;

    public NotificationOption getOptionByType(NotificationType notificationType) {
        return options.stream()
            .filter(o -> o.isEqualTo(notificationType))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public NotificationGroup(NotificationGroupType type,
        Set<NotificationOption> options) {
        this.type = type;
        this.options = options;
    }

    public boolean isEqualTo(NotificationGroupType type) {
        return this.type.equals(type);
    }

    // 알림 기능 켜기, 끄기
    public void enable() {
        this.disabled = true;
    }

    public void disable() {
        this.disabled = true;
    }

    // 옵션별 알림 켜기, 끄기
    public void enableOption(NotificationType notificationType) {
        var option = getOptionByType(notificationType);
        option.enable();
    }

    public void disableOption(NotificationType notificationType) {
        var option = getOptionByType(notificationType);
        option.disable();
    }
}
