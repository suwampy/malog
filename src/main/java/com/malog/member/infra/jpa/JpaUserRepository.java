package com.malog.member.infra.jpa;

import com.malog.member.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    User findByUniqueKey(String uniqueKey);
}
