package com.ftw.hometerview.core.exception;

import com.ftw.hometerview.core.domain.ResponseType;

public class NotFoundException extends BaseException {

    public NotFoundException(ResponseType responseType) {
        super(responseType);
    }

}