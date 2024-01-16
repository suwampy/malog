package com.malog.notification.domain;

import static com.malog.notification.domain.NotificationType.EMAIL;
import static com.malog.notification.domain.NotificationType.PUSH;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * NotificationSetting
 * - 알림 설정 관리
 * */
@Entity
@Table(name = "notification_setting")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Receiver receiver;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_setting_id")
    private Set<NotificationGroup> groups;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    private NotificationSetting(Receiver receiver, Set<NotificationGroup> groups) {
        this.receiver = receiver;
        this.groups = groups;
    }

    // 알림 그룹 비활성화
    public void disableGroup(NotificationGroupType groupType) {
        var group = groups.stream()
            .filter(g -> g.isEqualTo(groupType))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
        group.disable();
    }

    // 알림 그룹 활성화
    public void enableGroup(NotificationGroupType groupType) {
        var group = groups.stream()
            .filter(g -> g.isEqualTo(groupType))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
        group.enable();
    }

    public static NotificationSetting defaultSetting(String accountId) {
        return new NotificationSetting(new Receiver(accountId), Set.of(
            new NotificationGroup(NotificationGroupType.SYSTEM, Set.of(
                new NotificationOption(EMAIL),
                new NotificationOption(PUSH)
            )),
            new NotificationGroup(NotificationGroupType.ADMIN, Set.of(
                new NotificationOption(EMAIL),
                new NotificationOption(PUSH)
            )),
            new NotificationGroup(NotificationGroupType.POST, Set.of(
                new NotificationOption(EMAIL),
                new NotificationOption(PUSH)
            )),
            new NotificationGroup(NotificationGroupType.COMMENT, Set.of(
                new NotificationOption(EMAIL),
                new NotificationOption(PUSH)
            ))
        ));
    }
}
