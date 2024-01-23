package com.malog.member.infra.exception;

import com.malog.common.error.ErrorCode;
import com.malog.common.error.SystemException;

public final class PasswordNotMatchedException extends SystemException {
    public PasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }
}
