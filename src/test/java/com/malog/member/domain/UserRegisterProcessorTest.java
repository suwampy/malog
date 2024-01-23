package com.malog.member.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.malog.member.domain.service.UserRegisterProcessor;
import com.malog.member.infra.exception.DuplicatedEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("회원가입 processor 테스트")
class UserRegisterProcessorTest {
    @Test
    @DisplayName("회원가입 완료 된 후 이벤트 등록")
    void 회원가입_완료_이벤트_등록() {
        // Arrange
        var userRepository = new TestUserRepository();
        var sut = new UserRegisterProcessor(userRepository, new TestPasswordEncrypter());

        // Act
        var user = sut.register("hong123@gmail.com", "asdf1234!", "홍길동");

        // Assert
        assertThat(user.isEmailVerified()).isFalse();
        assertThat(user.pollAllEvents().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("회원 등록이 완료 된 후 이벤트 등록")
    void 회원_등록_완료_이벤트_등록() {
        // Arrange
        var userRepository = new TestUserRepository();
        var user = User.register("hong123@gmail.com", "asdf1234!", "홍길동");
        var sut = new UserRegisterProcessor(userRepository, new TestPasswordEncrypter());
        userRepository.save(user);
        user.pollAllEvents();

        // Act
        var confirmUser = sut.registerConfirm(user.getEmailCheckToken(), user.getEmail());

        // Assert
        assertThat(confirmUser.isEmailVerified()).isTrue();
        assertThat(confirmUser.pollAllEvents().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("회원가입 시 이메일이 중복된 경우 예외 발생")
    void 회원가입_이메일_중복_예외() {
        // Arrange
        var userRepository = new TestUserRepository();
        userRepository.save(User.register("hong123@gmail.com", "asdf1234!", "홍길동"));
        var sut = new UserRegisterProcessor(userRepository, new TestPasswordEncrypter());

        // Act

        // Assert
        assertThatExceptionOfType(DuplicatedEmailException.class)
            .isThrownBy(() -> sut.register("hong123@gmail.com", "asdf1234!", "홍길동"));
    }
}
