package com.malog.member.domain;

import com.malog.member.infra.jpa.UserRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public final class UserLoginProcessor {
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final PasswordEncoder passwordEncoder;

    public User login(String email, String password) {
        var user = userRepositoryAdapter.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException();
        }

        if (!user.isEmailVerified()) {
            throw new IllegalArgumentException();
        }

        return user;
    }

}
