package com.malog.member.domain;

import java.util.Arrays;

public enum UserRole {
    USER,
    ADMIN;

    public static UserRole findByRole(String role) {
        return Arrays.stream(UserRole.values())
            .filter(r -> r.name().equals(role))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
