package com.malog.common.security;


public interface PasswordEncrypter {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
