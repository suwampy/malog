package com.malog.member.domain;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRegisterProcessor {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String email, String password, String username) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("중복된 이메일이 존재합니다.");
        }

        var account = User.register(email, passwordEncoder.encode(password), username);
        userRepository.save(account);

        return account;
    }
}
