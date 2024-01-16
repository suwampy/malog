package com.malog.notification.domain;

import java.util.Arrays;

public enum NotificationGroupType {
    /**
     * 알림 그룹
     * - 시스템 알림
     * - 어드민 알림
     * - 포스트 알림
     * - 댓글 알림
     * */
    SYSTEM, ADMIN, POST, COMMENT;

    public static NotificationGroupType findByGroup(String groupName) {
        return Arrays.stream(values())
            .filter(g -> g.name().equals(groupName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 알림그룹이 존재하지 않습니다."));
    }

}
