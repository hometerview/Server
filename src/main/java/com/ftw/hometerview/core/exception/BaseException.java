package com.ftw.hometerview.core.exception;

import com.ftw.hometerview.core.domain.ResponseType;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ResponseType responseType;

    public BaseException(ResponseType responseType) {
        this.responseType = responseType;
    }
}
