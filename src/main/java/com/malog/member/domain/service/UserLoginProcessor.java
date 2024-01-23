package com.malog.member.domain.service;

import com.malog.common.security.PasswordEncrypter;
import com.malog.member.domain.User;
import com.malog.member.domain.UserRepository;
import com.malog.member.infra.exception.InvalidAccountException;
import com.malog.member.infra.exception.PasswordNotMatchedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public final class UserLoginProcessor {
    private final UserRepository userRepository;
    private final PasswordEncrypter passwordEncoder;

    public User login(String email, String password) {
        var user = userRepository.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotMatchedException();
        }

        if (!user.isEmailVerified()) {
            throw new InvalidAccountException();
        }

        return user;
    }

}
