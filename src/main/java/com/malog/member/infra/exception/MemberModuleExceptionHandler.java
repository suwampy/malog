package com.malog.member.infra.exception;

import com.malog.common.error.ErrorCode;
import com.malog.common.error.InvalidArgumentException;
import com.malog.common.error.InvalidTokenException;
import com.malog.common.error.SystemException;
import com.malog.member.infra.exception.AlreadyTokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice(basePackages = "com.malog.member")
@RestControllerAdvice
@Slf4j
public final class MemberModuleExceptionHandler {

    @ExceptionHandler(value = {
        InvalidTokenException.class,
        InvalidArgumentException.class,
        InvalidAccountException.class,
        DuplicatedEmailException.class,
        AlreadyTokenExpiredException.class,
        PasswordNotMatchedException.class,
        RefreshTokenNotFoundException.class
    })
    public ResponseEntity<?> onError(SystemException e) {
        log.error("SystemError: ", e);
        return ResponseEntity
            .status(toStatus(e.getErrorCode()))
            .body(e.getMessage());
    }

    private HttpStatus toStatus(ErrorCode errorCode) {
        return HttpStatus.valueOf(errorCode.getStatus());
    }
}
