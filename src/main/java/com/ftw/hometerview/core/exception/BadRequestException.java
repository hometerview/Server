package com.ftw.hometerview.core.exception;

import com.ftw.hometerview.core.domain.ResponseType;

public class BadRequestException extends BaseException {

    public BadRequestException(ResponseType responseType) {
        super(responseType);
    }
}