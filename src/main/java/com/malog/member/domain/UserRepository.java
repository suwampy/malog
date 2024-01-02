package com.malog.member.domain;

public interface UserRepository {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    void save(User user);
}
