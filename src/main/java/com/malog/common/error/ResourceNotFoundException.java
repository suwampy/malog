package com.malog.common.error;

public final class ResourceNotFoundException extends SystemException {

    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
