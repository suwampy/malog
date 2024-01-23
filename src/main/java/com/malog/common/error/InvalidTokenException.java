package com.malog.common.error;

public final class InvalidTokenException extends SystemException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
