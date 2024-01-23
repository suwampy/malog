package com.malog.member.infra.jpa;

import com.malog.common.error.ErrorCode;
import com.malog.common.error.ResourceNotFoundException;
import com.malog.member.domain.User;
import com.malog.member.domain.UserRepository;
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
    public User findByUniqueKey(String uKey) {
        return jpaUserRepository.findByUniqueKey(uKey);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND));
    }
}
