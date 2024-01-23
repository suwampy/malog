package com.malog.member.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.malog.member.domain.service.UserLoginProcessor;
import com.malog.member.infra.exception.PasswordNotMatchedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로그인 processor 테스트")
class UserLoginProcessorTest {
    String email = "hong123@email.com";
    String password = "asdf12345!@#$";
    String username = "홍길동";

    @Test
    @DisplayName("패스워드가 일치하지 않는 경우 예외 발생")
    void 패스워드_불일치() {
        // Arrange
        String invalidPassword = "asdf12345!@#$%^&";
        var userRepository = new TestUserRepository();
        userRepository.save(User.register(email, password, username));
        var sut = new UserLoginProcessor(userRepository, new TestPasswordEncrypter());

        // Act & Assert
        assertThatExceptionOfType(PasswordNotMatchedException.class)
            .isThrownBy(() -> sut.login(email, invalidPassword));
    }

}
