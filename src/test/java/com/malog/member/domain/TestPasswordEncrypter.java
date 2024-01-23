package com.malog.member.domain;

import com.malog.common.security.PasswordEncrypter;

public class TestPasswordEncrypter implements PasswordEncrypter {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}

