package com.malog.member.infra.jpa;

import com.malog.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByUniqueKey(String uniqueKey);
}
