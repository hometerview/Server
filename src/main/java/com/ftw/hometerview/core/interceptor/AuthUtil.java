package com.ftw.hometerview.core.interceptor;

public class AuthUtil {

    private AuthUtil() {
    }

    public static String getCurrentMemberId() {
        return AuthorizationContextHolder.getContext().getMemberId();
    }

}
