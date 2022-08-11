package com.ftw.hometerview.core.exception;

import com.ftw.hometerview.core.domain.ResponseType;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(ResponseType responseType) {
        super(responseType);
    }
}
