package com.malog.member.domain.service;

import com.malog.member.domain.User;
import com.malog.member.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public final class UserLoginProcessor {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User login(String email, String password) {
        var user = userRepository.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException();
        }

        if (!user.isEmailVerified()) {
            throw new IllegalArgumentException();
        }

        return user;
    }

}
