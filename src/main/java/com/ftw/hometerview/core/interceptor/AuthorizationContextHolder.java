package com.ftw.hometerview.core.interceptor;

import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.BadRequestException;
import org.springframework.util.Assert;

public class AuthorizationContextHolder {

    public static ThreadLocal<AuthContent> contextHolder = new ThreadLocal<>();

    public static void clearContext() {
        contextHolder.remove();
    }

    public static AuthContent getContext() {
        AuthContent ctx = contextHolder.get();
        if (ctx == null) {
            throw new BadRequestException(ResponseType.REQUEST_UNAUTHORIZED);
        }
        return ctx;
    }

    public static void setContext(AuthContent context) {
        Assert.notNull(context, "Only non-null Context instances are permitted");
        contextHolder.set(context);
    }

}
