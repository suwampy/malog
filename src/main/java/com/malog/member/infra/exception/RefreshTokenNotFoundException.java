package com.malog.member.infra.exception;

import com.malog.common.error.ErrorCode;
import com.malog.common.error.SystemException;

public final class RefreshTokenNotFoundException extends SystemException {
    public RefreshTokenNotFoundException() {
        super(ErrorCode.NOT_FOUND_REFRESH_TOKEN);
    }
}
