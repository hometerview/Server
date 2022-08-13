package com.ftw.hometerview.core.interceptor;

public class AuthUtil {

    private AuthUtil() {
    }

    public static Long getCurrentMemberId() {
        return Long.valueOf(AuthorizationContextHolder.getContext().getMemberId());
    }

}
