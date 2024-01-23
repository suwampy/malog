package com.malog.member.infra.exception;

import com.malog.common.error.ErrorCode;
import com.malog.common.error.SystemException;

public final class DuplicatedEmailException extends SystemException {
    public DuplicatedEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }
}
