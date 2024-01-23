package com.malog.member.infra.exception;

import com.malog.common.error.ErrorCode;
import com.malog.common.error.SystemException;

public final class InvalidAccountException extends SystemException {

    public InvalidAccountException() {
        super(ErrorCode.INVALID_ACCOUNT);
    }
}
