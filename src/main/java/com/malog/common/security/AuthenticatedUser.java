package com.malog.common.security;

import com.malog.member.domain.UserRole;
import java.util.Set;

public record AuthenticatedUser(String id, Set<UserRole> roles) {}