package com.malog.member.infra.jpa;

import com.malog.member.domain.User;
import com.malog.member.domain.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public final class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public void save(User user) {
        jpaUserRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }
}
