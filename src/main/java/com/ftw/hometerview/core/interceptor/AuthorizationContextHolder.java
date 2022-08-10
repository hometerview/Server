package com.ftw.hometerview.core.interceptor;

import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.BadRequestException;
import org.springframework.util.Assert;

public class AuthorizationContextHolder {

    public static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public void clearContext() {
        contextHolder.remove();
    }

    public String getContext() {
        String ctx = contextHolder.get();
        if (ctx == null) {
            throw new BadRequestException(ResponseType.REQUEST_UNAUTHORIZED);
        }
        return ctx;
    }

    public void setContext(String context) {
        Assert.notNull(context, "Only non-null Context instances are permitted");
        contextHolder.set(context);
    }

}