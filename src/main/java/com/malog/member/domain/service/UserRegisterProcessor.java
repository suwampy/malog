package com.malog.member.domain.service;

import com.malog.common.error.InvalidTokenException;
import com.malog.common.security.PasswordEncrypter;
import com.malog.member.domain.User;
import com.malog.member.domain.UserRepository;
import com.malog.member.infra.exception.DuplicatedEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRegisterProcessor {
    private final UserRepository userRepository;
    private final PasswordEncrypter passwordEncoder;

    public User register(String email, String password, String username) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicatedEmailException();
        }

        var account = User.register(email, passwordEncoder.encode(password), username);
        userRepository.save(account);

        return account;
    }

    public User registerConfirm(String token, String email) {
        var account = userRepository.findByEmail(email);
        if (!account.isValidToken(token)) {
            throw new InvalidTokenException();
        }

        account.completeRegister();
        userRepository.save(account);
        return account;
    }
}
