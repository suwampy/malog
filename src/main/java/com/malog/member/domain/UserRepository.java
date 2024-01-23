package com.malog.member.domain;

public interface UserRepository {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User save(User user);
    User findByUniqueKey(String uniqueKey);
}
