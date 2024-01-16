package com.malog.notification.domain;

import java.util.Arrays;

public enum NotificationType {
    EMAIL, PUSH;

    public static NotificationType findByType(String typeName) {
        return Arrays.stream(values())
            .filter(t -> t.name().equals(typeName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 알림 type이 존재하지 않습니다."));
    }
}
