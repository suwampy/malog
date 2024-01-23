package com.malog.member.infra.exception;

import com.malog.common.error.ErrorCode;
import com.malog.common.error.SystemException;

public final class AlreadyTokenExpiredException extends SystemException {
    public AlreadyTokenExpiredException() {
        super(ErrorCode.TOKEN_EXPIRED);
    }
}
