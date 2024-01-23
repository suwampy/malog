package com.malog.common.error;

public  enum ErrorCode {

    // System error
    INVALID_INPUT(400, "유효하지 않은 토큰입니다."),
    UN_AUTHENTICATED(401, "인증에 실패하였습니다."),
    UN_DEFINED_ERROR(500, "정의되지 않은 에러가 발생하였습니다."),
    METHOD_NOT_ALLOWED(405, "Http method is not allowed."),
    UNSUPPORTED_MEDIA_TYPE(415, "지원되지 않는 타입입니다."),
    UN_HANDLED(400, "시스템 에러가 발생하였습니다."),
    ACCESS_DENIED(403, "권한이 없습니다."),

    // Account module error
    ACCOUNT_NOT_FOUND(404, "계정을 찾을 수 없습니다."),
    ACCOUNT_INVALID_EMAIL_TOKEN(400, "이메일 토큰이 유효하지 않습니다."),
    DUPLICATE_EMAIL(400, "이메일이 중복되었습니다."),
    PASSWORD_NOT_MATCHED(400, "비밀번호가 일치하지 않습니다."),
    INVALID_ACCOUNT(400, "잘못된 계정입니다."),

    // Jwt, mail token error
    INVALID_TOKEN(400, "잘못된 토큰입니다."),
    NOT_FOUND_REFRESH_TOKEN(404, "리프레쉬 토큰을 찾을 수 없습니다."),
    TOKEN_EXPIRED(400, "토큰이 만료되었습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
