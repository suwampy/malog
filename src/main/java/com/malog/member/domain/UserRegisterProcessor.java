package com.malog.member.domain;

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

    public User registerConfirm(String token, String email) throws Exception {
        var account = userRepository.findByEmail(email);
        if (!account.isValidToken(token)) {
            throw new Exception("이메일 토큰정보가 올바르지 않습니다");
        }

        account.completeRegister();
        userRepository.save(account);
        return account;
    }
}
